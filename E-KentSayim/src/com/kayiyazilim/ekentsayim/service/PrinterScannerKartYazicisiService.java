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
import com.kayiyazilim.ekentsayim.dao.KullaniciDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.dao.Printer_Scanner_KartYazicisiDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.entity.Printer_Scanner_KartYazicisi;
import com.kayiyazilim.ekentsayim.model.search.Printer_Scanner_KartYazicisiSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "printerScannerKartYazicisiService")
@ViewScoped
public class PrinterScannerKartYazicisiService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private Printer_Scanner_KartYazicisiDao dao;

	private Printer_Scanner_KartYazicisi printerScannerKartYazicisi = new Printer_Scanner_KartYazicisi();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<Printer_Scanner_KartYazicisi> printerScannerKartYazicisiList;

	private Printer_Scanner_KartYazicisiSearch printerScannerKartYazicisiSearch = new Printer_Scanner_KartYazicisiSearch();

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> cesitList = new ArrayList<SelectItem>();

	public void ara() {
		printerScannerKartYazicisiList = dao
				.search(printerScannerKartYazicisiSearch);
	}

	@PostConstruct
	public void init() {
		printerScannerKartYazicisiList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e23,
				Alan.CESIT)) {
			cesitList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}
	}

	public void kaydet() {
		printerScannerKartYazicisi.setGununTarihi(new Date());
		dao.persist(printerScannerKartYazicisi);
		if (printerScannerKartYazicisiList == null)
			printerScannerKartYazicisiList = new ArrayList<Printer_Scanner_KartYazicisi>();
		printerScannerKartYazicisiList.add(printerScannerKartYazicisi);
		printerScannerKartYazicisi = new Printer_Scanner_KartYazicisi();
	}

	public void guncelle(RowEditEvent event) {
		printerScannerKartYazicisi = ((Printer_Scanner_KartYazicisi) event
				.getObject());
		dao.merge(printerScannerKartYazicisi);
		printerScannerKartYazicisi = new Printer_Scanner_KartYazicisi();
	}

	public void sil() {
		if (printerScannerKartYazicisi != null)
			dao.remove(printerScannerKartYazicisi);
		printerScannerKartYazicisiList.remove(printerScannerKartYazicisi);
		printerScannerKartYazicisi = new Printer_Scanner_KartYazicisi();
	}

	public List<SelectItem> getCesitList() {
		return cesitList;
	}

	public void setCesitList(List<SelectItem> cesitList) {
		this.cesitList = cesitList;
	}

	public Printer_Scanner_KartYazicisi getLcd() {
		return printerScannerKartYazicisi;
	}

	public void setLcd(Printer_Scanner_KartYazicisi printerScannerKartYazicisi) {
		this.printerScannerKartYazicisi = printerScannerKartYazicisi;
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

	public List<Printer_Scanner_KartYazicisi> getLcdList() {
		return printerScannerKartYazicisiList;
	}

	public void setLcdList(
			List<Printer_Scanner_KartYazicisi> printerScannerKartYazicisiList) {
		this.printerScannerKartYazicisiList = printerScannerKartYazicisiList;
	}

	public Printer_Scanner_KartYazicisi getPrinterScannerKartYazicisi() {
		return printerScannerKartYazicisi;
	}

	public void setPrinterScannerKartYazicisi(
			Printer_Scanner_KartYazicisi printerScannerKartYazicisi) {
		this.printerScannerKartYazicisi = printerScannerKartYazicisi;
	}

	public List<Printer_Scanner_KartYazicisi> getPrinterScannerKartYazicisiList() {
		return printerScannerKartYazicisiList;
	}

	public void setPrinterScannerKartYazicisiList(
			List<Printer_Scanner_KartYazicisi> printerScannerKartYazicisiList) {
		this.printerScannerKartYazicisiList = printerScannerKartYazicisiList;
	}

	public Printer_Scanner_KartYazicisiSearch getPrinterScannerKartYazicisiSearch() {
		return printerScannerKartYazicisiSearch;
	}

	public void setPrinterScannerKartYazicisiSearch(
			Printer_Scanner_KartYazicisiSearch printerScannerKartYazicisiSearch) {
		this.printerScannerKartYazicisiSearch = printerScannerKartYazicisiSearch;
	}

}
