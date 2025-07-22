package controllers;

import java.util.*;
import models.Maquina;

public class MaquinaController {

    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> resultado = new Stack<>();
        for (Maquina m : maquinas) {
            if (m.getSubred() < umbral) {
                resultado.push(m);
            }
        }
        return resultado;
    }

    public Set<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        return new TreeSet<>(pila);
    }

    public Map<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        Map<Integer, Queue<Maquina>> mapa = new TreeMap<>();
        for (Maquina m : maquinas) {
            int riesgo = m.getRiesgo();
            Queue<Maquina> cola = mapa.get(riesgo);

            if (cola == null) {
                cola = new LinkedList<>();
                mapa.put(riesgo, cola);
            }

            cola.add(m);
        }
        return mapa;

    }

    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
        int maxTam = -1;
        int riesgoSeleccionado = -1;
        Queue<Maquina> grupoSeleccionado = null;

        for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            int riesgo = entry.getKey();
            Queue<Maquina> grupo = entry.getValue();
            int size = grupo.size();

            if (size > maxTam || (size == maxTam && riesgo > riesgoSeleccionado)) {
                maxTam = size;
                riesgoSeleccionado = riesgo;
                grupoSeleccionado = grupo;
            }
        }

        Stack<Maquina> resultado = new Stack<>();
        if (grupoSeleccionado != null) {
            for (Maquina m : grupoSeleccionado) {
                resultado.push(m);
            }
        }
        return resultado;
    }
}
