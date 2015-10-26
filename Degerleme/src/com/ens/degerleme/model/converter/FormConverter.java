package com.ens.degerleme.model.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import com.ens.degerleme.dao.FormDao;
import com.ens.degerleme.model.entity.Form;

@FacesConverter(value = "formConverter")
public class FormConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		InitialContext ic;
		FormDao formDao = null;
		try {
			ic = new InitialContext();
			formDao = (FormDao) ic.lookup("java:global/Degerleme/FormDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value == null || value.equals("")) {
			return null;
		}
		Form result = (Form) formDao.formGetir(Integer.parseInt(value));

		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		}
		return "" + ((Form) obj).getId();
	}

}
