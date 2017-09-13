/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piaprojekat.entiteti;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

public class Avion implements Serializable{
    @Id
    private int id;
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "tip")
    private String tip;
    @Column(name = "id_proizvođača")
    private int idProizvođaca;
    @Column(name = "id_vlasnika")
    private int idVlasnika;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getIdProizvođaca() {
        return idProizvođaca;
    }

    public void setIdProizvođaca(int idProizvođaca) {
        this.idProizvođaca = idProizvođaca;
    }

    public int getIdVlasnika() {
        return idVlasnika;
    }

    public void setIdVlasnika(int idVlasnika) {
        this.idVlasnika = idVlasnika;
    }

    @Override
    public String toString() {
        return "Avion{" + "id=" + id + ", naziv=" + naziv + ", tip=" + tip + ", idProizvo\u0111aca=" + idProizvođaca + ", idVlasnika=" + idVlasnika + '}';
    }
}
