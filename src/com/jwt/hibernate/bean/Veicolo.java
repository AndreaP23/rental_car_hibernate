package com.jwt.hibernate.bean;

import java.util.LinkedList;
import java.util.List;

public class Veicolo {
    private int id;
    private String marca;
    private String modello;
    private int anno;
    private String targa;
    private String disponibilita;
    private List<Veicolo> veicoli;  


  
	public Veicolo(int id,String marca, String modello, int anno, String targa, String disponibilita) {
        this.id=id;
		this.marca = marca;
        this.modello = modello;
        this.anno = anno;
        this.targa = targa;
        this.disponibilita = disponibilita;
    }

	
    public Veicolo() {
        this.veicoli = new LinkedList<>();  
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(String disponibilita) {
        this.disponibilita = disponibilita;
    }

    public void aggiungiVeicolo(Veicolo veicolo) {
        this.veicoli.add(veicolo);
    }

    public List<Veicolo> getVeicoli() {
        return veicoli;
    }

    @Override
    public String toString() {
        return "Veicolo [id=" + id + ", marca=" + marca + ", modello=" + modello + ", anno=" + anno + ", targa=" + targa + ", disponibilita=" + disponibilita + "]";
    }
}