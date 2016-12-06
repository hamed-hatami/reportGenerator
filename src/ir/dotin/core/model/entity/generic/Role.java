package ir.dotin.core.model.entity.generic;

import javax.persistence.*;

@Entity
@Table(name = "tb_role")
@NamedQueries({
        @NamedQuery(
                name = "Role.list",
                query = "select r from Role r where r.deleted='0' "
        ),
        @NamedQuery(
                name = "Role.findById",
                query = "select r from Role r where r.id=:id"

        ),
        @NamedQuery(
                name = "Role.findByName",
                query = "select r from Role r where r.name=:name"

        ),
        @NamedQuery(
                name = "Role.maximum",
                query = "select max(r.id) from Role r"
        )
})
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Role_SEQ")
    @SequenceGenerator(name = "Role_SEQ", sequenceName = "Role_SEQ")
    @Column(name = "id")
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    @Transient
    private Boolean selected;

    public Role() {
    }

    public Role(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean  equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Role role = (Role) object;
            if (this.name == role.getName()) {
                result = true;
            }
        }
        return result;
    }
}