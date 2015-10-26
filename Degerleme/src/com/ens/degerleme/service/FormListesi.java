package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ens.degerleme.dao.FormDao;
import com.ens.degerleme.dao.searchobject.FormSearch;
import com.ens.degerleme.model.entity.Form;

@ManagedBean(name = "formListesi")
@ViewScoped
public class FormListesi implements Serializable {

	private static final long serialVersionUID = -8092288558031692932L;
	@EJB
	private FormDao formDao;

	private FormSearch formSearch = new FormSearch();

	public FormSearch getFormSearch() {
		return formSearch;
	}

	public void setFormSearch(FormSearch formSearch) {
		this.formSearch = formSearch;
	}

	private List<Form> formList = new ArrayList<Form>();

	public void listele() {
		formList = formDao.formGetir(formSearch);
	}

	public List<Form> getFormList() {
		return formList;
	}

	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}

}
