package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Maquina implements Comparable<Maquina> {
    private String nombre;
    private String ip;
    private List<Integer> codigos;
    private int subred;
    private int riesgo;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
        this.subred = calcularSubred();
        this.riesgo = calcularRiesgo();
    }

    public int calcularSubred() {
        String[] partes = ip.split("\\.");
        return Integer.parseInt(partes[3]);
    }

    public int calcularRiesgo() {
        int suma = 0;
        for (int codigo : codigos) {
            if (codigo % 5 == 0) {
                suma += codigo;
            }
        }
        String sinEspacios = nombre.replace(" ", "");
        Set<Character> caracteresUnicos = new HashSet<>();
        for (char c : sinEspacios.toCharArray()) {
            caracteresUnicos.add(c);
        }
        return suma * caracteresUnicos.size();
    }

    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public int getSubred() {
        return subred;
    }

    public int getRiesgo() {
        return riesgo;
    }

    @Override
    public int compareTo(Maquina other) {
        int cmp = Integer.compare(this.subred, other.subred);
        if (cmp == 0) {
            return this.nombre.compareTo(other.nombre);
        }
        return cmp;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Maquina)) return false;
        Maquina other = (Maquina) obj;
        return this.subred == other.subred && this.nombre.equals(other.nombre);
    }

    @Override
    public int hashCode() {
        return subred * 31 + nombre.hashCode();
    }

    @Override
    public String toString() {
        return nombre + " (" + ip + ")";
    }
}
