package com.kayiyazilim.ekentsayim.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.kayiyazilim.ekentsayim.model.type.Ekran;

@Entity
public class Bilgi implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8907071964566017331L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private Ekran ekran;
	
	@Transient
	private String ekranAdi;
	
	private String aciklama;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ekran getEkran() {
		return ekran;
	}

	public void setEkran(Ekran ekran) {
		this.ekran = ekran;
	}

	public String getEkranAdi() {
		return ekranAdi;
	}

	public void setEkranAdi(String ekranAdi) {
		this.ekranAdi = ekranAdi;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	
}
