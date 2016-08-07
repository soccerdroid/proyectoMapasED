/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

/**
 *
 * @author Administrador
 */
public class GrafoMA {

    private Object[] vertices;
    private int[][] matriz;

    public GrafoMA(int n) {
        vertices = new Object[n];
        matriz = new int[n][n];
    }

    public boolean existeVertice(Object o) {
        if (o != null) {
            for (int i = 0; i < this.vertices.length; i++) {
                if (o.equals(this.vertices[i])) //si comparo al revès, se caerìa mi programa si vertices[i] fuese nulo
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int indiceVertice(Object o) { //es lo mismo que indiceVertice
        for (int i = 0; i < this.vertices.length; i++) {
            if (o.equals(this.vertices[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        int indiceInicio = this.indiceVertice(origen);
        int indiceDestino = this.indiceVertice(destino);
        if (indiceInicio != -1 && indiceDestino != -1) {
            if (this.matriz[indiceInicio][indiceDestino] != 0) {
                this.matriz[indiceInicio][indiceDestino] = 0;
                return true;
            }
        }
        return false;
    }

    public boolean agregarArco(Object origen, Object destino, int peso) {

        int indiceInicio = this.indiceVertice(origen);
        int indiceDestino = this.indiceVertice(destino);
        if (indiceInicio != -1 && indiceDestino != -1) {

            this.matriz[indiceInicio][indiceDestino] = peso;
            return true;

        }
        return false;
    }

    public int gradoEntrada(Object vertice) {
        if (this.existeVertice(vertice)) {
            int grado = 0;
            int j = this.indiceVertice(vertice);
            int n = this.matriz.length;
            for (int i = 0; i < n; i++) {
                if (matriz[i][j] != 0) {
                    
                    grado += 1;
                }
            }
            return grado;
        }
        return -1;
    }

    public int gradoSalida(Object vertice) {
        if (this.existeVertice(vertice)) {
            int grado = 0;
            int j = this.indiceVertice(vertice);
            int n = this.matriz.length;
            for (int i = 0; i < n; i++) {
                if (matriz[j][i] != 0) {
                    grado += 1;
                }
            }
            return grado;
        }
        return -1;

    }

    public boolean esAdyacente(Object o1, Object o2) {
        if (this.existeVertice(o1) && this.existeVertice(o2)) {
            int i = this.indiceVertice(o1);
            int j = this.indiceVertice(o2);
            if (this.matriz[i][j] != 0 || this.matriz[j][i] != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean eliminarVertice(Object o) {

        for (int i = 0; i < this.vertices.length; i++) {
            if (o.equals(this.vertices[i])) {
                int pos = this.indiceVertice(o);
                for (int j = 0; j < this.matriz.length; j++) {
                    this.matriz[pos][j] = 0;
                    this.matriz[j][pos] = 0;
                }
                this.vertices[i] = null;
                return true;
            }
        }

        return false;
    }

    public boolean agregarVertice(Object o) {
        if (this.existeVertice(o)) {
            return false;
        } else {
            for (int i = 0; i < this.vertices.length; i++) {
                if (this.vertices[i] == null && !this.existeVertice(o)) {
                    this.vertices[i] = o;

                }

            }
            return true;

        }
    }

    public String toString() {
        String s = " ";
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null) {
                s += vertices[i] + " ";
            }
        }
        return s;
    }

}
