package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.kayi.qrvale.dao.AdminDao;
import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.dao.search.AdminSearch;
import com.kayi.qrvale.model.entity.Admin;
import com.kayi.qrvale.model.entity.Isletme;

@ManagedBean(name="adminListS")
@ViewScoped
public class AdminListService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3071001048837056277L;

	@EJB
	private AdminDao adminDao;
	
	@EJB
	private IsletmeDao isletmeDao;
	
	private AdminSearch adminSearch=new AdminSearch();
	
	private Admin admin=new Admin();
	
	private List<Admin> adminList=new ArrayList<Admin>();
	
	private Isletme isletme=new Isletme();
	
	private List<Isletme> isletmeList=new ArrayList<Isletme>();
	
	@PostConstruct
	public void init(){
		isletmeList=isletmeDao.hepsiniGetir();
	}
	
	public void adminAra(){
		adminSearch.setIsletme(isletme);
		adminList=adminDao.adminGetir(adminSearch);
	}

	public AdminSearch getAdminSearch() {
		return adminSearch;
	}

	public void setAdminSearch(AdminSearch adminSearch) {
		this.adminSearch = adminSearch;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
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
