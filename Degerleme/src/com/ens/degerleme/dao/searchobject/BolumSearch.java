package com.ens.degerleme.dao.searchobject;

import com.ens.degerleme.model.entity.Form;

public class BolumSearch {

	private Integer id;

	private Form form;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

}
