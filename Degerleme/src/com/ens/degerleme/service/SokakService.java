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
import com.ens.degerleme.dao.SokakDao;
import com.ens.degerleme.model.entity.Il;
import com.ens.degerleme.model.entity.Ilce;
import com.ens.degerleme.model.entity.Mahalle;
import com.ens.degerleme.model.entity.Sokak;



@ManagedBean(name = "sokakService")
@ViewScoped
public class SokakService implements Serializable {

	private static final long serialVersionUID = -6093393727814589660L;

	@EJB
	private IlDao ilDao;
	@EJB
	private IlceDao ilceDao;
	@EJB
	private MahalleDao mahalleDao;
	@EJB
	private SokakDao sokakDao;

	private Il secilenIl;
	private Ilce secilenIlce;
	private Mahalle secilenMahalle;
	private Sokak sokak;

	private List<Mahalle> mahalleList = new ArrayList<Mahalle>();
	private List<Ilce> ilceList = new ArrayList<Ilce>();
	private List<Il> ilList = new ArrayList<Il>();

	public void sokakKaydet() {
		sokak.setMahalle(secilenMahalle);
		if (sokak.getId() == null) {
			sokakDao.sokakKaydet(sokak);
		} else {
			sokak = sokakDao.sokakGuncelle(sokak);
		}
		FacesMessage mesaj = new FacesMessage("Kaydetme iþlemi baþarýlý",
				"Ýl: " + secilenMahalle.getIlce().getIl().getAdi() + " Ýlçe: "
						+ secilenMahalle.getIlce().getAdi() + "  Mahalle: "
						+ secilenMahalle.getAdi() + "  Sokak: "
						+ sokak.getAdi() + " baþarýyla kaydedildi.");
		FacesContext.getCurrentInstance().addMessage(null, mesaj);
	}

	@PostConstruct
	public void init() {
		ilList = ilDao.hepsiniGetir();

		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		sokak = (Sokak) flash.get("sokak");
		if (sokak == null) {
			sokak = new Sokak();
		} else {
			if (sokak.getMahalle() != null) {
				secilenMahalle = sokak.getMahalle();
				secilenIlce = secilenMahalle.getIlce();
				secilenIl = secilenIlce.getIl();
				ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
				mahalleList = mahalleDao.mahalleGetirByIlceId(secilenIlce
						.getId());
			}
		}

	}

	public void ilSecildi() {
		ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
	}

	public void ilceSecildi() {
		mahalleList = mahalleDao.mahalleGetirByIlceId(secilenIlce.getId());
	}

	public Sokak getSokak() {
		return sokak;
	}

	public void setSokak(Sokak sokak) {
		this.sokak = sokak;
	}

	public List<Mahalle> getMahalleList() {
		return mahalleList;
	}

	public void setMahalleList(List<Mahalle> mahalleList) {
		this.mahalleList = mahalleList;
	}

	public Mahalle getSecilenMahalle() {
		return secilenMahalle;
	}

	public void setSecilenMahalle(Mahalle secilenMahalle) {
		this.secilenMahalle = secilenMahalle;
	}

	public SokakDao getSokakDao() {
		return sokakDao;
	}

	public void setSokakDao(SokakDao sokakDao) {
		this.sokakDao = sokakDao;
	}

	public MahalleDao getMahalleDao() {
		return mahalleDao;
	}

	public void setMahalleDao(MahalleDao mahalleDao) {
		this.mahalleDao = mahalleDao;
	}

	public IlDao getIlDao() {
		return ilDao;
	}

	public void setIlDao(IlDao ilDao) {
		this.ilDao = ilDao;
	}

	public IlceDao getIlceDao() {
		return ilceDao;
	}

	public void setIlceDao(IlceDao ilceDao) {
		this.ilceDao = ilceDao;
	}

	public Il getSecilenIl() {
		return secilenIl;
	}

	public void setSecilenIl(Il secilenIl) {
		this.secilenIl = secilenIl;
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
}