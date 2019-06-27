package influenzer.bot.cetecop.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Evento.class)
public abstract class Evento_ extends influenzer.bot.generic.model.AbstractEntidade_ {

	public static volatile SingularAttribute<Evento, String> nmEvento;
	public static volatile SingularAttribute<Evento, Integer> ano;
	public static volatile CollectionAttribute<Evento, Problema> tbproblemaCollection;

	public static final String NM_EVENTO = "nmEvento";
	public static final String ANO = "ano";
	public static final String TBPROBLEMA_COLLECTION = "tbproblemaCollection";

}

