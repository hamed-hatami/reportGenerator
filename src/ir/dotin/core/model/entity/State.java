package ir.dotin.core.model.entity;

import ir.dotin.core.model.entity.generic.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by h.safyari on 8/29/2016.
 */

@Entity
@Table(name = "tb_State")
@NamedQueries({
        @NamedQuery(
                name = "State.list",
                query = "select s from State s where s.deleted='0'"
        ),
        @NamedQuery(
                name = "State.findById",
                query = "select s from State s where s.id=:id"
        ),
        @NamedQuery(
                name = "State.exist",
                query = "select s from State s where s.name=:name and s.deleted = '0'"
        )
})
public class State extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "State_SEQ")
    @SequenceGenerator(name = "State_SEQ", sequenceName = "State_SEQ")
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "state",targetEntity = City.class)
    private Set<City> citySet;

    public State() {
    }

    public Set<City> getCitySet() {
        return citySet;
    }

    public void setCitySet(Set<City> citySet) {
        this.citySet = citySet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
