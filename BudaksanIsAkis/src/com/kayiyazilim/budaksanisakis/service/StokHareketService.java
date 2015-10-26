package com.kayiyazilim.budaksanisakis.service;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.primefaces.event.RowEditEvent;

import com.kayiyazilim.budaksanisakis.dao.AracDao;
import com.kayiyazilim.budaksanisakis.dao.MalzemeDao;
import com.kayiyazilim.budaksanisakis.dao.ProjeDao;
import com.kayiyazilim.budaksanisakis.dao.SoforDao;
import com.kayiyazilim.budaksanisakis.dao.StokHareketDao;
import com.kayiyazilim.budaksanisakis.dao.YakitHareketDao;
import com.kayiyazilim.budaksanisakis.model.entity.Arac;
import com.kayiyazilim.budaksanisakis.model.entity.Malzeme;
import com.kayiyazilim.budaksanisakis.model.entity.Proje;
import com.kayiyazilim.budaksanisakis.model.entity.Sofor;
import com.kayiyazilim.budaksanisakis.model.entity.StokHareket;
import com.kayiyazilim.budaksanisakis.model.entity.YakitHareket;
import com.kayiyazilim.budaksanisakis.model.type.HareketTipi;
import com.kayiyazilim.budaksanisakis.model.type.MalzemeTipi;

@ManagedBean(name = "stokHareketService")
@ViewScoped
public class StokHareketService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8789358569441901718L;

	@EJB
	private StokHareketDao stokHareketDao;

	@EJB
	private MalzemeDao malzemeDao;

	@EJB
	private YakitHareketDao yakitHareketDao;

	@EJB
	private ProjeDao projeDao;

	@EJB
	private AracDao aracDao;

	@EJB
	private SoforDao soforDao;

	private Proje proje;

	private Arac arac = new Arac();

	private Sofor sofor = new Sofor();

	private Malzeme malzeme = new Malzeme();

	private StokHareket stokHareket = new StokHareket();

	private YakitHareket yakitHareket = new YakitHareket();

	private List<Malzeme> malzemeList;

	private List<Proje> projeList;

	private List<Arac> aracList;

	private List<Sofor> soforList;

	private List<YakitHareket> yakitHareketList = new ArrayList<YakitHareket>();

	private List<StokHareket> stokHareketList;

	private List<StokHareket> filterStokHareketList;

	public List<StokHareket> getFilterStokHareketList() {
		return filterStokHareketList;
	}

	public void setFilterStokHareketList(List<StokHareket> filterStokHareketList) {
		this.filterStokHareketList = filterStokHareketList;
	}

	public MalzemeTipi[] getMalzemeTipleri() {
		return MalzemeTipi.values();
	}

	public HareketTipi[] getHareketTipleri() {
		return HareketTipi.values();
	}

	public void malzemeyeGoreGetir() {
		stokHareketList = stokHareketDao.stokHareketGetirByMalzeme(malzeme);
	}

	public void projeyeGoreGetir() {
		stokHareketList = stokHareketDao.stokHareketGetirByProje(proje);
	}

	@PostConstruct
	public void init() {
		stokHareketList = stokHareketDao.findAll();
		malzemeList = malzemeDao.findAll();
		projeList = projeDao.findAll();
		aracList = aracDao.findAll();
		soforList = soforDao.findAll();
	}

	public void sil() {
		stokHareketDao.remove(stokHareket);
		stokHareketList.remove(stokHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Stok Hareketi Silme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		stokHareket = new StokHareket();
	}

	public void kaydet() {
		Malzeme malzeme = stokHareket.getMalzeme();
		malzeme.setMevcutMiktar(malzeme.getMevcutMiktar()
				+ stokHareket.getGelenMiktar() - stokHareket.getGidenMiktar());
		malzemeDao.merge(malzeme);
		stokHareket.setYakitHareketList(yakitHareketList);
		stokHareketDao.persist(stokHareket);
		stokHareketList.add(stokHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Stok Hareketi Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		stokHareket = new StokHareket();
		yakitHareketList = new ArrayList<YakitHareket>();
	}

	public void guncelle(RowEditEvent event) {
		stokHareket = (StokHareket) event.getObject();
		stokHareketDao.merge(stokHareket);
		stokHareket = new StokHareket();
	}

	public void yakitHareketEkle() {
		yakitHareket.setStokHareket(stokHareket);
		yakitHareket.setProje(stokHareket.getProje());
		yakitHareketList.add(yakitHareket);
		if (stokHareket.getId() != 0)
			yakitHareketDao.persist(yakitHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Yakýt Hareketi Ekleme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		yakitHareket = new YakitHareket();
	}

	public void yakitHareketGuncelle(RowEditEvent event) {
		yakitHareket = (YakitHareket) event.getObject();
		if (yakitHareket.getId() != 0)
			yakitHareketDao.merge(yakitHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Yakýt Hareketi Güncelleme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		yakitHareket = new YakitHareket();
	}

	public void yakitHareketSil() {
		stokHareket.getYakitHareketList().remove(yakitHareket);
		stokHareketDao.merge(stokHareket);
		FacesMessage mesaj2 = new FacesMessage(
				"Yakýt Hareketi Silme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		yakitHareket = new YakitHareket();
	}

	public void aracKaydet() {
		aracDao.persist(arac);
		aracList.add(arac);
		FacesMessage mesaj2 = new FacesMessage(
				"Araç Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		yakitHareket.setArac(arac);
		arac = new Arac();
	}

	public void soforKaydet() {
		soforDao.persist(sofor);
		soforList.add(sofor);
		FacesMessage mesaj2 = new FacesMessage(
				"Þoför Kaydetme Ýþlemi Tamamlandý.", "");
		FacesContext.getCurrentInstance().addMessage("", mesaj2);
		yakitHareket.setSofor(sofor);
		sofor = new Sofor();
	}

	public void raporla() throws JRException, IOException, NamingException,
			SQLException {
		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		String reportPath = FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/reports/");

		JasperDesign jasperDesign = JRXmlLoader.load(reportPath
				+ "/report2.jrxml");
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);

		InitialContext initialContext = new InitialContext();
		DataSource dataSource = (DataSource) initialContext
				.lookup("java:/budaksanisakis");
		Connection connection = dataSource.getConnection();

		Map<String, Object> map = new HashMap<String, Object>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				map, connection);
		response.setContentType("application/pdf");
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				response.getOutputStream());
		exporter.exportReport();
		FacesContext.getCurrentInstance().responseComplete();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean filterByDate(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
	}

	public StokHareket getStokHareket() {
		return stokHareket;
	}

	public void setStokHareket(StokHareket stokHareket) {
		this.stokHareket = stokHareket;
	}

	public YakitHareket getYakitHareket() {
		return yakitHareket;
	}

	public void setYakitHareket(YakitHareket yakitHareket) {
		this.yakitHareket = yakitHareket;
	}

	public List<Malzeme> getMalzemeList() {
		return malzemeList;
	}

	public void setMalzemeList(List<Malzeme> malzemeList) {
		this.malzemeList = malzemeList;
	}

	public List<Proje> getProjeList() {
		return projeList;
	}

	public void setProjeList(List<Proje> projeList) {
		this.projeList = projeList;
	}

	public List<YakitHareket> getYakitHareketList() {
		return yakitHareketList;
	}

	public void setYakitHareketList(List<YakitHareket> yakitHareketList) {
		this.yakitHareketList = yakitHareketList;
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

	public Malzeme getMalzeme() {
		return malzeme;
	}

	public void setMalzeme(Malzeme malzeme) {
		this.malzeme = malzeme;
	}

	public List<StokHareket> getStokHareketList() {
		return stokHareketList;
	}

	public void setStokHareketList(List<StokHareket> stokHareketList) {
		this.stokHareketList = stokHareketList;
	}

	public Proje getProje() {
		return proje;
	}

	public void setProje(Proje proje) {
		this.proje = proje;
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

}
