package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ens.degerleme.dao.BankaDao;
import com.ens.degerleme.dao.BankaSubesiDao;
import com.ens.degerleme.dao.BinaDao;
import com.ens.degerleme.dao.FirmaDao;
import com.ens.degerleme.dao.FormDao;
import com.ens.degerleme.dao.IlDao;
import com.ens.degerleme.dao.IlceDao;
import com.ens.degerleme.dao.IsEmriDao;
import com.ens.degerleme.dao.KisiDao;
import com.ens.degerleme.dao.MahalleDao;
import com.ens.degerleme.dao.SokakDao;
import com.ens.degerleme.dao.UserDao;
import com.ens.degerleme.dao.searchobject.BinaSearch;
import com.ens.degerleme.dao.searchobject.FirmaSearch;
import com.ens.degerleme.dao.searchobject.KisiSearch;
import com.ens.degerleme.model.entity.Banka;
import com.ens.degerleme.model.entity.BankaSubesi;
import com.ens.degerleme.model.entity.Bina;
import com.ens.degerleme.model.entity.Firma;
import com.ens.degerleme.model.entity.Form;
import com.ens.degerleme.model.entity.Il;
import com.ens.degerleme.model.entity.Ilce;
import com.ens.degerleme.model.entity.IsEmri;
import com.ens.degerleme.model.entity.Kisi;
import com.ens.degerleme.model.entity.Mahalle;
import com.ens.degerleme.model.entity.Sokak;
import com.ens.degerleme.model.entity.User;

@ManagedBean(name = "isEmriGirisi")
@ViewScoped
public class IsEmriGiris implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2794485036562255038L;

	@ManagedProperty(value = "#{kisiGirisi}")
	private KisiGiris kisiGiris;

	@ManagedProperty(value = "#{firmaGirisi}")
	private FirmaGiris firmaGiris;

	@ManagedProperty(value = "#{binaGirisi}")
	private BinaGiris binaGiris;

	@EJB
	private IsEmriDao isEmriDao;

	@EJB
	private BankaSubesiDao bankaSubesiDao;

	@EJB
	private BankaDao bankaDao;

	@EJB
	private KisiDao kisiDao;

	@EJB
	private FirmaDao firmaDao;

	@EJB
	private UserDao userDao;

	@EJB
	private BinaDao binaDao;

	@EJB
	private IlDao ilDao;

	@EJB
	private IlceDao ilceDao;

	@EJB
	private MahalleDao mahalleDao;

	@EJB
	private SokakDao sokakDao;

	@EJB
	private FormDao formDao;

	private boolean tuzelKisi = false;

	private IsEmri isEmri = new IsEmri();

	private Kisi secilenKisi = new Kisi();

	private Firma secilenFirma = new Firma();

	private Il secilenIl = new Il();

	private Ilce secilenIlce = new Ilce();

	private Mahalle secilenMahalle = new Mahalle();

	private KisiSearch kisiSearch = new KisiSearch();

	private FirmaSearch firmaSearch = new FirmaSearch();

	private BinaSearch binaSearch = new BinaSearch();

	private List<Kisi> kisiList = new ArrayList<Kisi>();

	private List<Firma> firmaList = new ArrayList<Firma>();

	private List<Banka> bankaList = new ArrayList<Banka>();

	private List<BankaSubesi> bankaSubesiList = new ArrayList<BankaSubesi>();

	private List<User> userList = new ArrayList<User>();

	private List<Il> ilList = new ArrayList<Il>();

	private List<Ilce> ilceList = new ArrayList<Ilce>();

	private List<Mahalle> mahalleList = new ArrayList<Mahalle>();

	private List<Sokak> sokakList = new ArrayList<Sokak>();

	private List<Bina> binaList = new ArrayList<Bina>();

	private List<Form> formList = new ArrayList<Form>();

	@PostConstruct
	public void init() {
		userList = userDao.hepsiniGetir();
		bankaList = bankaDao.hepsiniGetir();
		ilList = ilDao.hepsiniGetir();
		kisiList = kisiDao.hepsiniGetir();
		firmaList = firmaDao.hepsiniGetir();
		formList = formDao.hepsiniGetir();
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String isEmriId = params.get("isEmriId");
		if (isEmriId != null && !!"".equals(isEmriId)) {
			isEmri = (isEmriDao.isEmriGetir(Integer.valueOf(isEmriId)));
			secilenMahalle = isEmri.getBina().getSokak().getMahalle();
			secilenIlce = secilenMahalle.getIlce();
			secilenIl = secilenIlce.getIl();
			bankaSecildi();
			mahalleSecildi();
			ilceSecildi();
			ilSecildi();
			if (isEmri.getKisi() != null) {
				kisiSec(isEmri.getKisi());
				tuzelKisi = true;
			} else {
				firmaSec(isEmri.getFirma());
			}
		}
		if (isEmri.getBina() == null) {
			isEmri.setBina(new Bina());
		}
	}

	public void kisiSec(Kisi kisi) {
		secilenKisi.setId(kisi.getId());
		secilenKisi.setName(kisi.getName());
		secilenKisi.setSurName(kisi.getSurName());
		secilenKisi.setTcKimlikNo(kisi.getTcKimlikNo());
		secilenKisi.setTelNo(kisi.getTelNo());
		secilenKisi.setAdres(kisi.getAdres());
	}

	public void firmaSec(Firma firma) {
		secilenFirma.setId(firma.getId());
		secilenFirma.setName(firma.getName());
		secilenFirma.setAdres(firma.getAdres());
	}

	public void bankaSecildi() {
		if (isEmri.getBanka() != null) {
			bankaSubesiList = bankaSubesiDao.bankaSubesiGetirByBankaId(isEmri
					.getBanka().getId());
		} else {
			isEmri.setBankaSubesi(null);
			bankaSubesiList = new ArrayList<BankaSubesi>();
		}
	}

	public void ilSecildi() {
		if (secilenIl != null) {
			ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
		} else {
			isEmri.getBina().setSokak(null);
			ilceList = new ArrayList<Ilce>();
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenIlce = null;
			secilenMahalle = null;
		}
	}

	public void ilceSecildi() {
		if (secilenIlce != null) {
			mahalleList = mahalleDao.mahalleGetirByIlceId(secilenIlce.getId());
		} else {
			isEmri.getBina().setSokak(null);
			mahalleList = new ArrayList<Mahalle>();
			sokakList = new ArrayList<Sokak>();
			secilenMahalle = null;
		}
	}

	public void mahalleSecildi() {
		if (secilenMahalle != null) {
			sokakList = sokakDao.sokakGetirByMahalleId(secilenMahalle.getId());
		} else {
			sokakList = new ArrayList<Sokak>();
			isEmri.getBina().setSokak(null);
		}
	}

	public void sonucSecildi(Bina bina) {
		isEmri.setBina(bina);
		secilenMahalle = isEmri.getBina().getSokak().getMahalle();
		secilenIlce = secilenMahalle.getIlce();
		secilenIl = secilenIlce.getIl();
		ilceList = ilceDao.ilceGetirByIlId(secilenIl.getId());
		mahalleList = mahalleDao.mahalleGetirByIlceId(secilenIlce.getId());
		sokakList = sokakDao.sokakGetirByMahalleId(secilenMahalle.getId());
		isEmri.getBina().setAdres(
				isEmri.getBina().getSokak().getMahalle().getIlce().getIl()
						.getAdi().toUpperCase()
						+ "/"
						+ isEmri.getBina().getSokak().getMahalle().getIlce()
								.getAdi()
						+ "/"
						+ isEmri.getBina().getSokak().getMahalle().getAdi()
						+ "/"
						+ isEmri.getBina().getSokak().getAdi()
						+ " Kapý No:"
						+ isEmri.getBina().getKapiNo()
						+ " Daire No:" + isEmri.getBina().getDaireNo());
	}

	public void adresAra() {
		binaSearch.setIl(secilenIl);
		binaSearch.setIlce(secilenIlce);
		binaSearch.setMahalle(secilenMahalle);
		binaSearch.setSokak(isEmri.getBina().getSokak());
		binaList = binaDao.binaGetir(binaSearch);
	}

	public void kisiListele() {
		kisiList = kisiDao.kisiGetir(kisiSearch);
	}

	public void firmaListele() {
		firmaList = firmaDao.firmaGetir(firmaSearch);
	}

	public void binaListele() {
		binaList = binaDao.binaGetir(binaSearch);
	}

	public void kaydet() {
		if (tuzelKisi == false) {
			isEmri.setFirma(secilenFirma);
			isEmri.setKisi(null);
		} else {
			isEmri.setKisi(secilenKisi);
			isEmri.setFirma(null);
		}
		if (isEmri.getBina().getAdres() == null) {
			isEmri.getBina().setAdres(
					isEmri.getBina().getSokak().getMahalle().getIlce().getIl()
							.getAdi().toUpperCase()
							+ "/"
							+ isEmri.getBina().getSokak().getMahalle()
									.getIlce().getAdi()
							+ "/"
							+ isEmri.getBina().getSokak().getMahalle().getAdi()
							+ "/"
							+ isEmri.getBina().getSokak().getAdi()
							+ " Kapý No:"
							+ isEmri.getBina().getKapiNo()
							+ " Daire No:" + isEmri.getBina().getDaireNo());
		}
		if (isEmri.getId() == null) {
			isEmriDao.isEmriKaydet(isEmri);
		} else {
			isEmri = isEmriDao.isEmriGuncelle(isEmri);
		}
		isEmri = new IsEmri();
	}

	public void kisiKaydet() {
		getKisiGiris().kaydet();
		isEmri.setKisi(getKisiGiris().getKisi());
		kisiList.add(isEmri.getKisi());
	}

	public void firmaKaydet() {
		getFirmaGiris().kaydet();
		isEmri.setFirma(getFirmaGiris().getFirma());
		firmaList.add(isEmri.getFirma());
	}

	public void binaKaydet() {
		getBinaGiris().getBina().setSokak(isEmri.getBina().getSokak());
		getBinaGiris().getBina().setAdres(
				secilenIl.getAdi().toUpperCase() + "/" + secilenIlce.getAdi()
						+ "/" + secilenMahalle.getAdi() + "/"
						+ isEmri.getBina().getSokak().getAdi() + " Kapý No:"
						+ binaGiris.getBina().getKapiNo() + " Daire No:"
						+ binaGiris.getBina().getDaireNo());
		getBinaGiris().kaydet();
		isEmri.setBina(getBinaGiris().getBina());
		binaList.add(isEmri.getBina());
	}

	public IsEmri getIsEmri() {
		return isEmri;
	}

	public void setIsEmri(IsEmri isEmri) {
		this.isEmri = isEmri;
	}

	public List<BankaSubesi> getBankaSubesiList() {
		return bankaSubesiList;
	}

	public void setBankaSubesiList(List<BankaSubesi> bankaSubesiList) {
		this.bankaSubesiList = bankaSubesiList;
	}

	public List<Banka> getBankaList() {
		return bankaList;
	}

	public void setBankaList(List<Banka> bankaList) {
		this.bankaList = bankaList;
	}

	public boolean isTuzelKisi() {
		return tuzelKisi;
	}

	public void setTuzelKisi(boolean tuzelKisi) {
		this.tuzelKisi = tuzelKisi;
	}

	public KisiSearch getKisiSearch() {
		return kisiSearch;
	}

	public void setKisiSearch(KisiSearch kisiSearch) {
		this.kisiSearch = kisiSearch;
	}

	public List<Kisi> getKisiList() {
		return kisiList;
	}

	public void setKisiList(List<Kisi> kisiList) {
		this.kisiList = kisiList;
	}

	public FirmaSearch getFirmaSearch() {
		return firmaSearch;
	}

	public void setFirmaSearch(FirmaSearch firmaSearch) {
		this.firmaSearch = firmaSearch;
	}

	public List<Firma> getFirmaList() {
		return firmaList;
	}

	public void setFirmaList(List<Firma> firmaList) {
		this.firmaList = firmaList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Kisi getSecilenKisi() {
		return secilenKisi;
	}

	public void setSecilenKisi(Kisi secilenKisi) {
		this.secilenKisi = secilenKisi;
	}

	public Firma getSecilenFirma() {
		return secilenFirma;
	}

	public void setSecilenFirma(Firma secilenFirma) {
		this.secilenFirma = secilenFirma;
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

	public List<Bina> getBinaList() {
		return binaList;
	}

	public void setBinaList(List<Bina> binaList) {
		this.binaList = binaList;
	}

	public BinaSearch getBinaSearch() {
		return binaSearch;
	}

	public void setBinaSearch(BinaSearch binaSearch) {
		this.binaSearch = binaSearch;
	}

	public List<Form> getFormList() {
		return formList;
	}

	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}

	public KisiGiris getKisiGiris() {
		return kisiGiris;
	}

	public void setKisiGiris(KisiGiris kisiGiris) {
		this.kisiGiris = kisiGiris;
	}

	public FirmaGiris getFirmaGiris() {
		return firmaGiris;
	}

	public void setFirmaGiris(FirmaGiris firmaGiris) {
		this.firmaGiris = firmaGiris;
	}

	public List<Sokak> getSokakList() {
		return sokakList;
	}

	public void setSokakList(List<Sokak> sokakList) {
		this.sokakList = sokakList;
	}

	public BinaGiris getBinaGiris() {
		return binaGiris;
	}

	public void setBinaGiris(BinaGiris binaGiris) {
		this.binaGiris = binaGiris;
	}

}
