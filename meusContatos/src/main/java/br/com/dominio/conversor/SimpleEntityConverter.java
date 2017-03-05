package br.com.dominio.conversor;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dominio.modelo.BaseEntity;

@FacesConverter(value = "simpleEntityConverter")
public class SimpleEntityConverter implements Converter {  
	  
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
    	
    	System.out.println("SimpleEntityConverter - getAsObject - valor: "+value);
    	
        if (value != null) {  

        	System.out.println("SimpleEntityConverter - getAsObject - Retorna o Objeto ");        	
        	
        	return this.getAttributesFrom(component).get(value);  
        }  
        return null;  
    }  
  
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {  

    	System.out.println("SimpleEntityConverter - getAsString - Inicio ");
    	    	
        if (value != null && !"".equals(value)) {  
  
        	System.out.println("SimpleEntityConverter - getAsString -  Classe: " + value.getClass().getSimpleName());
        	
            BaseEntity entity = (BaseEntity) value;  
  
            // adiciona item como atributo do componente  
            this.addAttribute(component, entity);  
  
            Integer codigo = entity.getId();  

            
            if (codigo != null) {  
            	System.out.println("SimpleEntityConverter - getAsString - valor: "+String.valueOf(codigo));
                return String.valueOf(codigo);  
            }  
        }  
  
    	System.out.println("SimpleEntityConverter - getAsString - Valor Nulo ");
                
        return (String) value;  
    }  
  
    protected void addAttribute(UIComponent component, BaseEntity o) {  
        String key = o.getId().toString(); // codigo da empresa como chave neste caso  

    	System.out.println("SimpleEntityConverter - addAttribute - key " + key);
        
        this.getAttributesFrom(component).put(key, o);  
    }  
  
    protected Map<String, Object> getAttributesFrom(UIComponent component) {  
        return component.getAttributes();  
               
    }  
  
}  
