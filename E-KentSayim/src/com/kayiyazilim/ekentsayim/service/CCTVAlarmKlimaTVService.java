package com.kayiyazilim.ekentsayim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.ekentsayim.dao.BolgeDao;
import com.kayiyazilim.ekentsayim.dao.CCTV_Alarm_Klima_TVDao;
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.CCTV_Alarm_Klima_TV;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.CCTV_Alarm_Klima_TVSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "cctvAlarmKlimaTVService")
@ViewScoped
public class CCTVAlarmKlimaTVService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private CCTV_Alarm_Klima_TVDao dao;

	private CCTV_Alarm_Klima_TV cctvAlarmKlimaTV = new CCTV_Alarm_Klima_TV();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<CCTV_Alarm_Klima_TV> cctvAlarmKlimaTVList;

	private CCTV_Alarm_Klima_TVSearch cctvAlarmKlimaTVSearch = new CCTV_Alarm_Klima_TVSearch();

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> cesitList = new ArrayList<SelectItem>();

	public void ara() {
		cctvAlarmKlimaTVList = dao.search(cctvAlarmKlimaTVSearch);
	}

	@PostConstruct
	public void init() {
		cctvAlarmKlimaTVList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e29,
				Alan.CESIT)) {
			cesitList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void kaydet() {
		cctvAlarmKlimaTV.setGununTarihi(new Date());
		dao.persist(cctvAlarmKlimaTV);
		if (cctvAlarmKlimaTVList == null)
			cctvAlarmKlimaTVList = new ArrayList<CCTV_Alarm_Klima_TV>();
		cctvAlarmKlimaTVList.add(cctvAlarmKlimaTV);
		cctvAlarmKlimaTV = new CCTV_Alarm_Klima_TV();
	}

	public void guncelle(RowEditEvent event) {
		cctvAlarmKlimaTV = ((CCTV_Alarm_Klima_TV) event.getObject());
		dao.merge(cctvAlarmKlimaTV);
		cctvAlarmKlimaTV = new CCTV_Alarm_Klima_TV();
	}

	public void sil() {
		if (cctvAlarmKlimaTV != null)
			dao.remove(cctvAlarmKlimaTV);
		cctvAlarmKlimaTVList.remove(cctvAlarmKlimaTV);
		cctvAlarmKlimaTV = new CCTV_Alarm_Klima_TV();
	}

	public List<SelectItem> getCesitList() {
		return cesitList;
	}

	public void setCesitList(List<SelectItem> cesitList) {
		this.cesitList = cesitList;
	}

	public CCTV_Alarm_Klima_TV getLcd() {
		return cctvAlarmKlimaTV;
	}

	public void setLcd(CCTV_Alarm_Klima_TV cctvAlarmKlimaTV) {
		this.cctvAlarmKlimaTV = cctvAlarmKlimaTV;
	}

	public List<Kullanici> getKullaniciList() {
		return kullaniciList;
	}

	public void setKullaniciList(List<Kullanici> kullaniciList) {
		this.kullaniciList = kullaniciList;
	}

	public List<Bolge> getBolgeList() {
		return bolgeList;
	}

	public void setBolgeList(List<Bolge> bolgeList) {
		this.bolgeList = bolgeList;
	}

	public List<CCTV_Alarm_Klima_TV> getLcdList() {
		return cctvAlarmKlimaTVList;
	}

	public void setLcdList(List<CCTV_Alarm_Klima_TV> cctvAlarmKlimaTVList) {
		this.cctvAlarmKlimaTVList = cctvAlarmKlimaTVList;
	}

	public CCTV_Alarm_Klima_TV getCctvAlarmKlimaTV() {
		return cctvAlarmKlimaTV;
	}

	public void setCctvAlarmKlimaTV(CCTV_Alarm_Klima_TV cctvAlarmKlimaTV) {
		this.cctvAlarmKlimaTV = cctvAlarmKlimaTV;
	}

	public List<CCTV_Alarm_Klima_TV> getCctvAlarmKlimaTVList() {
		return cctvAlarmKlimaTVList;
	}

	public void setCctvAlarmKlimaTVList(
			List<CCTV_Alarm_Klima_TV> cctvAlarmKlimaTVList) {
		this.cctvAlarmKlimaTVList = cctvAlarmKlimaTVList;
	}

	public CCTV_Alarm_Klima_TVSearch getCctvAlarmKlimaTVSearch() {
		return cctvAlarmKlimaTVSearch;
	}

	public void setCctvAlarmKlimaTVSearch(
			CCTV_Alarm_Klima_TVSearch cctvAlarmKlimaTVSearch) {
		this.cctvAlarmKlimaTVSearch = cctvAlarmKlimaTVSearch;
	}

}
