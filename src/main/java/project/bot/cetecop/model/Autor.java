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
@Table(name = "Cetecop_Autor")
@NamedQueries({
    @NamedQuery(name = Autor.FIND_ALL, query = "SELECT t FROM Autor t"),
    @NamedQuery(name = Autor.FIND_BY_ID, query = "SELECT t FROM Autor t WHERE t.id = :idAutor")})

public class Autor extends AbstractEntidade{

    public final static String FIND_ALL = "Autor.findAll";
    public final static String FIND_BY_ID = "Autor.findById";    
    
    @Basic(optional = false)
    @Column(name = "nmAutor")
    private String nmAutor;
   
    @OneToMany(mappedBy = "idAutor")
    private Collection<Problema> tbproblemaCollection;

    public Autor() {
    }

    public Autor(Long idAutor) {
        this.id = idAutor;
    }

    public Autor(Long idAutor, String nmAutor) {
        this.id = idAutor;
        this.nmAutor = nmAutor;
    }

    public String getNmAutor() {
        return nmAutor;
    }

    public void setNmAutor(String nmAutor) {
        this.nmAutor = nmAutor;
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
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uribot.Tbautor[ idAutor=" + id + " ]";
    }
    
}
