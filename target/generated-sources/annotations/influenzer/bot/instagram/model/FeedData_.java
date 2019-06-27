package influenzer.bot.instagram.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FeedData.class)
public abstract class FeedData_ extends influenzer.bot.generic.model.AbstractEntidade_ {

	public static volatile SingularAttribute<FeedData, Feed> feed;
	public static volatile SingularAttribute<FeedData, byte[]> pic;
	public static volatile SingularAttribute<FeedData, String> type;

	public static final String FEED = "feed";
	public static final String PIC = "pic";
	public static final String TYPE = "type";

}

