package miembros;

public class MiembroEstudiante extends Miembro {

    private String centrodeformacion;

    public MiembroEstudiante(String nombre, int edad, String universidaddistrital) {
        super(nombre, edad);
        this.centrodeformacion = centrodeformacion;
    }

    public String getCentrodeformacion() {
        return centrodeformacion;
    }

    @Override
    public double cuotaMensual() {
        return super.cuotaMensual() - 15000.0;
    }
}