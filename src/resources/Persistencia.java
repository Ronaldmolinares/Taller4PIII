package resources;

import model.*;
import view.View;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Persistencia {
    
    private View view = new View();
    private Universidad uni = new Universidad();
    
    public void cargarEstudiantesDesdeArchivo(ArrayList<Estudiante> estudiantes, String ruta) {
        estudiantes.clear(); // Limpiamos la lista actual antes de cargar los datos, asegura que los estudiantes del archivo sean los únicos en la lista después de la carga

        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            // Abre un bloque try-with-resources que se encarga de cerrar automáticamente el BufferedReader al final
            // La clase BufferedReader se utiliza para leer caracteres de un flujo de entrada (archivo)
            String line;
            while ((line = reader.readLine()) != null) {
                // Lee cada línea del archivo hasta llegar al final
                String[] parts = line.split(",");              //para dividir una cadena (string) en varias subcadenas (tokens)basadas en un delimitador especificado ","             
                // Divide la línea en partes utilizando la coma como separador
                if (parts.length == 3) {
                    // Si la línea se dividió en 4 partes (atributos esperados)
                    estudiantes.add(new Estudiante(parts[0], parts[1], parts[2]));
                    // Crea un nuevo estudiante utilizando las partes y agrega el estudiante a la lista
                }
            }
            view.showMessage("Datos de estudiantes cargados desde el archivo.");
            // Muestra un mensaje indicando que los datos se han cargado exitosamente desde el archivo
            // verEstudiantesRegistrados();  (Este comentario indica que aquí podrías llamar a verEstudiantesRegistrados() si deseas mostrar los estudiantes después de cargarlos)
        } catch (IOException e) {
            view.showMessage("No se pudo cargar los datos de estudiantes desde el archivo.");
            // Captura y maneja cualquier excepción de tipo IOException que pueda ocurrir durante la lectura del archivo
            // Muestra un mensaje de error si se produce una excepción
        }
    }
    
        public void guardarEstudiantesEnArchivo(String ruta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            // Abre un bloque try-with-resources que se encarga de cerrar automáticamente el BufferedWriter al final
            // La clase BufferedWriter se utiliza para escribir caracteres en un flujo de salida
            for (Estudiante estudiante : uni.getEstudiantes()) {
                // Itera sobre la lista de estudiantes para guardar cada uno en el archivo
                writer.write(
                        estudiante.getNombresApellidos() + ","
                        + estudiante.getCodigoEstudiante() + ","
                        + estudiante.getCorreoElectronico());
                // Escribe los datos del estudiante en una línea del archivo, separados por comas
                // Cada estudiante se representa como una LÍNEA en el archivo
                writer.newLine();
                // Agrega una nueva línea para separar los datos del próximo estudiante en el archivo
            }
            System.out.println("Datos de estudiantes guardados en el archivo.");
            // Muestra un mensaje indicando que los datos se han guardado exitosamente en el archivo
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de estudiantes en el archivo.");
            // Captura y maneja cualquier excepción de tipo IOException que pueda ocurrir durante la escritura en el archivo
            // Muestra un mensaje de error si se produce una excepción
        }
    }
}
