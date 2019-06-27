package influenzer.bot.cetecop.model;

import influenzer.bot.generic.model.AbstractEntidade;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="uri")
public class Uri extends AbstractEntidade{
    
    @Column(name="nmproblema")
    private String nome;
    
    @Column(name="categoria")
    private String categoria;
    
    @Column(name="resolvidos")
    private int resolvidos;
    
    @Column(name="nivel")
    private int nivel;
    
    @Column(name="idUri")
    private int idUri;

    public Uri(){ }
    
    public Uri(Long idUri) {
        this.id = idUri;
    }

    public Uri(int idUri, String nome, String categoria, int resolvidos, int nivel) {
        this.idUri = idUri;
        this.nome = nome;
        this.categoria = categoria;
        this.resolvidos = resolvidos;
        this.nivel = nivel;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getResolvidos() {
        return resolvidos;
    }

    public void setResolvidos(int resolvidos) {
        this.resolvidos = resolvidos;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getIdUri() {
        return idUri;
    }

    public void setIdUri(int idUri) {
        this.idUri = idUri;
    }
    
    
    
}
