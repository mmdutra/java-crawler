package influenzer.bot.cetecop.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TestCase.class)
public abstract class TestCase_ extends influenzer.bot.generic.model.AbstractEntidade_ {

	public static volatile SingularAttribute<TestCase, String> conteudo;
	public static volatile SingularAttribute<TestCase, String> caso;
	public static volatile SingularAttribute<TestCase, Problema> idProblema;

	public static final String CONTEUDO = "conteudo";
	public static final String CASO = "caso";
	public static final String ID_PROBLEMA = "idProblema";

}

