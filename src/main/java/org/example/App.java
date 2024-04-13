package org.example;

import org.example.crud.impl.AdresaRepository;
import org.example.model.*;
import org.example.model.enums.Pohlavi;
import org.example.model.enums.Telefon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class App {
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


        session.close();

    }

    private static void nativeQuery(Session session) {
        session.getTransaction().begin();
        List<Osoba> osoby = session.createNativeQuery("select * from osoba", Osoba.class)
        .getResultList();

        for (Osoba osoba : osoby) {
            System.out.println("id = " + osoba.getId() + ", přezdívka = " + osoba.getJmeno().getPrezdivka());
        }

        session.getTransaction().commit();
    }

    private static void addAdresaOsobe(Session session) {
        session.getTransaction().begin();


        Osoba osoba = session.find(Osoba.class,1L);

        Adresa adresa = new Adresa();
        adresa.setUlice("Okrasná");
        adresa.setMesto("Mesto");
        adresa.setPsc("12345");
        adresa.setOsoba(osoba);

        session.persist(adresa);
        session.getTransaction().commit();
    }

    private static void nactiOsobupridejtelefon(Session session) {
        session.getTransaction().begin();

        Osoba osoba5 = new Osoba();
        osoba5.setPohlavi(Pohlavi.ZENA);
        osoba5.setJmeno(new Jmeno("Bc.", null, "Alena", null, "Alikoa"));
        session.persist(osoba5);

        Telefon telefon1 = new Telefon();
        telefon1.setCislo("603124125");
        telefon1.setOsoba(osoba5);

      //  osoba5.getTelefony().add(telefon1);

        session.persist(telefon1);
        session.getTransaction().commit();
    }

    private static void deleteTelefon(Session session) {
        session.getTransaction().begin();

        Telefon telefon = session.find(Telefon.class, 1L);
        telefon.setOsoba(null);
        session.persist(telefon);

        session.getTransaction().commit();
    }

    private static void getTelefon(Session session) {
        session.getTransaction().begin();

        Telefon telefon = session.find(Telefon.class, 1L);
        System.out.println(telefon);
        session.persist(telefon);

        session.getTransaction().commit();
    }

    private static void saveTelefon(Session session) {
        session.getTransaction().begin();

        Telefon telefon = new Telefon();
        telefon.setCislo("603603603");
        session.persist(telefon);

        session.getTransaction().commit();
    }

    private static void saveTelefonKOsobe(Session session) {
        session.getTransaction().begin();

        Osoba osoba1 = new Osoba();
        osoba1.setPohlavi(Pohlavi.ZENA);
        osoba1.setJmeno(new Jmeno("Mgr.", "Phd.", "Petra", null, "Petrikova"));
        osoba1.setCisloOP("XX456SS");
        session.persist(osoba1);

        Telefon telefon = new Telefon();
        telefon.setOsoba(osoba1);
        telefon.setCislo("603603603");
        session.persist(telefon);

        session.getTransaction().commit();
    }

    private static void saveOsobuTelefony(Session session) {
        session.getTransaction().begin();

        Osoba osoba1 = new Osoba();
        osoba1.setPohlavi(Pohlavi.ZENA);
        osoba1.setJmeno(new Jmeno(null, null, "Alex", null, "Bernard"));
        osoba1.setCisloOP("XX477SS");
        ArrayList<Telefon> telefons = new ArrayList<>();


        Telefon telefon = new Telefon();
        telefon.setCislo("111111111");

        Telefon telefon2 = new Telefon();
        telefon2.setCislo("000000000");

        osoba1.addTelefon(telefon);
        osoba1.addTelefon(telefon2);

        session.persist(osoba1);

        session.getTransaction().commit();
    }

    private static void saveOsobaPrintOsoba(Session session) {
        Osoba osoba1 = new Osoba();
        osoba1.setPohlavi(Pohlavi.ZENA);
        osoba1.setJmeno(new Jmeno("Mgr.", "Phd.", "Alena", null, "Petrikova"));
        osoba1.setCisloOP("XX456SS");

        session.getTransaction().begin();
        session.persist(osoba1);
        session.getTransaction().commit();

        session.getTransaction().begin();
        List<Osoba> result = session.createQuery("from Osoba", Osoba.class).getResultList();

        for (Osoba osoba : result) {
            System.out.println("Osoba id: " + osoba.getId() +
                    ", Pohlaví: " + osoba.getPohlavi() +
                    ", Pohlaví kod: " + osoba.getPohlavi().getKod() +
                    ", Jméno: " + osoba.getJmeno() +
                    ", Op: " + osoba.getCisloOP());
        }

        session.getTransaction().commit();


    }

    private static void saveVicetel(Session session) {
        session.getTransaction().begin();

        Osoba osoba = session.find(Osoba.class, 1L);

        Telefon telefon = new Telefon();
        telefon.setCislo("604604604");
        telefon.setOsoba(osoba);
        session.persist(telefon);

        Telefon telefon2 = new Telefon();
        telefon2.setCislo("606606066");
        telefon2.setOsoba(osoba);
        session.persist(telefon2);

        Telefon telefon3 = new Telefon();
        telefon3.setCislo("605605605");
        telefon3.setOsoba(osoba);
        session.persist(telefon3);

        session.getTransaction().commit();

    }

    private static void loadSkupina(Session session) {
        SkupinaKontaktu skupina = session.find(SkupinaKontaktu.class, 1L);
        System.out.println(skupina);
    }

    private static void loadOsoba(Session session) {
       Osoba osoba = session.find(Osoba.class, 1L);
       // System.out.println(osoba);
    }

    private static void saveSkupinaOsob(Session session) {
        session.getTransaction().begin();

        Osoba osoba = session.find(Osoba.class, 1L);
        Osoba osoba2 = session.find(Osoba.class, 2L);
        Osoba osoba3 = session.find(Osoba.class, 3L);

        SkupinaKontaktu skupina = new SkupinaKontaktu();
        skupina.setNazevSkupiny("Skupina 1");
        skupina.getOsobyVeSkupine().add(osoba);
        skupina.getOsobyVeSkupine().add(osoba2);

        SkupinaKontaktu skupina2 = new SkupinaKontaktu();
        skupina2.setNazevSkupiny("Skupina 2");
        skupina2.getOsobyVeSkupine().add(osoba);
        skupina2.getOsobyVeSkupine().add(osoba3);

        session.persist(skupina);
        session.persist(skupina2);

        session.getTransaction().commit();
    }
}