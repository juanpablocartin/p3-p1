/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author luare
 */
public class Medicion {

    private int numero;
    private int referencia;
    private int lectura;

    public Medicion(int numero, int referencia, int lectura) {
        this.numero = numero;
        this.referencia = referencia;
        this.lectura = lectura;
    }

    public Medicion() {
        numero = 0;
        referencia = 0;
        lectura = 0;
    }

    public Medicion(int numero) {
        this.numero = numero;
        numero = 0;
        referencia = 0;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public int getLectura() {
        return lectura;
    }

    public void setLectura(int lectura) {
        this.lectura = lectura;
    }

}
