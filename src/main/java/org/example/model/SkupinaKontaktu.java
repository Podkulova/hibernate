package org.example.model;

import jakarta.persistence.*;
import org.example.converters.PohlaviConverter;
import org.example.model.enums.Pohlavi;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skupina")
public class SkupinaKontaktu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nazev")
    private String nazevSkupiny;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "skupina_id",
                    foreignKey = @ForeignKey(name = "fk_skupina_id")),
            inverseJoinColumns = @JoinColumn(name = "osoba_id",
                    foreignKey = @ForeignKey(name = "fk_2_osoba_id")))
    private List<Osoba> osobyVeSkupine = new ArrayList<>();

    // Konstruktor bez parametru


    public SkupinaKontaktu() {
    }

    //Getter, Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazevSkupiny() {
        return nazevSkupiny;
    }

    public void setNazevSkupiny(String nazevSkupiny) {
        this.nazevSkupiny = nazevSkupiny;
    }

    public List<Osoba> getOsobyVeSkupine() {
        return osobyVeSkupine;
    }

    public void setOsobyVeSkupine(List<Osoba> osobyVeSkupine) {
        this.osobyVeSkupine = osobyVeSkupine;
    }

    @Override
    public String toString() {
        return "SkupinaKontaktu{" +
                "id=" + id +
                ", nazevSkupiny='" + nazevSkupiny + '\'' +
                ", osobyVeSkupine=" + osobyVeSkupine +
                '}';
    }
}
