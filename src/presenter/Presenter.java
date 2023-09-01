package presenter;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import view.*;
import resources.Persistencia;

public class Presenter {

    private View view;
    private Universidad u;
    private Persistencia p; 
    private String ruta ="src/data/estudiantes.txt";

    public Presenter() {
        this.view = new View();
    }

    public void run() {
        this.u = new Universidad();
        this.cargarDatos();
        // Cargar los estudiantes desde el archivo al inicio del programa
        this.p=new Persistencia();
        p.cargarEstudiantesDesdeArchivo(u.getEstudiantes(),ruta);
// Agregar el hook de apagado para guardar datos antes de salir (gancho de apagado:
//se ejecutará justo antes de que el programa finalice su ejecución, ya sea porque está terminando normalmente o debido a una finalización abrupta)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            p.guardarEstudiantesEnArchivo(ruta);
            view.showMessage("Guardando datos antes de salir...");
        }));
        
        
        
        
        int opcion;

        do {
            view.mostrarMenu();
            opcion = view.leerOpcion(3);

            switch (opcion) {
                case 1 -> {
                    view.menuEstudiante();
                    this.switchEstudiante();
                }
                case 2 -> {
                    view.menuProgramaAcademico();
                    this.switchProgramaAcademico();
                }
                case 3 -> {
                    view.menuAsignaturas();
                    this.switchAsignaturas();
                }
                case 0 -> {
                    view.showMessage("¡Hasta luego!");
                }
            }
        } while (opcion != 0);

        do {
            view.mostrarMenu();
            opcion = view.leerOpcion(18);

            switch (opcion) {
                case 1 ->{
                    verEstudiantePorPrograma();
                    break;
                }
                case 2 ->{
                    registrarEstudiante();
                    break;}
                case 3 ->{
                    modificarEstudiante();
                    break;}
                case 4 ->{
                    eliminarEstudiante();
                    break;}
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
                    view.showMessage("ï¿½Hasta luego!");
                default ->
                    view.showMessage("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        //reader.close(); // Cerrar el BufferedReader al finalizar
    }

    public void cargarDatos() {
        ProgramaAcademico ingSistemas = new ProgramaAcademico("Ingenieria en Sistemas y Computación", "1234");
        ProgramaAcademico ingMetalurgica = new ProgramaAcademico("Ingenieria Metalurgica", "5134");
        ProgramaAcademico psicologia = new ProgramaAcademico("Psicologia", "5342");
        u.getProgramasAcademicos().add(ingSistemas);
        u.getProgramasAcademicos().add(ingMetalurgica);
        u.getProgramasAcademicos().add(psicologia);
    }

    public static void main(String[] args) {
        new Presenter().run();
    }

    private void verEstudiantes() {
        view.showMessage(u.verEstudiantesRegistrados());
    }

    private void registrarEstudiante() {
        
        view.showMessage("=== Registrar Estudiante ===");
        // Declaración de variables para almacenar los datos del estudiante
        String codigoEstudiante = null;
        String nombresApellidos = null;
        String correoElectronico = null;
        List<Estudiante> estudiantes= u.getEstudiantes();
        // Ciclo para validar y registrar los datos del estudiante
        while (true) {
             // Si el código del estudiante aún no se ha ingresado
            if (codigoEstudiante == null) {
                codigoEstudiante = view.readString("Código Estudiante: ");
                // Validar si el estudiante ya está registrado por código
                for (Estudiante estudiante : estudiantes) {
                    if (estudiante.getCodigoEstudiante().equals(codigoEstudiante)) {
                        view.showMessage("El estudiante con este código ya está registrado.");
                        codigoEstudiante = null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            } // Si los nombres y apellidos del estudiante aún no se han ingresado
            else if (nombresApellidos == null) {
                nombresApellidos = view.readStringText("Nombres y Apellidos: ");
            } // Si el correo electrónico del estudiante aún no se ha ingresado
            else if (correoElectronico == null) {
                correoElectronico = view.readEmail("Correo Electrónico: ");
                // Validar si el estudiante ya está registrado por correo electrónico
                for (Estudiante estudiante : estudiantes) {
                    if (estudiante.getCorreoElectronico().equalsIgnoreCase(correoElectronico)) {
                        view.showMessage("El estudiante con este correo electrónico ya está registrado.");
                        correoElectronico = null; // Reiniciar para volver a pedir el dato
                        break;
                    }
                }
            }
            // Si se han ingresado todos los datos requeridos, registrar el estudiante
            if (codigoEstudiante != null && nombresApellidos != null && correoElectronico != null) {
                u.getEstudiantes().add(new Estudiante( codigoEstudiante,nombresApellidos, correoElectronico));
                //estudiantes.add(new Estudiante(id_estudiante, nombresApellidos, codigoEstudiante, correoElectronico));
                view.showMessage("Estudiante registrado exitosamente.");
                p.guardarEstudiantesEnArchivo(ruta);
                break; // Salir del bucle en caso de éxito
            }
        }
    }

    private void eliminarEstudiante() {
        view.showMessage("== Eliminar Estudiante ==");
        if (u.getEstudiantes().isEmpty()) {
            view.showMessage("No existen estudiantes");
            return;
        }
        view.showMessage(u.verEstudiantesRegistrados());
        int iEs = view.readIndex("Ingrese el indice del estudiante que desea eliminar: ", u.getEstudiantes().size());
        view.showMessage(u.eliminarEstudiante(iEs));
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
            case 1 -> // nombre completo
                nuevo = view.readStringText("Ingrese el nuevo nombre y apellido: ");
            case 2 -> // codigo estudiantes
                nuevo = view.readString("Ingrese el nuevo codigo: ");
            case 3 -> // correo electronico
                nuevo = view.readEmail("Ingrese el nuevo correo electronico: ");
            default -> {
            }
        }

        view.showMessage(u.modificarEstudiante(index, option, nuevo));
    }

    private void crearProgramaAcademico() throws Exception {
        view.showMessage("=== Crear Programa Acádemico ===");

        String nombrePrograma = view.readStringText("Nombre del Programa Acádemico: ");
        String codigoSNIES = view.readInt("Codigo SNIES: ") + "";

        for (ProgramaAcademico pa : u.getProgramasAcademicos()) {
            if (pa.getCodigoSNIES().equals(codigoSNIES)) {
                throw new Exception("Programa ya existente.");
            }
        }

        u.getProgramasAcademicos().add(new ProgramaAcademico(nombrePrograma));
        view.showMessage("Programa Acádemico creado exitosamente.");
    }

    private void matricularEstudiante() {
        view.showMessage("=== Matricular Estudiante en Programa Acádemico ===");

        if (u.getEstudiantes().isEmpty()) {
            view.showMessage("No hay estudiantes registrados.");
            return;
        }

        view.showMessage(u.verEstudiantesRegistrados());

        int indiceEstudiante = view.readInt("Ingrese el índice del estudiante que desea matricular: ");

        if (u.getProgramasAcademicos().isEmpty()) {
            view.showMessage("No hay programas academicos registrados.");
            return;
        }

        view.showMessage(u.verProgramasAcademicos());
        int indicePrograma = view.readIndex("Ingrese el indice del programa academico en el que desea matricular al estudiante: ", u.getProgramasAcademicos().size());

        boolean estudianteMatriculado=u.getProgramasAcademicos().get(indicePrograma).getEstudiantesMatriculados().contains(u.getEstudiantes().get(indiceEstudiante));
        if (estudianteMatriculado) {
            view.showMessage("El estudiante ya está matriculado en este programa.");
        } else {
            u.getProgramasAcademicos().get(indicePrograma).getEstudiantesMatriculados().add(u.getEstudiantes().get(indiceEstudiante));
            //programaSeleccionado.Estudiantes_matriculados_programa(estudianteSeleccionado);
            view.showMessage("Estudiante matriculado exitosamente en el programa academico");
        } 
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
        view.showMessage("=== Modificar Programa Academico ===");

        if (u.getProgramasAcademicos().isEmpty()) {
            view.showMessage("No hay programas acade½micos registrados.");
            return;
        }
        view.showMessage(u.verProgramasAcademicos());

        int iPa = view.readIndex("Ingrese el indice del programa que desea modificar: ",
                u.getEstudiantes().size());
        ProgramaAcademico pa = u.getProgramasAcademicos().get(iPa);

        view.showMessage("Que desea modificar.");
        view.showMessage("""
                         1. Nombre del programa.
                         2. Codigo SNIES.
                         """);
        int option = view.leerOpcion(2);

        switch (option) {
            case 1 -> {
                String nomNuevo = view.readStringText("Ingrese el nuevo nombre para el programa academico: ");
                pa.setNombrePrograma(nomNuevo);
            }
            case 2 -> {
                String codNuevo = view.readInt("Ingrese el codigo SNIES: ") + "";
                pa.setCodigoSNIES(codNuevo);
            }
        }

        view.showMessage("Programa academico modifica con exito.");
    }

    private void eliminarProgramaAcademico() {
        view.showMessage("== Eliminar Programa Academico");
        view.showMessage(u.verProgramasAcademicos());
        int iPa = view.readIndex("Ingrese el indice del programa academico que desea eliminar: ", u.getProgramasAcademicos().size());

        u.getProgramasAcademicos().remove(iPa);
        view.showMessage("Programa Academico eliminado con exito.");
    }

    private void crearAsignatura() {
        view.showMessage("=== Crear Asignaturas ===");
        view.showMessage(u.verProgramasAcademicos());
        int iPa = view.readIndex("Ingrese el indice del programa academico en donde quieres crear la asignatura: ", u.getProgramasAcademicos().size());
        ProgramaAcademico pa = u.getProgramasAcademicos().get(iPa);

        String nombresAsignatura = view.readStringText("Nombre Asignatura: ");                //valida tipo dato (solo texto)y entrada no vacï¿½a
        String codigoAsignatura = view.readString("Cï¿½digo Asignatura: ");
        String creditosAsignatura = view.readString("Creditos Asignatura: ");

        try {
            pa.crearAsignatura(nombresAsignatura, codigoAsignatura, creditosAsignatura);
            view.showMessage("Asignatura creada con exito.");
        } catch (Exception e) {
            view.showMessage(e.toString());
        }
    }

    public void modificarAsignatura() {
        view.showMessage("=== Modificar Asignatura ===");

        view.showMessage(u.verProgramasAcademicos());
        int indexPr = view.readIndex("Digite el indice del programa academico: ", u.getProgramasAcademicos().size());
        ProgramaAcademico pa = u.getProgramasAcademicos().get(indexPr);

        if (pa.getListaMaterias().isEmpty()) {
            view.showMessage("No hay asignaturas registradas.");
            return;
        }
        view.showMessage("=== Asignaturas ===");
        ProgramaAcademico pr = u.getProgramasAcademicos().get(indexPr);

        view.showMessage(pr.verAsignaturas());

        int indexA = view.readIndex("Ingrese el ï¿½ndice de la asignatura que desea modificar: ", pr.getListaMaterias().size());
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
            case 0->
                view.mostrarMenu();
            default ->
                view.showMessage("Opcion invalida");
        }
        view.showMessage("Asignatura modificada exitosamente.");
    }

    public void eliminarAsignatura() {
        view.showMessage("=== Eliminar Asignatura ===");

        view.showMessage(u.verProgramasAcademicos());
        int indexPr = view.readIndex("Digite el indice del programa academico donde desea eliminar la asignatura: ", u.getProgramasAcademicos().size());
        ProgramaAcademico pa = u.getProgramasAcademicos().get(indexPr);

        if (pa.getListaMaterias().isEmpty()) {
            view.showMessage("No hay asignaturas registradas.");
            return;
        }

        view.showMessage("=== Asignaturas ===");
        ProgramaAcademico pr = u.getProgramasAcademicos().get(indexPr);

        view.showMessage(pr.verAsignaturas());
        int indexA = view.readIndex("Ingrese el indice de la asignatura que desea modificar: ", pr.getListaMaterias().size());
        pr.getListaMaterias().remove(indexA);
        view.showMessage("Asignatura eliminada correctamente.");
    }

    private void verAsignaturasRegistradas() {
        view.showMessage("== Asignaturas Registradas");
        for (ProgramaAcademico pr : u.getProgramasAcademicos()) {
            view.showMessage(pr.getNombrePrograma().toUpperCase());
            for (Asignatura a : pr.getListaMaterias()) {
                view.showMessage(a.toString());
            }
            view.showMessage("");
        }
    }

    private void eliminar_Matricula_Estudiante_programa() {
        view.showMessage("== Eliminar Matricula Estudiante ==");

        if (u.getProgramasAcademicos().isEmpty()) {
            view.showMessage("No hay Programas Academicos aún.");
            return;
        }

        view.showMessage(u.verProgramasAcademicos());
        int iPa = view.readIndex("Ingrese el indice de donde quiere quitar la matricula del estudiante: ", u.getProgramasAcademicos().size());
        ProgramaAcademico pa = u.getProgramasAcademicos().get(iPa);

        if (pa.getEstudiantesMatriculados().isEmpty()) {
            view.showMessage("Aún no hay ningun estudiante matriculado.");
            return;
        }

        view.showMessage(pa.verEstudiantesProgramaAcademico());
        int iEs = view.readIndex("Ingrese el indice del estudiante: ", iPa);
        pa.getEstudiantesMatriculados().remove(iEs);
    }

    private void matricularEstudiante_asigantura() {
        view.showMessage("== Matricular Estudiante ==");
        if (u.getEstudiantes().isEmpty()) {
            view.showMessage("Primero registra algunos estudiantes.");
            return;
        }
        if (u.getProgramasAcademicos().isEmpty()) {
            view.showMessage("Aún no existen programas Academicos");
            return;
        }
        view.showMessage(u.verProgramasAcademicos());
        int iPa = view.readIndex("Ingrese el indice del programa academico en donde desea matricular al estudiante: ", u.getProgramasAcademicos().size());
        ProgramaAcademico pa = u.getProgramasAcademicos().get(iPa);
        if (pa.getEstudiantesMatriculados().isEmpty()) {
            view.showMessage("Primero matricula algunos estudiantes.");
            return;
        }
        view.showMessage(pa.verEstudiantesProgramaAcademico());
        int iEs = view.readIndex("Ingrese el indice del estudiante que desea matricular: ", pa.getEstudiantesMatriculados().size());
        view.showMessage(pa.verAsignaturas());
        int iAs = view.readIndex("Ingrese el indice de la materia: ", pa.getListaMaterias().size());
        Asignatura a = pa.getListaMaterias().get(iAs);

        a.getLista_estudiantes_asignatura().add(u.getEstudiantes().get(iEs));
    }

    private void verEstudiantesMatriculadosEnAsignatura() {
        view.showMessage("== Estudiantes Matriculados Asignatura ==");

        view.showMessage(u.verProgramasAcademicos());
        int iPa = view.readIndex("Ingrese el indice del programa academico: ", u.getProgramasAcademicos().size());
        ProgramaAcademico pa = u.getProgramasAcademicos().get(iPa);

        view.showMessage(pa.verAsignaturas());
    }

    private void eliminarEstudianteDeAsignatura() {
        view.showMessage("== Eliminar Estudiante Asignatura ==");

        view.showMessage(u.verProgramasAcademicos());
        int iPa = view.readIndex("Ingrese el indice del programa academico: ", u.getProgramasAcademicos().size());
        ProgramaAcademico pa = u.getProgramasAcademicos().get(iPa);

        view.showMessage(pa.verAsignaturas());
        int iAs = view.readIndex("Ingrese el indice de la asignatura que desea eliminar: ", pa.getListaMaterias().size());

        pa.getListaMaterias().remove(iAs);
        view.showMessage("Materia eliminada con exito.");
    }

    private void switchEstudiante() {
        int option;

        do {
            option = view.leerOpcion(2);
            switch (option) {
                case 1 -> {
                    view.menuEstudianteResgitro();
                    this.switchEstudianteRegistro();
                  
                }
                case 2 -> {
                    view.menuEstudianteMatricula();
                    this.switchEstudianteMatricula();  
                }
                case 0->
                    view.mostrarMenu();
                default ->
                    view.showMessage("");
            }
            break;
        } while (option != 0);
        
    }

    private void switchEstudianteRegistro() {
        int option;

        do {
            option = view.leerOpcion(4);

            switch (option) {
                case 1 ->
                    this.verEstudiantes();
                case 2 ->
                    this.registrarEstudiante();
                case 3 ->
                    this.modificarEstudiante();
                case 4 ->
                    this.eliminarEstudiante();
                case 0->
                    view.mostrarMenu();
                default ->
                    view.showMessage("");
            }
            break;
        } while (option != 0);
    }

    private void switchEstudianteMatricula() {
        int option;

        do {
            option = view.leerOpcion(6);

            switch (option) {
                case 1 ->
                    this.matricularEstudiante();
                case 2 ->
                    this.verEstudiantePorPrograma();
                case 3 ->
                    this.eliminar_Matricula_Estudiante_programa();
                case 4 ->
                    this.matricularEstudiante_asigantura();
                case 5 ->
                    this.verEstudiantesMatriculadosEnAsignatura();
                case 6 ->
                    this.eliminarEstudianteDeAsignatura();
                case 0->
                    view.mostrarMenu();
                default ->
                    view.showMessage("");
            }
            break;
        } while (option != 0);
    }

    private void switchProgramaAcademico() {
        int option;

        do {
            option = view.leerOpcion(4);

            switch (option) {
                case 1 ->
                {
                    try {
                        this.crearProgramaAcademico();
                    } catch (Exception ex) {
                        view.showMessage(ex.toString());
                    }
                }
                case 2 ->
                    this.modificarProgramaAcademico();
                case 3 ->
                    this.eliminarProgramaAcademico();
                case 4 ->
                    view.showMessage(u.verProgramasAcademicos());
                case 0->
                    view.mostrarMenu();
                default ->
                    view.showMessage("");
            }
            break;
        } while (option != 0);
    }

    private void switchAsignaturas() {
        int option;

        do {
            option = view.leerOpcion(4);

            switch (option) {
                case 1 ->
                    this.crearAsignatura();
                case 2 ->
                    this.modificarAsignatura();
                case 3 ->
                    this.eliminarAsignatura();
                case 4 ->
                    this.verAsignaturasRegistradas();
                case 0->
                    view.mostrarMenu();
                default ->
                    view.showMessage("");
            }
            break;
        } while (option != 0);
    }

}
