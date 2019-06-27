package influenzer.bot.instagram.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PhotoVideo.class)
public abstract class PhotoVideo_ extends influenzer.bot.generic.model.AbstractEntidade_ {

	public static volatile SingularAttribute<PhotoVideo, Timestamp> data;
	public static volatile SingularAttribute<PhotoVideo, String> filePath;
	public static volatile CollectionAttribute<PhotoVideo, Feed> feeds;
	public static volatile SingularAttribute<PhotoVideo, String> url;

	public static final String DATA = "data";
	public static final String FILE_PATH = "filePath";
	public static final String FEEDS = "feeds";
	public static final String URL = "url";

}

