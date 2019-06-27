package influenzer.bot.instagram.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfileHistory.class)
public abstract class ProfileHistory_ extends influenzer.bot.generic.model.AbstractEntidade_ {

	public static volatile SingularAttribute<ProfileHistory, Integer> qtdSeguindo;
	public static volatile SingularAttribute<ProfileHistory, Integer> qtdSeguidores;
	public static volatile SingularAttribute<ProfileHistory, Integer> qtdPostagens;
	public static volatile SingularAttribute<ProfileHistory, String> userName;

	public static final String QTD_SEGUINDO = "qtdSeguindo";
	public static final String QTD_SEGUIDORES = "qtdSeguidores";
	public static final String QTD_POSTAGENS = "qtdPostagens";
	public static final String USER_NAME = "userName";

}

