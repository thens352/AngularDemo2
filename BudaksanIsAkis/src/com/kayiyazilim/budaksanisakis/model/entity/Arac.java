package com.kayiyazilim.budaksanisakis.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Arac implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5848419930373434440L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String plaka;

	@OneToMany(mappedBy = "arac", orphanRemoval = true)
	private List<YakitHareket> yakitHareketList = new ArrayList<YakitHareket>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaka() {
		return plaka;
	}

	public void setPlaka(String plaka) {
		this.plaka = plaka;
	}

	public List<YakitHareket> getYakitHareketList() {
		return yakitHareketList;
	}

	public void setYakitHareketList(List<YakitHareket> yakitHareketList) {
		this.yakitHareketList = yakitHareketList;
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
		Arac other = (Arac) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
