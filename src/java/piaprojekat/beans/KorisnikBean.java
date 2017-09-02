/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piaprojekat.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import piaprojekat.entiteti.Korisnik;
import piaprojekat.util.HibernateUtil;

/**
 *
 * @author Aleksandar
 */
@ManagedBean
@SessionScoped
public class KorisnikBean {
    private Korisnik korisnik=new Korisnik();
    private String greska;
    public KorisnikBean() {
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getGreska() {
        return greska;
    }
    
    public String login(){
        greska="";
        System.out.println("Korisnik: "+korisnik.getKorisnickoIme());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
//        Criteria c=session.createCriteria(Korisnik.class);
//        c.add(Restrictions.eq("korisnickoIme", korisnik.getKorisnickoIme()));
//        Korisnik k=(Korisnik) c.uniqueResult();
        Query query = session.createSQLQuery("select * from korisnik where `korisničko_ime`='"+korisnik.getKorisnickoIme()+"'");
        Object k=query.uniqueResult();
        if(k==null){
            greska="Pogrešno";
            return "index";
        }
        //korisnik=k;
        return "loginTemp.xhtml?faces-redirect=true";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        korisnik=null;
        return "index.xhtml?faces-redirect=true";
    }
}
