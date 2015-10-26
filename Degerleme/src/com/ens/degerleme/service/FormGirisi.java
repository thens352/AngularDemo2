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
import com.ens.degerleme.model.type.SoruTipi;

@ManagedBean(name = "formGirisi")
@ViewScoped
public class FormGirisi implements Serializable {

	private static final long serialVersionUID = -8092288558031692932L;

	@EJB
	private FormDao formDao;

	@EJB
	private BolumDao bolumDao;

	@EJB
	private SoruDao soruDao;

	@EJB
	private SecenekDao secenekDao;

	private Form form = new Form();

	private Bolum bolum = new Bolum();

	private Soru soru = new Soru();

	private Secenek secenek = new Secenek();

	private boolean soruUyari = false;
	private boolean defaultCevapSoru = false;
	private boolean parametreSayisiSoru = false;
	private boolean defaultCevapSecenek = false;
	private boolean parametreSayisiSecenek = false;
	private boolean aciklamaVarMi = false;
	private boolean secenekVarMi = false;

	private SoruTipi soruTipi;

	private List<Bolum> bolumList = new ArrayList<Bolum>();

	public SoruTipi[] getSoruTipleri() {
		return SoruTipi.values();
	}

	@PostConstruct
	public void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String formId = params.get("formId");
		if (formId != null && !!"".equals(formId)) {
			form = formDao.formGetir(Integer.valueOf(formId));
		}
	}

	public void bolumGuncelleHazirla(Bolum bolum_g) {
		bolum = bolum_g;
	}

	public void soruGuncelleHazirla(Soru soru_g) {
		soru = soru_g;
	}

	public void secenekGuncelleHazirla(Secenek secenek_g) {
		secenek = secenek_g;
	}

	public void formSil(Form form_s) {
		formDao.formSil(form_s.getId());
	}

	public void bolumSil(Bolum bolum_s) {
		form.getBolumList().remove(bolum_s);
		bolumDao.bolumSil(bolum_s.getId());
	}

	public void soruSil(Soru soru_s) {
		bolum.getSoruListesi().remove(soru_s);
		soruDao.soruSil(soru_s.getId());
	}

	public void secenekSil(Secenek secenek_s) {
		soru.getSeceneklList().remove(secenek_s);
		secenekDao.secenekSil(secenek_s.getId());
	}

	public void yeniBolum() {
		bolum.setForm(form);
	}

	public void yeniSoru() {
		soru.setBolum(bolum);
	}

	public void yeniSecenek() {
		secenek.setSoru(soru);
	}

	public void secenekEkle() {
		if (soruTipi.getI18nKey() == "Çoktan Seçmeli Soru") {
			secenek.setSecenekTipi(SecenekTipi.RADIO);
		} else if (soruTipi.getI18nKey() == "Çoktan Çok Seçmeli Soru") {
			secenek.setSecenekTipi(SecenekTipi.CHECKBOX);
		} else if (soruTipi.getI18nKey() == "Boþluk Doldurmalý Soru") {
			secenek.setSecenekTipi(SecenekTipi.TEXTBOX);
		} else if (soruTipi.getI18nKey() == "Çoktan Seçmeli Boþluk Doldurmalý Soru") {
			secenek.setSecenekTipi(SecenekTipi.RADIO);
		} else if (soruTipi.getI18nKey() == "Çoktan Çok Seçmeli Text Soru") {
			secenek.setSecenekTipi(SecenekTipi.TEXTBOX);
		}
		if (soru.getSeceneklList().contains(secenek)) {
			soru.getSeceneklList().remove(secenek);
		}
		soru.getSeceneklList().add(secenek);
		secenek = new Secenek();
	}

	public void soruEkle() {
		if (bolum.getSoruListesi().contains(soru)) {
			bolum.getSoruListesi().remove(soru);
		}
		bolum.getSoruListesi().add(soru);
		soru = new Soru();
	}

	public void bolumEkle() {
		if (form.getBolumList().contains(bolum)) {
			form.getBolumList().remove(bolum);
		}
		form.getBolumList().add(bolum);
		bolum = new Bolum();
	}

	public void kaydet() {
		if (form.getId() == null) {
			formDao.formKaydet(form);
		} else {
			form = formDao.formGuncelle(form);
		}
		for (Bolum bolum_f : form.getBolumList()) {
			if (bolum_f.getId() == null) {
				bolumDao.bolumKaydet(bolum_f);
			} else {
				bolum_f = bolumDao.bolumGuncelle(bolum_f);
			}
			for (Soru soru_f : bolum_f.getSoruListesi()) {
				if (soru_f.getId() == null) {
					soruDao.soruKaydet(soru_f);
				} else {
					soru_f = soruDao.soruGuncelle(soru_f);
				}
				for (Secenek secenek_f : soru_f.getSeceneklList()) {
					if (secenek_f.getId() == null) {
						secenekDao.secenekKaydet(secenek_f);
					} else {
						secenek_f = secenekDao.secenekGuncelle(secenek_f);
					}
				}
			}
		}
		form = new Form();
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

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Bolum getBolum() {
		return bolum;
	}

	public void setBolum(Bolum bolum) {
		this.bolum = bolum;
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

	public List<Bolum> getBolumList() {
		return bolumList;
	}

	public void setBolumList(List<Bolum> bolumList) {
		this.bolumList = bolumList;
	}

	public SoruTipi getSoruTipi() {
		return soruTipi;
	}

	public void setSoruTipi(SoruTipi soruTipi) {
		this.soruTipi = soruTipi;
	}

}
