package com.ens.degerleme.model.converter;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ens.degerleme.model.entity.Bolum;

@FacesConverter(value = "bolumConverter")
@SessionScoped
public class BolumConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		Bolum entity = null;
		if (value == null) {
			entity = null;
		} else if (value != null && value.toString().equals("0")) {
			entity = null;
		} else {
			List<UIComponent> selectItems = ((HtmlSelectOneMenu) component).getChildren();
			for (UIComponent uiComponent : selectItems) {
				UISelectItems item = (UISelectItems) uiComponent;
				@SuppressWarnings("unchecked")
				List<Bolum> bolumList = (List<Bolum>) item.getValue();
				for (Bolum bolum : bolumList)
					if (bolum.getId().equals(new Integer(value))) {
						entity = bolum;
						break;
					}
			}
		}

		return entity;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		if (value.toString().equals("0")) {
			return "";
		}
		return ((Bolum) value).getId().toString();

	}

}
