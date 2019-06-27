package influenzer.bot.cetecop.model;

import influenzer.bot.generic.model.AbstractEntidade;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Catecop_Problema")
@NamedQueries({
    @NamedQuery(name = "Problema.findAll", query = "SELECT p FROM Problema p")})
public class Problema extends AbstractEntidade {

    @Lob
    @Size(max = 65535)
    @Column(name = "nmproblema")
    private String nmproblema;    
    @Lob
    @Column(name = "descricao",nullable = true, columnDefinition = "TEXT")
    private String descricao;
    @Lob
    @Column(name = "input",nullable = true, columnDefinition = "TEXT")
    private String input;
    @Lob
    @Column(name = "output",nullable = true, columnDefinition = "TEXT")
    private String output;
    @JoinColumn(name = "idEvento", referencedColumnName = "id")
    @ManyToOne
    private Evento idEvento;
    @JoinColumn(name = "idAutor", referencedColumnName = "id")
    @ManyToOne
    private Autor idAutor;
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    @ManyToOne
    private Categoria idCategoria;

    public Problema() {
    }

    public String getNmproblema() {
        return nmproblema;
    }

    public void setNmproblema(String nmproblema) {
        this.nmproblema = nmproblema;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    public Autor getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Autor idAutor) {
        this.idAutor = idAutor;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }
    
    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
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
        if (!(object instanceof Problema)) {
            return false;
        }
        Problema other = (Problema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "influenzer.bot.cetecop.model.Problema[ idProblema=" + id + " ]";
    }
    
}
