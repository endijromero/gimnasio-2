package com.gimnasio.model;

import com.gimnasio.persistences.Usuarios;
import com.gimnasio.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emimaster16
 */
public class GimnasioModel {

    private List<Object> listPersist;

    public GimnasioModel() {
        this.listPersist = new ArrayList();
    }

    public Long persist(Object persist) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Long id = null;
        try {
            transaction = session.beginTransaction();
            id = (Long) session.save(persist);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public List<Usuarios> getDatosUsurios(String loggin) {
        List<Usuarios> listUsuarios = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Long id = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Usuarios WHERE loggin = :logginC");
            query.setParameter("logginC", loggin);
            listUsuarios = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return listUsuarios;
    }

    public List<Object> getListPersist() {
        return listPersist;
    }

    public void setListPersist(List<Object> listPersist) {
        this.listPersist = listPersist;
    }

}
