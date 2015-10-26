package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ens.degerleme.dao.BolumDao;
import com.ens.degerleme.dao.FormDao;
import com.ens.degerleme.model.entity.Bolum;
import com.ens.degerleme.model.entity.Form;

@ManagedBean(name = "bolumGirisi")
@ViewScoped
public class BolumGiris implements Serializable {

	private static final long serialVersionUID = -5051032553739368816L;

	@EJB
	private FormDao formDao;

	@EJB
	private BolumDao bolumDao;

	private Form form = new Form();

	private List<Form> formList = new ArrayList<Form>();

	private Bolum bolum = new Bolum();

	@PostConstruct
	public void init() {
		formList = formDao.hepsiniGetir();
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String bolumId = params.get("bolumId");
		if (bolumId != null && !!"".equals(bolumId)) {
			bolum = bolumDao.bolumGetir(Integer.valueOf(bolumId));
			form=bolum.getForm();
		}
	}

	public void kaydet() {
		bolum.setForm(form);
		if (bolum.getId() == null) {
			bolumDao.bolumKaydet(bolum);
		} else {
			bolum = bolumDao.bolumGuncelle(bolum);
		}
	}

	public Bolum getBolum() {
		return bolum;
	}

	public void setBolum(Bolum bolum) {
		this.bolum = bolum;
	}

	public BolumDao getBolumDao() {
		return bolumDao;
	}

	public void setBolumDao(BolumDao bolumDao) {
		this.bolumDao = bolumDao;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public List<Form> getFormList() {
		return formList;
	}

	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}

}