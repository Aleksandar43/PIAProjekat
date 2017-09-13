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
import piaprojekat.entiteti.Aerodrom;
import piaprojekat.entiteti.AvioKompanija;
import piaprojekat.entiteti.Korisnik;
import piaprojekat.entiteti.Let;
import piaprojekat.entiteti.LetPosada;
import piaprojekat.entiteti.Licenca;
import piaprojekat.entiteti.RadarskiCentar;
import piaprojekat.util.HibernateUtil;

@ManagedBean
@ApplicationScoped
public class AplikacijaKontroler {
    private Korisnik poljaZaRegistraciju=new Korisnik();
    private int brojGresakaKodRegistracije=0;
    private Aerodrom noviAerodrom=new Aerodrom();
    private AvioKompanija novaAvioKompanija=new AvioKompanija();
    private Licenca novaLicenca=new Licenca();
    private Let noviLet=new Let();
    private boolean noviLetCarter;
    private List<RadarskiCentar> noviLetRadarskiCentri=new ArrayList<>();
    private LetPosada noviLetPosada=new LetPosada();
    public AplikacijaKontroler() {
    }

    public Korisnik getPoljaZaRegistraciju() {
        return poljaZaRegistraciju;
    }

    public void setPoljaZaRegistraciju(Korisnik poljaZaRegistraciju) {
        this.poljaZaRegistraciju = poljaZaRegistraciju;
    }

    public Aerodrom getNoviAerodrom() {
        return noviAerodrom;
    }

    public void setNoviAerodrom(Aerodrom noviAerodrom) {
        this.noviAerodrom = noviAerodrom;
    }

    public AvioKompanija getNovaAvioKompanija() {
        return novaAvioKompanija;
    }

    public void setNovaAvioKompanija(AvioKompanija novaAvioKompanija) {
        this.novaAvioKompanija = novaAvioKompanija;
    }

    public Licenca getNovaLicenca() {
        return novaLicenca;
    }

    public void setNovaLicenca(Licenca novaLicenca) {
        this.novaLicenca = novaLicenca;
    }

    public Let getNoviLet() {
        return noviLet;
    }

    public void setNoviLet(Let noviLet) {
        this.noviLet = noviLet;
    }

    public boolean isNoviLetCarter() {
        return noviLetCarter;
    }

    public List<RadarskiCentar> getNoviLetRadarskiCentri() {
        return noviLetRadarskiCentri;
    }

    public void setNoviLetRadarskiCentri(List<RadarskiCentar> noviLetRadarskiCentri) {
        this.noviLetRadarskiCentri = noviLetRadarskiCentri;
    }

    public void setNoviLetCarter(boolean noviLetCarter) {
        this.noviLetCarter = noviLetCarter;
    }

    public LetPosada getNoviLetPosada() {
        return noviLetPosada;
    }

    public void setNoviLetPosada(LetPosada noviLetPosada) {
        this.noviLetPosada = noviLetPosada;
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
    
    public String dodajAerodrom(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        noviAerodrom.setIataKod(noviAerodrom.getIataKod().toUpperCase());
        Query provera=session.createSQLQuery("select * from aerodrom where iata_kod='"+noviAerodrom.getIataKod()+"'");
        if (provera.uniqueResult()==null) {
            session.save(noviAerodrom);
            session.getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aerodrom uspešno dodat", "Aerodrom uspešno dodat"));
            noviAerodrom.setIataKod("");
            noviAerodrom.setNaziv("");
            noviAerodrom.setGrad("");
            noviAerodrom.setDrzava("");
            noviAerodrom.setBrojPisti(0);
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aerodrom sa kodom \""+noviAerodrom.getIataKod()+"\" već postoji u bazi", "Aerodrom sa kodom \""+noviAerodrom.getIataKod()+"\" već postoji u bazi"));
        }
        session.close();
        return "administrator";
    }
    
    public String dodajAvioKompaniju(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query provera=session.createSQLQuery("select * from aviokompanija where ime='"+novaAvioKompanija.getIme()+"'");
        if (provera.uniqueResult()==null) {
            session.save(novaAvioKompanija);
            session.getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Avio-kompanija uspešno dodata", "Avio-kompanija uspešno dodata"));
            novaAvioKompanija.setIme("");
            novaAvioKompanija.setAdresa("");
            novaAvioKompanija.setDrzava("");
            novaAvioKompanija.setVebsajt("");
            novaAvioKompanija.seteMail("");
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avio-kompanija \""+novaAvioKompanija.getIme()+"\" već postoji u bazi", "Avio-kompanija \""+novaAvioKompanija.getIme()+"\" već postoji u bazi"));
        }
        session.close();
        return "administrator";
    }
    
    public String dodavanjeLicence(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        novaLicenca.setBrojLicence(novaLicenca.getBrojLicence().toUpperCase());
        Query provera=session.createSQLQuery("select * from licenca where broj_licence='"+novaLicenca.getBrojLicence()+"'");
        if (provera.uniqueResult()==null) {
            session.save(novaLicenca);
            session.getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Licenca uspešno dodata", "Licenca uspešno dodata"));
            novaLicenca.setBrojLicence("");
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Licenca \""+novaLicenca.getBrojLicence()+"\" već postoji u bazi", "Licenca \""+novaLicenca.getBrojLicence()+"\" već postoji u bazi"));
        }
        session.close();
        return "administrator";
    }
    
    public String dodajLet(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(noviLet);
        session.save(noviLetPosada);
        if(!noviLetCarter){
            Let ekstraLet = noviLet;
            LetPosada ekstraLetPosada=noviLetPosada;
            while(true){
                Date vremePoletanjaStaro = ekstraLet.getVremePoletanja();
                Calendar c=Calendar.getInstance();
                c.setTime(vremePoletanjaStaro);
                c.add(Calendar.DATE, 7);
                Date vremePoletanjaNovo = c.getTime();
                if(vremePoletanjaNovo.getMonth()!=vremePoletanjaStaro.getMonth()) break;
                ekstraLet.setVremePoletanja(vremePoletanjaNovo);
                ekstraLet.setId(ekstraLet.getId()+1);
                ekstraLetPosada.setIdLet(ekstraLet.getId());
                session.save(ekstraLet);
            }
        }
        session.getTransaction().commit();
        session.close();
        return "administrator";
    }
}
