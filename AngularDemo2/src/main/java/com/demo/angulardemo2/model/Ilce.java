package com.demo.angulardemo2.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "ilce")
@XmlRootElement
public class Ilce implements Serializable {

	/**
    * 
    */
	private static final long serialVersionUID = 9083159750191807750L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String ad;

	@ManyToOne
	private Il il;

	@OneToMany(mappedBy = "ilce", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Mahalle> ilce = new HashSet<Mahalle>();

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Ilce)) {
			return false;
		}
		Ilce other = (Ilce) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (ad != null && !ad.trim().isEmpty())
			result += ", ad: " + ad;
		return result;
	}

	public Il getIl() {
		return this.il;
	}

	public void setIl(final Il il) {
		this.il = il;
	}

	@JsonIgnore
	public Set<Mahalle> getIlce() {
		return this.ilce;
	}

	public void setIlce(final Set<Mahalle> ilce) {
		this.ilce = ilce;
	}

}