package model;

import java.util.ArrayList;

public class ProgramaAcademico {

    private String nombrePrograma;
    private String codigoSNIES;
    private ArrayList<Estudiante> estudiantesMatriculados = new ArrayList<>();
    private ArrayList<Asignatura> listaMaterias = new ArrayList<>();

    public String getCodigoSNIES() {
        return codigoSNIES;
    }

    public void setCodigoSNIES(String codigoSNIES) {
        this.codigoSNIES = codigoSNIES;
    }

    public ProgramaAcademico(String nombrePrograma, String codigoSNIES) {
        this.nombrePrograma = nombrePrograma;
        this.codigoSNIES = codigoSNIES;
    }
    
    public ProgramaAcademico() {

    }

    public ArrayList<Estudiante> getEstudiantesMatriculados() {
        return estudiantesMatriculados;
    }

    public void setEstudiantesMatriculados(ArrayList<Estudiante> estudiantesMatriculados) {
        this.estudiantesMatriculados = estudiantesMatriculados;
    }

    public ArrayList<Asignatura> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(ArrayList<Asignatura> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public ProgramaAcademico(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void matricularEstudiante(Estudiante estudiante) {
        estudiantesMatriculados.add(estudiante);
    }

    public void agregarEstudianteMatriculado(Estudiante estudiante) {
        estudiantesMatriculados.add(estudiante);
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }
    
    
    
    public String verAsignaturas() {
        if(listaMaterias.isEmpty()) {
            return "No hay materias creadas";
        }
        String str = "";
        for(Asignatura m: listaMaterias) {
            str += m.toString() + "\n";
        }
        return str;
    }
    
    public String verEstudiantesProgramasAcademicos() {
        String str = "";
        str += "Programa Académico: " + nombrePrograma
                + ", Estudiantes Matriculados: " + estudiantesMatriculados.size() + "\n";
        int i = 1;
        for (Estudiante e : estudiantesMatriculados) {
            str += i + ". " + e.getNombresApellidos();
            i++;
        }
        return str;
    }
        @Override
    public String toString() {
        return "ProgramaAcademico{" + "nombrePrograma=" + nombrePrograma + ", codigoSNIES=" + codigoSNIES + ", estudiantesMatriculados=" + estudiantesMatriculados + ", listaMaterias=" + listaMaterias + '}';
    }
    
       
}
