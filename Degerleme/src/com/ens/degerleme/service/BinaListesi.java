package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ens.degerleme.dao.BinaDao;
import com.ens.degerleme.dao.IlDao;
import com.ens.degerleme.dao.IlceDao;
import com.ens.degerleme.dao.MahalleDao;
import com.ens.degerleme.dao.SokakDao;
import com.ens.degerleme.dao.searchobject.BinaSearch;
import com.ens.degerleme.model.entity.Bina;
import com.ens.degerleme.model.entity.Il;
import com.ens.degerleme.model.entity.Ilce;
import com.ens.degerleme.model.entity.Mahalle;
import com.ens.degerleme.model.entity.Sokak;

@ManagedBean(name = "binaListesi")
@ViewScoped
public class BinaListesi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9129263815284598875L;

	@EJB
	private BinaDao binaDao;

	@EJB
	private IlDao ilDao;

	@EJB
	private IlceDao ilceDao;

	@EJB
	private MahalleDao mahalleDao;

	@EJB
	private SokakDao sokakDao;

	private Bina bina = new Bina();

	private BinaSearch binaSearch = new BinaSearch();

	private List<Bina> binaList = new ArrayList<Bina>();

	private Il secilenIl = new Il();

	private Ilce secilenIlce = new Ilce();

	private Mahalle secilenMahalle = new Mahalle();

	private List<Il> ilList = new ArrayList<Il>();

	private List<Ilce> ilceList = new ArrayList<Ilce>();

	private List<Mahalle> mahalleList = new ArrayList<Mahalle>();

	private List<Sokak> sokakList = new ArrayList<Sokak>();

	@PostConstruct
	public void init(){
		ilList = ilDao.hepsiniGetir();
		
	}
	
	public void binaAra() {
		binaSearch.setIl(secilenIl);
		binaSearch.setIlce(secilenIlce);
		binaSearch.setMahalle(secilenMahalle);
		binaSearch.setSokak(bina.getSokak());
		binaList = binaDao.binaGetir(binaSearch);
	}

	
	public void ilSecildi() {
		if (secilenIl != null) {
			ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
		} else {
			bina.setSokak(null);
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
			bina.setSokak(null);
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
			bina.setSokak(null);
		}
	}

	public BinaDao getBinaDao() {
		return binaDao;
	}

	public void setBinaDao(BinaDao binaDao) {
		this.binaDao = binaDao;
	}

	public BinaSearch getBinaSearch() {
		return binaSearch;
	}

	public void setBinaSearch(BinaSearch binaSearch) {
		this.binaSearch = binaSearch;
	}

	public void listele() {

		binaList = binaDao.binaGetir(binaSearch);
	}

	public List<Bina> getBinaList() {
		return binaList;
	}

	public void setBinaList(List<Bina> binaList) {
		this.binaList = binaList;
	}

	public Bina getBina() {
		return bina;
	}

	public void setBina(Bina bina) {
		this.bina = bina;
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
}
