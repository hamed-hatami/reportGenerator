package ir.dotin.core.model.entity;

import ir.dotin.core.model.entity.generic.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by h.safyari on 11/13/2016.
 */
@Entity
@Table(name = "tb_Interests")
@NamedQueries({
        @NamedQuery(
                name = "Interests.list",
                query = "select i from Interests i where i.deleted='0' "
        ),
        @NamedQuery(
                name = "Interests.findById",
                query = "select i from Interests i where i.id=:id"

        ),
        @NamedQuery(
                name = "Interests.findParent",
                query = "select i from Interests i where i.parent is null"

        ),
        @NamedQuery(
                name = "Interests.actualCustomer",
                query = "select i from Interests i join i.actualCustomerSet a where a.id=:actualCustomerId "
        )
})
public class Interests extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Interests_SEQ")
    @SequenceGenerator(name = "Interests_SEQ", sequenceName = "Interests_SEQ")
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "path_Of_Node")
    private String pathOfNode;

    @Column(name = "path_Of_String")
    private String pathOfString;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID", referencedColumnName = "id")
    private Interests parent;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "PARENTID")
    private List<Interests> children;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_actualCustomer_interests", joinColumns = {
            @JoinColumn(name = "interests_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)})
    private Set<ActualCustomer> actualCustomerSet;

    public Interests() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Interests getParent() {
        return parent;
    }

    public void setParent(Interests parent) {
        this.parent = parent;
    }

    public List<Interests> getChildren() {
        return children;
    }

    public void setChildren(List<Interests> children) {
        this.children = children;
    }

    public Set<ActualCustomer> getActualCustomerSet() {
        return actualCustomerSet;
    }

    public void setActualCustomerSet(Set<ActualCustomer> actualCustomerSet) {
        this.actualCustomerSet = actualCustomerSet;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getPathOfNode() {
        return pathOfNode;
    }

    public void setPathOfNode(String pathOfNode) {
        this.pathOfNode = pathOfNode;
    }

    public String getPathOfString() {
        return pathOfString;
    }

    public void setPathOfString(String pathOfString) {
        this.pathOfString = pathOfString;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id==null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Interests other = (Interests) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
