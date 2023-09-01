package view;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;  //manejo de excepciones
import java.io.InputStreamReader;

/**
 *
 * @author Samir
 */
public class View {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String readString(String message) {
        showMessage(message);
        try {
            String input = br.readLine().trim();
            if (input.isEmpty()) {
                throw new Exception();
            }
            return input;

        } catch (Exception e) {
            showMessage(e.toString());
            return readString(message);
        }
    }

    public String readEmail(String message) {
        try {
            String input = readString(message);
            if (input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
                return input;
            }
            throw new Exception("Ingrese una direcciÃ³n de correo vÃ¡lida. Intente nuevamente.");
        } catch (Exception e) {
            showMessage(e.toString());
            return readEmail(message);
        }
    }

    public String readStringText(String message) {
        try {
            String input = readString(message);
            if (input.matches("^[a-zA-Z\\s]+$")) {
                return input;
            }
            throw new Exception("Caracteres no validos. Intente nuevamente.");
        } catch (Exception e) {
            showMessage(e.toString());
            return readStringText(message);
        }
    }

    public int readInt(String message) {
        try {
            return Integer.parseInt(readString(message));
        } catch (NumberFormatException e) {
            showMessage("Solamente se permiten numeros.");
            return readInt(message);
        }
    }

    public int readIndex(String message, int maximo) {
        int index;
        try {
            index = readInt(message);
            if (!(index >= 0 && index < maximo)) {
                throw new Exception("Índice no válido. Intente nuevamente.");
            }
            return index;
        } catch (Exception e) {
            showMessage(e.toString());
            return readIndex(message, maximo);
        }
    }
    
    public void menuEstudiante() {
        this.showMessage("1. Registrar (Ver, registrar, modificar, eliminar) ");
        this.showMessage("2. Matricular (Programa Academico--Asignatura) ");
        this.showMessage("0.Salir");
    }

    public void menuEstudianteResgitro() {
        this.showMessage("=== LISTA ESTUDIANTES ===");
        this.showMessage("1. Ver estudiantes registrados");
        this.showMessage("2. Registrar un nuevo estudiante");
        this.showMessage("3. Modificar registro de estudiante");
        this.showMessage("4. Eliminar registro de estudiante");
        this.showMessage("0. Salir");
    }

    public void menuEstudianteMatricula() {
        this.showMessage("==== LISTA MATRICULA===");
        this.showMessage("1. Matricular estudiantes en programa");
        this.showMessage("2. Ver estudiantes matriculados en programa");
        this.showMessage("3. Eliminar estudiantes matricualados en programa");
        this.showMessage("4. Matricular estudiantes en asignatura");
        this.showMessage("5. Ver estudiantes matriculados en asignatura");
        this.showMessage("6. Eliminar estudiantes matricualados en asignatura");
        this.showMessage("0. Salir");
    }

    public void menuProgramaAcademico() {
            this.showMessage("=== LISTA PROGRAMA ACEDEMICO ===");
        this.showMessage("1. Crear programa academico");
        this.showMessage("2. Modificar programa academico");
        this.showMessage("3. Eliminar programa acadademico");
        this.showMessage("4. Ver programas acadademicos registrados");
        this.showMessage("0. Salir");
    }

    public void menuAsignaturas() {
            this.showMessage("=== LISTA ASIGNATURA ===");
        this.showMessage("1. Crear asignatura");
        this.showMessage("2. Modificar asignatura");
        this.showMessage("3. Eliminar asignatura");
        this.showMessage("4. Ver asignaturas registradas");
        this.showMessage("0. Salir");
    }

    public void mostrarMenu() {
        this.showMessage("=== BIENVENIDO A la UPTC ===");
        this.showMessage("1. Estudiantes.");
        this.showMessage("2. Programa Academico.");
        this.showMessage("3. Asignaturas");
        this.showMessage("0. Salir");
    }

    public int leerOpcion(int max) {
        int opcion;
        while (true) {
            try {
                this.showMessage("Ingrese una opción: ");
                String input = br.readLine().trim();           //para leer la entrada del usuario espacios en blanco adicionales y saltos de lÃ­nea vacÃ­os
                if (!input.isEmpty()) {
                    opcion = Integer.parseInt(input);
                    if (opcion >= 0 && opcion <= max) {
                        break;
                    } else {
                        this.showMessage("Opción no válida. Intente nuevamente.");
                    }
                } else {
                    this.showMessage("No se permiten campos vací­os. Intente nuevamente.");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
        return opcion;
    }
}
