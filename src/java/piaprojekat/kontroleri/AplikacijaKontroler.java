/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piaprojekat.kontroleri;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.hibernate.Query;
import org.hibernate.Session;
import piaprojekat.entiteti.AvioKompanija;
import piaprojekat.entiteti.Korisnik;
import piaprojekat.util.HibernateUtil;

/**
 *
 * @author Aleksandar
 */
@ManagedBean
@ApplicationScoped
public class AplikacijaKontroler {
    private Korisnik poljaZaRegistraciju=new Korisnik();
    private int brojGresakaKodRegistracije=0;
    public AplikacijaKontroler() {
    }

    public Korisnik getPoljaZaRegistraciju() {
        return poljaZaRegistraciju;
    }

    public void setPoljaZaRegistraciju(Korisnik poljaZaRegistraciju) {
        this.poljaZaRegistraciju = poljaZaRegistraciju;
    }
    
    public String registracija(){
        String adresa=null;
        System.out.println("Dugme kliknuto");
        proveraKorisnickogImena();
        proveraDatumaRodjenja();
        if (brojGresakaKodRegistracije==0) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println("Korisnik napunjen");
            session.save(poljaZaRegistraciju);
            System.out.println("Korisnik sačuvan");
            session.getTransaction().commit();
            System.out.println("Korisnik poslat (commit)");
            session.close();
            adresa="index.xhtml?faces-redirect=true";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zahtev za registraciju uspešna", "Zahtev za registraciju je uspešno podnet. Molimo vas da sačekate da administrator odobri zahtev."));
        }
        brojGresakaKodRegistracije=0;
        poljaZaRegistraciju.setLozinka("");
        return adresa;
    }

    public List<AvioKompanija> getAvioKompanije(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Query query=session.createSQLQuery("select * from aviokompanija").addEntity(AvioKompanija.class);
        List list = query.list();
        return list;
    }
    
    public void proveraKorisnickogImena(){
        String ki=poljaZaRegistraciju.getKorisnickoIme();
        Session session=HibernateUtil.getSessionFactory().openSession();
        Query query=session.createSQLQuery("select * from korisnik where korisničko_ime='"+ki+"'").addEntity(Korisnik.class);
        List list = query.list();
        if(list.size()>0){
            brojGresakaKodRegistracije++;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Korisničko ime zauzeto", null));
        }
    }
    
    public void proveraDatumaRodjenja(){
        Date datumRodjenja=(Date) poljaZaRegistraciju.getDatumRodjenja();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.YEAR, -18);
        Date pre18godina=c.getTime();
        if(datumRodjenja.after(pre18godina)){
            brojGresakaKodRegistracije++;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registrovani korisnici moraju imati bar 18 godina", "Registrovani korisnici moraju imati bar 18 godina"));
        }
    }
}
