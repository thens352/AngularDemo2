package com.kayiyazilim.budaksanisakis.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.budaksanisakis.dao.AracDao;
import com.kayiyazilim.budaksanisakis.dao.FaturaDao;
import com.kayiyazilim.budaksanisakis.dao.MalzemeDao;
import com.kayiyazilim.budaksanisakis.dao.ProjeDao;
import com.kayiyazilim.budaksanisakis.dao.SoforDao;
import com.kayiyazilim.budaksanisakis.dao.StokHareketDao;
import com.kayiyazilim.budaksanisakis.dao.UrunDao;
import com.kayiyazilim.budaksanisakis.dao.YakitHareketDao;
import com.kayiyazilim.budaksanisakis.model.entity.Arac;
import com.kayiyazilim.budaksanisakis.model.entity.Fatura;
import com.kayiyazilim.budaksanisakis.model.entity.Malzeme;
import com.kayiyazilim.budaksanisakis.model.entity.Proje;
import com.kayiyazilim.budaksanisakis.model.entity.Sofor;
import com.kayiyazilim.budaksanisakis.model.entity.StokHareket;
import com.kayiyazilim.budaksanisakis.model.entity.Urun;
import com.kayiyazilim.budaksanisakis.model.entity.YakitHareket;
import com.kayiyazilim.budaksanisakis.model.type.HareketTipi;
import com.kayiyazilim.budaksanisakis.model.type.MalzemeTipi;

@ManagedBean(name = "faturaService")
@ViewScoped
public class FaturaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6982393724258770393L;

	@EJB
	private FaturaDao faturaDao;

	@EJB
	private ProjeDao projeDao;

	@EJB
	private MalzemeDao malzemeDao;

	@EJB
	private UrunDao urunDao;

	@EJB
	private StokHareketDao stokHareketDao;

	@EJB
	private YakitHareketDao yakitHareketDao;

	@EJB
	private AracDao aracDao;

	@EJB
	private SoforDao soforDao;

	private List<Arac> aracList;

	private List<Sofor> soforList;

	private List<YakitHareket> yakitHareketList = new ArrayList<YakitHareket>();

	private YakitHareket yakitHareket = new YakitHareket();

	private Fatura fatura = new Fatura();

	private Urun urun = new Urun();

	private List<Malzeme> malzemeList;

	private List<Proje> projeList;

	private List<Urun> urunList = new ArrayList<Urun>();

	private List<StokHareket> stokHareketList = new ArrayList<StokHareket>();

	private List<Fatura> faturaList;

	private List<Fatura> filtrelenenFaturaList;

	private StokHareket stokHareket = new StokHareket();

	private Malzeme malzeme = new Malzeme();

	private Arac arac = new Arac();

	private Sofor sofor = new Sofor();

	public HareketTipi[] getHareketTipleri() {
		return HareketTipi.values();
	}

	public MalzemeTipi[] getMalzemeTipleri() {
		return MalzemeTipi.values();
	}

	@PostConstruct
	public void init() {
		projeList = projeDao.findAll();
		malzemeList = malzemeDao.findAll();
		aracList = aracDao.findAll();
		soforList = soforDao.findAll();
		faturaList = faturaDao.findAll();
	}

	public void refresh() {
		faturaList = faturaDao.findAll();
	}

	public void kaydet() {
		for (Urun temp : urunList) {
			temp.setFatura(fatura);
			fatura.setToplamTutar(fatura.getToplamTutar()
					+ temp.getToplamTutar());
		}
		for (StokHareket tempSH : stokHareketList) {
			tempSH.setProje(fatura.getProje());
			tempSH.setFatura(fatura);
			tempSH.getMalzeme()
					.setMevcutMiktar(
							tempSH.getMalzeme().getMevcutMiktar()
									+ tempSH.getGelenMiktar()
									- tempSH.getGidenMiktar());
			malzemeDao.merge(tempSH.getMalzeme());
			for (YakitHareket tempYH : tempSH.getYakitHareketList()) {
				tempYH.setProje(tempSH.getProje());
				tempYH.setStokHareket(tempSH);
			}
		}
		fatura.setUrunList(urunList);
		fatura.setStokHareketList(stokHareketList);
		faturaDao.persist(fatura);
		faturaList.add(fatura);
		FacesMessage mesaj2 = new FacesMessage(
				"Fatura Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		fatura = new Fatura();
	}

	public void guncelle() {
		fatura.setToplamTutar(0);
		for (Urun temp : urunList) {
			if (temp.getId() == 0)
				temp.setFatura(fatura);
			fatura.setToplamTutar(fatura.getToplamTutar()
					+ temp.getToplamTutar());
		}
		for (StokHareket tempSH : stokHareketList) {
			tempSH.setProje(fatura.getProje());
			tempSH.setFatura(fatura);
			tempSH.getMalzeme()
					.setMevcutMiktar(
							tempSH.getMalzeme().getMevcutMiktar()
									+ tempSH.getGelenMiktar()
									- tempSH.getGidenMiktar());
			malzemeDao.merge(tempSH.getMalzeme());
			for (YakitHareket tempYH : tempSH.getYakitHareketList()) {
				tempYH.setProje(tempSH.getProje());
				tempYH.setStokHareket(tempSH);
			}
		}
		faturaDao.merge(fatura);
		FacesMessage mesaj2 = new FacesMessage(
				"Fatura Güncelleme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		fatura = new Fatura();
	}

	public void urunGuncelle(RowEditEvent event) {
		urun = ((Urun) event.getObject());
		urun.setToplamTutar(urun.getBirimTutar() * urun.getMiktar());
		FacesMessage mesaj2 = new FacesMessage(
				"Ürün Güncelleme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		urun = new Urun();
	}

	public void urunSil() {
		urunList.remove(urun);
		FacesMessage mesaj2 = new FacesMessage("Ürün Silme Ýþlemi Tamamlandý.",
				"");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		urun = new Urun();
	}

	public void stokHareketSil() {
		stokHareketList.remove(stokHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Stok Hareket Silme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		stokHareket = new StokHareket();
	}

	public void yakitHareketSil() {
		yakitHareketList.remove(yakitHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Yakýt Hareket Silme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		yakitHareket = new YakitHareket();
	}

	public void sil() {
		faturaDao.remove(fatura);
		faturaList.remove(fatura);
		FacesMessage mesaj2 = new FacesMessage(
				"Fatura Silme Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		fatura = new Fatura();
	}

	public void malzemeKaydet() {
		malzemeDao.persist(malzeme);
		malzemeList.add(malzeme);
		FacesMessage mesaj2 = new FacesMessage(
				"Malzeme Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		malzeme = new Malzeme();
	}

	public void aracKaydet() {
		aracDao.persist(arac);
		aracList.add(arac);
		FacesMessage mesaj2 = new FacesMessage(
				"Araç Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		arac = new Arac();
	}

	public void soforKaydet() {
		soforDao.persist(sofor);
		soforList.add(sofor);
		FacesMessage mesaj2 = new FacesMessage(
				"Þoför Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		sofor = new Sofor();
	}

	public void urunEkle() {
		urun.setToplamTutar(urun.getBirimTutar() * urun.getMiktar());
		urunList.add(urun);
		FacesMessage mesaj2 = new FacesMessage(
				"Ürün Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		urun = new Urun();
	}

	public void stokHareketEkle() {
		stokHareketList.add(stokHareket);
		stokHareket.setYakitHareketList(yakitHareketList);
		FacesMessage mesaj2 = new FacesMessage(
				"Stok Hareket Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		stokHareket = new StokHareket();
		yakitHareketList = new ArrayList<YakitHareket>();
	}

	public void yakitHareketEkle() {
		yakitHareketList.add(yakitHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Yakýt Hareket Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		yakitHareket = new YakitHareket();
	}

	public void faturaYenile() {
		// urunlist ve stokhareketlist i sil ve faturanýn içerisindekileri
		// kullan ama yakithareketlisti de newle
		fatura = new Fatura();
		urunList = new ArrayList<Urun>();
		stokHareketList = new ArrayList<StokHareket>();
		yakitHareketList = new ArrayList<YakitHareket>();
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public List<Proje> getProjeList() {
		return projeList;
	}

	public void setProjeList(List<Proje> projeList) {
		this.projeList = projeList;
	}

	public List<Malzeme> getMalzemeList() {
		return malzemeList;
	}

	public void setMalzemeList(List<Malzeme> malzemeList) {
		this.malzemeList = malzemeList;
	}

	public Urun getUrun() {
		return urun;
	}

	public void setUrun(Urun urun) {
		this.urun = urun;
	}

	public List<Urun> getUrunList() {
		return urunList;
	}

	public void setUrunList(List<Urun> urunList) {
		this.urunList = urunList;
	}

	public List<StokHareket> getStokHareketList() {
		return stokHareketList;
	}

	public void setStokHareketList(List<StokHareket> stokHareketList) {
		this.stokHareketList = stokHareketList;
	}

	public StokHareket getStokHareket() {
		return stokHareket;
	}

	public void setStokHareket(StokHareket stokHareket) {
		this.stokHareket = stokHareket;
	}

	public List<YakitHareket> getYakitHareketList() {
		return yakitHareketList;
	}

	public void setYakitHareketList(List<YakitHareket> yakitHareketList) {
		this.yakitHareketList = yakitHareketList;
	}

	public YakitHareket getYakitHareket() {
		return yakitHareket;
	}

	public void setYakitHareket(YakitHareket yakitHareket) {
		this.yakitHareket = yakitHareket;
	}

	public List<Arac> getAracList() {
		return aracList;
	}

	public void setAracList(List<Arac> aracList) {
		this.aracList = aracList;
	}

	public List<Sofor> getSoforList() {
		return soforList;
	}

	public void setSoforList(List<Sofor> soforList) {
		this.soforList = soforList;
	}

	public List<Fatura> getFaturaList() {
		return faturaList;
	}

	public void setFaturaList(List<Fatura> faturaList) {
		this.faturaList = faturaList;
	}

	public Malzeme getMalzeme() {
		return malzeme;
	}

	public void setMalzeme(Malzeme malzeme) {
		this.malzeme = malzeme;
	}

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public Sofor getSofor() {
		return sofor;
	}

	public void setSofor(Sofor sofor) {
		this.sofor = sofor;
	}

	public List<Fatura> getFiltrelenenFaturaList() {
		return filtrelenenFaturaList;
	}

	public void setFiltrelenenFaturaList(List<Fatura> filtrelenenFaturaList) {
		this.filtrelenenFaturaList = filtrelenenFaturaList;
	}
}
