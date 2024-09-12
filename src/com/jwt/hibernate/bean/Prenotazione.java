package com.jwt.hibernate.bean;

import java.util.LinkedList;
import java.util.List;

public class Prenotazione {

    private int prenotazioneId;
    private User user; 
    private Veicolo veicolo; 
    private String dataPrenotazione;
    private String dataInizio;
    private String dataFine;
    private List<Prenotazione> prenotazioni;

    public Prenotazione(User user, Veicolo veicolo, String dataPrenotazione, String dataInizio, String dataFine) {
        this.user = user;
        this.veicolo = veicolo;
        this.dataPrenotazione = dataPrenotazione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }
    
    
    public Prenotazione() {
        this.prenotazioni = new LinkedList<>();  
    }

    public Prenotazione(int prenotazioneId, User user, Veicolo veicolo, String dataPrenotazione) {
        this.prenotazioneId = prenotazioneId;
        this.user = user;
        this.veicolo = veicolo;
        this.dataPrenotazione = dataPrenotazione;
    }

    public int getPrenotazioneId() {
        return prenotazioneId;
    }

    public void setPrenotazioneId(int prenotazioneId) {
        this.prenotazioneId = prenotazioneId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public String getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(String dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }
    
    public List<Prenotazione> getPrenotazioni(){
    	return prenotazioni;
    }

    @Override
    public String toString() {
        return "Prenotazione [prenotazioneId=" + prenotazioneId + ", user=" + user + ", veicolo=" + veicolo + ", dataPrenotazione=" + dataPrenotazione + "]";
    }
}
