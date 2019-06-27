package influenzer.bot.instagram.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Location.class)
public abstract class Location_ extends influenzer.bot.generic.model.AbstractTemporalEntidade_ {

	public static volatile SingularAttribute<Location, String> link;
	public static volatile SingularAttribute<Location, String> nome;
	public static volatile SingularAttribute<Location, String> idInstagram;
	public static volatile SingularAttribute<Location, String> descricao;

	public static final String LINK = "link";
	public static final String NOME = "nome";
	public static final String ID_INSTAGRAM = "idInstagram";
	public static final String DESCRICAO = "descricao";

}

