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
import com.ens.degerleme.dao.SecenekDao;
import com.ens.degerleme.dao.SoruDao;
import com.ens.degerleme.dao.searchobject.SecenekSearch;
import com.ens.degerleme.model.entity.Bolum;
import com.ens.degerleme.model.entity.Form;
import com.ens.degerleme.model.entity.Secenek;
import com.ens.degerleme.model.entity.Soru;

@ManagedBean(name = "secenekListesi")
@ViewScoped
public class SecenekListesi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8848341462017321731L;

	@EJB
	private FormDao formDao;

	@EJB
	private BolumDao bolumDao;

	@EJB
	private SoruDao soruDao;

	@EJB
	private SecenekDao secenekDao;

	private List<Form> formList = new ArrayList<Form>();

	private Form form = new Form();

	private List<Bolum> bolumList = new ArrayList<Bolum>();

	private Bolum bolum = new Bolum();

	private List<Soru> soruList = new ArrayList<Soru>();

	private List<Secenek> secenekList = new ArrayList<Secenek>();

	private SecenekSearch secenekSearch = new SecenekSearch();
	
	@PostConstruct
	public void init() {
		formList = formDao.hepsiniGetir();
	}
	
	public void secenekSil(Secenek secenek_s) {
		secenekDao.secenekSil(secenek_s.getId());
	}

	public void formSecildi() {
		bolumList = bolumDao.bolumGetirByFormId(form.getId());
	}

	public void bolumSecildi() {
		soruList = soruDao.soruGetirByBolumId(bolum.getId());
	}

	public void listele() {
		secenekList = secenekDao.secenekGetir(secenekSearch);
	}

	public List<Form> getFormList() {
		return formList;
	}

	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public List<Bolum> getBolumList() {
		return bolumList;
	}

	public void setBolumList(List<Bolum> bolumList) {
		this.bolumList = bolumList;
	}

	public Bolum getBolum() {
		return bolum;
	}

	public void setBolum(Bolum bolum) {
		this.bolum = bolum;
	}

	public List<Soru> getSoruList() {
		return soruList;
	}

	public void setSoruList(List<Soru> soruList) {
		this.soruList = soruList;
	}

	public List<Secenek> getSecenekList() {
		return secenekList;
	}

	public void setSecenekList(List<Secenek> secenekList) {
		this.secenekList = secenekList;
	}

	public SecenekSearch getSecenekSearch() {
		return secenekSearch;
	}

	public void setSecenekSearch(SecenekSearch secenekSearch) {
		this.secenekSearch = secenekSearch;
	}

}
