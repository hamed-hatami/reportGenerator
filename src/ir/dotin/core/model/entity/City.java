package ir.dotin.core.model.entity;

import ir.dotin.core.model.entity.generic.BaseEntity;

import javax.persistence.*;

/**
 * Created by h.safyari on 8/29/2016.
 */
@Entity
@Table(name = "tb_City")
@NamedQueries({
        @NamedQuery(
                name = "City.list",
                query = "select c from City c where c.deleted='0'"
        ),
        @NamedQuery(
                name = "City.findById",
                query = "select c from City c where c.id=:id"
        ),
        @NamedQuery(
                name = "City.exist",
                query = "select c from City c where c.name=:name and c.deleted = '0'"
        ),
        @NamedQuery(
                name = "City.findByStateId",
                query = "select c from City c where c.state.id=:stateId"
        )
})
public class City extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "City_SEQ")
    @SequenceGenerator(name = "City_SEQ", sequenceName = "City_SEQ")
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "c_state", referencedColumnName = "id")
    private State state;

    @Column(name = "name")
    private String name;

    public City() {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
