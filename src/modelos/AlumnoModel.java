package modelos;

public class AlumnoModel {

    private String id;
    private String nombre;
    private String apellidos;
    private int asignaturas_matriculadas;

    public AlumnoModel(String id ,String nombre,String apellidos, int asignaturas_matriculadas) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.asignaturas_matriculadas = asignaturas_matriculadas;
    }

    public AlumnoModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getAsignaturas_matriculadas() {
        return asignaturas_matriculadas;
    }

    public void setAsignaturas_matriculadas(int asignaturas_matriculadas) {
        this.asignaturas_matriculadas = asignaturas_matriculadas;
    }

    @Override
    public String toString() {
        return "AlumnoModel{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", asignaturas_matriculadas=" + asignaturas_matriculadas +
                '}';
    }
}
