package com.ens.degerleme.model.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import com.ens.degerleme.dao.UserDao;
import com.ens.degerleme.model.entity.User;

@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		InitialContext ic;
		UserDao userDao = null;
		try {
			ic = new InitialContext();
			userDao = (UserDao) ic.lookup("java:global/Degerleme/UserDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value == null || value.equals("")) {
			return null;
		}
		User result = (User) userDao.userGetir(Integer.parseInt(value));

		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		}
		return "" + ((User) obj).getId();
	}

}
