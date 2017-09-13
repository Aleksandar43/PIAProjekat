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

@Entity(name = "licenca")
@Table(name = "licenca")
public class Licenca implements Serializable {
    @Id
    @Column(name = "broj_licence",length = 9)
    private String brojLicence;
    @Column(name = "id_pilota")
    private int idPilota;

    public String getBrojLicence() {
        return brojLicence;
    }

    public void setBrojLicence(String brojLicence) {
        this.brojLicence = brojLicence;
    }

    public int getIdPilota() {
        return idPilota;
    }

    public void setIdPilota(int idPilota) {
        this.idPilota = idPilota;
    }

    @Override
    public String toString() {
        return "Licenca{" + "brojLicence=" + brojLicence + ", idPilota=" + idPilota + '}';
    }
}
