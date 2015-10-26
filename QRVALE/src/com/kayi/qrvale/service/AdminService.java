package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.kayi.qrvale.dao.AdminDao;
import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.model.entity.Admin;
import com.kayi.qrvale.model.entity.Isletme;

@ManagedBean(name = "adminService")
@ViewScoped
public class AdminService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -459119593083620953L;

	@EJB
	private AdminDao adminDao;

	@EJB
	private IsletmeDao isletmeDao;

	private Isletme isletme = new Isletme();

	private List<Isletme> isletmeList = new ArrayList<Isletme>();

	private Admin admin = new Admin();

	public void adminKaydet() {
		if (admin.getId() == null) {
			adminDao.adminKaydet(admin);
		} else {
			admin = adminDao.adminGuncelle(admin);
		}
	}

	@PostConstruct
	public void init() {
		isletmeList = isletmeDao.hepsiniGetir();

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String adminId = params.get("adminId");
		if (adminId != null && !"".equals(adminId)) {
			admin = adminDao.adminGetirById(Integer.valueOf(adminId));
			isletme = admin.getIsletme();
		}
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}

	public List<Isletme> getIsletmeList() {
		return isletmeList;
	}

	public void setIsletmeList(List<Isletme> isletmeList) {
		this.isletmeList = isletmeList;
	}
}
