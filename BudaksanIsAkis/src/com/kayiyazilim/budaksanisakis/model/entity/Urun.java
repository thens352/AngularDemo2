package com.kayiyazilim.budaksanisakis.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Urun implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1431563689493639436L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne
	private Malzeme malzeme;
	
	private int miktar;
	
	private double birimTutar;
	
	private double toplamTutar;
	
	@ManyToOne
	private Fatura fatura;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Malzeme getMalzeme() {
		return malzeme;
	}

	public void setMalzeme(Malzeme malzeme) {
		this.malzeme = malzeme;
	}

	public int getMiktar() {
		return miktar;
	}

	public void setMiktar(int miktar) {
		this.miktar = miktar;
	}

	public double getBirimTutar() {
		return birimTutar;
	}

	public void setBirimTutar(double birimTutar) {
		this.birimTutar = birimTutar;
	}

	public double getToplamTutar() {
		return toplamTutar;
	}

	public void setToplamTutar(double toplamTutar) {
		this.toplamTutar = toplamTutar;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
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
		Urun other = (Urun) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
