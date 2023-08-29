package model;

import java.util.ArrayList;

public class ProgramaAcademico {

    private String nombrePrograma;
    private ArrayList<Estudiante> estudiantesMatriculados = new ArrayList<>();
    private ArrayList<Asignatura> listaMaterias = new ArrayList<>();

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

    @Override
    public String toString() {
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
    
       
}
