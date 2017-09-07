/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piaprojekat.kontroleri;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
public class KorisnikKontroler implements Serializable{
    private Korisnik korisnik;
    private String korisnickoImePrijava,lozinkaPrijava;
    private String greska;
    public KorisnikKontroler() {
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
        if(korisnik!=null) return korisnik.getTip()+".xhtml?faces-redirect=true";
        else return null;
    }
    
    public void prijava(ActionEvent e){
        boolean loggedIn;
        FacesMessage message,message2 = null;
        RequestContext context=RequestContext.getCurrentInstance();
        greska="";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query2 = session.createSQLQuery("select * from korisnik where `korisničko_ime`='"+korisnickoImePrijava+"' and `lozinka`='"+lozinkaPrijava+"'").addEntity(Korisnik.class);
        Korisnik k2=(Korisnik) query2.uniqueResult();
        korisnik=k2;
        session.close();
        if(korisnik!=null) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", korisnik.getKorisnickoIme());
        } else {
            System.out.println("k=null");
            greska="Neispravni podaci";
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
            message2 = new FacesMessage(FacesMessage.SEVERITY_WARN, "Greška", "Pogrešni podaci");
        }
         
        //FacesContext.getCurrentInstance().addMessage(null, message);
        if(message2!=null) FacesContext.getCurrentInstance().addMessage("prijavaMeni:prijavaDugme", message2);
        context.addCallbackParam("loggedIn", loggedIn);
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        korisnik=null;
        return "index.xhtml?faces-redirect=true";
    }    
}
