package model;

public class Estudiante {
    String nombresApellidos;
    String codigoEstudiante;
    String correoElectronico;

    public Estudiante(String nombresApellidos, String codigoEstudiante, String correoElectronico) {
        this.nombresApellidos = nombresApellidos;
        this.codigoEstudiante = codigoEstudiante;
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "Nombres y Apellidos: " + nombresApellidos +
                ", Código de Estudiante: " + codigoEstudiante +
                ", Correo Electrónico: " + correoElectronico;
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
    
    
}
