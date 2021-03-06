package com.ServiceClasses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DaoClasses.usersDao;
import com.EntityClasses.UserRole;


@Service("userDetailsService")
public class SecurityService implements UserDetailsService {

	@Autowired
	private usersDao userDao1;

	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	
		com.EntityClasses.User_Info user_info = userDao1.findByUserName(username);        //Here we are getting the username
		List<GrantedAuthority> authorities = buildUserAuthority(user_info.getUserRole()); //Here we are getting the userrole  that's why we haven't close the session.
		return buildUserForAuthentication(user_info, authorities);		
	}
	
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
	
	
	
	private User buildUserForAuthentication(com.EntityClasses.User_Info user_info, List<GrantedAuthority> authorities) {
			return new User(user_info.getName(), user_info.getPassword(), user_info.isEnabled(), true, true, true, authorities);
	}

	
}



