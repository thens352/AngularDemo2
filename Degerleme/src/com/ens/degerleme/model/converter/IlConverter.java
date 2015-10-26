package com.ens.degerleme.model.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import com.ens.degerleme.dao.IlDao;
import com.ens.degerleme.model.entity.Il;

@FacesConverter(value = "ilConverter")
public class IlConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		InitialContext ic;
		IlDao ilDao = null;
		try {
			ic = new InitialContext();
			ilDao = (IlDao) ic.lookup("java:global/Degerleme/IlDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value == null || value.equals("")) {
			return null;
		}
		Il result = (Il) ilDao.ilGetirById(Integer.parseInt(value));

		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		}
		return "" + ((Il) obj).getId();
	}

}
