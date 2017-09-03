/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piaprojekat.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;
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
    private String korisnickoImePrijava,lozinkaPrijava;
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

    public String getKorisnickoImePrijava() {
        return korisnickoImePrijava;
    }

    public void setKorisnickoImePrijava(String korisnickoImePrijava) {
        this.korisnickoImePrijava = korisnickoImePrijava;
    }

    public String getLozinkaPrijava() {
        return lozinkaPrijava;
    }

    public void setLozinkaPrijava(String lozinkaPrijava) {
        this.lozinkaPrijava = lozinkaPrijava;
    }
    
    public String login(){
        if(korisnik!=null) return "loginTemp.xhtml?faces-redirect=true";
        else return null;
    }
    
    public void prijava(ActionEvent e){
        boolean loggedIn;
        FacesMessage message;
        RequestContext context=RequestContext.getCurrentInstance();
        greska="";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query2 = session.createSQLQuery("select * from korisnik where `korisničko_ime`='"+korisnickoImePrijava+"' and `lozinka`='"+lozinkaPrijava+"'").addEntity(Korisnik.class);
        Korisnik k2=(Korisnik) query2.uniqueResult();
        korisnik=k2;
        if(korisnik!=null) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", korisnik.getKorisnickoIme());
            korisnik=k2;
        } else {
            System.out.println("k=null");
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        korisnik=null;
        return "index.xhtml?faces-redirect=true";
    }
}
