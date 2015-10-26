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
import com.ens.degerleme.dao.SecenekDao;
import com.ens.degerleme.dao.SoruDao;
import com.ens.degerleme.model.entity.Bolum;
import com.ens.degerleme.model.entity.Form;
import com.ens.degerleme.model.entity.Secenek;
import com.ens.degerleme.model.entity.Soru;
import com.ens.degerleme.model.type.SecenekTipi;

@ManagedBean(name = "secenekGirisi")
@ViewScoped
public class SecenekGiris implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8317808996180640258L;

	@EJB
	private FormDao formDao;

	@EJB
	private BolumDao bolumDao;

	@EJB
	private SoruDao soruDao;

	@EJB
	private SecenekDao secenekDao;

	private List<Form> formList = new ArrayList<Form>();

	private Form form = new Form();

	private List<Bolum> bolumList = new ArrayList<Bolum>();

	private Bolum bolum = new Bolum();

	private List<Soru> soruList = new ArrayList<Soru>();

	private Soru soru = new Soru();

	private Secenek secenek = new Secenek();

	private boolean soruUyari = false;
	private boolean defaultCevapSoru = false;
	private boolean parametreSayisiSoru = false;
	private boolean defaultCevapSecenek = false;
	private boolean parametreSayisiSecenek = false;
	private boolean aciklamaVarMi = false;
	private boolean secenekVarMi = false;

	@PostConstruct
	public void init() {
		formList = formDao.hepsiniGetir();
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String secenekId = params.get("secenekId");
		if (secenekId != null && !!"".equals(secenekId)) {
			secenek = secenekDao.secenekGetir(Integer.valueOf(secenekId));
			soru = secenek.getSoru();
			bolum = soru.getBolum();
			form = bolum.getForm();
			soruDegistir(soru);
			bolumSecildi();
			formSecildi();
		}
	}

	public void soruDegistir(Soru soru_td) {
		switch (soru_td.getSoruTipi().getI18nKey()) {
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
			secenek.setSecenekTipi(SecenekTipi.RADIO);
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
			secenek.setSecenekTipi(SecenekTipi.CHECKBOX);
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
			secenek.setSecenekTipi(SecenekTipi.TEXTBOX);
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
			secenek.setSecenekTipi(SecenekTipi.RADIO);
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
			secenek.setSecenekTipi(SecenekTipi.CHECKBOX);
			break;
		}
		}
	}

	public void formSecildi() {
		bolumList = bolumDao.bolumGetirByFormId(form.getId());
	}

	public void bolumSecildi() {
		soruList = soruDao.soruGetirByBolumId(bolum.getId());
	}

	public void kaydet() {
		secenek.setSoru(soru);
		if (secenek.getId() == null) {
			secenekDao.secenekKaydet(secenek);
		} else {
			secenekDao.secenekGuncelle(secenek);
		}
	}

	public List<Form> getFormList() {
		return formList;
	}

	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public List<Bolum> getBolumList() {
		return bolumList;
	}

	public void setBolumList(List<Bolum> bolumList) {
		this.bolumList = bolumList;
	}

	public Bolum getBolum() {
		return bolum;
	}

	public void setBolum(Bolum bolum) {
		this.bolum = bolum;
	}

	public List<Soru> getSoruList() {
		return soruList;
	}

	public void setSoruList(List<Soru> soruList) {
		this.soruList = soruList;
	}

	public Soru getSoru() {
		return soru;
	}

	public void setSoru(Soru soru) {
		this.soru = soru;
	}

	public Secenek getSecenek() {
		return secenek;
	}

	public void setSecenek(Secenek secenek) {
		this.secenek = secenek;
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

}
