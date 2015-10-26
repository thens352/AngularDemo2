package com.kayi.qrvale.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sokak {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(unique = true)
	private String adi;

	@ManyToOne
	private Mahalle mahalle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public Mahalle getMahalle() {
		return mahalle;
	}

	public void setMahalle(Mahalle mahalle) {
		this.mahalle = mahalle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adi == null) ? 0 : adi.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mahalle == null) ? 0 : mahalle.hashCode());
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
		if (adi == null) {
			if (other.adi != null)
				return false;
		} else if (!adi.equals(other.adi))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mahalle == null) {
			if (other.mahalle != null)
				return false;
		} else if (!mahalle.equals(other.mahalle))
			return false;
		return true;
	}
}
