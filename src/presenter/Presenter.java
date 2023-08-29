package presenter;


import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import view.*;

public class Presenter {

    private View view;
    private Universidad u;
    private ProgramaAcademico pa;

    public Presenter() {
        this.view = new View();

    }

    public void run() {
        ProgramaAcademico ingSistemas = new ProgramaAcademico("Ingenieria en Sistemas y Computación");
        ProgramaAcademico ingMetalurgica = new ProgramaAcademico("Metalurgía");
        ProgramaAcademico psicologia = new ProgramaAcademico("Psicologia");

        this.u = new Universidad();

        int opcion;

        do {
            view.mostrarMenu();
            opcion = view.leerOpcion(18);

            switch (opcion) {
                case 1 ->
                    verEstudiantePorPrograma();
                case 2 ->
                    registrarEstudiante();
                case 3 ->
                    modificarEstudiante();
                case 4 ->
                    eliminarEstudiante();
                case 5 -> {
                    try {
                        crearProgramaAcademico();
                    } catch (Exception ex) {
                        Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case 6 ->
                    modificarProgramaAcademico();
                case 7 ->
                    eliminarProgramaAcademico();
                case 8 ->
                    view.showMessage(u.verProgramasAcademicos());
                case 9 ->
                    crearAsignatura();
                case 10 ->
                    modificarAsignatura();
                case 11 ->
                    eliminarAsignatura();
                case 12 ->
                    verAsignaturasRegistradas();
                case 13 -> {
                    view.showMessage(" " + u.verEstudiantesRegistrados());
                    matricularEstudiante();
                }
                case 14 ->
                    view.showMessage(u.mostrarEstudiantesPrograma());
                case 15 ->
                    eliminar_Matricula_Estudiante_programa();
                case 16 ->
                    matricularEstudiante_asigantura();
                case 17 ->
                    verEstudiantesMatriculadosEnAsignatura();
                case 18 ->
                    eliminarEstudianteDeAsignatura();
                case 0 ->
                    System.out.println("¡Hasta luego!");
                default ->
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        //reader.close(); // Cerrar el BufferedReader al finalizar
    }

    public static void main(String[] args) {
        new Presenter().run();
    }

    private void registrarEstudiante() {                             //método para registrar estudiantes

        view.showMessage("=== Registrar Estudiante ===");

        String nombresApellidos = view.readStringText("Nombres y Apellidos: ");                //valida tipo dato (solo texto)y entrada no vacía
        String codigoEstudiante = view.readString("Código de Estudiante: ");
        String correoElectronico = view.readString("Correo Electrónico: ");              //valida tipo dato (tipo correo xxx@.xxx)y entrada no vacía

        u.getEstudiantes().add(new Estudiante(nombresApellidos, codigoEstudiante, correoElectronico));
        view.showMessage("Estudiante registrado exitosamente.");
    }

    public void modificarEstudiante() {
        view.showMessage(u.verEstudiantesRegistrados());

        int index = view.readIndex("Ingrese el indice del estudiante que desea modificar: ",
                u.getEstudiantes().size());
        view.showMessage("Que desea modificar.");
        view.showMessage("""
                1. Nombres y Apellidos.
                2. Codigo de Estudiante.
                3. Correo Electronico.
                """);
        int option = view.leerOpcion(3);
        String nuevo = "";
        switch (option) {
            case 1: // nombre completo
                nuevo = view.readStringText("Ingrese el nuevo nombre y apellido: ");
                break;
            case 2: // codigo estudiantes
                nuevo = view.readString("Ingrese el nuevo codigo: ");
                break;
            case 3: // correo electronico
                nuevo = view.readEmail("Ingrese el nuevo correo electronico: ");
                break;
            default:
                break;
        }

        view.showMessage(u.modificarEstudiante(index, option, nuevo));
    }

    private void crearProgramaAcademico() throws Exception {
        view.showMessage("=== Crear Programa Académico ===");

        String nombrePrograma = view.readStringText("Nombre del Programa Académico: ");

        for (ProgramaAcademico pa : u.getProgramasAcademicos()) {
            if (nombrePrograma.toUpperCase().equals(pa.getNombrePrograma().toUpperCase())) {
                throw new Exception("Nombre del programa ya existente ");
            }
        }

        u.getProgramasAcademicos().add(new ProgramaAcademico(nombrePrograma));
        view.showMessage("Programa Académico creado exitosamente.");
    }

    private void matricularEstudiante() {
        view.showMessage("=== Matricular Estudiante en Programa Académico ===");

        if (u.getEstudiantes().isEmpty()) {
            view.showMessage("No hay estudiantes registrados.");
            return;
        }

        u.verEstudiantesRegistrados();

        int indiceEstudiante = view.readInt("Ingrese el índice del estudiante que desea matricular: ");

        if (u.getProgramasAcademicos().isEmpty()) {
            view.showMessage("No hay programas académicos registrados.");
            return;
        }

        u.verProgramasAcademicos();

        int indicePrograma = view.readIndex("Ingrese el índice del programa académico en el que desea matricular al estudiante: ", u.getProgramasAcademicos().size());

        u.getProgramasAcademicos().get(indicePrograma).getEstudiantesMatriculados().add(u.getEstudiantes().get(indiceEstudiante));

        view.showMessage("Estudiante matriculado exitosamente en el programa académico.");
    }

    private void verEstudiantePorPrograma() {
        view.showMessage("=== MOSTRAR ESTUDIANTES POR PROGRAMA ===");
        int i = 1;

        for (ProgramaAcademico p : u.getProgramasAcademicos()) {
            view.showMessage(i + ". " + p.getNombrePrograma());
            i++;
        }
        int option = view.leerOpcion(u.getProgramasAcademicos().size());

        view.showMessage(u.getProgramasAcademicos().get(option - 1).toString());
    }

    private void modificarProgramaAcademico() {
 /*        view.showMessage("=== Modificar Programa Académico ===");

        if (u.getProgramasAcademicos().isEmpty()) {
            view.showMessage("No hay programas académicos registrados.");
            return;
        }
        u.verProgramasAcademicos();
      
        int index = view.readIndex("Ingrese el indice del programa que desea modificar: ",
                u.getEstudiantes().size());
//Veificar si index == lista
        ProgramaAcademico programaSeleccionado = u.getProgramasAcademicos().get(index);

        String nuevoNombrePrograma = programaSeleccionado.nombre_programa;
        String nuevoCodigoSNIES = programaSeleccionado.codigo_SNIES;

        while (true) {
            System.out.print("Nuevo Nombre del Programa Académico (" + nuevoNombrePrograma + "): ");
            String input = leerCadenaNoVaciaTextoPunto();
            if (!input.isEmpty()) {
                nuevoNombrePrograma = input;
                break;
            } else {
                System.out.println("Ingrese un valor válido (texto y puntos). Intente nuevamente.");
            }
        }

        while (true) {
            System.out.print("Nuevo Código SNIES del programa (" + nuevoCodigoSNIES + "): ");
            String input = leerCodigoNumerico();
            boolean codigoSNIESRegistrado = false;
            if (!input.isEmpty()) {
                if (!input.equalsIgnoreCase(programaSeleccionado.codigo_SNIES)) {
                    for (ProgramaAcademico programa : programaAcademico) {
                        if (programa.codigo_SNIES.equalsIgnoreCase(input)) {
                            view.showMessage("El programa académico con este código SNIES ya está registrado.");
                            codigoSNIESRegistrado = true;
                            break;
                        }
                    }
                    if (!codigoSNIESRegistrado) {
                        nuevoCodigoSNIES = input;
                        break;
                    }
                } else {
                    System.out.println("El nuevo código SNIES es igual al actual.");
                    break;
                }
            } else {
                System.out.println("No se permiten campos vacíos. Intente nuevamente.");
            }
        }

        programaSeleccionado.nombre_programa = nuevoNombrePrograma;
        programaSeleccionado.codigo_SNIES = nuevoCodigoSNIES;

        view.showMessage("Programa académico modificado exitosamente.");
*/
    }

    private void eliminarProgramaAcademico() {

    }

    private void crearAsignatura() {

        view.showMessage("=== Crear Asignaturas ===");
        while (true) {
            String nombresAsignatura = view.readStringText("Nombre Asignatura: ");                //valida tipo dato (solo texto)y entrada no vacía
            String codigoAsignatura = view.readString("Código Asignatura: ");
            String creditosAsignatura = view.readString("Creditos Asignatura: ");              //valida tipo dato (tipo correo xxx@.xxx)y entrada no vacía

            // Validar si el programa académico ya está registrado por código SNIES
            boolean codigo_asignaturaRegistrado = false;
            for (Asignatura Cod_asignaturaValidar : pa.getListaMaterias()) {
                if (Cod_asignaturaValidar.getCodigo_asignatura().equalsIgnoreCase(codigoAsignatura)) {
                    System.out.println("La asignatura con este código ya está registrada.");
                    codigo_asignaturaRegistrado = true;
                    break;
                }
            }
            if (!codigo_asignaturaRegistrado) {
                pa.getListaMaterias().add(new Asignatura(nombresAsignatura, codigoAsignatura, creditosAsignatura));
                System.out.println("Asignatura creada exitosamente.");
                break; // Salir del bucle en caso de éxito
            }
        }
        view.showMessage("Asignatura registrado exitosamente.");
    }

    public void modificarAsignatura() {
        view.showMessage("=== Modificar Asignatura ===");

        if (pa.getListaMaterias().isEmpty()) {
            view.showMessage("No hay asignaturas registradas.");
            return;
        }

        view.showMessage(u.verProgramasAcademicos());
        int indexPr = view.readIndex("Digite el indice del programa academico: ", u.getProgramasAcademicos().size());

        view.showMessage("=== Asignaturas ===");
        ProgramaAcademico pr = u.getProgramasAcademicos().get(indexPr);

        view.showMessage(pr.verAsignaturas());

        int indexA = view.readIndex("Ingrese el índice de la asignatura que desea modificar: ", pr.getListaMaterias().size());
        Asignatura asignatura = pr.getListaMaterias().get(indexA);

        view.showMessage("""
                         1. Nombre asignatura.
                         2. Código.
                         3. Créditos asignatura.
                         """);
        int option = view.leerOpcion(3);

        switch (option) {
            case 1 -> {
                String nombre = view.readStringText("Ingrese el nuevo nombre de la asignatura: ");
                asignatura.setNombre_asignatura(nombre);
            }
            case 2 -> {
                String codigo = view.readString("Ingrese el nuevo codigo de la asignatura: ");
                asignatura.setCodigo_asignatura(codigo);
            }
            case 3 -> {
                int creditos = view.readInt("Ingrese los nuevos creditos de la asignatura: ");
                asignatura.setCreditos_asignatura(creditos + "");
            }
            default ->
                view.showMessage("Opcion invalida");
        }
        view.showMessage("Asignatura modificada exitosamente.");
    }

    public void eliminarAsignatura() {
        view.showMessage("=== Eliminar Asignatura ===");

        if (pa.getListaMaterias().isEmpty()) {
            view.showMessage("No hay asignaturas registradas.");
            return;
        }

        view.showMessage(u.verProgramasAcademicos());
        int indexPr = view.readIndex("Digite el indice del programa academico: ", u.getProgramasAcademicos().size());

        view.showMessage("=== Asignaturas ===");
        ProgramaAcademico pr = u.getProgramasAcademicos().get(indexPr);

        view.showMessage(pr.verAsignaturas());

        int indexA = view.readIndex("Ingrese el índice de la asignatura que desea modificar: ", pr.getListaMaterias().size());
    }

    private void eliminarEstudiante() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void verAsignaturasRegistradas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void eliminar_Matricula_Estudiante_programa() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void matricularEstudiante_asigantura() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void verEstudiantesMatriculadosEnAsignatura() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void eliminarEstudianteDeAsignatura() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
