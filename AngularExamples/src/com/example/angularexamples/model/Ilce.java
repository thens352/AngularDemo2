package com.example.angularexamples.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Ilce implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7397342685766481538L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(unique = true)
	private String ad;

	@ManyToOne
	private Il il;

	@OneToMany(mappedBy = "ilce", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Mahalle> mahalleList = new ArrayList<Mahalle>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
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
		Ilce other = (Ilce) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Il getIl() {
		return il;
	}

	public void setIl(Il il) {
		this.il = il;
	}

	@JsonIgnore
	public List<Mahalle> getMahalleList() {
		return mahalleList;
	}

	public void setMahalleList(List<Mahalle> mahalleList) {
		this.mahalleList = mahalleList;
	}
}
