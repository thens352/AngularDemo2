package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ens.degerleme.dao.BolumDao;
import com.ens.degerleme.dao.FormDao;
import com.ens.degerleme.dao.searchobject.BolumSearch;
import com.ens.degerleme.model.entity.Bolum;
import com.ens.degerleme.model.entity.Form;

@ManagedBean(name = "bolumListesi")
@ViewScoped
public class BolumListesi implements Serializable {

	private static final long serialVersionUID = -8092288558031692932L;
	@EJB
	private BolumDao bolumDao;

	@EJB
	private FormDao formDao;

	@PostConstruct
	public void init() {
		formList = formDao.hepsiniGetir();
	}

	private List<Form> formList = new ArrayList<Form>();

	private BolumSearch bolumSearch = new BolumSearch();

	public BolumSearch getBolumSearch() {
		return bolumSearch;
	}

	public void setBolumSearch(BolumSearch bolumSearch) {
		this.bolumSearch = bolumSearch;
	}

	private List<Bolum> bolumList = new ArrayList<Bolum>();

	public void listele() {
		bolumList = bolumDao.bolumGetir(bolumSearch);
	}

	public List<Bolum> getBolumList() {
		return bolumList;
	}

	public void setBolumList(List<Bolum> bolumList) {
		this.bolumList = bolumList;
	}

	public List<Form> getFormList() {
		return formList;
	}

	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}

}
