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
import com.kayiyazilim.ekentsayim.dao.NoteBook_MasaustuPC_MonitorDao;
import com.kayiyazilim.ekentsayim.dao.ParametreDao;
import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.entity.NoteBook_MasaustuPC_Monitor;
import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.NoteBook_MasaustuPC_MonitorSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Cihaz;
import com.kayiyazilim.ekentsayim.model.type.Durum;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@ManagedBean(name = "noteBookMasaustuPCMonitorService")
@ViewScoped
public class NoteBookMasaustuPCMonitorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291907220340255921L;

	@EJB
	private KullaniciDao kullaniciDao;

	@EJB
	private BolgeDao bolgeDao;

	@EJB
	private NoteBook_MasaustuPC_MonitorDao dao;

	private NoteBook_MasaustuPC_Monitor noteBookMasaustuPCMonitor = new NoteBook_MasaustuPC_Monitor();

	private List<Kullanici> kullaniciList;

	private List<Bolge> bolgeList;

	private List<NoteBook_MasaustuPC_Monitor> noteBookMasaustuPCMonitorList;

	private NoteBook_MasaustuPC_MonitorSearch noteBookMasaustuPCMonitorSearch = new NoteBook_MasaustuPC_MonitorSearch();

	public Durum[] getDurumTipleri() {
		return Durum.values();
	}

	@EJB
	private ParametreDao parametreDao;

	private List<SelectItem> cesitList = new ArrayList<SelectItem>();

	private List<SelectItem> uzerindekiIsletimSistemiList = new ArrayList<SelectItem>();

	public void ara() {
		noteBookMasaustuPCMonitorList = dao
				.search(noteBookMasaustuPCMonitorSearch);
	}

	public Cihaz[] getCihazlar() {
		return Cihaz.values();
	}

	@PostConstruct
	public void init() {
		noteBookMasaustuPCMonitorList = dao.findAll();
		kullaniciList = kullaniciDao.findAll();
		bolgeList = bolgeDao.findAll();

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e24,
				Alan.CESIT)) {
			cesitList.add(new SelectItem(parametre.getDeger(), parametre
					.getDeger()));
		}

		for (Parametre parametre : parametreDao.getParametreListesi(Ekran.e24,
				Alan.UZERINDEKIISLETIMSISTEMI)) {
			uzerindekiIsletimSistemiList.add(new SelectItem(parametre
					.getDeger(), parametre.getDeger()));
		}
	}

	public void kaydet() {
		noteBookMasaustuPCMonitor.setGununTarihi(new Date());
		dao.persist(noteBookMasaustuPCMonitor);
		if (noteBookMasaustuPCMonitorList == null)
			noteBookMasaustuPCMonitorList = new ArrayList<NoteBook_MasaustuPC_Monitor>();
		noteBookMasaustuPCMonitorList.add(noteBookMasaustuPCMonitor);
		noteBookMasaustuPCMonitor = new NoteBook_MasaustuPC_Monitor();
	}

	public void guncelle(RowEditEvent event) {
		noteBookMasaustuPCMonitor = ((NoteBook_MasaustuPC_Monitor) event
				.getObject());
		dao.merge(noteBookMasaustuPCMonitor);
		noteBookMasaustuPCMonitor = new NoteBook_MasaustuPC_Monitor();
	}

	public void sil() {
		if (noteBookMasaustuPCMonitor != null)
			dao.remove(noteBookMasaustuPCMonitor);
		noteBookMasaustuPCMonitorList.remove(noteBookMasaustuPCMonitor);
		noteBookMasaustuPCMonitor = new NoteBook_MasaustuPC_Monitor();
	}

	public List<SelectItem> getCesitList() {
		return cesitList;
	}

	public void setCesitList(List<SelectItem> cesitList) {
		this.cesitList = cesitList;
	}

	public List<SelectItem> getUzerindekiIsletimSistemiList() {
		return uzerindekiIsletimSistemiList;
	}

	public void setUzerindekiIsletimSistemiList(
			List<SelectItem> uzerindekiIsletimSistemiList) {
		this.uzerindekiIsletimSistemiList = uzerindekiIsletimSistemiList;
	}

	public NoteBook_MasaustuPC_Monitor getLcd() {
		return noteBookMasaustuPCMonitor;
	}

	public void setLcd(NoteBook_MasaustuPC_Monitor noteBookMasaustuPCMonitor) {
		this.noteBookMasaustuPCMonitor = noteBookMasaustuPCMonitor;
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

	public List<NoteBook_MasaustuPC_Monitor> getLcdList() {
		return noteBookMasaustuPCMonitorList;
	}

	public void setLcdList(
			List<NoteBook_MasaustuPC_Monitor> noteBookMasaustuPCMonitorList) {
		this.noteBookMasaustuPCMonitorList = noteBookMasaustuPCMonitorList;
	}

	public NoteBook_MasaustuPC_Monitor getNoteBookMasaustuPCMonitor() {
		return noteBookMasaustuPCMonitor;
	}

	public void setNoteBookMasaustuPCMonitor(
			NoteBook_MasaustuPC_Monitor noteBookMasaustuPCMonitor) {
		this.noteBookMasaustuPCMonitor = noteBookMasaustuPCMonitor;
	}

	public List<NoteBook_MasaustuPC_Monitor> getNoteBookMasaustuPCMonitorList() {
		return noteBookMasaustuPCMonitorList;
	}

	public void setNoteBookMasaustuPCMonitorList(
			List<NoteBook_MasaustuPC_Monitor> noteBookMasaustuPCMonitorList) {
		this.noteBookMasaustuPCMonitorList = noteBookMasaustuPCMonitorList;
	}

	public NoteBook_MasaustuPC_MonitorSearch getNoteBookMasaustuPCMonitorSearch() {
		return noteBookMasaustuPCMonitorSearch;
	}

	public void setNoteBookMasaustuPCMonitorSearch(
			NoteBook_MasaustuPC_MonitorSearch noteBookMasaustuPCMonitorSearch) {
		this.noteBookMasaustuPCMonitorSearch = noteBookMasaustuPCMonitorSearch;
	}

}
