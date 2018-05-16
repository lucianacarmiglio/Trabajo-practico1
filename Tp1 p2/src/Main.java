import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	public static char[] leerArchivo(String archivo) {

		// Creamos un String que va a contener todo el texto del archivo
		String texto = "";

		try {
			// Creamos un archivo FileReader que obtiene lo que tenga el archivo
			FileReader lector = new FileReader(archivo);

			// El contenido de lector se guarda en un BufferedReader
			BufferedReader contenido = new BufferedReader(lector);

			// Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y
			// lo mostramos
			String linea;
			while ((linea = contenido.readLine()) != null) {
				System.out.println(linea);
				texto = texto + linea;
			}
		}

		// Si se causa un error al leer cae aqui
		catch (Exception e) {
			System.out.println("Error al leer");
		}

		return texto.toCharArray();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char[] texto = leerArchivo("C:\\Users\\7tecnica\\Desktop\\Texto.txt");
		texto = modificarMayusculas(texto);
		String textoModif = new String(texto);
		System.out.println("Mayusculas: \n" + textoModif);
	}

	private static char[] modificarMayusculas(char[] texto) {
		int i = 0;
		if (texto[0] == ' ') {
			while (texto[i] == ' ') {
				i++;
			}
			texto[i] = Character.toUpperCase(texto[i]);
		}

		for (i = 0; i < texto.length - 1; i++) {
			if (texto[i] == '.') {
				int j = i;

				while (texto[j] == '.' || texto[j] == ' ') {
					j++;
				}

				texto[j] = Character.toUpperCase(texto[j]);
			}
		}

		return texto;
	}

}