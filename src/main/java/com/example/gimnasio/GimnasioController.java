package com.example.gimnasio;

import miembros.Miembro;
import miembros.MiembroEstudiante;
import miembros.MiembroPremium;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GimnasioController {

    private List<Miembro> miembros;
    private Map<String, String> horarios;

    public GimnasioController() {
        miembros = new ArrayList<>();
        miembros.add(new Miembro("Andres", 12));
        miembros.add(new MiembroPremium("Santiago", 13, "Rodriguez "));
        miembros.add(new MiembroEstudiante("Stiward", 17, "Universidad Distrital"));
        miembros.add(new MiembroPremium("Luis", 23, "Hernandez"));

        horarios = new HashMap<>();
        horarios.put("Fuerza", "Lunes y Viernes - 7:00 AM a 10:0 AM");
        horarios.put("senderismo", "Martes y Jueves - 5:00 PM a 3:00 PM");
        horarios.put("cardio", "Lunes a Viernes - 6:00 PM a 6:00 PM");
    }

    @GetMapping("/miembros")
    public List<Miembro> obtenerMiembros() {
        return miembros;
    }

    @GetMapping("/miembros/buscar/{nombre}")
    public String buscarMiembro(@PathVariable String nombre) {
        for (Miembro m : miembros) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m.descripcion() + " - cuota mensual: $" + m.cuotaMensual();
            }
        }
        return "No se encontro el cliente " + nombre;
    }

    @GetMapping("/miembros/cuota/{minima}")
    public List<Miembro> filtrarPorCuota(@PathVariable double minima) {
        List<Miembro> resultado = new ArrayList<>();
        for (Miembro m : miembros) {
            if (m.cuotaMensual() >= minima) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    @GetMapping("/clases/{nombre}")
    public String consultarClase(@PathVariable String nombre) {
        for (Map.Entry<String, String> entrada : horarios.entrySet()) {
            if (entrada.getKey().equalsIgnoreCase(nombre)) {
                return entrada.getValue();
            }
        }
        return "Clase no disponible";
    }
}