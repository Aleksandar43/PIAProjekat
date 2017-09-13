/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piaprojekat.entiteti;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.NaturalId;

@Entity(name = "korisnik")
@Table(name = "korisnik")
public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    @Column(name = "korisničko_ime")
    @NaturalId
    private String korisnickoIme;
    @Column(name = "lozinka")
    private String lozinka;
    @Column(name = "ime")
    private String ime;
    @Column(name = "prezime")
    private String prezime;
    @Column(name = "pol")
    private String pol;
    @Column(name = "datum_rođenja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumRodjenja;
    @Column(name = "`e-mail`")
    private String eMail;
    @Column(name = "tip")
    private String tip;
    @Column(name = "id_kompanije")
    private Integer idKompanije;
    @Column(name = "odobren")
    private int odobren;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getIdKompanije() {
        return idKompanije;
    }

    public void setIdKompanije(Integer idKompanije) {
        this.idKompanije = idKompanije;
    }

    public int getOdobren() {
        return odobren;
    }

    public void setOdobren(int odobren) {
        this.odobren = odobren;
    }

    @Override
    public String toString() {
        return "Korisnik{" + "id=" + id + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", ime=" + ime + ", prezime=" + prezime + ", pol=" + pol + ", datumRodjenja=" + datumRodjenja + ", eMail=" + eMail + ", tip=" + tip + ", idKompanije=" + idKompanije + ", odobren=" + odobren + '}';
    }
    
    public String getImeIPrezime(){
        return ime+" "+prezime;
    }
}
