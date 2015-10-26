package com.kayi.qrvale.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.kayi.qrvale.dao.IlDao;
import com.kayi.qrvale.dao.IlceDao;
import com.kayi.qrvale.dao.IsletmeDao;
import com.kayi.qrvale.dao.MahalleDao;
import com.kayi.qrvale.dao.SokakDao;
import com.kayi.qrvale.dao.search.IsletmeSearch;
import com.kayi.qrvale.model.entity.Il;
import com.kayi.qrvale.model.entity.Ilce;
import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.entity.Mahalle;
import com.kayi.qrvale.model.entity.Sokak;

@ManagedBean(name = "isletmeList")
@ViewScoped
public class IsletmeListService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5431181873806563637L;

	@EJB
	private IsletmeDao isletmeDao;

	@EJB
	private IlDao ilDao;

	@EJB
	private IlceDao ilceDao;

	@EJB
	private MahalleDao mahalleDao;

	@EJB
	private SokakDao sokakDao;

	private Isletme isletme = new Isletme();

	private IsletmeSearch isletmeSearch = new IsletmeSearch();

	private List<Isletme> isletmeList = new ArrayList<Isletme>();

	private Il secilenIl = new Il();

	private Ilce secilenIlce = new Ilce();

	private Mahalle secilenMahalle = new Mahalle();

	private List<Il> ilList = new ArrayList<Il>();

	private List<Ilce> ilceList = new ArrayList<Ilce>();

	private List<Mahalle> mahalleList = new ArrayList<Mahalle>();

	private List<Sokak> sokakList = new ArrayList<Sokak>();

	@PostConstruct
	public void init() {
		ilList = ilDao.hepsiniGetir();

	}

	public void isletmeAra() {
		isletmeSearch.setIl(secilenIl);
		isletmeSearch.setIlce(secilenIlce);
		isletmeSearch.setMahalle(secilenMahalle);
		isletmeSearch.setSokak(isletme.getSokak());
		isletmeList = isletmeDao.isletmeGetir(isletmeSearch);
	}

	public void ilSecildi() {
		if (secilenIl != null) {
			ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
		} else {
			isletme.setSokak(null);
			ilceList = new ArrayList<Ilce>();
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenIlce = null;
			secilenMahalle = null;
		}
	}

	public void ilceSecildi() {
		if (secilenIlce != null) {
			mahalleList = mahalleDao.mahalleGetirByIlceId(secilenIlce.getId());
		} else {
			isletme.setSokak(null);
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenMahalle = null;
		}
	}

	public void mahalleSecildi() {
		if (secilenMahalle != null) {
			sokakList = sokakDao.sokakGetirByMahalleId(secilenMahalle.getId());
		} else {
			sokakList = new ArrayList<Sokak>();
			isletme.setSokak(null);
		}
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

	public Il getSecilenIl() {
		return secilenIl;
	}

	public void setSecilenIl(Il secilenIl) {
		this.secilenIl = secilenIl;
	}

	public Ilce getSecilenIlce() {
		return secilenIlce;
	}

	public void setSecilenIlce(Ilce secilenIlce) {
		this.secilenIlce = secilenIlce;
	}

	public Mahalle getSecilenMahalle() {
		return secilenMahalle;
	}

	public void setSecilenMahalle(Mahalle secilenMahalle) {
		this.secilenMahalle = secilenMahalle;
	}

	public List<Il> getIlList() {
		return ilList;
	}

	public void setIlList(List<Il> ilList) {
		this.ilList = ilList;
	}

	public List<Ilce> getIlceList() {
		return ilceList;
	}

	public void setIlceList(List<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

	public List<Mahalle> getMahalleList() {
		return mahalleList;
	}

	public void setMahalleList(List<Mahalle> mahalleList) {
		this.mahalleList = mahalleList;
	}

	public List<Sokak> getSokakList() {
		return sokakList;
	}

	public void setSokakList(List<Sokak> sokakList) {
		this.sokakList = sokakList;
	}

	public IsletmeSearch getIsletmeSearch() {
		return isletmeSearch;
	}

	public void setIsletmeSearch(IsletmeSearch isletmeSearch) {
		this.isletmeSearch = isletmeSearch;
	}

}
