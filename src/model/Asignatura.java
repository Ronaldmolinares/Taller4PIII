package model;

import java.util.ArrayList;
import java.util.List;

public class Asignatura {
    String nombre_asignatura;
    String codigo_asignatura;
    String creditos_asignatura;
    List<Estudiante> lista_estudiantes_asignatura; // Nueva lista para estudiantes matriculados

    
    public void Lista_estudiantes_asignatura(Estudiante estudiante) {
        lista_estudiantes_asignatura.add(estudiante);
    }
    public String getNombre_asignatura() {
        return nombre_asignatura;
    }

    public void setNombre_asignatura(String nombre_asignatura) {
        this.nombre_asignatura = nombre_asignatura;
    }

    public String getCodigo_asignatura() {
        return codigo_asignatura;
    }

    public void setCodigo_asignatura(String codigo_asignatura) {
        this.codigo_asignatura = codigo_asignatura;
    }

    public String getCreditos_asignatura() {
        return creditos_asignatura;
    }

    public void setCreditos_asignatura(String creditos_asignatura) {
        this.creditos_asignatura = creditos_asignatura;
    }

    public List<Estudiante> getLista_estudiantes_asignatura() {
        return lista_estudiantes_asignatura;
    }

    public void setLista_estudiantes_asignatura(List<Estudiante> lista_estudiantes_asignatura) {
        this.lista_estudiantes_asignatura = lista_estudiantes_asignatura;
    }

    
    public Asignatura(String nombre_asignatura, String codigo_asignatura, String creditos_asignatura) {
        this.nombre_asignatura = nombre_asignatura;
        this.codigo_asignatura = codigo_asignatura;
        this.creditos_asignatura = creditos_asignatura;
        this.lista_estudiantes_asignatura = new ArrayList<>(); // Inicializaci�n de la lista
    }
    
    
   public String toString(int i) {
        return "Indice: " + i +
                "Nombre: " + nombre_asignatura + 
                ", C�digo: " + codigo_asignatura+
                ", Cr�ditos Asignatura: " + creditos_asignatura;
   }

    @Override
    public String toString() {
        return "Nombre_Asignatura= " + nombre_asignatura + ", C�digo_Asignatura= " + codigo_asignatura + ", Creditos_Asignatura= " + creditos_asignatura + ", Lista_estudiantes_Asignatura= " + lista_estudiantes_asignatura;
    }
    
}
