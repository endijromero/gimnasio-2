package com.gimnasio.model;

import com.gimnasio.persistences.Paquetes;
import com.gimnasio.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
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

    public List<Object> getListPersist() {
        return listPersist;
    }

    public void setListPersist(List<Object> listPersist) {
        this.listPersist = listPersist;
    }

}
