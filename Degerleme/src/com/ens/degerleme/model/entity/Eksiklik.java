package com.ens.degerleme.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Eksiklik implements Serializable {

	private static final long serialVersionUID = -1588999722814000024L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String metin;

	private int siraNo;
	
	@ManyToOne
	private Rapor rapor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMetin() {
		return metin;
	}

	public void setMetin(String metin) {
		this.metin = metin;
	}

	public int getSiraNo() {
		return siraNo;
	}

	public void setSiraNo(int siraNo) {
		this.siraNo = siraNo;
	}

	public Rapor getRapor() {
		return rapor;
	}

	public void setRapor(Rapor rapor) {
		this.rapor = rapor;
	}

}
