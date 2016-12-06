package ir.dotin.core.model.entity;

import ir.dotin.core.model.entity.BaseInformationChild;
import ir.dotin.core.model.entity.generic.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by h.safyari on 10/3/2016.
 */
@Entity
@Table(name = "tb_BaseInforMationParent")
@NamedQueries({
        @NamedQuery(
                name = "BaseInformationParent.list",
                query = "select b from BaseInformationParent b where b.deleted='0' "
        ),
        @NamedQuery(
                name = "BaseInformationParent.findById",
                query = "select b from BaseInformationParent b where b.id=:id"

        ),
        @NamedQuery(
                name = "BaseInformationParent.findByName",
                query = "select b from BaseInformationParent b where b.persianName=:name"

        ),
        @NamedQuery(
                name = "BaseInformationParent.exist",
                query = "select b from BaseInformationParent b where b.englishName=:englishName and b.deleted = '0'"
        )
})
public class BaseInformationParent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BaseInforMationParent_SEQ")
    @SequenceGenerator(name = "BaseInforMationParent_SEQ", sequenceName = "BaseInforMationParent_SEQ")
    @Column(name = "id")
    private long id;
    @Column(name = "persian_name")
    private String persianName;
    @Column(name = "english_name")
    private String englishName;


    @OneToMany(mappedBy = "baseInformationParent",targetEntity = BaseInformationChild.class)
    private Set<BaseInformationChild> baseInformationChildSet;

    public BaseInformationParent() {
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

    public Set<BaseInformationChild> getBaseInformationChildSet() {
        return baseInformationChildSet;
    }

    public void setBaseInformationChildSet(Set<BaseInformationChild> baseInformationChildSet) {
        this.baseInformationChildSet = baseInformationChildSet;
    }
}
