package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Jmeno {
    @Column(name = "titul_pred")
    private String titulPred;

    @Column(name = "titul_za")
    private String titulZa;

    @Column(name = "prvni_jmeno")
    private String prvniJmeno;

    @Column(name = "stredni_jmeno")
    private String stredniJmeno;

    private String prezdivka;

    //Konstruktor

    public Jmeno() {
    }

    public Jmeno(String titulPred, String titulZa, String prvniJmeno, String stredniJmeno, String prezdivka) {
        this.titulPred = titulPred;
        this.titulZa = titulZa;
        this.prvniJmeno = prvniJmeno;
        this.stredniJmeno = stredniJmeno;
        this.prezdivka = prezdivka;
    }

    //Getter, Setter
    public String getTitulPred() {
        return titulPred;
    }

    public void setTitulPred(String titulPred) {
        this.titulPred = titulPred;
    }

    public String getTitulZa() {
        return titulZa;
    }

    public void setTitulZa(String titulZa) {
        this.titulZa = titulZa;
    }

    public String getPrvniJmeno() {
        return prvniJmeno;
    }

    public void setPrvniJmeno(String prvniJmeno) {
        this.prvniJmeno = prvniJmeno;
    }

    public String getStredniJmeno() {
        return stredniJmeno;
    }

    public void setStredniJmeno(String stredniJmeno) {
        this.stredniJmeno = stredniJmeno;
    }

    public String getPrezdivka() {
        return prezdivka;
    }

    public void setPrezdivka(String prezdivka) {
        this.prezdivka = prezdivka;
    }

    @Override
    public String toString() {
        return "Jmeno{" +
                "titulPred='" + titulPred + '\'' +
                ", titulZa='" + titulZa + '\'' +
                ", prvniJmeno='" + prvniJmeno + '\'' +
                ", stredniJmeno='" + stredniJmeno + '\'' +
                ", prezdivka='" + prezdivka + '\'' +
                '}';
    }
}
