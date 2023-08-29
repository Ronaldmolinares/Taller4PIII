package model;

import java.util.ArrayList;
import java.util.List;

public class Universidad {

    private static List<Estudiante> estudiantes = new ArrayList<>();
    private static List<ProgramaAcademico> programasAcademicos = new ArrayList<>();

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public static void setEstudiantes(List<Estudiante> estudiantes) {
        Universidad.estudiantes = estudiantes;
    }

    public List<ProgramaAcademico> getProgramasAcademicos() {
        return programasAcademicos;
    }

    public static void setProgramasAcademicos(List<ProgramaAcademico> programasAcademicos) {
        Universidad.programasAcademicos = programasAcademicos;
    }

    public String verEstudiantesRegistrados() {                       //método para leer lista estudiantes 
        String str = "";
        if (estudiantes.isEmpty()) {                                        //Verifica si la lista de estudiantes está vacía
            str = ("No hay estudiantes registrados.");
        } else {
            str += ("=== Estudiantes Registrados ===\n");
            int index = 0;
            for (Estudiante estudiante : estudiantes) {                     //bucle for-each que recorre la lista de estudiantes
                str += ("Índice " + index + ": " + estudiante + "\n");
                index++;
            }
        }

        return str;
    }

    public String modificarEstudiante(int index, int cambio, String nuevo) {
        String str = "";
        if (estudiantes.isEmpty()) { // Verifica si la lista de estudiantes está vacía
            return "No hay estudiantes registrados.";
        } else {
            // muestra los estudiantes registrados
            Estudiante estudiante = estudiantes.get(index);
            switch (cambio) {
                case 1: // nombre completo
                    estudiante.setNombresApellidos(nuevo);
                    str = "nombre y apellido";
                    break;
                case 2:
                    estudiante.setCodigoEstudiante(nuevo);
                    str = "codigo del estudiante";
                case 3:
                    estudiante.setCorreoElectronico(nuevo);
                    str = "correo electronico del estudiante";
                default:
                    break;
            }
            return "Se a modificado el " + str + " satisfactoriamente.";
        }
    }

    public String verProgramasAcademicos() {
        String str = " ";
        if (programasAcademicos.isEmpty()) {
          str = "No hay programas académicos registrados.";
        } else {
            System.out.println("=== Programas Académicos Registrados ===");
            int index = 0;
            for (ProgramaAcademico programa : programasAcademicos) {
                str += "Índice " + index + ": " + programa;
                index++;
            }
            str += " "+ "\n";
        }
        return str;
    }
    
     public String mostrarEstudiantesPrograma() {
         String str = "";
        if (programasAcademicos.isEmpty()) { // Verifica si la lista de programas académicos está vacía
            str = " No hay programas académicos registrados.";
        } else {
            str = "=== Estudiantes Matriculados por Programa Académico ===" + "\n";
            for (ProgramaAcademico programa : programasAcademicos) { // Itera a través de la lista de programas académicos usando un bucle for-each
                str += "Programa: " + programa.getNombrePrograma(); // Imprime el nombre del programa académico actual

                List<Estudiante> estudiantesMatriculados = programa.getEstudiantesMatriculados(); // Obtiene la lista de estudiantes matriculados en el programa actual
                if (estudiantesMatriculados.isEmpty()) { // Verifica si la lista de estudiantes matriculados está vacía para este programa
                    str += " No hay estudiantes matriculados en este programa."+ "\n";
                } else {
                    for (Estudiante estudiante : estudiantesMatriculados) { // Itera a través de la lista de estudiantes matriculados en el programa actual
                        str += " " + estudiante; // Imprime la información del estudiante usando su método toString()
                    }
                }
                str += " "+ "\n"; // Imprime una línea en blanco para separar la información de diferentes programas académicos
            }
        }
        return str;
    }

    public String eliminarEstudiante(int index) {
        estudiantes.remove(index);

        return "Estudiante eliminado satisfactoriamente";
    }

}
