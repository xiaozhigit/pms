package com.xxx.pms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pms.constant.YmlConstant;
import com.xxx.pms.entity.User;
import com.xxx.pms.mapper.UserMapper;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.UserService;
import com.xxx.pms.util.CommonUtils;
import com.xxx.pms.util.JwtUtils;
import com.xxx.pms.util.PinYinUtils;
import com.xxx.pms.util.ResponseUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    UserMapper userMapper;


    /**
     * 增加用户
     * @param user
     * @param request
     * @return
     */
    @Override
    public Response addUser(User user, HttpServletRequest request) {
        //验证用户名(同手机号)是否存在
        User checkUser = new User();
        checkUser.setUsername(user.getPhone());
        List<User> checkUserList = userMapper.select(checkUser);
        if(checkUserList.size() > 0){
            return ResponseUtils.errorMessage(new String[]{"200","用户名(手机号)已存在，请重新输入！"},null);
        }
        String name = user.getName();
        String pinYinHeadChar = PinYinUtils.getPinYinHeadChar(name);
        user.setInitials(pinYinHeadChar);
        String encodePassword = "";
        String password = user.getPassword();
        //如果没有设置密码就用默认密码
        if(StringUtils.isEmpty(password)){
            encodePassword = passwordEncoder.encode(YmlConstant.PASSWORD);
        }else {
            encodePassword = passwordEncoder.encode(password);
        }
        user.setPassword(encodePassword);
        int companyId = JwtUtils.getCompanyIdByRequest(request);
        user.setCompanyId(companyId);
        //获取创建人的信息
        int creatorUserId = JwtUtils.getUserIdByRequest(request);
        User creatorUser = userMapper.selectByPrimaryKey(creatorUserId);

        user.setCreateId(creatorUserId);
        user.setCreateName(creatorUser.getName());
        user.setUsername(user.getPhone());
        userMapper.insertSelective(user);
        return ResponseUtils.success();
    }


    public Map addUser(User user) {
        Map returnMap=new HashMap<String,Object>();
        returnMap.put("code",200);
        //验证用户名(同手机号)是否存在
        if(phoneIsExist(user.getPhone())){
            returnMap.put("code",400);
            returnMap.put("msg","手机号已存在，请重新输入");
        }
        String pinYinHeadChar = PinYinUtils.getPinYinHeadChar(user.getName());
        user.setInitials(pinYinHeadChar);
        String encodePassword = "";
        String password = user.getPassword();
        //如果没有设置密码就用默认密码
        if(StringUtils.isEmpty(password)){
            encodePassword = passwordEncoder.encode(YmlConstant.PASSWORD);
        }else {
            encodePassword = passwordEncoder.encode(password);
        }
        user.setPassword(encodePassword);
        user.setUsername(user.getPhone());
        user.setStatue(true);
        user.setDelFlag(false);
        userMapper.insertSelective(user);
        returnMap.put("msg",user.getId());
        return returnMap;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    public Response updateUser(User user) {
        //验证用户名(同手机号)是否存在
        User checkUser = new User();
        checkUser.setUsername(user.getPhone());
        List<User> checkUserList = userMapper.select(checkUser);
        if(checkUserList.size() > 0){
            return ResponseUtils.errorMessage(new String[]{"200","用户名(手机号)已存在，请重新输入！"},null);
        }
        String name = user.getName();
        String pinYinHeadChar = PinYinUtils.getPinYinHeadChar(name);
        user.setInitials(pinYinHeadChar);
        user.setUsername(user.getPhone());
        userMapper.updateByPrimaryKeySelective(user);
        return ResponseUtils.success();
    }

    /**
     * 查询用户
     * @param id
     * @return
     */
    @Override
    public Response getUserById(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        return ResponseUtils.successData(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public Response deleteUserById(int id) {
        userMapper.deleteByPrimaryKey(id);
        return ResponseUtils.success();
    }

    /**
     * 分页条件查询用户列表
     * @param form
     * @param request
     * @return
     */
    @Override
    public Response getUserListByPage(RequestParamPage<User> form, HttpServletRequest request) {
        PageHelper.startPage(form.getPage(), form.getPageSize());

        User user = form.getParam();
        int companyId = JwtUtils.getCompanyIdByRequest(request);
        user.setCompanyId(companyId);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("statue",true);
        if(CommonUtils.isNotEmpty(user.getCompanyId())){
            criteria.andEqualTo("companyId",user.getCompanyId());
        }
        if(CommonUtils.isNotEmpty(user.getName())){
            criteria.andLike("name","%"+user.getName()+"%")
                    .orLike("initials","%"+user.getName()+"%");
        }
        if(CommonUtils.isNotEmpty(user.getPhone())){
            criteria.andLike("phone","%"+user.getPhone()+"%");
        }
        example.setOrderByClause("gmt_create desc");
        List<User> userList = userMapper.selectByExample(example);

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return ResponseUtils.successData(pageInfo);
    }

    @Override
    public Response updatePassword(String oldPassword, String newPassword, HttpServletRequest request) {
        int userId = JwtUtils.getUserIdByRequest(request);
        User user = userMapper.selectByPrimaryKey(userId);
        boolean flag = passwordEncoder.matches(oldPassword, user.getPassword());
        if(flag){
            String newPasswordEncoder = passwordEncoder.encode(newPassword);
            user.setPassword(newPasswordEncoder);
            userMapper.updateByPrimaryKeySelective(user);
        }else {
            return ResponseUtils.errorMessage(new String[]{"200","旧密码输入错误"},null);
        }
        return ResponseUtils.success();
    }

    @Override
    public Response updateUserStatueById(int id, Boolean statue) {
        User user = userMapper.selectByPrimaryKey(id);
        user.setStatue(statue);
        userMapper.updateByPrimaryKeySelective(user);
        return ResponseUtils.success();
    }

    @Override
    public List<User> selectUsersByCompanyIdAndRoleId(Integer companyId, Integer roleId) {
        Example example=new Example(User.class);
        example.and().andEqualTo("companyId",companyId).andEqualTo("roleId",roleId);
        return userMapper.selectByExample(example);
    }

    @Override
    public int updateUserRoleIdByCompanyIdAndRoleId(Integer companyId, Integer roleId) {
        return userMapper.updateUserRoleIdByCompanyIdAndRoleId(companyId,roleId);
    }


    public Boolean phoneIsExist(String phoneNumber){
        return userMapper.selectByPhoneNumber(phoneNumber)>0;
    };

    @Override
    public Integer getAdminRoleIdByCompanyId(Integer companyId) {
        return userMapper.getAdminRoleIdByCompanyId(companyId);
    }
}
