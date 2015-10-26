package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.search.BolgeSearch;
import com.kayiyazilim.ekentsayim.model.type.Statu;

@ManagedBean(name = "bolgeService")
@ViewScoped
public class BolgeService implements Serializable {

	private static final long serialVersionUID = -2997544208093666719L;

	@EJB
	private BolgeDao bolgeDao;

	private Bolge bolge = new Bolge();

	private List<Bolge> bolgeList;

	private BolgeSearch bolgeSearch = new BolgeSearch();
	
	public Statu[] getStatus(){
		return Statu.values();
	}

	@PostConstruct
	public void init() {
		bolgeList = bolgeDao.findAll();
	}

	public void ara() {
		bolgeList = bolgeDao.search(bolgeSearch);
	}

	public void ekle() {
		bolgeDao.persist(bolge);
		if (bolgeList == null)
			bolgeList = new ArrayList<Bolge>();
		bolgeList.add(bolge);
		bolge = new Bolge();
	}

	public void guncelle(RowEditEvent event) {
		bolge = ((Bolge) event.getObject());
		bolgeDao.merge(bolge);
		bolge = new Bolge();
	}

	public void sil() {
		if (bolge != null)
			bolgeDao.remove(bolge);
		bolgeList.remove(bolge);
		bolge = new Bolge();
	}

	public BolgeSearch getBolgeSearch() {
		return bolgeSearch;
	}

	public void setBolgeSearch(BolgeSearch bolgeSearch) {
		this.bolgeSearch = bolgeSearch;
	}

	public Bolge getBolge() {
		return bolge;
	}

	public void setBolge(Bolge bolge) {
		this.bolge = bolge;
	}

	public List<Bolge> getBolgeList() {
		return bolgeList;
	}

	public void setBolgeList(List<Bolge> bolgeList) {
		this.bolgeList = bolgeList;
	}

}
