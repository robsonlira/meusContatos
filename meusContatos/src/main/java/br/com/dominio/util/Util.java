package br.com.dominio.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Pattern;

public class Util {

	public static String getConfiguration(String configurationKey) {

		final ResourceBundle bundle = ResourceBundle.getBundle("appconf");

		return bundle.getString(configurationKey);
	}
	

	public static String generateGUID() {

		UUID GUID = UUID.randomUUID();
		String key = GUID.toString();
		return key;
	}

	public static Path criarDiretorio(String diretorio) throws Exception {
		return Files.createDirectories(Paths.get(diretorio));
	}

	public static Path criarArquivo(String arquivo) throws Exception {
		return Files.createFile(Paths.get(arquivo));
	}

	public static void copiandoArquivo(Path arquivoOrigem, String arquivoDestino) {

		try {
			Path target = criarArquivo(arquivoDestino);

			copiandoArquivo(arquivoOrigem, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void copiandoArquivo(Path arquivoOrigem, Path arquivoDestino) throws Exception {
		Files.copy(arquivoOrigem, arquivoDestino, StandardCopyOption.REPLACE_EXISTING);
	}

	public static void deletandoArquivo(Path arquivo) throws Exception {
		Files.delete(arquivo);
	}

	public static String semAcento(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

}
