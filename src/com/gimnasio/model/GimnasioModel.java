package com.gimnasio.model;

import com.gimnasio.persistences.Usuarios;
import com.gimnasio.util.HibernateUtil;
import com.gimnasio.util.Util;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

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

        //        List<Usuarios> listUsuarios = null;
        //        Session session = HibernateUtil.getSessionFactory().openSession();
        //        Transaction transaction = null;
        //        Long id = null;
        //        try {
        //            transaction = session.beginTransaction();
        //            Query query = session.createQuery("FROM Usuarios WHERE loggin = :logginC");
        //            query.setParameter("logginC", loggin);
        //            listUsuarios = query.list();
        //        } catch (HibernateException e) {
        //            transaction.rollback();
        //            e.printStackTrace();
        //        } finally {
        //            transaction.commit();
        //            session.close();
        //        }
        //        return listUsuarios;
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

    /*
    public List<ComboDto> getGruposEnumerado() throws HibernateException {
        List<ComboDto> lista = new ArrayList();
        List<Grupos> listGrupos = this.getGruposConsulta();
        for (Grupos grupo : listGrupos) {
            ComboDto dto = new ComboDto(String.valueOf(grupo.getCodGrupo()), grupo.getNombre());
            lista.add(dto);
        }
        return lista;
    }

    public List<Grupos> getGruposConsulta() throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Grupos");
        List<Grupos> listaGrupos = query.list();
        session.getTransaction().commit();
        return listaGrupos;
    }

    public List<Usuarios> getDatosUsuarios(String loggin, Short tipoUsuario) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Usuarios usu WHERE usu.loggin = :loggin");
        query.setParameter("loggin", loggin);
        List<Usuarios> listUsuarios = query.list();
        session.getTransaction().commit();
        return listUsuarios;
    }

    public List<TablaDto> getUsuariosEstudiantesBasicoTabla(String codigo, String nombresB, String apellidosB, Short tipoPerfilB, String grupoB) {
        return this.getUsuariosEstudiantesBasicoTabla(codigo, nombresB, apellidosB, tipoPerfilB, grupoB, null);
    }

    public List<TablaDto> getUsuariosEstudiantesBasicoTabla(String codigo, String nombresB, String apellidosB, Short tipoPerfilB, String grupoB, List<String> codigos) {
        List<TablaDto> lista = new ArrayList();
        List<AlumnoGrupo> listAlumnoGrupos = this.getUsuariosEstudiantes(codigo, nombresB, apellidosB, tipoPerfilB, grupoB, codigos);
        for (AlumnoGrupo al : listAlumnoGrupos) {
            TablaDto tabla = new TablaDto(
                    al.getUsuarios().getCodUsuario().toString(),
                    Util.getQuitaNULL(al.getUsuarios().getPriApellido()).toUpperCase(),
                    Util.getQuitaNULL(al.getUsuarios().getSegApellido()).toUpperCase(),
                    Util.getQuitaNULL(al.getUsuarios().getPriNombre()).toUpperCase(),
                    Util.getQuitaNULL(al.getUsuarios().getSegNombre()).toUpperCase(),
                    al.getGrupos().getNombre(),
                    ((al.getUsuarios().getUsuariosHuellas() != null && al.getUsuarios().getUsuariosHuellas().getHuella() != null) ? "Si" : "No"));
            if ((al.getUsuarios().getUsuariosHuellas() != null && al.getUsuarios().getUsuariosHuellas().getHuella() != null)) {
                tabla.setColor(Color.YELLOW);
            } else {
                tabla.setColor(Color.WHITE);
            }
            lista.add(tabla);
        }
        return lista;
    }

  
    public List<UsuariosDto> getUsuariosHuellasConsulta() throws HibernateException {

        List<UsuariosDto> listUsuariosDto = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(UsuariosHuellas.class, "uh");
            criteria.createAlias("uh.usuarios", "usu");
            criteria.addOrder(Order.asc("usu.codUsuario"));
            List<UsuariosHuellas> result = criteria.list();
            result.stream().map((user) -> {
                UsuariosDto usuario = new UsuariosDto();
                usuario.setCodigoUsuario(user.getUsuarios().getCodUsuario());
                usuario.setPrimerNombre(user.getUsuarios().getPriNombre());
                usuario.setSegundoNombre(user.getUsuarios().getSegNombre());
                usuario.setPrimerApellido(user.getUsuarios().getPriApellido());
                usuario.setSegundoApellido(user.getUsuarios().getSegApellido());
                usuario.setTipoUsuario(user.getUsuarios().getTipoUsuario());
                usuario.setLoggin(user.getUsuarios().getLoggin());
                usuario.setPassword(user.getUsuarios().getPassword());
                usuario.setHuella(user.getHuella());
                usuario.setTieneHuella("Si");
                usuario.setDedo(user.getDedo());
                return usuario;
            }).forEach((usuario) -> {
                listUsuariosDto.add(usuario);
            });
        } catch (HibernateException ex) {
            Logger.getLogger(GimnasioModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.getTransaction().commit();
        }
        return listUsuariosDto;
    }

    public List<AlumnoGrupo> getUsuariosEstudiantes(String codigo,
            String nombresB,
            String apellidosB,
            Short tipoPerfilB,
            String grupoB,
            List<String> listaCodigos) {
        List<AlumnoGrupo> listAlumnoGrupos = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AlumnoGrupo.class, "al");
            criteria.createAlias("al.grupos", "gru");
            criteria.createAlias("al.usuarios", "usu");
            criteria.createAlias("usu.usuariosHuellas", "uh", JoinType.LEFT_OUTER_JOIN);
            if (!Util.getVacio(grupoB)) {
                criteria.add(Restrictions.eq("gru.codGrupo", Integer.parseInt(grupoB)));
            }
            if (!Util.getVacio(codigo)) {
                criteria.add(Restrictions.eq("usu.codUsuario", Integer.parseInt(codigo)));
            }
            if (!Util.getVacio(nombresB)) {
                criteria.add(Restrictions.ilike("CONCAT(u.priNombre, ' ', COALESCE(u.segNombre, ''))", "%" + nombresB.toUpperCase() + "%"));
            }
            if (!Util.getVacio(apellidosB)) {
                criteria.add(Restrictions.ilike("UPPER(CONCAT(u.priApellido,' ', COALESCE(u.segApellido, '')))", "%" + apellidosB.toUpperCase() + "%"));
            }
            if (!Util.getVacio(tipoPerfilB.toString())) {
                criteria.add(Restrictions.eq("usu.tipoUsuario", tipoPerfilB));
            }
            if (listaCodigos != null && listaCodigos.size() > 0) {
                criteria.add(Restrictions.in("usu.codUsuario", listaCodigos));
            }
            criteria.addOrder(Order.asc("usu.priApellido"));
            criteria.addOrder(Order.asc("usu.segApellido"));
            criteria.addOrder(Order.asc("usu.priNombre"));
            criteria.addOrder(Order.asc("usu.segNombre"));
            listAlumnoGrupos = criteria.list();
            JOptionPane.showMessageDialog(null, "Numero de registro: " + listAlumnoGrupos.size());

        } catch (HibernateException ex) {
            Logger.getLogger(GimnasioModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.getTransaction().commit();
        }
        return listAlumnoGrupos;
    }

    public void setInsertMotivos(AsMotivos motivo) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        session.save(motivo);
        tx.commit();
        session.close();
    }

    public void setInsertAlumnoGrupos(AlumnoGrupo alumnoGrupo) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        session.save(alumnoGrupo);
        tx.commit();
        session.close();
    }

    public void setPersist(JTextArea text) {
        int acciones = 1;
        int total = this.listPersist.size();
        if (text != null) {
            text.setText("Se afectaran " + total + " Registros de la base de datos\n" + text.getText());
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Object persist : this.listPersist) {
                try {
                    session.save(persist);
                    if (text != null) {
                        text.setText("Registro " + acciones + " afectado de " + total + "\n" + text.getText());
                        acciones++;
                    }
                } catch (Exception e) {
                    if (text != null) {
                        text.setText("El registro " + acciones + " presenta errores para el proceso " + total + "\n" + text.getText());
                        System.out.println(session.getClass());
                        acciones++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(HibernateException.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            tx.commit();
            session.close();
        }
    }
     */
    public List<Object> getListPersist() {
        return listPersist;
    }

    public void setListPersist(List<Object> listPersist) {
        this.listPersist = listPersist;
    }

}
