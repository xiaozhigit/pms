package com.xxx.vcard.service.impl;


import com.xxx.vcard.entity.SysUser;
import com.xxx.vcard.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
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
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		sysUser = userMapper.selectOne(sysUser);
		//获取用户权限
		User user = new User(username,sysUser.getPassword() , AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin"));
		System.out.println(user.getPassword());
	    return user;
    }


}
