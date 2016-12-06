package ir.dotin.core.model.entity.generic;

import ir.dotin.core.model.EventType;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseEntity implements Serializable {

    @Column(name = "deleted")
    protected String deleted;
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    protected EventType eventType;
    @Column(name = "event_date")
    protected String eventDate;
    @Column(name = "event_by")
    protected String eventBy;

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventBy() {
        return eventBy;
    }

    public void setEventBy(String eventBy) {
        this.eventBy = eventBy;
    }
}
