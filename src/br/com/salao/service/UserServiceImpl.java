package br.com.salao.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.salao.dao.UserDao;
import br.com.salao.entity.Role;
import br.com.salao.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
		return new org.springframework.security.core.userdetails.User(
			user.getUsername(), 
			user.getPassword(), 
			user.isEnabled(), 
			true, 
			true, 
			true, 
			mapRolesToAuthorities(user.getRoles())
		);
	}
	
	/**
	 * 
	 * @param roles
	 * @return
	 */
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map( role -> new SimpleGrantedAuthority(role.getName()) ).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		return null;
	}

}
