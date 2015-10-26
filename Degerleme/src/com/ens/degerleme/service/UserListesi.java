package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ens.degerleme.dao.UserDao;
import com.ens.degerleme.dao.searchobject.UserSearch;
import com.ens.degerleme.model.entity.User;

@ManagedBean(name = "userListesi")
@ViewScoped
public class UserListesi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -906489967913556783L;

	@EJB
	private UserDao userDao;

	private UserSearch userSearch = new UserSearch();

	private List<User> userList = new ArrayList<User>();

	public UserSearch getUserSearch() {
		return userSearch;
	}

	public void setUserSearch(UserSearch userSearch) {
		this.userSearch = userSearch;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public void listele() {
		userList = userDao.userGetir(userSearch);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
