package com.ens.degerleme.model.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import com.ens.degerleme.dao.IlceDao;
import com.ens.degerleme.model.entity.Ilce;

@FacesConverter(value = "ilceConverter")
public class IlceConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		InitialContext ic;
		IlceDao ilceDao = null;
		try {
			ic = new InitialContext();
			ilceDao = (IlceDao) ic.lookup("java:global/Degerleme/IlceDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(value==null || value.equals("")){
			return null;
		}
		Ilce result = (Ilce) ilceDao.ilceGetirById(Integer.parseInt(value));

		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		
		if(obj==null || obj.equals("")){
			return null;
		}
		return ""+ ((Ilce) obj).getId();
	}

}
