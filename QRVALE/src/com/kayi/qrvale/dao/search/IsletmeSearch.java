package com.kayi.qrvale.dao.search;

import com.kayi.qrvale.model.entity.Il;
import com.kayi.qrvale.model.entity.Ilce;
import com.kayi.qrvale.model.entity.Mahalle;
import com.kayi.qrvale.model.entity.Sokak;

public class IsletmeSearch {

	private String isletmeAdi;
	private Il il;
	private Ilce ilce;
	private Mahalle mahalle;
	private Sokak sokak;

	public String getIsletmeAdi() {
		return isletmeAdi;
	}

	public void setIsletmeAdi(String isletmeAdi) {
		this.isletmeAdi = isletmeAdi;
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

}
