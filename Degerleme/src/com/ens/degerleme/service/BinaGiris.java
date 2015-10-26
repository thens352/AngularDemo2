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

import com.ens.degerleme.dao.BinaDao;
import com.ens.degerleme.dao.IlDao;
import com.ens.degerleme.dao.IlceDao;
import com.ens.degerleme.dao.MahalleDao;
import com.ens.degerleme.dao.SokakDao;
import com.ens.degerleme.model.entity.Bina;
import com.ens.degerleme.model.entity.Il;
import com.ens.degerleme.model.entity.Ilce;
import com.ens.degerleme.model.entity.Mahalle;
import com.ens.degerleme.model.entity.Sokak;

@ManagedBean(name = "binaGirisi")
@ViewScoped
public class BinaGiris implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8604671278513738024L;

	@EJB
	private BinaDao binaDao;

	private Bina bina = new Bina();

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
		ilList = ilDao.hepsiniGetir();

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String binaId = params.get("binaId");
		if (binaId != null && !!"".equals(binaId)) {
			bina = binaDao.binaGetir(Integer.valueOf(binaId));
			secilenMahalle=bina.getSokak().getMahalle();
			secilenIlce=secilenMahalle.getIlce();
			secilenIl=secilenIlce.getIl();
			mahalleSecildi();
			ilceSecildi();
			ilSecildi();
		}
	}

	public void ilSecildi() {
		if (secilenIl != null) {
			ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
		} else {
			getBina().setSokak(null);
			ilceList = new ArrayList<Ilce>();
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenIlce = null;
			secilenMahalle = null;
			getBina().setSokak(null);
		}
	}

	public void ilceSecildi() {
		if (secilenIlce != null) {
			mahalleList = mahalleDao.mahalleGetirByIlceId(secilenIlce.getId());
		} else {
			getBina().setSokak(null);
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenMahalle = null;
			getBina().setSokak(null);
		}
	}

	public void mahalleSecildi() {
		if (secilenMahalle != null) {
			sokakList = sokakDao.sokakGetirByMahalleId(secilenMahalle.getId());
		} else {
			sokakList = new ArrayList<Sokak>();
			getBina().setSokak(null);
		}
	}

	public void kaydet() {
		getBina().setAdres(
				getBina().getSokak().getMahalle().getIlce().getIl().getAdi()
						.toUpperCase()
						+ " / "
						+ getBina().getSokak().getMahalle().getIlce().getAdi()
						+ " / "
						+ getBina().getSokak().getMahalle().getAdi()
						+ " / "
						+ getBina().getSokak().getAdi()
						+ " Kapý No:"
						+ getBina().getKapiNo()
						+ " Daire No:"
						+ getBina().getDaireNo());
		if (bina.getId() == null) {
			binaDao.binaKaydet(bina);
		} else {
			bina = binaDao.binaGuncelle(bina);
		}
	}

	public Bina getBina() {
		return bina;
	}

	public void setBina(Bina bina) {
		this.bina = bina;
	}

	public IlDao getIlDao() {
		return ilDao;
	}

	public void setIlDao(IlDao ilDao) {
		this.ilDao = ilDao;
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
