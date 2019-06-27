package influenzer.bot.generic.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@MappedSuperclass
public abstract class AbstractTemporalEntidade implements Serializable{
    
    protected static final long serialVersionUID = 1L;
           
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, insertable=false, updatable=false,
              columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false, insertable=false, updatable=false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updated;

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }
    
        
    @Override
    public String toString () {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
