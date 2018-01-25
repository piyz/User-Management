package by.matrosov.usermanagment.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    //TODO more detailed DATA format

    @CreatedDate
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    protected Date creationDate;

    @LastModifiedDate
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    protected Date lastModifiedDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
