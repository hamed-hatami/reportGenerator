package ir.dotin.core.model.entity;

import ir.dotin.core.model.entity.generic.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by h.safyari on 8/29/2016.
 */

@Entity
@Table(name = "tb_country")
@NamedQueries({
        @NamedQuery(
                name = "Country.list",
                query = "select c from Country c where c.deleted='0'"
        ),
        @NamedQuery(
                name = "Country.findById",
                query = "select c from Country c where c.id=:id"
        ),
        @NamedQuery(
                name = "Country.exist",
                query = "select c from Country c where c.name=:name and c.deleted = '0'"
        )
})
public class Country extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Country_SEQ")
    @SequenceGenerator(name = "Country_SEQ", sequenceName = "Country_SEQ")
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    public Country() {
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
