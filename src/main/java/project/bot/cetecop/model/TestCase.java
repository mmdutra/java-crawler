package influenzer.bot.cetecop.model;

import influenzer.bot.generic.model.AbstractEntidade;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Cetecop_TestCase")
@NamedQueries({
    @NamedQuery(name = TestCase.FIND_ALL, query = "SELECT t FROM TestCase t"),
    @NamedQuery(name = TestCase.FIND_BY_ID, query = "SELECT t FROM TestCase t WHERE t.id = :idTestcase")})
public class TestCase  extends AbstractEntidade {

    public final static String FIND_ALL = "TestCase.findAll";
    public final static String FIND_BY_ID = "TestCase.findById";

    
    @Column(name = "conteudo")
    private String conteudo;
    @Basic(optional = false)
    @Column(name = "caso")
    private String caso;
    @JoinColumn(name = "idProblema", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Problema idProblema;

    public TestCase() {
    }

    public TestCase(Long idTestcase) {
        this.id = idTestcase;
    }

    public TestCase(Long idTestcase, String caso) {
        this.id = idTestcase;
        this.caso = caso;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }

    public Problema getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(Problema idProblema) {
        this.idProblema = idProblema;
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
        if (!(object instanceof TestCase)) {
            return false;
        }
        TestCase other = (TestCase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uribot.model.bean.Tbtestcase[ idTestcase=" + id + " ]";
    }
    
}
