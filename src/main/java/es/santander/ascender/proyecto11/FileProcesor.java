package es.santander.ascender.proyecto11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Esta clase proporciona métodos para leer, escribir y eliminar vovales de un
 * archivo de texto.
 * Implementa la interfaz {@link IFileProcesor}.
 */
public class FileProcesor implements IFileProcesor {

    /**
     * Lee el contenido de un archivo y lo devuelve como una cadena.
     *
     * @param filePath Ruta del archivo a leer.
     * @return El contenido del archivo como una cadena de texto.
     * @throws Exception Si ocurre un error al leer el archivo (por ejemplo, archivo
     *                   no encontrado o problemas de acceso).
     */
    @Override
    public String leerFile(String filePath) throws Exception {
        try {
            // Lee todo el contenido del archivo como bytes y lo convierte en una cadena
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            // Lanza una excepción personalizada si ocurre un error
            throw new Exception("Error al leer el archivo: " + filePath, e);
        }
    }

    /**
     * Elimina todas las vocales (tanto mayúsculas como minúsculas con o sin acento)
     * de la cadena proporcionada.
     *
     * @param input La cadena de texto de la cual eliminar las vocales.
     * @return La cadena resultante sin las vocales.
     */
    @Override
    public String eliminarVocales(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        // Utiliza una expresión regular para eliminar las vocales
        return input.replaceAll("[aeiouAEIOUáéíóúÁÉÍÓÚ]", "");
    }

    /**
     * Escribe contenido en un archivo especificado por la ruta proporcionada.
     *
     * @param filePath La ruta del archivo en el que se debe escribir el contenido.
     * @param content  El contenido que se debe escribir en el archivo.
     * @throws Exception Si ocurre un error al escribir en el archivo.
     */
    @Override
    public void escribirAFile(String filePath, String content) throws Exception {
        try {
            // Escribe el contenido en el archivo
            Files.write(Paths.get(filePath), content.getBytes());
        } catch (IOException e) {
            // Lanza una excepción personalizada si ocurre un error
            throw new Exception("Error al escribir en el archivo: " + filePath, e);
        }
    }
}
