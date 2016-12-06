package ir.dotin.core.model.entity.generic;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_workgroup")
@Cacheable(value = true)
@NamedQueries({
        @NamedQuery(
                name = "Workgroup.list",
                query = "select w from Workgroup w where w.deleted='0'"
        ),
        @NamedQuery(
                name = "Workgroup.findById",
                query = "select w from Workgroup w where w.id=:id"
        ),
        @NamedQuery(
                name = "Workgroup.findByRoleId",
                query = "select w from Workgroup w join w.roles r where r.id in :roleId and w.deleted='0'"
        ),
        @NamedQuery(
                name = "WorkGroup.maximum",
                query = "select max(w.id) from Workgroup w"
        )
})
public class Workgroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Workgroup_SEQ")
    @SequenceGenerator(name = "Workgroup_SEQ", sequenceName = "Workgroup_SEQ")
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "persian_description")
    private String persianDescription;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_workgroup_role", joinColumns = {
            @JoinColumn(name = "workgroup_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)})
    private Set<Role> roles;


    public Workgroup() {
    }

    public Workgroup(String name, String persianDescription, Set<Role> roles) {
        this.name = name;
        this.persianDescription = persianDescription;
        this.roles = roles;
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

    public String getPersianDescription() {
        return persianDescription;
    }

    public void setPersianDescription(String persianDescription) {
        this.persianDescription = persianDescription;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}