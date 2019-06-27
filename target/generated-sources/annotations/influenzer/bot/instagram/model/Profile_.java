package influenzer.bot.instagram.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Profile.class)
public abstract class Profile_ extends influenzer.bot.generic.model.AbstractTemporalEntidade_ {

	public static volatile SingularAttribute<Profile, Integer> qtdSeguindo;
	public static volatile SingularAttribute<Profile, Integer> qtdSeguidores;
	public static volatile SingularAttribute<Profile, PhotoVideo> fotoPerfil;
	public static volatile SingularAttribute<Profile, Integer> qtdPostagens;
	public static volatile SingularAttribute<Profile, Date> bePublic;
	public static volatile CollectionAttribute<Profile, ProfileCite> feedsComentados;
	public static volatile SingularAttribute<Profile, String> nome;
	public static volatile SingularAttribute<Profile, String> userName;
	public static volatile SingularAttribute<Profile, Boolean> privado;
	public static volatile CollectionAttribute<Profile, Feed> feedsCurtidos;

	public static final String QTD_SEGUINDO = "qtdSeguindo";
	public static final String QTD_SEGUIDORES = "qtdSeguidores";
	public static final String FOTO_PERFIL = "fotoPerfil";
	public static final String QTD_POSTAGENS = "qtdPostagens";
	public static final String BE_PUBLIC = "bePublic";
	public static final String FEEDS_COMENTADOS = "feedsComentados";
	public static final String NOME = "nome";
	public static final String USER_NAME = "userName";
	public static final String PRIVADO = "privado";
	public static final String FEEDS_CURTIDOS = "feedsCurtidos";

}

