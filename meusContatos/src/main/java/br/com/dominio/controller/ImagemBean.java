package br.com.dominio.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class ImagemBean {
	
	
	@ManagedProperty("#{param.arquivo}")
	private String arquivo;
	
	private StreamedContent foto;
	
	public String getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
	public StreamedContent getFoto() throws IOException {
		if(arquivo == null || arquivo.isEmpty()){
			//Path path = Paths.get("D:/lixo/branco.png");
			//InputStream stream = Files.newInputStream(path);
			//foto = new DefaultStreamedContent(stream);			
			return new DefaultStreamedContent();			
		}else{
			
			String caminho = "D:\\Lixo\\" + arquivo;
			
			Path path = Paths.get(caminho);
			
			InputStream stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		}
		return foto;
	}
	
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
}

