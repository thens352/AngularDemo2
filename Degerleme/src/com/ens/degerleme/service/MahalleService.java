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
import com.ens.degerleme.dao.MahalleDao;
import com.ens.degerleme.model.entity.Il;
import com.ens.degerleme.model.entity.Ilce;
import com.ens.degerleme.model.entity.Mahalle;



@ManagedBean(name = "mahalleService")
@ViewScoped
public class MahalleService implements Serializable {

	private static final long serialVersionUID = -6093393727814589661L;
	@EJB
	private MahalleDao mahalleDao;
	@EJB
	private IlceDao ilceDao;

	@EJB
	private IlDao ilDao;
	private Il secilenIl;
	private Ilce secilenIlce;
	private Mahalle mahalle;

	private List<Il> ilList = new ArrayList<Il>();
	private List<Ilce> ilceList = new ArrayList<Ilce>();

	public void mahalleKaydet() {
		mahalle.setIlce(secilenIlce);
		if (mahalle.getId() == null) {

			mahalleDao.mahalleKaydet(mahalle);
		} else {
			mahalle = mahalleDao.mahalleGuncelle(mahalle);
		}
		FacesMessage mesaj = new FacesMessage("Kaydetme iþlemi baþarýlý",
				"Ýl: " + secilenIlce.getIl().getAdi() + " Ýlçe: "
						+ secilenIlce.getAdi() + "  Mahalle: "
						+ mahalle.getAdi() + " baþarýyla kaydedildi.");
		FacesContext.getCurrentInstance().addMessage(null, mesaj);
	}

	@PostConstruct
	public void init() {
		ilList = ilDao.hepsiniGetir();

		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		mahalle = (Mahalle) flash.get("mahalle");
		if (mahalle == null) {
			mahalle = new Mahalle();
		} else {
			if (mahalle.getIlce() != null) {
				secilenIlce = mahalle.getIlce();
				secilenIl = secilenIlce.getIl();
				ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
			}
		}
	}

	public void ilSecildi() {
		ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
	}

	public Mahalle getMahalle() {
		return mahalle;
	}

	public void setMahalle(Mahalle mahalle) {
		this.mahalle = mahalle;
	}

	public Ilce getSecilenIlce() {
		return secilenIlce;
	}

	public void setSecilenIlce(Ilce secilenIlce) {
		this.secilenIlce = secilenIlce;
	}

	public List<Ilce> getIlceList() {
		return ilceList;
	}

	public void setIlceList(List<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

	public List<Il> getIlList() {
		return ilList;
	}

	public void setIlList(List<Il> ilList) {
		this.ilList = ilList;
	}

	public Il getSecilenIl() {
		return secilenIl;
	}

	public void setSecilenIl(Il secilenIl) {
		this.secilenIl = secilenIl;
	}

	public MahalleDao getMahalleDao() {
		return mahalleDao;
	}

	public void setMahalleDao(MahalleDao mahalleDao) {
		this.mahalleDao = mahalleDao;
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

}