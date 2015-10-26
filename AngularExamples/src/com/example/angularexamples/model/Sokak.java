package com.example.angularexamples.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sokak implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6038090526485782519L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(unique = true)
	private String ad;

	@ManyToOne
	private Mahalle mahalle;

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
		Sokak other = (Sokak) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Mahalle getMahalle() {
		return mahalle;
	}

	public void setMahalle(Mahalle mahalle) {
		this.mahalle = mahalle;
	}

}
