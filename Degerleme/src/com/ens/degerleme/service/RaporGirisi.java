package com.ens.degerleme.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.ens.degerleme.dao.CevapDao;
import com.ens.degerleme.dao.CevapSecenekDao;
import com.ens.degerleme.dao.EksiklikDao;
import com.ens.degerleme.dao.IsEmriDao;
import com.ens.degerleme.dao.OnlemDao;
import com.ens.degerleme.dao.RaporDao;
import com.ens.degerleme.model.Bosluk;
import com.ens.degerleme.model.entity.Bolum;
import com.ens.degerleme.model.entity.Cevap;
import com.ens.degerleme.model.entity.CevapSecenek;
import com.ens.degerleme.model.entity.Eksiklik;
import com.ens.degerleme.model.entity.IsEmri;
import com.ens.degerleme.model.entity.Onlem;
import com.ens.degerleme.model.entity.Rapor;
import com.ens.degerleme.model.entity.Secenek;
import com.ens.degerleme.model.entity.Soru;
import com.ens.degerleme.model.type.IsEmriDurumu;

@ManagedBean(name = "raporGirisi")
@ViewScoped
public class RaporGirisi implements Serializable {

	private static final long serialVersionUID = -8092288558031692932L;

	@EJB
	private RaporDao raporDao;

	@EJB
	private IsEmriDao isEmriDao;

	@EJB
	private OnlemDao onlemDao;

	@EJB
	private EksiklikDao eksiklikDao;

	@EJB
	private CevapSecenekDao cevapSecenekDao;

	@EJB
	private CevapDao cevapDao;

	private List<Bolum> bolumList = new ArrayList<Bolum>();

	private Bolum seciliBolum;

	private Rapor rapor = new Rapor();

	private Soru seciliSoru;

	private List<Secenek> secilenSecenekList = new ArrayList<Secenek>();

	private Cevap cevap = new Cevap();

	private IsEmri isEmri = new IsEmri();

	private Eksiklik eksiklik = new Eksiklik();

	private Onlem onlem = new Onlem();

	private boolean kisi = false;
	private boolean soruUyari = false;
	private boolean cevapMetni = false;
	private boolean radioTable = false;
	private boolean checkTable = false;
	private boolean aciklamaVar = false;
	private boolean boslukDoldurma = false;
	private boolean guncelle = false;

	@PostConstruct
	public void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String isEmriId = params.get("isEmriId");
		String raporId = params.get("raporId");
		if (isEmriId != null && !!"".equals(isEmriId)) {
			isEmri = isEmriDao.isEmriGetir(Integer.valueOf(isEmriId));
			rapor.setIsEmri(isEmri);
			if (isEmri.getFirma() == null) {
				kisi = true;
			}
			bolumList = isEmri.getForm().getBolumList();
			if (bolumList == null || bolumList.size() == 0) {
			} else {
				seciliBolum = bolumList.get(0);
			}
		}
		if (raporId != null && !!"".equals(raporId)) {
			guncelle = true;
			rapor = raporDao.raporGetir(Integer.valueOf(raporId));
			isEmri = rapor.getIsEmri();
			if (isEmri.getFirma() == null) {
				kisi = true;
			}
			bolumList = isEmri.getForm().getBolumList();
			if (bolumList == null || bolumList.size() == 0) {
			} else {
				seciliBolum = bolumList.get(0);
			}
		}
	}

	public void tarihSecildi(SelectEvent event) {

		rapor.setRaporTarihi((Date) event.getObject());
	}

	public void soruCevaplaHazirla(Soru soru) {
		if (soru.getId() != null) {
			seciliSoru = soru;
			cevap.setSoru(seciliSoru);
			switch (seciliSoru.getSoruTipi().getI18nKey()) {
			case "Text Soru": {
				setSoruUyari(false);
				setCevapMetni(true);
				setRadioTable(false);
				setCheckTable(false);
				setBoslukDoldurma(false);
				break;
			}
			case "Çoktan Seçmeli Soru": {
				setSoruUyari(true);
				setCevapMetni(false);
				setRadioTable(true);
				setCheckTable(false);
				setBoslukDoldurma(false);
				break;
			}
			case "Çoktan Çok Seçmeli Soru": {
				setSoruUyari(true);
				setCevapMetni(false);
				setRadioTable(false);
				setCheckTable(true);
				setBoslukDoldurma(false);
				break;
			}
			case "Boþluk Doldurmalý Soru": {
				setSoruUyari(false);
				setCevapMetni(false);
				setRadioTable(false);
				setCheckTable(false);
				setBoslukDoldurma(true);
				for (Secenek secenek : seciliSoru.getSeceneklList()) {
					for (int i = 0; i < secenek.getParametreSayisi(); i++) {
						Bosluk bosluk = new Bosluk();
						bosluk.setParam("" + (i + 1));
						cevap.getEkranCevapSecenek().getBoslukList()
								.add(bosluk);
					}
				}
				break;
			}
			case "Çoktan Seçmeli Boþluk Doldurmalý Soru": {
				setSoruUyari(false);
				setCevapMetni(false);
				setRadioTable(true);
				setCheckTable(false);
				setBoslukDoldurma(false);
				for (Secenek secenek : seciliSoru.getSeceneklList()) {
					for (int i = 0; i < secenek.getParametreSayisi(); i++) {
						Bosluk bosluk = new Bosluk();
						bosluk.setParam("" + (i + 1));
						cevap.getEkranCevapSecenek().getBoslukList()
								.add(bosluk);
					}
				}
				break;
			}
			case "Çoktan Çok Seçmeli Text Soru": {
				setSoruUyari(false);
				setCevapMetni(false);
				setRadioTable(false);
				setCheckTable(true);
				setBoslukDoldurma(false);
				break;
			}
			}
		}
	}

	public void Cevapla() {
		if (seciliSoru.getSoruTipi().getI18nKey() == "Çoktan Seçmeli Soru") {
			cevap.getEkranCevapSecenek().setSecildi(true);
			cevap.setMetin(cevap.getEkranCevapSecenek().getSecenek().getMetin());
			cevap.getCevapSecenekList().add(cevap.getEkranCevapSecenek());
			cevap.setEkranCevapSecenek(new CevapSecenek());
		}
		if (seciliSoru.getSoruTipi().getI18nKey() == "Çoktan Çok Seçmeli Soru") {
			for (Secenek secenek : secilenSecenekList) {
				cevap.getEkranCevapSecenek().setSecenek(secenek);
				cevap.getEkranCevapSecenek().setSecildi(true);
				if (cevap.getMetin() == null) {
					cevap.setMetin(secenek.getMetin());
				} else {
					cevap.setMetin(cevap.getMetin() + ", " + secenek.getMetin());
				}
				cevap.getCevapSecenekList().add(cevap.getEkranCevapSecenek());
				cevap.setEkranCevapSecenek(new CevapSecenek());
			}
		}
		if (seciliSoru.getSoruTipi().getI18nKey() == "Boþluk Doldurmalý Soru") {
			for (Secenek secenek : seciliSoru.getSeceneklList()) {
				if (cevap.getMetin() == null) {
					cevap.setMetin(secenek.getMetin());
				} else {
					cevap.setMetin(cevap.getMetin() + secenek.getMetin());
				}
			}
			for (Bosluk bosluk : cevap.getEkranCevapSecenek().getBoslukList()) {
				cevap.setMetin(cevap.getMetin().replaceFirst("___",
						bosluk.getMetin()));
			}
			cevap.getCevapSecenekList().add(cevap.getEkranCevapSecenek());
			cevap.setEkranCevapSecenek(new CevapSecenek());
		}
		if (seciliSoru.getSoruTipi().getI18nKey() == "Çoktan Seçmeli Boþluk Doldurmalý Soru") {
			if (cevap.getEkranCevapSecenek().getSecenek().getAciklama() != null) {
				if (cevap.getMetin() == null) {
					cevap.setMetin(cevap.getEkranCevapSecenek().getSecenek()
							.getMetin()
							+ "("
							+ cevap.getEkranCevapSecenek().getSecenek()
									.getAciklama() + ")");
				} else {
					cevap.setMetin(cevap.getEkranCevapSecenek().getSecenek()
							.getMetin()
							+ "("
							+ cevap.getEkranCevapSecenek().getSecenek()
									.getAciklama() + ")");
				}
				for (Bosluk bosluk : cevap.getEkranCevapSecenek()
						.getBoslukList()) {
					cevap.setMetin(cevap.getMetin().replaceFirst("___",
							bosluk.getMetin()));
				}
			} else {
				cevap.setMetin(cevap.getEkranCevapSecenek().getSecenek()
						.getMetin());
			}

			cevap.getCevapSecenekList().add(cevap.getEkranCevapSecenek());
			cevap.setEkranCevapSecenek(new CevapSecenek());
		}
		if (seciliSoru.getSoruTipi().getI18nKey() == "Çoktan Çok Seçmeli Text Soru") {
			for (Secenek secenek : secilenSecenekList) {
				cevap.getEkranCevapSecenek().setSecenek(secenek);
				cevap.getEkranCevapSecenek().setSecildi(true);
				if (cevap.getMetin() == null) {
					if (secenek.getAciklama() != null) {
						secenek.setMetin(secenek.getMetin() + "("
								+ secenek.getAciklama() + ")");
					}
					cevap.setMetin(secenek.getMetin());
				} else {
					if (secenek.getAciklama() != null) {
						secenek.setMetin(secenek.getMetin() + "("
								+ secenek.getAciklama() + ")");
					}
					cevap.setMetin(cevap.getMetin() + ", " + secenek.getMetin());
				}
				cevap.getCevapSecenekList().add(cevap.getEkranCevapSecenek());
				cevap.getEkranCevapSecenek().setSecildi(false);
			}
		}

		try {
			if (guncelle) {
				for (Cevap cevap_k : rapor.getCevapList()) {
					if (cevap_k.getSoru() == seciliSoru) {
						if (cevap_k.getId() != null) {
							cevap.setId(cevap_k.getId());
						}
						for (CevapSecenek cevapSecenek_k : cevap_k
								.getCevapSecenekList()) {
							if (cevapSecenek_k.getId() != null) {
								cevapSecenekDao.CevapSecenekSil(cevapSecenek_k
										.getId());
							}
						}
						rapor.getCevapList().remove(cevap_k);
					}
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}

		rapor.getCevapList().add(cevap);
		cevap = new Cevap();
	}

	public String cevaplariGetir(Soru satir) {
		String satirCevabi = "";
		for (Cevap cevap_sc : rapor.getCevapList()) {
			if (cevap_sc.getSoru().getId() == satir.getId()) {
				satirCevabi += cevap_sc.getMetin();
			}
		}
		return satirCevabi;
	}

	public void eksiklikEkle() {
		eksiklik.setRapor(rapor);
		rapor.getEksiklikList().add(eksiklik);
		eksiklik = new Eksiklik();
	}

	public void onlemEkle() {
		onlem.setRapor(rapor);
		rapor.getOnlemList().add(onlem);
		onlem = new Onlem();
	}

	public void kaydet() {

		if (rapor.getId() == null) {
			raporDao.raporKaydet(rapor);
		}

		isEmri.setRapor(rapor);
		isEmri.setIsEmriDurumu(IsEmriDurumu.KONTROLEGONDERILDI);
		isEmri = isEmriDao.isEmriGuncelle(isEmri);

		for (Cevap cevap_f : rapor.getCevapList()) {
			cevap_f.setRapor(rapor);
			if (cevap_f.getId() == null) {
				cevapDao.CevapKaydet(cevap_f);
			} else {
				cevapDao.CevapGuncelle(cevap_f);
			}
			for (CevapSecenek cevapSecenek_f : cevap_f.getCevapSecenekList()) {
				cevapSecenek_f.setCevap(cevap_f);
				if (cevapSecenek_f.getId() == null) {
					cevapSecenekDao.CevapSecenekKaydet(cevapSecenek_f);
				}
			}
		}
		for (Onlem onlem_f : rapor.getOnlemList()) {
			if (onlem_f.getId() == null) {
				onlemDao.onlemKaydet(onlem_f);
			} else {
				onlem_f = onlemDao.onlemGuncelle(onlem_f);
			}
		}
		for (Eksiklik eksiklik_f : rapor.getEksiklikList()) {
			if (eksiklik_f.getId() == null) {
				eksiklikDao.eksiklikKaydet(eksiklik_f);
			} else {
				eksiklik_f = eksiklikDao.eksiklikGuncelle(eksiklik_f);
			}
		}
		if (rapor.getId() != null) {
			rapor = raporDao.raporGuncelle(rapor);
		}
	}

	public List<Secenek> getSecilenSecenekList() {
		return secilenSecenekList;
	}

	public void setSecilenSecenekList(List<Secenek> secilenSecenekList) {
		this.secilenSecenekList = secilenSecenekList;
	}

	public Rapor getRapor() {
		return rapor;
	}

	public void setRapor(Rapor rapor) {
		this.rapor = rapor;
	}

	public boolean isCheckTable() {
		return checkTable;
	}

	public void setCheckTable(boolean checkTable) {
		this.checkTable = checkTable;
	}

	public boolean isRadioTable() {
		return radioTable;
	}

	public void setRadioTable(boolean radioTable) {
		this.radioTable = radioTable;
	}

	public List<Bolum> getBolumList() {
		return bolumList;
	}

	public void setBolumList(List<Bolum> bolumList) {
		this.bolumList = bolumList;
	}

	public Bolum getSeciliBolum() {
		return seciliBolum;
	}

	public void setSeciliBolum(Bolum seciliBolum) {
		this.seciliBolum = seciliBolum;
	}

	public boolean isBoslukDoldurma() {
		return boslukDoldurma;
	}

	public void setBoslukDoldurma(boolean boslukDoldurma) {
		this.boslukDoldurma = boslukDoldurma;
	}

	public boolean isKisi() {
		return kisi;
	}

	public void setKisi(boolean kisi) {
		this.kisi = kisi;
	}

	public IsEmri getIsEmri() {
		return isEmri;
	}

	public void setIsEmri(IsEmri isEmri) {
		this.isEmri = isEmri;
	}

	public Eksiklik getEksiklik() {
		return eksiklik;
	}

	public void setEksiklik(Eksiklik eksiklik) {
		this.eksiklik = eksiklik;
	}

	public Onlem getOnlem() {
		return onlem;
	}

	public void setOnlem(Onlem onlem) {
		this.onlem = onlem;
	}

	public boolean isGuncelle() {
		return guncelle;
	}

	public void setGuncelle(boolean guncelle) {
		this.guncelle = guncelle;
	}

	public Cevap getCevap() {
		return cevap;
	}

	public void setCevap(Cevap cevap) {
		this.cevap = cevap;
	}

	public boolean isSoruUyari() {
		return soruUyari;
	}

	public void setSoruUyari(boolean soruUyari) {
		this.soruUyari = soruUyari;
	}

	public boolean isCevapMetni() {
		return cevapMetni;
	}

	public void setCevapMetni(boolean cevapMetni) {
		this.cevapMetni = cevapMetni;
	}

	public Soru getSeciliSoru() {
		return seciliSoru;
	}

	public void setSeciliSoru(Soru seciliSoru) {
		this.seciliSoru = seciliSoru;
	}

	public boolean isAciklamaVar() {
		return aciklamaVar;
	}

	public void setAciklamaVar(boolean aciklamaVar) {
		this.aciklamaVar = aciklamaVar;
	}

}
