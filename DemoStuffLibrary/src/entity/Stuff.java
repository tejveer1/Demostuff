/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sidhant
 */
@Entity
@XmlRootElement
public class Stuff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String stuff;
    private String moreStuff;
    private String Tuna;
    private String Delta;

    public String getDelta() {
        return Delta;
    }

    public void setDelta(String Delta) {
        this.Delta = Delta;
    }

    public String getLambda() {
        return Lambda;
    }

    public void setLambda(String Lambda) {
        this.Lambda = Lambda;
    }
    private String Lambda;

    public String getTuna() {
        return Tuna;
    }

    public void setTuna(String Tuna) {
        this.Tuna = Tuna;
    }

    public String getStuff() {
        return stuff;
    }

    public void setStuff(String stuff) {
        this.stuff = stuff;
    }

    public String getMoreStuff() {
        return moreStuff;
    }

    public void setMoreStuff(String moreStuff) {
        this.moreStuff = moreStuff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stuff)) {
            return false;
        }
        Stuff other = (Stuff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Stuff[ id=" + id + " ]";
    }
    
}
