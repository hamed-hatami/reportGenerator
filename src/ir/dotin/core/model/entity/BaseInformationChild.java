package ir.dotin.core.model.entity;

import ir.dotin.core.model.entity.generic.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by h.safyari on 10/3/2016.
 */
@Entity
@Table(name = "tb_BaseInforMationChild")
@NamedQueries({
        @NamedQuery(
                name = "BaseInformationChild.list",
                query = "select b from BaseInformationChild b where b.deleted='0' "
        ),
        @NamedQuery(
                name = "BaseInformationChild.findById",
                query = "select b from BaseInformationChild b where b.id=:id"

        ),
        @NamedQuery(
                name = "BaseInformationChild.findByName",
                query = "select b from BaseInformationChild b where b.persianName=:name"

        ),
        @NamedQuery(
                name = "BaseInformationChild.exist",
                query = "select b from BaseInformationChild b where b.englishName=:englishName and b.deleted = '0' and b.parent.id=:parentId"
        ),
        @NamedQuery(
                name = "BaseInformationChild.existwitoutParent",
                query = "select b from BaseInformationChild b where b.englishName=:englishName and b.deleted = '0' and b.parent is null "
        ),
        @NamedQuery(
                name = "BaseInformationChild.findByParentName",
                query = "select b from BaseInformationChild b where b.parent.englishName=:englishName"
        )
})
public class BaseInformationChild extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BaseInformationChild_SEQ")
    @SequenceGenerator(name = "BaseInformationChild_SEQ", sequenceName = "BaseInformationChild_SEQ")
    @Column(name = "id")
    private long id;
    @Column(name = "persian_name")
    private String persianName;
    @Column(name = "english_name")
    private String englishName;
    @Column(name = "priority")
    private float priority;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "baseInfo_Parent_Id", referencedColumnName = "id")
    private BaseInformationParent baseInformationParent;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID", referencedColumnName = "id")
    private BaseInformationChild parent;

    @OneToMany(
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "PARENTID")
    private List<BaseInformationChild> children;

    public BaseInformationChild() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPersianName() {
        return persianName;
    }

    public void setPersianName(String persianName) {
        this.persianName = persianName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }

    public BaseInformationParent getBaseInformationParent() {
        return baseInformationParent;
    }

    public void setBaseInformationParent(BaseInformationParent baseInformationParent) {
        this.baseInformationParent = baseInformationParent;
    }

    public BaseInformationChild getParent() {
        return parent;
    }

    public void setParent(BaseInformationChild parent) {
        this.parent = parent;
    }

    public List<BaseInformationChild> getChildren() {
        return children;
    }

    public void setChildren(List<BaseInformationChild> children) {
        this.children = children;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

