/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piaprojekat.entiteti;

import javax.persistence.Column;
import javax.persistence.Id;

public class LetPosada {
    @Id
    @Column(name = "id_let")
    private int idLet;
    @Column
    private Integer pilot;
    @Column
    private Integer kopilot;
    @Column
    private Integer stjuardesa1;
    @Column
    private Integer stjuardesa2;
    @Column
    private Integer stjuardesa3;
    @Column
    private Integer stjuardesa4;
    @Column
    private Integer stjuardesa5;

    public int getIdLet() {
        return idLet;
    }

    public void setIdLet(int idLet) {
        this.idLet = idLet;
    }

    public Integer getPilot() {
        return pilot;
    }

    public void setPilot(Integer pilot) {
        this.pilot = pilot;
    }

    public Integer getKopilot() {
        return kopilot;
    }

    public void setKopilot(Integer kopilot) {
        this.kopilot = kopilot;
    }

    public Integer getStjuardesa1() {
        return stjuardesa1;
    }

    public void setStjuardesa1(Integer stjuardesa1) {
        this.stjuardesa1 = stjuardesa1;
    }

    public Integer getStjuardesa2() {
        return stjuardesa2;
    }

    public void setStjuardesa2(Integer stjuardesa2) {
        this.stjuardesa2 = stjuardesa2;
    }

    public Integer getStjuardesa3() {
        return stjuardesa3;
    }

    public void setStjuardesa3(Integer stjuardesa3) {
        this.stjuardesa3 = stjuardesa3;
    }

    public Integer getStjuardesa4() {
        return stjuardesa4;
    }

    public void setStjuardesa4(Integer stjuardesa4) {
        this.stjuardesa4 = stjuardesa4;
    }

    public Integer getStjuardesa5() {
        return stjuardesa5;
    }

    public void setStjuardesa5(Integer stjuardesa5) {
        this.stjuardesa5 = stjuardesa5;
    }

    @Override
    public String toString() {
        return "LetPosada{" + "idLet=" + idLet + ", pilot=" + pilot + ", kopilot=" + kopilot + ", stjuardesa1=" + stjuardesa1 + ", stjuardesa2=" + stjuardesa2 + ", stjuardesa3=" + stjuardesa3 + ", stjuardesa4=" + stjuardesa4 + ", stjuardesa5=" + stjuardesa5 + '}';
    }
}
