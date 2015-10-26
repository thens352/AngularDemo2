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

import com.ens.degerleme.dao.BolumDao;
import com.ens.degerleme.dao.FormDao;
import com.ens.degerleme.dao.SoruDao;
import com.ens.degerleme.model.entity.Bolum;
import com.ens.degerleme.model.entity.Form;
import com.ens.degerleme.model.entity.Soru;
import com.ens.degerleme.model.type.SoruTipi;

@ManagedBean(name = "soruGirisi")
@ViewScoped
public class SoruGiris implements Serializable {

	private static final long serialVersionUID = -5051032553739368816L;

	@EJB
	private FormDao formDao;

	@EJB
	private BolumDao bolumDao;

	@EJB
	private SoruDao soruDao;

	private List<Form> formList = new ArrayList<Form>();

	private List<Bolum> bolumList = new ArrayList<Bolum>();

	private Form form = new Form();

	private Bolum bolum = new Bolum();

	private Soru soru = new Soru();

	private boolean soruUyari = false;
	private boolean defaultCevapSoru = false;
	private boolean parametreSayisiSoru = false;
	private boolean defaultCevapSecenek = false;
	private boolean parametreSayisiSecenek = false;
	private boolean aciklamaVarMi = false;
	private boolean secenekVarMi = false;

	private SoruTipi soruTipi;

	public SoruTipi[] getSoruTipleri() {
		return SoruTipi.values();
	}

	@PostConstruct
	public void init() {
		formList = formDao.hepsiniGetir();
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String soruId = params.get("soruId");
		if (soruId != null && !!"".equals(soruId)) {
			soru = soruDao.soruGetir(Integer.valueOf(soruId));
			soruTipiDegistir(soru.getSoruTipi());
			bolum = soru.getBolum();
			form = bolum.getForm();
			formSecildi();
		}
	}

	public void formSecildi() {
		if (form != null) {
			bolumList = bolumDao.bolumGetirByFormId(form.getId());
		}
	}

	public void kaydet() {
		soru.setBolum(bolum);
		if (soru.getId() == null) {
			soruDao.soruKaydet(soru);
		} else {
			soru = soruDao.soruGuncelle(soru);
		}
	}

	public void soruTipiDegistir(SoruTipi soruTipi_g) {
		soruTipi = soruTipi_g;
		switch (soruTipi_g.getI18nKey()) {
		case "Text Soru": {
			setSecenekVarMi(false);
			setSoruUyari(false);
			setDefaultCevapSoru(false);
			setParametreSayisiSoru(false);
			setDefaultCevapSecenek(false);
			setParametreSayisiSecenek(false);
			setAciklamaVarMi(false);
			break;
		}
		case "Çoktan Seçmeli Soru": {
			setSecenekVarMi(true);
			setSoruUyari(true);
			setDefaultCevapSoru(true);
			setParametreSayisiSoru(false);
			setDefaultCevapSecenek(false);
			setParametreSayisiSecenek(false);
			setAciklamaVarMi(true);
			break;
		}
		case "Çoktan Çok Seçmeli Soru": {
			setSecenekVarMi(true);
			setSoruUyari(true);
			setDefaultCevapSoru(false);
			setParametreSayisiSoru(false);
			setDefaultCevapSecenek(false);
			setParametreSayisiSecenek(false);
			setAciklamaVarMi(true);
			break;
		}
		case "Boþluk Doldurmalý Soru": {
			setSecenekVarMi(true);
			setSoruUyari(false);
			setDefaultCevapSoru(false);
			setParametreSayisiSoru(true);
			setDefaultCevapSecenek(false);
			setParametreSayisiSecenek(true);
			setAciklamaVarMi(false);
			break;
		}
		case "Çoktan Seçmeli Boþluk Doldurmalý Soru": {
			setSecenekVarMi(true);
			setSoruUyari(true);
			setDefaultCevapSoru(false);
			setParametreSayisiSoru(true);
			setDefaultCevapSecenek(false);
			setParametreSayisiSecenek(true);
			setAciklamaVarMi(true);
			break;
		}
		case "Çoktan Çok Seçmeli Text Soru": {
			setSecenekVarMi(true);
			setSoruUyari(false);
			setDefaultCevapSoru(false);
			setParametreSayisiSoru(false);
			setDefaultCevapSecenek(false);
			setParametreSayisiSecenek(false);
			setAciklamaVarMi(true);
			break;
		}
		}
		if (bolum != null) {
			soru.setBolum(bolum);
		}
	}

	public List<Form> getFormList() {
		return formList;
	}

	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}

	public List<Bolum> getBolumList() {
		return bolumList;
	}

	public void setBolumList(List<Bolum> bolumList) {
		this.bolumList = bolumList;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public SoruTipi getSoruTipi() {
		return soruTipi;
	}

	public void setSoruTipi(SoruTipi soruTipi) {
		this.soruTipi = soruTipi;
	}

	public Bolum getBolum() {
		return bolum;
	}

	public void setBolum(Bolum bolum) {
		this.bolum = bolum;
	}

	public boolean isSoruUyari() {
		return soruUyari;
	}

	public void setSoruUyari(boolean soruUyari) {
		this.soruUyari = soruUyari;
	}

	public boolean isDefaultCevapSoru() {
		return defaultCevapSoru;
	}

	public void setDefaultCevapSoru(boolean defaultCevapSoru) {
		this.defaultCevapSoru = defaultCevapSoru;
	}

	public boolean isParametreSayisiSoru() {
		return parametreSayisiSoru;
	}

	public void setParametreSayisiSoru(boolean parametreSayisiSoru) {
		this.parametreSayisiSoru = parametreSayisiSoru;
	}

	public boolean isDefaultCevapSecenek() {
		return defaultCevapSecenek;
	}

	public void setDefaultCevapSecenek(boolean defaultCevapSecenek) {
		this.defaultCevapSecenek = defaultCevapSecenek;
	}

	public boolean isParametreSayisiSecenek() {
		return parametreSayisiSecenek;
	}

	public void setParametreSayisiSecenek(boolean parametreSayisiSecenek) {
		this.parametreSayisiSecenek = parametreSayisiSecenek;
	}

	public boolean isAciklamaVarMi() {
		return aciklamaVarMi;
	}

	public void setAciklamaVarMi(boolean aciklamaVarMi) {
		this.aciklamaVarMi = aciklamaVarMi;
	}

	public boolean isSecenekVarMi() {
		return secenekVarMi;
	}

	public void setSecenekVarMi(boolean secenekVarMi) {
		this.secenekVarMi = secenekVarMi;
	}

	public Soru getSoru() {
		return soru;
	}

	public void setSoru(Soru soru) {
		this.soru = soru;
	}

}