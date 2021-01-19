package com.xxx.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pms.constant.AccessStateCodeConstant;
import com.xxx.pms.constant.YmlConstant;
import com.xxx.pms.entity.Company;
import com.xxx.pms.entity.Project;
import com.xxx.pms.entity.User;
import com.xxx.pms.mapper.CompanyMapper;
import com.xxx.pms.mapper.UserMapper;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.ProjectService;
import com.xxx.pms.service.UserService;
import com.xxx.pms.util.CommonUtils;
import com.xxx.pms.util.PinYinUtils;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xxx.pms.constant.AccessStateCodeConstant.PHONE_NUMBER_REPEAT;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    UserMapper userMapper;
    @Resource
    CompanyMapper companyMapper;
    @Resource
    private ProjectService projectService;



    /**
     * 增加用户
     * @param user
     * @return
     */
    @Override
    public Response insertUser(User user) {
        //验证用户名(同手机号)是否存在
        if(this.phoneIsExist(user.getPhone().trim())){
            return  ResponseUtils.fillState(PHONE_NUMBER_REPEAT);
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
        user.setUsername(user.getPhone());
        user.setStatue(true);
        userMapper.insertSelective(user);
        return ResponseUtils.success();
    }


    public Map addUser(User user) {
        Map returnMap=new HashMap<String,Object>();
        returnMap.put("code",200);
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
        checkUser.setPhone(user.getPhone());
        List<User> checkUserList = userMapper.select(checkUser);
        if(checkUserList.size() > 0){
            return ResponseUtils.errorMessage(PHONE_NUMBER_REPEAT,null);
        }
        String name = user.getName();
        String pinYinHeadChar = PinYinUtils.getPinYinHeadChar(name);
        user.setInitials(pinYinHeadChar);
        user.setUsername(user.getPhone());
        userMapper.updateByPrimaryKeySelective(user);
        return ResponseUtils.success();
    }

    @Override
    public int updateByPrimaryKeySelective(User user){
       return  userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 查询用户
     * @param id
     * @return
     */
    @Override
    public Response getUserById(int id) {
        User user = userMapper.selectUserById(id);
        user.setProjectName(projectService.getProjectNameById(user.getProjectId()));
        Company company = companyMapper.selectByPrimaryKey(user.getCompanyId());
        Map<String,Object> data = new HashMap<>();
        data.put("user",user);
        data.put("company",company);
        return ResponseUtils.successData(data);
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
     * @param companyId
     * @return
     */
    @Override
    public Response getUserListByPage(RequestParamPage<User> form, int companyId) {
        PageHelper.startPage(form.getPage(), form.getPageSize());

        User user = form.getParam();
        user.setCompanyId(companyId);
        List<User> userList = userMapper.selectUserList(user);

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return ResponseUtils.successData(pageInfo);
    }

    @Override
    public Response updatePassword(String oldPassword, String newPassword, int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        boolean flag = passwordEncoder.matches(oldPassword, user.getPassword());
        if(flag){
            String newPasswordEncoder = passwordEncoder.encode(newPassword);
            user.setPassword(newPasswordEncoder);
            userMapper.updateByPrimaryKeySelective(user);
        }else {
            return ResponseUtils.errorMessage(AccessStateCodeConstant.PASSWORD_INFO,null);
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
    public Boolean phoneIsRepeat(Integer userId, String phoneNumber) {
        return userMapper.selectPhoneIsRepeat(userId,phoneNumber)>0;
    }

    @Override
    public Integer getAdminRoleIdByCompanyId(Integer companyId) {
        return userMapper.getAdminRoleIdByCompanyId(companyId);
    }

    @Override
    public Response getUserList(int companyId) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId",companyId);
        criteria.andNotEqualTo("delFlag",1);
        criteria.andNotEqualTo("statue",0);
        List<User> userList = userMapper.selectByExample(example);
        return ResponseUtils.successData(userList);
    }
}
