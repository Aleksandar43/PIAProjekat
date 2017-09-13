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

@Entity
@Table
public class Aerodrom implements Serializable {
    @Id
    @Column(name = "IATA_kod",length = 3)
    private String iataKod;
    @Column
    private String naziv;
    @Column
    private String grad;
    @Column(name = "država")
    private String drzava;
    @Column(name = "broj_pisti")
    private int brojPisti;

    public String getIataKod() {
        return iataKod;
    }

    public void setIataKod(String iataKod) {
        this.iataKod = iataKod;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public int getBrojPisti() {
        return brojPisti;
    }

    public void setBrojPisti(int brojPisti) {
        this.brojPisti = brojPisti;
    }

    @Override
    public String toString() {
        return "Aerodrom{" + "iataKod=" + iataKod + ", naziv=" + naziv + ", grad=" + grad + ", drzava=" + drzava + ", brojPisti=" + brojPisti + '}';
    }
    
    public String getIATAKodINaziv(){
        return iataKod+" - "+naziv;
    }
}
