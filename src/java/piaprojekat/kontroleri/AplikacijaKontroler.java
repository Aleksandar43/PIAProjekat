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
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
        proveraEMaila();
        proveraLozinke();
        if (brojGresakaKodRegistracije==0) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println("Korisnik napunjen");
            session.save(poljaZaRegistraciju);
            System.out.println("Korisnik sačuvan");
            session.getTransaction().commit();
            System.out.println("Korisnik poslat (commit)");
            session.close();
            adresa="index";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zahtev za registraciju uspešna", "Zahtev za registraciju je uspešno podnet. Molimo vas da sačekate da administrator odobri zahtev."));
        }
        brojGresakaKodRegistracije=0;
        poljaZaRegistraciju.setLozinka("");
        return adresa;
    }

    public void proveraKorisnickogImena(){
        String ki=poljaZaRegistraciju.getKorisnickoIme();
        Session session=HibernateUtil.getSessionFactory().openSession();
        Query query=session.createSQLQuery("select * from korisnik where korisničko_ime='"+ki+"'").addEntity(Korisnik.class);
        List list = query.list();
        session.close();
        if(list.size()>0){
            brojGresakaKodRegistracije++;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Korisničko ime zauzeto", null));
        }
    }
    
    public void proveraDatumaRodjenja(){
        Date datumRodjenja=poljaZaRegistraciju.getDatumRodjenja();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.YEAR, -18);
        Date pre18godina=c.getTime();
        if(datumRodjenja.after(pre18godina)){
            brojGresakaKodRegistracije++;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registrovani korisnici moraju imati bar 18 godina", "Registrovani korisnici moraju imati bar 18 godina"));
        }
    }
    
    public void proveraEMaila(){
        String eMail=poljaZaRegistraciju.geteMail();
        if(!Pattern.matches("[\\w.]+@\\w+\\.\\w+", eMail)){
            brojGresakaKodRegistracije++;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neispravna e-mail adresa", "Neispravna e-mail adresa"));
        }
    }
    
    public void proveraLozinke(){
        String lozinka=poljaZaRegistraciju.getLozinka();
        String regexDuzina=".{8,10}";
        String regexVelikoSlovo=".*[A-Z].*";
        String regexMalaSlova=".*[a-z].*[a-z].*[a-z].*";
        String regexCifra=".*[0-9].*";
        String regexSpecijalniZnak=".*[^A-Za-z0-9].*";
        String regexPrviZnak=".*[A-Za-z].*";
        String regex3UzastopnaZnaka=".*(.)\\1\\1.*";
        if(!(Pattern.matches(regexDuzina, lozinka)
                && Pattern.matches(regexVelikoSlovo, lozinka)
                && Pattern.matches(regexMalaSlova, lozinka)
                && Pattern.matches(regexCifra, lozinka)
                && Pattern.matches(regexSpecijalniZnak, lozinka)
                && Pattern.matches(regexPrviZnak, lozinka)
                && !Pattern.matches(regex3UzastopnaZnaka, lozinka))){
            brojGresakaKodRegistracije++;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neispravna lozinka", "Neispravna lozinka"));
        }
    }

    public void odobriKorisnika(ActionEvent event){
        Korisnik k=(Korisnik) event.getComponent().getAttributes().get("korisnik");
        k.setOdobren(1);
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(k);
        session.getTransaction().commit();
        session.close();
    }
    
    public String odobri(Korisnik k){
        k.setOdobren(1);
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(k);
        session.getTransaction().commit();
        session.close();
        return "administrator";
    }
    
    public String odbij(Korisnik k){
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Object o = session.load(Korisnik.class, k.getId());
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return "administrator";
    }
    
    public List<AvioKompanija> getAvioKompanije(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Query query=session.createSQLQuery("select * from aviokompanija").addEntity(AvioKompanija.class);
        List list = query.list();
        session.close();
        return list;
    }
    
    public List<Korisnik> getNeodobreniKorisnici(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Query query=session.createSQLQuery("select * from korisnik where odobren=0").addEntity(Korisnik.class);
        List list = query.list();
        session.close();
        return list;
    }
}
