package com.bionic.edu;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import com.bionic.edu.entities.Category;

@FacesConverter(value = "categoryDataTableConverter")
public class CategoryDataTableConverter extends CategoryConverter{
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Category && ((Category) value).getCategory_id() == 0) {
            return String.valueOf(((Category) value).hashCode());
        }
        return super.getAsString(context, component, value);
    }
}
