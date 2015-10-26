package com.ens.degerleme.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "uuser")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7049390015265443632L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String name;
	private String surName;

	@OneToMany(mappedBy = "atananUser")
	private List<IsEmri> atananIsEmriList = new ArrayList<IsEmri>();

	@OneToMany(mappedBy = "kontrolEdenUser")
	private List<IsEmri> kontrolEdenIsEmriList = new ArrayList<IsEmri>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public List<IsEmri> getAtananIsEmriList() {
		return atananIsEmriList;
	}

	public void setAtananIsEmriList(List<IsEmri> atananIsEmriList) {
		this.atananIsEmriList = atananIsEmriList;
	}

	public List<IsEmri> getKontrolEdenIsEmriList() {
		return kontrolEdenIsEmriList;
	}

	public void setKontrolEdenIsEmriList(List<IsEmri> kontrolEdenIsEmriList) {
		this.kontrolEdenIsEmriList = kontrolEdenIsEmriList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		return true;
	}

}