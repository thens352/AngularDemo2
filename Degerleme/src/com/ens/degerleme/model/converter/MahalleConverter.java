package com.ens.degerleme.model.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import com.ens.degerleme.dao.MahalleDao;
import com.ens.degerleme.model.entity.Mahalle;

@FacesConverter(value = "mahalleConverter")
public class MahalleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		InitialContext ic;
		MahalleDao mahalleDao = null;
		try {
			ic = new InitialContext();
			mahalleDao = (MahalleDao) ic.lookup("java:global/Degerleme/MahalleDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(value==null || value.equals("")){
			return null;
		}

		Mahalle result = (Mahalle) mahalleDao.mahalleGetirById(Integer.parseInt(value));

		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		
		if(obj==null || obj.equals("")){
			return null;
		}
		return ""+ ((Mahalle) obj).getId();
	}

}
