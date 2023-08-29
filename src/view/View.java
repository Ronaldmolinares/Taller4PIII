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
            throw new Exception("Ingrese una dirección de correo válida. Intente nuevamente.");
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
            showMessage(e.toString());
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
    
    public void mostrarMenu() {                         // mostrar opciones menú
       this.showMessage("=== MENÚ - LISTA ESTUDIANTES PROGRAMACIÓN III ===");
       this.showMessage("1. Ver estudiantes registrados");
       this.showMessage("2. Registrar un nuevo estudiante");
       this.showMessage("3. Modificar registro de estudiante");
       this.showMessage("4. Eliminar registro de estudiante");
       this.showMessage("5. Crear programa académico");
       this.showMessage("6. Modificar programa academico");
       this.showMessage("7. Eliminar programa académico");
       this.showMessage("8. Ver programas académicos registrados");
       this.showMessage("9. Crear asignatura");
       this.showMessage("10. Modificar asignatura");
       this.showMessage("11. Eliminar asignatura");
       this.showMessage("12. Ver asignaturas registradas");
       this.showMessage("13. Matricular estudiantes en programa");
       this.showMessage("14. Ver matricula de estudiantes en programa");
       this.showMessage("15. Eliminar estudiantes matricualados en programa");
       this.showMessage("16. Matricular estudiantes en asignatura");
       this.showMessage("17. Ver matricula de estudiantes en asignatura");
       this.showMessage("18. Eliminar estudiantes matricualados en asignatura");
       this.showMessage("0. Salir");
    }

    public int leerOpcion(int max) {                               // método para leer tipo de dato - opción menú
        int opcion;
        while (true) {
            try {
                System.out.print("Ingrese una opción: ");
                String input = br.readLine().trim();           //para leer la entrada del usuario espacios en blanco adicionales y saltos de línea vacíos
                if (!input.isEmpty()) {
                    opcion = Integer.parseInt(input);
                    if (opcion >= 0 && opcion <= max) {
                        break;
                    } else {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                } else {
                    System.out.println("No se permiten campos vacíos. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
        return opcion;
    }
    
        public String leerCodigoNumerico() {
        String input;
        while (true) {
            try {
                input = br.readLine().trim();
                if (!input.isEmpty() && input.matches("^[0-9]+$")) { // Verifica que la entrada contenga solo números
                    return input;
                }
                System.out.println("Ingrese un valor válido (solo números). Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
    }

}
