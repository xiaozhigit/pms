package com.xxx.pms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.SysUser;
import com.xxx.pms.mapper.UserMapper;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.UserService;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AutoSqlController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "autoSql1")
    public Response autoSql1(Integer pageNo, Integer pageSize) {
        Page<SysUser> users = (Page<SysUser>) userService.findUserListByPage(pageNo,pageSize);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<SysUser> pageInfo = new PageInfo<>(users);
        return ResponseUtils.successData(pageInfo);
    }

    @RequestMapping(value = "autoSql2")

    public Response autoSql2() {
        return ResponseUtils.successData(userMapper.getAllUserBySelf());
    }

    @RequestMapping(value = "autoSql3")
    public Response autoSql3() {
        return ResponseUtils.successData(userMapper.getAllUserBySelfSql());
    }

    @RequestMapping(value = "autoSql4")
    public Response autoSql4() {
        return ResponseUtils.successData(userMapper.selectByPrimaryKey(1));
    }


}
