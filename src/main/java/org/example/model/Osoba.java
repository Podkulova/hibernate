package org.example.model;

import jakarta.persistence.*;
import org.example.converters.PohlaviConverter;
import org.example.model.enums.Pohlavi;
import org.example.model.enums.Telefon;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Osoba extends Obcan{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = PohlaviConverter.class)
    private Pohlavi pohlavi;

    @Embedded
    private Jmeno jmeno;

    @OneToMany(mappedBy = "osoba", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Telefon> telefony = new ArrayList<>();

    @ManyToMany(mappedBy = "osobyVeSkupine", fetch = FetchType.LAZY)
    private List<SkupinaKontaktu> skupinaosoby = new ArrayList<>();

    @OneToOne(mappedBy = "osoba", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Adresa adresa;

    public void addTelefon(Telefon telefon) {
        telefony.add(telefon);
        telefon.setOsoba(this);
    }

    public void removeTelefon(Telefon telefon) {
        telefony.remove(telefon);
        telefon.setOsoba(null);
    }

    // Konstruktor
    public Osoba() {
    }

    public Osoba(Pohlavi pohlavi) {
        this.pohlavi = pohlavi;
    }

    //Getter a Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pohlavi getPohlavi() {
        return pohlavi;
    }

    public void setPohlavi(Pohlavi pohlavi) {
        this.pohlavi = pohlavi;
    }

    public Jmeno getJmeno() {
        return jmeno;
    }

    public void setJmeno(Jmeno jmeno) {
        this.jmeno = jmeno;
    }

    public List<Telefon> getTelefony() {
        return telefony;
    }

    public void setTelefony(List<Telefon> telefony) {
        this.telefony = telefony;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "id=" + id +
                ", pohlavi=" + pohlavi +
                ", jmeno=" + jmeno +
                ", telefony=" + telefony +
                '}';
    }
}
