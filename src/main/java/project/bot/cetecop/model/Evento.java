package influenzer.bot.cetecop.model;

import influenzer.bot.generic.model.AbstractEntidade;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cetecop_Evento")
@NamedQueries({
    @NamedQuery(name = Evento.FIND_ALL, query = "SELECT t FROM Evento t"),
    @NamedQuery(name = Evento.FIND_BY_ID, query = "SELECT t FROM Evento t WHERE t.id = :idEvento"),
    @NamedQuery(name = Evento.FIND_BY_ANO, query = "SELECT t FROM Evento t WHERE t.ano = :ano")})

public class Evento extends AbstractEntidade{

    public final static String FIND_ALL = "Evento.findAll";
    public final static String FIND_BY_ID = "Evento.findById";    
    public final static String FIND_BY_ANO = "Evento.findByAno";   
    
    @Basic(optional = false)
    @Column(name = "nmEvento")
    private String nmEvento;
    @Basic(optional = false)
    @Column(name = "ano")
    private int ano;
    @OneToMany(mappedBy = "idEvento")
    private Collection<Problema> tbproblemaCollection;

    public Evento() {
    }

    public Evento(Long idEvento) {
        this.id = idEvento;
    }

    public Evento(Long idEvento, String nmEvento, int ano) {
        this.id = idEvento;
        this.nmEvento = nmEvento;
        this.ano = ano;
    }

    public String getNmEvento() {
        return nmEvento;
    }

    public void setNmEvento(String nmEvento) {
        this.nmEvento = nmEvento;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Collection<Problema> getTbproblemaCollection() {
        return tbproblemaCollection;
    }

    public void setTbproblemaCollection(Collection<Problema> tbproblemaCollection) {
        this.tbproblemaCollection = tbproblemaCollection;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uribot.Tbevento[ idEvento=" + id + " ]";
    }
    
}
