package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ens.degerleme.dao.UserDao;
import com.ens.degerleme.model.entity.User;

@ManagedBean(name = "userGirisi")
@ViewScoped
public class UserGiris implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2506281805797907555L;

	@EJB
	private UserDao userDao;

	private User user = new User();

	@PostConstruct
	public void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String userId = params.get("userId");
		if (userId != null && !!"".equals(userId)) {
			user = userDao.userGetir(Integer.valueOf(userId));
		}
	}

	public void kaydet() {
		if (user.getId() == null) {
			userDao.userKaydet(user);
		} else {
			user = userDao.userGuncelle(user);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
