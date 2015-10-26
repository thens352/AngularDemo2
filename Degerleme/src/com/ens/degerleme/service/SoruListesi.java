package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ens.degerleme.dao.BolumDao;
import com.ens.degerleme.dao.SoruDao;
import com.ens.degerleme.dao.searchobject.SoruSearch;
import com.ens.degerleme.model.entity.Bolum;
import com.ens.degerleme.model.entity.Soru;

@ManagedBean(name = "soruListesi")
@ViewScoped
public class SoruListesi implements Serializable {

	private static final long serialVersionUID = -8092288558031692932L;
	@EJB
	private SoruDao soruDao;

	@EJB
	private BolumDao bolumDao;

	private List<Bolum> bolumList = new ArrayList<Bolum>();

	private SoruSearch soruSearch = new SoruSearch();

	@PostConstruct
	public void init() {
		bolumList = bolumDao.hepsiniGetir();
	}

	public List<Bolum> getBolumList() {
		return bolumList;
	}

	public void setBolumList(List<Bolum> bolumList) {
		this.bolumList = bolumList;
	}

	public SoruSearch getSoruSearch() {
		return soruSearch;
	}

	public void setSoruSearch(SoruSearch soruSearch) {
		this.soruSearch = soruSearch;
	}

	private List<Soru> soruList = new ArrayList<Soru>();

	public void listele() {
		soruList = soruDao.soruGetir(soruSearch);
	}

	public List<Soru> getSoruList() {
		return soruList;
	}

	public void setSoruList(List<Soru> soruList) {
		this.soruList = soruList;
	}

}
