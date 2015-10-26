package com.ens.degerleme.model.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import com.ens.degerleme.dao.BankaSubesiDao;
import com.ens.degerleme.model.entity.BankaSubesi;

@FacesConverter(value="bankaSubesiConverter")
public class BankaSubesiConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		InitialContext ic;
		BankaSubesiDao bankaSubesiDao=null;
		try {
			ic = new InitialContext();
			bankaSubesiDao = (BankaSubesiDao) ic.lookup("java:global/Degerleme/BankaSubesiDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value == null || value.equals("")) {
			return null;
		}
		BankaSubesi result = (BankaSubesi) bankaSubesiDao.bankaSubesiGetir(Integer.parseInt(value));

		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		}
		return "" + ((BankaSubesi) obj).getId();
	}

}
