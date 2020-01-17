package br.com.salao.dao;

import br.com.salao.entity.User;

public interface UserDao {

	User findByUserName(String userName);
}
