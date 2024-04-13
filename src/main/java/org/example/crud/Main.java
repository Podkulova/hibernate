package org.example.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.example.crud.impl.AdresaRepository;
import org.example.model.Adresa;
import org.example.model.Message;
import org.example.model.Osoba;
import org.example.model.SkupinaKontaktu;
import org.example.model.enums.Telefon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {


        SessionFactory sessionFactory = new Configuration()
                .configure("META-INFO/hibernate.cfg.xml")
                .addAnnotatedClass(Message.class)
                .addAnnotatedClass(Osoba.class)
                .addAnnotatedClass(Telefon.class)
                .addAnnotatedClass(SkupinaKontaktu.class)
                .addAnnotatedClass(Adresa.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        AdresaRepository adresaRepository = new AdresaRepository(session);

        //--------------------------------------------------------------------------------------------------------------
        Adresa adresa = new Adresa();
        adresa.setUlice("Dlouhá třída 15");
        adresa.setPsc("12345");
        adresa.setMesto("Dlouhánov");

        Adresa adresa2 = new Adresa();
        adresa2.setUlice("Dlouhá třída 17");
        adresa2.setPsc("12345");
        adresa2.setMesto("Dlouhánov");

        adresaRepository.create(adresa);
        adresaRepository.create(adresa2);

        System.out.println("Přidávám dvě adresy" + "\n");

        //--------------------------------------------------------------------------------------------------------------
        Adresa adresaZdb = adresaRepository.read(Adresa.class, 1L);

        System.out.println("Adresa z db:");
        System.out.println(adresaZdb + "\n");

        //--------------------------------------------------------------------------------------------------------------
        Adresa mojeAdresa = new Adresa();
        mojeAdresa.setId(1L);
        mojeAdresa.setUlice("Měním adresu 10");
        Adresa updateAdresa = adresaRepository.update(mojeAdresa);

        System.out.println("Adresa update:");
        System.out.println(updateAdresa);
        System.out.println("Update adresy" + "\n");


        //--------------------------------------------------------------------------------------------------------------

        //adresaRepository.delete();
    }
}
