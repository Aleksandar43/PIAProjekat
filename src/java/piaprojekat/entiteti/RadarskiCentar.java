/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piaprojekat.entiteti;

import javax.persistence.Column;
import javax.persistence.Id;

public class RadarskiCentar {
    @Id
    private String id;
    @Column
    private String naziv;
    @Column(name = "država")
    private String drzava;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    @Override
    public String toString() {
        return "RadarskiCentar{" + "id=" + id + ", naziv=" + naziv + ", drzava=" + drzava + '}';
    }
}
