package br.com.dominio.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtil {

    /**
     * Recupera a mensagem do arquivo de internacionalização.
     * @param key Chave a ser recuperada
     * @return Mensagem
     * @throws IOException
     */
    public final static String getMessage(String key, Object... args)
	    throws IOException {
	Locale ptBr = new Locale("pt", "BR");
	ResourceBundle boundle = ResourceBundle.getBundle("resources.Messages", ptBr);
	
	String message = boundle.getString(key);
	if (message != null) {
	    message = MessageFormat.format(message, args);
	}
	return message;
    }
	
	
}
