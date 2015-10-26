package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginAuthenticator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8323380670613448500L;

	public String logout() {

		FacesContext context = FacesContext.getCurrentInstance();
		// HttpServletRequest request = (HttpServletRequest)
		// context.getExternalContext().getRequest();
		context.getExternalContext().invalidateSession();
		// request.logout();
		return "/anasayfa?faces-redirect=true";
	}
}
