package org.example.model.enums;

import jakarta.persistence.*;
import org.example.model.Osoba;

import java.util.Objects;

@Entity
public class Telefon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cislo;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_telefon_osoba_id"))
    private Osoba osoba;

    public Telefon() {
    }

    public Long getId() {
        return id;
    }

    public String getCislo() {
        return cislo;
    }

    public void setCislo(String cislo) {
        this.cislo = cislo;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }



    @Override
    public String toString() {
        return "Telefon{" +
                "id=" + id +
                ", cislo='" + cislo + '\'' +
                ", osoba=" + osoba +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Telefon telefon = (Telefon) o;
//        return Objects.equals(id, telefon.id) && Objects.equals(cislo, telefon.cislo) && Objects.equals(osoba, telefon.osoba);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, cislo, osoba);
//    }
}
