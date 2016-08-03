package com.gimnasio.controller;

import com.gimnasio.util.HibernateUtil;
import com.gimnasio.model.Persona;
import javax.swing.JOptionPane;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 
 * @author emimaster16
 */
public class Operaciones {

    public void setRegistrarPersonas(Persona persona) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tr = session.beginTransaction();
        session.save(persona);
        tr.commit();
        session.close();
        JOptionPane.showMessageDialog(null, "insert OK");
    }
}
