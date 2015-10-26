package com.kayi.qrvale.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.kayi.qrvale.dao.AdminDao;
import com.kayi.qrvale.model.entity.Admin;

@ManagedBean(name = "loginService")
@ViewScoped
public class LoginService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4117104508831839151L;

	@EJB
	private AdminDao adminDao = new AdminDao();

	private Admin admin;

	private String kullaniciAdi;

	private String sifre;

	private boolean yKullaniciAdi = false;

	private boolean ySifre = false;

	public String login() {
		admin = adminDao.adminGetirByKullaniciAdi(kullaniciAdi);

		if (admin != null) {
			if (admin.getSifre().equals(sifre)) {
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.getSessionMap().put("ID", admin.getId());
					FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("isletmeID", admin.getIsletme().getId());
					FacesContext.getCurrentInstance().getExternalContext()
							.getSessionMap().put("ad", admin.getAd());
					FacesContext.getCurrentInstance().getExternalContext()
							.getSessionMap().put("soyad", admin.getSoyad());
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("user/anasayfa.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "user/anasayfa?faces-redirect=true";
			} else {
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("login.jsf?error=2");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "login?faces-redirect=true";
			}
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("login.jsf?error=1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "login?faces-redirect=true";
		}
	}
	
	public static String sessionDoldu() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("../login.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "login?faces-redirect=true";
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("../login.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "login?faces-redirect=true";
	}

	@PostConstruct
	public void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String error = params.get("error");
		if (error != null && !"".equals(error)) {
			if (Integer.valueOf(error) == 1)
				setyKullaniciAdi(true);
			else
				setySifre(true);
		}
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public boolean isyKullaniciAdi() {
		return yKullaniciAdi;
	}

	public void setyKullaniciAdi(boolean yKullaniciAdi) {
		this.yKullaniciAdi = yKullaniciAdi;
	}

	public boolean isySifre() {
		return ySifre;
	}

	public void setySifre(boolean ySifre) {
		this.ySifre = ySifre;
	}
}
