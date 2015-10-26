package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ens.degerleme.dao.KisiDao;
import com.ens.degerleme.dao.searchobject.KisiSearch;
import com.ens.degerleme.model.entity.Kisi;

@ManagedBean(name="kisiListesi")
@ViewScoped
public class KisiListesi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1325951646598655454L;
	
	@EJB
	
	private KisiDao kisiDao;
	
	private KisiSearch kisiSearch=new KisiSearch();
	
	private List<Kisi> kisiList=new ArrayList<Kisi>();
	
	public void listele(){
		setKisiList(kisiDao.kisiGetir(kisiSearch));
	}

	public KisiSearch getKisiSearch() {
		return kisiSearch;
	}

	public void setKisiSearch(KisiSearch kisiSearch) {
		this.kisiSearch = kisiSearch;
	}

	public List<Kisi> getKisiList() {
		return kisiList;
	}

	public void setKisiList(List<Kisi> kisiList) {
		this.kisiList = kisiList;
	}
}
