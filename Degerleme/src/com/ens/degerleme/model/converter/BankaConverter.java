package com.ens.degerleme.model.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import com.ens.degerleme.dao.BankaDao;
import com.ens.degerleme.model.entity.Banka;

@FacesConverter(value = "bankaConverter")
public class BankaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		InitialContext ic;
		BankaDao bankaDao = null;
		try {
			ic = new InitialContext();
			bankaDao = (BankaDao) ic.lookup("java:global/Degerleme/BankaDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value == null || value.equals("")) {
			return null;
		}
		Banka result = (Banka) bankaDao.bankaGetir(Integer.parseInt(value));

		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		}
		return "" + ((Banka) obj).getId();
	}

}
