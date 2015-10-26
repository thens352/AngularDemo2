package com.ens.degerleme.service;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.ens.degerleme.dao.RaporDao;
import com.ens.degerleme.dao.searchobject.RaporSearch;
import com.ens.degerleme.model.entity.Rapor;

@ManagedBean(name = "raporListesi")
@ViewScoped
public class RaporListesi implements Serializable {

	private static final long serialVersionUID = -8092288558031692932L;
	@EJB
	private RaporDao raporDao;
	@PersistenceContext
	private EntityManager entityManager;

	private Integer raporNo;

	private RaporSearch raporSearch = new RaporSearch();

	private List<Rapor> raporList = new ArrayList<Rapor>();

	private String kisiFirma;

	public String guncelle(Integer id) {
		return "raporGiris?faces-redirect=true&amp;includeViewParams=true&amp;altKategoriId="
				+ id;
	}

	public String guncelle() {
		return "raporGiris?faces-redirect=true&amp;includeViewParams=true&amp;altKategoriId=" + 2;
	}

	public void raporla(Integer raporId) throws Exception {
		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		String reportPath = FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/report/");

		JasperDesign jasperDesign = JRXmlLoader.load(reportPath
				+ "/report1.jrxml");
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);

		InitialContext initialContext = new InitialContext();
		DataSource dataSource = (DataSource) initialContext
				.lookup("java:/degerleme");
		Connection connection = dataSource.getConnection();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("P_RAPOR_ID", raporId);
		map.put("SUBREPORT_DIR", reportPath + "/");

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
	
	public void listele() {
		raporList = raporDao.raporGetir(raporSearch);
	}

	public RaporDao getRaporDao() {
		return raporDao;
	}

	public void setRaporDao(RaporDao raporDao) {
		this.raporDao = raporDao;
	}

	public String getKisiFirma() {
		return kisiFirma;
	}

	public void setKisiFirma(String kisiFirma) {
		this.kisiFirma = kisiFirma;
	}

	public RaporSearch getRaporSearch() {
		return raporSearch;
	}

	public void setRaporSearch(RaporSearch raporSearch) {
		this.raporSearch = raporSearch;
	}

	public List<Rapor> getRaporList() {
		return raporList;
	}

	public void setRaporList(List<Rapor> raporList) {
		this.raporList = raporList;
	}

	public Integer getRaporNo() {
		return raporNo;
	}

	public void setRaporNo(Integer raporNo) {
		this.raporNo = raporNo;
	}

}
