package com.ens.degerleme.dao.searchobject;

import com.ens.degerleme.model.entity.Il;
import com.ens.degerleme.model.entity.Ilce;
import com.ens.degerleme.model.entity.Mahalle;
import com.ens.degerleme.model.entity.Sokak;

public class BinaSearch {

	private Integer id;
	private String adres;
	private Il il;
	private Ilce ilce;
	private Mahalle mahalle;
	private Sokak sokak;
	private String daireNo;
	private String kapiNo;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public Il getIl() {
		return il;
	}

	public void setIl(Il il) {
		this.il = il;
	}

	public Ilce getIlce() {
		return ilce;
	}

	public void setIlce(Ilce ilce) {
		this.ilce = ilce;
	}

	public Mahalle getMahalle() {
		return mahalle;
	}

	public void setMahalle(Mahalle mahalle) {
		this.mahalle = mahalle;
	}

	public Sokak getSokak() {
		return sokak;
	}

	public void setSokak(Sokak sokak) {
		this.sokak = sokak;
	}

	public String getDaireNo() {
		return daireNo;
	}

	public void setDaireNo(String daireNo) {
		this.daireNo = daireNo;
	}

	public String getKapiNo() {
		return kapiNo;
	}

	public void setKapiNo(String kapiNo) {
		this.kapiNo = kapiNo;
	}

}
