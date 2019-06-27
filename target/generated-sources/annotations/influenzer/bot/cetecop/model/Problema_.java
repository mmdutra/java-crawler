package influenzer.bot.cetecop.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Problema.class)
public abstract class Problema_ extends influenzer.bot.generic.model.AbstractEntidade_ {

	public static volatile SingularAttribute<Problema, String> output;
	public static volatile SingularAttribute<Problema, String> input;
	public static volatile SingularAttribute<Problema, Autor> idAutor;
	public static volatile SingularAttribute<Problema, Evento> idEvento;
	public static volatile SingularAttribute<Problema, String> nmproblema;
	public static volatile SingularAttribute<Problema, Categoria> idCategoria;
	public static volatile SingularAttribute<Problema, String> descricao;

	public static final String OUTPUT = "output";
	public static final String INPUT = "input";
	public static final String ID_AUTOR = "idAutor";
	public static final String ID_EVENTO = "idEvento";
	public static final String NMPROBLEMA = "nmproblema";
	public static final String ID_CATEGORIA = "idCategoria";
	public static final String DESCRICAO = "descricao";

}

