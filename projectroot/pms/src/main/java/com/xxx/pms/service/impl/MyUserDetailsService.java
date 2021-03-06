package com.xxx.pms.service.impl;


import com.xxx.pms.entity.User;
import com.xxx.pms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * security 用来查询用户权限信息
 */
@Component
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserMapper userMapper;
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 获取用户信息
		User sysUser = new User();
		sysUser.setUsername(username);
		sysUser = userMapper.selectOne(sysUser);
		//获取用户权限
		org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(username,sysUser.getPassword() , AuthorityUtils.commaSeparatedStringToAuthorityList(""));
		System.out.println(user.getPassword());
	    return user;
    }


}
