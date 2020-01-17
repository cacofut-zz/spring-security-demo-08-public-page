package br.com.salao.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.salao.entity.User;

public interface UserService extends UserDetailsService{

	User findByUserName(String userName);

    //void save(CrmUser crmUser);

}
