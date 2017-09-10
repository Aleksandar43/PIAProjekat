/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piaprojekat.entiteti;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "aviokompanija")
@Table(name = "aviokompanija")
public class AvioKompanija implements Serializable{
    @Id
    private int id;
    @Column(unique = true)
    private String ime;
    @Column
    private String adresa;
    @Column(name = "država")
    private String drzava;
    @Column
    private String vebsajt;
    @Column(name = "`e-mail`")
    private String eMail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getVebsajt() {
        return vebsajt;
    }

    public void setVebsajt(String vebsajt) {
        this.vebsajt = vebsajt;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "AvioKompanija{" + "id=" + id + ", ime=" + ime + ", adresa=" + adresa + ", drzava=" + drzava + ", vebsajt=" + vebsajt + ", eMail=" + eMail + '}';
    }
}
