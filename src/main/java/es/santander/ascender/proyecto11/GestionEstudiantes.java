package es.santander.ascender.proyecto11;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementación de la interfaz IGestionEstudiantes para gestionar
 * estudiantes y sus calificaciones.
 */
public class GestionEstudiantes implements IGestionEstudiantes {

    private final Map<String, Integer> estudiantes;

    /**
     * Constructor de la clase. Inicializa la estructura de datos para almacenar los estudiantes.
     */
    public GestionEstudiantes() {
        this.estudiantes = new HashMap<>();
    }

    @Override
    public boolean agregarEstudiante(String nombre, int calificacion) {
         // Normalizar el nombre antes de agregarlo
         String nombreNormalizado = normalizarNombre(nombre);

        if (estudiantes.containsKey(nombreNormalizado)) {
            return false; // El estudiante ya existe, no se puede agregar de nuevo
        }
        estudiantes.put(nombreNormalizado, calificacion);
        return true;
    }

    @Override
    public Integer obtenerCalificacion(String nombre) {
         // Normalizar el nombre antes de buscarlo
         String nombreNormalizado = normalizarNombre(nombre);

        return estudiantes.get(nombreNormalizado); // Devuelve la calificación o null si no existe
    }

    @Override
    public Map<String, Integer> obtenerEstudiantesYCalificaciones() {
        return new HashMap<>(estudiantes); // Devuelve una copia del mapa para evitar modificaciones externas
    }

    @Override
    public boolean existeEstudiante(String nombre) {
        // Normalizar el nombre antes de verificar existencia
        String nombreNormalizado = normalizarNombre(nombre);

        return estudiantes.containsKey(nombreNormalizado); // Verifica si el estudiante existe
    }

    @Override
    public boolean eliminarEstudiante(String nombre) {
        // Normalizar el nombre antes de eliminar
        String nombreNormalizado = normalizarNombre(nombre);

        if (estudiantes.containsKey(nombreNormalizado)) {
            estudiantes.remove(nombreNormalizado); // Elimina el estudiante si existe
            return true;
        }
        return false;
    }

    @Override
    public void agregarEstudiantes(Set<String> nuevosEstudiantes, Map<String, Integer> nuevasCalificaciones) {
        for (String estudiante : nuevosEstudiantes) {
            String estudianteNormalizado = normalizarNombre(estudiante);
            if (nuevasCalificaciones.containsKey(estudianteNormalizado)) {
                estudiantes.put(estudianteNormalizado, nuevasCalificaciones.get(estudiante)); // Agrega o actualiza la calificación
            }
        }
    }

      private String normalizarNombre(String nombre) {
        // Convertir a minúsculas
        nombre = nombre.toLowerCase();

        // Eliminar acentos
        nombre = Normalizer.normalize(nombre, Normalizer.Form.NFD);
        nombre = nombre.replaceAll("[^\\p{ASCII}]", ""); // Elimina caracteres no ASCII (acentos)

        return nombre;
    }
}
