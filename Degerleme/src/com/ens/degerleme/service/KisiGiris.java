package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ens.degerleme.dao.IlDao;
import com.ens.degerleme.dao.IlceDao;
import com.ens.degerleme.dao.KisiDao;
import com.ens.degerleme.dao.MahalleDao;
import com.ens.degerleme.dao.SokakDao;
import com.ens.degerleme.model.entity.Il;
import com.ens.degerleme.model.entity.Ilce;
import com.ens.degerleme.model.entity.Kisi;
import com.ens.degerleme.model.entity.Mahalle;
import com.ens.degerleme.model.entity.Sokak;

@ManagedBean(name = "kisiGirisi")
@ViewScoped
public class KisiGiris implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2569448333853141332L;

	@EJB
	private KisiDao kisiDao;

	private Kisi kisi = new Kisi();

	@EJB
	private IlDao ilDao;

	@EJB
	private IlceDao ilceDao;

	@EJB
	private MahalleDao mahalleDao;

	@EJB
	private SokakDao sokakDao;

	private Il secilenIl = new Il();

	private Ilce secilenIlce = new Ilce();

	private Mahalle secilenMahalle = new Mahalle();

	private List<Il> ilList = new ArrayList<Il>();

	private List<Ilce> ilceList = new ArrayList<Ilce>();

	private List<Mahalle> mahalleList = new ArrayList<Mahalle>();

	private List<Sokak> sokakList = new ArrayList<Sokak>();

	@PostConstruct
	public void init() {
		ilList=ilDao.hepsiniGetir();
		
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String kisiId = params.get("kisiId");
		if (kisiId != null && !!"".equals(kisiId)) {
			kisi = kisiDao.kisiGetir(Integer.valueOf(kisiId));
			secilenMahalle = kisi.getSokak().getMahalle();
			secilenIlce = secilenMahalle.getIlce();
			secilenIl = secilenIlce.getIl();
			mahalleSecildi();
			ilceSecildi();
			ilSecildi();
		}
	}

	public void ilSecildi() {
		if (secilenIl != null) {
			ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
		} else {
			getKisi().setSokak(null);
			ilceList = new ArrayList<Ilce>();
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenIlce = null;
			secilenMahalle = null;
			getKisi().setSokak(null);
		}
	}

	public void ilceSecildi() {
		if (secilenIlce != null) {
			mahalleList = mahalleDao.mahalleGetirByIlceId(secilenIlce.getId());
		} else {
			getKisi().setSokak(null);
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenMahalle = null;
			getKisi().setSokak(null);
		}
	}

	public void mahalleSecildi() {
		if (secilenMahalle != null) {
			sokakList = sokakDao.sokakGetirByMahalleId(secilenMahalle.getId());
		} else {
			sokakList = new ArrayList<Sokak>();
			getKisi().setSokak(null);
		}
	}

	public void kaydet() {
		getKisi().setAdres(
				getKisi().getSokak().getMahalle().getIlce().getIl().getAdi()
						.toUpperCase()
						+ " / "
						+ getKisi().getSokak().getMahalle().getIlce().getAdi()
						+ " / "
						+ getKisi().getSokak().getMahalle().getAdi()
						+ " / "
						+ getKisi().getSokak().getAdi()
						+ " Kapý No:"
						+ getKisi().getKapiNo()
						+ " Daire No:"
						+ getKisi().getDaireNo());
		if (kisi.getId() == null) {
			kisiDao.kisiKaydet(kisi);
		} else {
			kisi = kisiDao.kisiGuncelle(kisi);
		}

	}

	public Kisi getKisi() {
		return kisi;
	}

	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
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

	public Mahalle getSecilenMahalle() {
		return secilenMahalle;
	}

	public void setSecilenMahalle(Mahalle secilenMahalle) {
		this.secilenMahalle = secilenMahalle;
	}

	public List<Il> getIlList() {
		return ilList;
	}

	public void setIlList(List<Il> ilList) {
		this.ilList = ilList;
	}

	public List<Ilce> getIlceList() {
		return ilceList;
	}

	public void setIlceList(List<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

	public List<Mahalle> getMahalleList() {
		return mahalleList;
	}

	public void setMahalleList(List<Mahalle> mahalleList) {
		this.mahalleList = mahalleList;
	}

	public List<Sokak> getSokakList() {
		return sokakList;
	}

	public void setSokakList(List<Sokak> sokakList) {
		this.sokakList = sokakList;
	}
}
