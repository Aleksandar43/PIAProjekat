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

@Entity(name="let")
@Table(name="let")
public class Let implements Serializable {
    @Id
    @Column
    private int id;
    @Column
    private String status;
    @Column(name="polazni_aerodrom")
    private String polazniAerodrom;
    @Column(name="polazni_terminal")
    private String polazniTerminal;
    @Column(name="polazni_gejt")
    private String polazniGejt;
    @Column(name="odredišni_aerodrom")
    private String odredisniAerodrom;
    @Column(name="odredišni_terminal")
    private String odredisniTerminal;
    @Column(name="odredišni_gejt")
    private String odredisniGejt;
    @Column(name="id_aviona")
    private int idAviona;
    @Column(name="id_kompanije")
    private int idKompanije;
    @Column(name="standardna_dužina_trajanja")
    private int standardnaDuzinaTrajanja;
    @Column(name="vreme_poletanja")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date vremePoletanja;
    @Column(name="planirano_vreme_dolaska")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date planiranoVremeDolaska;
    @Column(name="očekivano_vreme_dolaska")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ocekivanoVremeDolaska;
    @Column(name="vreme_sletanja")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date vremeSletanja;
    @Column(name="prinudno_sletanje_aerodrom")
    private Integer prinudnoSletanjeAerodrom;    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPolazniAerodrom() {
        return polazniAerodrom;
    }

    public void setPolazniAerodrom(String polazniAerodrom) {
        this.polazniAerodrom = polazniAerodrom;
    }

    public String getPolazniTerminal() {
        return polazniTerminal;
    }

    public void setPolazniTerminal(String polazniTerminal) {
        this.polazniTerminal = polazniTerminal;
    }

    public String getPolazniGejt() {
        return polazniGejt;
    }

    public void setPolazniGejt(String polazniGejt) {
        this.polazniGejt = polazniGejt;
    }

    public String getOdredisniAerodrom() {
        return odredisniAerodrom;
    }

    public void setOdredisniAerodrom(String odredisniAerodrom) {
        this.odredisniAerodrom = odredisniAerodrom;
    }

    public String getOdredisniTerminal() {
        return odredisniTerminal;
    }

    public void setOdredisniTerminal(String odredisniTerminal) {
        this.odredisniTerminal = odredisniTerminal;
    }

    public String getOdredisniGejt() {
        return odredisniGejt;
    }

    public void setOdredisniGejt(String odredisniGejt) {
        this.odredisniGejt = odredisniGejt;
    }

    public int getIdAviona() {
        return idAviona;
    }

    public void setIdAviona(int idAviona) {
        this.idAviona = idAviona;
    }

    public int getIdKompanije() {
        return idKompanije;
    }

    public void setIdKompanije(int idKompanije) {
        this.idKompanije = idKompanije;
    }

    public int getStandardnaDuzinaTrajanja() {
        return standardnaDuzinaTrajanja;
    }

    public void setStandardnaDuzinaTrajanja(int standardnaDuzinaTrajanja) {
        this.standardnaDuzinaTrajanja = standardnaDuzinaTrajanja;
    }

    public Date getVremePoletanja() {
        return vremePoletanja;
    }

    public void setVremePoletanja(Date vremePoletanja) {
        this.vremePoletanja = vremePoletanja;
    }

    public Date getPlaniranoVremeDolaska() {
        return planiranoVremeDolaska;
    }

    public void setPlaniranoVremeDolaska(Date planiranoVremeDolaska) {
        this.planiranoVremeDolaska = planiranoVremeDolaska;
    }

    public Date getOcekivanoVremeDolaska() {
        return ocekivanoVremeDolaska;
    }

    public void setOcekivanoVremeDolaska(Date ocekivanoVremeDolaska) {
        this.ocekivanoVremeDolaska = ocekivanoVremeDolaska;
    }

    public Date getVremeSletanja() {
        return vremeSletanja;
    }

    public void setVremeSletanja(Date vremeSletanja) {
        this.vremeSletanja = vremeSletanja;
    }

    public Integer getPrinudnoSletanjeAerodrom() {
        return prinudnoSletanjeAerodrom;
    }

    public void setPrinudnoSletanjeAerodrom(Integer prinudnoSletanjeAerodrom) {
        this.prinudnoSletanjeAerodrom = prinudnoSletanjeAerodrom;
    }

    @Override
    public String toString() {
        return "Let{" + "id=" + id + ", status=" + status + ", polazniAerodrom=" + polazniAerodrom + ", polazniTerminal=" + polazniTerminal + ", polazniGejt=" + polazniGejt + ", odredisniAerodrom=" + odredisniAerodrom + ", odredisniTerminal=" + odredisniTerminal + ", odredisniGejt=" + odredisniGejt + ", idAviona=" + idAviona + ", idKompanije=" + idKompanije + ", standardnaDuzinaTrajanja=" + standardnaDuzinaTrajanja + ", vremePoletanja=" + vremePoletanja + ", planiranoVremeDolaska=" + planiranoVremeDolaska + ", ocekivanoVremeDolaska=" + ocekivanoVremeDolaska + ", vremeSletanja=" + vremeSletanja + ", prinudnoSletanjeAerodrom=" + prinudnoSletanjeAerodrom + '}';
    }
}
