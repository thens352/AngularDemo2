package com.ens.degerleme.model.converter;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.selectoneradio.SelectOneRadio;

import com.ens.degerleme.model.entity.CevapSecenek;

@FacesConverter(value = "cevapSecenekConverter")
@SessionScoped
public class CevapSecenekConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		CevapSecenek entity = null;
		if (value == null) {
			entity = null;
		} else if (value != null && value.toString().equals("0")) {
			entity = null;
		} else {
			List<UIComponent> selectItems = ((SelectOneRadio) component).getChildren();
			for (UIComponent uiComponent : selectItems) {
				UISelectItems item = (UISelectItems) uiComponent;
				@SuppressWarnings("unchecked")
				List<CevapSecenek> cevapSecenekList = (List<CevapSecenek>) item.getValue();
				for (CevapSecenek cevapSecenek : cevapSecenekList)
					if (cevapSecenek.getSecenek().getId().equals(new Integer(value))) {
						entity = cevapSecenek;
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
		return ((CevapSecenek) value).getSecenek().getId().toString();

	}

}
