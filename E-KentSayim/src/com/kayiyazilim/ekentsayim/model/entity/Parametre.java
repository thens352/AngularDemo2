package com.kayiyazilim.ekentsayim.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@Entity
public class Parametre implements Serializable {

	private static final long serialVersionUID = 5417107518846541365L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Enumerated(EnumType.STRING)
	private Ekran ekran;

	@Enumerated(EnumType.STRING)
	private Alan alan;

	private String deger;
	
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

	public Alan getAlan() {
		return alan;
	}

	public void setAlan(Alan alan) {
		this.alan = alan;
	}

	public String getDeger() {
		return deger;
	}

	public void setDeger(String deger) {
		this.deger = deger;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametre other = (Parametre) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
