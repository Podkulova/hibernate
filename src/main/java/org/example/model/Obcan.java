package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Obcan {
    @Column(name = "cislo_op")
    private String cisloOP;

    public String getCisloOP() {
        return cisloOP;
    }

    public void setCisloOP(String cisloOP) {
        this.cisloOP = cisloOP;
    }

    @Override
    public String toString() {
        return "Obcan{" +
                "cisloOP='" + cisloOP + '\'' +
                '}';
    }
}
