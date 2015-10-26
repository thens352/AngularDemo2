package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.kayi.qrvale.dao.AracDao;
import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.dao.search.AracSearch;
import com.kayi.qrvale.model.entity.Admin;
import com.kayi.qrvale.model.entity.Arac;
import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.type.Marka;
import com.kayi.qrvale.model.type.Renk;

@ManagedBean(name = "aracListS")
@ViewScoped
public class AracListService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2134879069889088682L;

	@EJB
	private AracDao aracDao;

	@EJB
	private IsletmeDao isletmeDao;

	private AracSearch aracSearch = new AracSearch();

	private Admin admin = new Admin();

	private Isletme isletme;

	private List<Arac> aracList = new ArrayList<Arac>();

	public Marka[] getMarkalar() {
		return Marka.values();
	}

	public Renk[] getRenkler() {
		return Renk.values();
	}

	@PostConstruct
	public void init() {
		try {
			isletme = isletmeDao.IsletmeGetirById((Integer) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("isletmeID"));
		} catch (Exception e) {
			LoginService.sessionDoldu();
		}
	}

	public void aracAra() {
		aracSearch.setIsletme(isletme);
		aracList = aracDao.aracGetir(aracSearch);
	}

	public AracSearch getAracSearch() {
		return aracSearch;
	}

	public void setAracSearch(AracSearch aracSearch) {
		this.aracSearch = aracSearch;
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

	public AracDao getAracDao() {
		return aracDao;
	}

	public void setAracDao(AracDao aracDao) {
		this.aracDao = aracDao;
	}

	public List<Arac> getAracList() {
		return aracList;
	}

	public void setAracList(List<Arac> aracList) {
		this.aracList = aracList;
	}
}
