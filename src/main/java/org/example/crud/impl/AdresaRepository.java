package org.example.crud.impl;

import jakarta.persistence.EntityManager;
import org.example.crud.CrudRepository;
import org.example.model.Adresa;
import org.hibernate.Session;

public class AdresaRepository implements CrudRepository<Adresa> {
    private Session session;

    public AdresaRepository(Session session) {
        this.session = session;
    }

    @Override
    public void create(Adresa entita) {
       try {
           session.getTransaction().begin();
           session.persist(entita);
           session.getTransaction().commit();
       } catch (Exception e) {
           try {
               session.getTransaction().rollback();
           } catch (Exception e1) {
           //nepodařil se rollback
         }
       }
    }

    @Override
    public Adresa read(Class<Adresa> entityClass, Object id) {
        session.getTransaction().begin();
        Adresa adresa = session.find(entityClass,id);
        session.getTransaction().commit();
        return adresa;
    }

    @Override
    public Adresa update(Adresa entita) {
        session.getTransaction().begin();
        Adresa adresa = session.merge(entita);
        session.getTransaction().commit();
        return adresa;
    }

    @Override
    public void delete(Adresa entita) {
        session.getTransaction().begin();
        session.remove(entita);
        session.getTransaction().commit();

    }
}
