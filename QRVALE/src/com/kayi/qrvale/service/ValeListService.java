package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.dao.ValeDao;
import com.kayi.qrvale.dao.search.ValeSearch;
import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.entity.Vale;

@ManagedBean(name = "valeListS")
@ViewScoped
public class ValeListService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6840968968888737985L;

	@EJB
	private ValeDao valeDao;

	@EJB
	private IsletmeDao isletmeDao;

	private Vale vale = new Vale();

	private List<Vale> valeList = new ArrayList<Vale>();

	private Isletme isletme;

	private ValeSearch valeSearch = new ValeSearch();

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

	public void valeAra() {
		valeSearch.setIsletme(isletme);
		valeList = valeDao.valeGetir(valeSearch);
	}

	public Vale getVale() {
		return vale;
	}

	public void setVale(Vale vale) {
		this.vale = vale;
	}

	public List<Vale> getValeList() {
		return valeList;
	}

	public void setValeList(List<Vale> valeList) {
		this.valeList = valeList;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}

	public ValeSearch getValeSearch() {
		return valeSearch;
	}

	public void setValeSearch(ValeSearch valeSearch) {
		this.valeSearch = valeSearch;
	}
}
