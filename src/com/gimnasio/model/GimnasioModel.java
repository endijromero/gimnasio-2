package com.gimnasio.model;

import com.gimnasio.persistences.Paquetes;
import com.gimnasio.persistences.Usuarios;
import com.gimnasio.util.HibernateUtil;
import com.gimnasio.util.Util;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Usuarios usu WHERE usu.loggin = :loggin");
        query.setParameter("loggin", loggin);
        List<Usuarios> listUsuarios = query.list();
        session.getTransaction().commit();
        return listUsuarios;
    }

    public void setTruncateTable(String table) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String sql = String.format("TRUNCATE TABLE %s", table);
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }

    public void setDeleteAllTable(List<String> listTable) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            for (String table : listTable) {
                String sql = String.format("DELETE FROM %s", table);
                session.createSQLQuery(sql).executeUpdate();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<TablaDto> getPaquetesDatosTabla(Integer idPquete) {
        return this.getPaquetesDatosTablaDto(idPquete);
    }

    public List<TablaDto> getPaquetesDatosTablaDto(Integer idPquete) throws HibernateException {
        List<TablaDto> lista = new ArrayList();
        List<Paquetes> listPaquetes = this.getPaquetesDatos(idPquete);
        Short index = 0;
        for (Paquetes paquete : listPaquetes) {
            TablaDto tabla = new TablaDto(
                    paquete.getId().toString(),
                    Util.getQuitaNULL(paquete.getNombre()).toUpperCase(),
                    Util.getQuitaNULL(paquete.getPrecioBase().toString()),
                    String.valueOf(paquete.getYnTiquetera()),
                    String.valueOf(paquete.getYnTiquetera()));
            tabla.setColor((index % 2 == 0 ? Color.LIGHT_GRAY : Color.GRAY));
            lista.add(tabla);
        }
        return lista;
    }

    public List<Paquetes> getPaquetesDatos(Integer idPaquete) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Paquetes.class, "paquete");
        if (!Util.getVacio(idPaquete.toString())) {
            criteria.add(Restrictions.eq("paquete.id", idPaquete));
        }
        criteria.addOrder(Order.asc("paquete.id"));
        List<Paquetes> result = criteria.list();
        session.getTransaction().commit();
        return result;
    }

    public List<Object> getListPersist() {
        return listPersist;
    }

    public void setListPersist(List<Object> listPersist) {
        this.listPersist = listPersist;
    }

}
