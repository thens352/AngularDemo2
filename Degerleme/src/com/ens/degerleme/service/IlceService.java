package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import com.ens.degerleme.dao.IlDao;
import com.ens.degerleme.dao.IlceDao;
import com.ens.degerleme.model.entity.Il;
import com.ens.degerleme.model.entity.Ilce;


@ManagedBean(name = "ilceService")
@ViewScoped
public class IlceService implements Serializable {

	private static final long serialVersionUID = -6093393727814589664L;
	@EJB
	private IlceDao ilceDao;
	@EJB
	private IlDao ilDao;

	private Il secilenIl;
	private Ilce ilce;

	private List<Il> ilList = new ArrayList<Il>();

	private List<Ilce> ilceList = new ArrayList<Ilce>();

	public void ilceKaydet() {
		ilce.setIl(secilenIl);
		if (ilce.getId() == null) {

			ilceDao.ilceKaydet(ilce);
		} else {
			ilce = ilceDao.ilceGuncelle(ilce);
		}

		FacesMessage mesaj = new FacesMessage("Kaydetme iþlemi baþarýlý",
				"Ýl: " + ilce.getIl().getAdi() + " Ýlçe: " + ilce.getAdi()
						+ " baþarýyla kaydedildi.");
		FacesContext.getCurrentInstance().addMessage(null, mesaj);
	}

	@PostConstruct
	public void init() {
		ilList = ilDao.hepsiniGetir();

		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		ilce = (Ilce) flash.get("ilce");
		if (ilce == null) {
			ilce = new Ilce();

		} else {
			if (ilce.getIl() != null) {
				secilenIl = ilce.getIl();
			}
		}
	}

	public void ilceListele() {
		ilceList = ilceDao.hepsiniGetir();
	}

	public Il getSecilenIl() {
		return secilenIl;
	}

	public void setSecilenIl(Il secilenIl) {
		this.secilenIl = secilenIl;
	}

	public Ilce getIlce() {
		return ilce;
	}

	public void setIlce(Ilce ilce) {
		this.ilce = ilce;
	}

	public List<Il> getIlList() {
		return ilList;
	}

	public void setIlList(List<Il> ilList) {
		this.ilList = ilList;
	}

	public IlceDao getIlceDao() {
		return ilceDao;
	}

	public void setIlceDao(IlceDao ilceDao) {
		this.ilceDao = ilceDao;
	}

	public IlDao getIlDao() {
		return ilDao;
	}

	public void setIlDao(IlDao ilDao) {
		this.ilDao = ilDao;
	}

	public List<Ilce> getIlceList() {
		return ilceList;
	}

	public void setIlceList(List<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

}