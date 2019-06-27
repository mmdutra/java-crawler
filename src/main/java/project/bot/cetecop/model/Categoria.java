package influenzer.bot.cetecop.model;

import influenzer.bot.generic.model.AbstractEntidade;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "Cetecop_Categoria")
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.id = :id"),
    @NamedQuery(name = "Categoria.findByDescricao", query = "SELECT c FROM Categoria c WHERE c.descricao = :descricao")})
public class Categoria extends AbstractEntidade {
    
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;

    public Categoria(String descricao) {
        this.descricao = descricao;
    }
    
    public Categoria(){
        this("");
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id!= null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id!= null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "influenzer.bot.cetecop.model.Categoria[ idCategoria=" + id + " ]";
    }
    
}
