import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Creo un archivo de texto con el objeto File
		File file = new File("C:\\Users\\7tecnica\\Desktop\\texto.txt");
		// Defino la referencia de un Scanner
		Scanner sc;

		try {
			// Creo el Scanner para leer el archivo identificado en file
			sc = new Scanner(file);

			// hasNextLine() devuelve un valor booleano, indicando si existe o
			// no una siguiente línea a leer
			while (sc.hasNextLine())
				System.out.println(sc.nextLine());

			// close() cierra la lectura del archivo
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Otra alternativa, es utilizar un BufferedReader
		BufferedReader br = null;

		try {
			// Creo el objeto en base al file, pero previamente lo tengo que
			// leer a través de un FileReader
			br = new BufferedReader(new FileReader(file));

			// readLine() lee la siguiente línea, en caso que no haya nada para
			// leer devuelve null
			String linea;

			// De esta forma creo mi diccionario en java
			HashMap<String, Integer> palabrasyrepeticiones = new HashMap<String, Integer>();
			while ((linea = br.readLine()) != null) {

				System.out.println("lineacompleta: " + linea);
				for (String palabra : linea.split(" ")) {// Divide las palabras
					System.out.println("palabra: " + palabra);

					if (palabrasyrepeticiones.containsKey(palabra.toLowerCase())) {
						// ya existe
						int valorActual = palabrasyrepeticiones.get(palabra.toLowerCase());
						valorActual++;
						palabrasyrepeticiones.put(palabra.toLowerCase(), valorActual);
					} else {
						// nuevo
						palabrasyrepeticiones.put(palabra.toLowerCase(), 1);
					}

				}
			}

			// Muestro las palabras sin ordenar, tal como lo fui identificando
			System.out.println("SIN ORDENAR");
			for (Entry<String, Integer> palabra : palabrasyrepeticiones.entrySet()) {
				System.out.println("palabra: " + palabra.getKey() + " = " + palabra.getValue());
			}

			// Armo una lista con los registros, para poder ordenarlos
			List<Entry<String, Integer>> list = new ArrayList<>(palabrasyrepeticiones.entrySet());
			// Los ordeno comparando el valor
			list.sort(Entry.comparingByValue());

			// Armo un mapa con los valores ordenados
			Map<String, Integer> result = new LinkedHashMap<>();
			for (Entry<String, Integer> entry : list) {
				result.put(entry.getKey(), entry.getValue());
			}

			System.out.println("ORDENADO");
			List<String> reverseOrderedKeys = new ArrayList<String>(result.keySet());
			Collections.reverse(reverseOrderedKeys);
			
			// Defino la cantidad de palabras a mostrar
			int top = 1;
			int i = 0;
			
			for (String key : reverseOrderedKeys) {
				if (i < top) {
					System.out.println("palabra: " + key + " = " + result.get(key));
					i++;
				}
				else {
					break;
				}
			}

			// cierro el archivo
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}