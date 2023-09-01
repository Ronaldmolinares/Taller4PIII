package model;

public class Estudiante {
    String nombresApellidos;
    String codigoEstudiante;
    String correoElectronico;

    public Estudiante(String codigoEstudiante, String nombresApellidos, String correoElectronico) {
        this.nombresApellidos = nombresApellidos;
        this.codigoEstudiante = codigoEstudiante;
        this.correoElectronico = correoElectronico;
    }

    public Estudiante() {
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
        @Override
    public String toString() {
        return  "Código de Estudiante: " + codigoEstudiante +
                " , Nombres y Apellidos: " + nombresApellidos +
                " , Correo Electrónico: " + correoElectronico;
    }
    
}
