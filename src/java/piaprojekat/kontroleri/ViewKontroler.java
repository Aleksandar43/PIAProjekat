/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piaprojekat.kontroleri;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;
import piaprojekat.entiteti.AvioKompanija;
import piaprojekat.entiteti.Korisnik;
import piaprojekat.util.HibernateUtil;

/**
 *
 * @author Aleksandar
 */
@ManagedBean
@ViewScoped
public class ViewKontroler implements Serializable{
    private List<Korisnik> neodobreniKorisnici;
    private List<AvioKompanija> avioKompanije;
    public ViewKontroler() {
    }
    
    public List<AvioKompanija> getAvioKompanije(){
        if (avioKompanije==null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery("select * from aviokompanija order by ime").addEntity(AvioKompanija.class);
            List list = query.list();
            session.close();
            avioKompanije=list;
        }
        return avioKompanije;
    }
    
    public List<Korisnik> getNeodobreniKorisnici(){
        if (neodobreniKorisnici==null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery("select * from korisnik where odobren=0").addEntity(Korisnik.class);
            List list = query.list();
            session.close();
            neodobreniKorisnici=list;
        }
        return neodobreniKorisnici;
    }
    
    public void promenaPodatakaAvioKompanije(RowEditEvent e){
        AvioKompanija ak=(AvioKompanija)e.getObject();
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(ak);
        session.getTransaction().commit();
        session.close();
    }
}
