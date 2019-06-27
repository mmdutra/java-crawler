package influenzer.bot.instagram.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Feed.class)
public abstract class Feed_ extends influenzer.bot.generic.model.AbstractEntidade_ {

	public static volatile SingularAttribute<Feed, Integer> numVisualizao;
	public static volatile SingularAttribute<Feed, TipoFeed> tipo;
	public static volatile SingularAttribute<Feed, Location> localizacao;
	public static volatile SingularAttribute<Feed, FeedData> dados;
	public static volatile SingularAttribute<Feed, TipoDadoFeed> fotoOuVideo;
	public static volatile SingularAttribute<Feed, Profile> donoDoFeed;
	public static volatile SingularAttribute<Feed, String> url;
	public static volatile SingularAttribute<Feed, String> descricao;
	public static volatile SetAttribute<Feed, Tag> tags;
	public static volatile SetAttribute<Feed, Profile> citados;
	public static volatile SingularAttribute<Feed, Date> dataPostagem;
	public static volatile CollectionAttribute<Feed, PhotoVideo> photosVideos;
	public static volatile SingularAttribute<Feed, Integer> numCurtidas;
	public static volatile CollectionAttribute<Feed, ProfileCite> comentarios;
	public static volatile SingularAttribute<Feed, String> urlImage;
	public static volatile SetAttribute<Feed, Profile> likes;

	public static final String NUM_VISUALIZAO = "numVisualizao";
	public static final String TIPO = "tipo";
	public static final String LOCALIZACAO = "localizacao";
	public static final String DADOS = "dados";
	public static final String FOTO_OU_VIDEO = "fotoOuVideo";
	public static final String DONO_DO_FEED = "donoDoFeed";
	public static final String URL = "url";
	public static final String DESCRICAO = "descricao";
	public static final String TAGS = "tags";
	public static final String CITADOS = "citados";
	public static final String DATA_POSTAGEM = "dataPostagem";
	public static final String PHOTOS_VIDEOS = "photosVideos";
	public static final String NUM_CURTIDAS = "numCurtidas";
	public static final String COMENTARIOS = "comentarios";
	public static final String URL_IMAGE = "urlImage";
	public static final String LIKES = "likes";

}

