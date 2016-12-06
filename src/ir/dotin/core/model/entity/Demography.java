package ir.dotin.core.model.entity;

import ir.dotin.core.model.entity.generic.BaseEntity;

import javax.persistence.*;

/**
 * Created by h.safyari on 11/12/2016.
 */
@Entity
@Table(name = "tb_demography")
@NamedQueries({
        @NamedQuery(
                name = "Demography.list",
                query = "select d from Demography d where d.deleted='0' "
        ),
        @NamedQuery(
                name = "Demography.findById",
                query = "select d from Demography d where d.id=:id"

        )
})
public class Demography extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Demography_SEQ")
    @SequenceGenerator(name = "Demography_SEQ", sequenceName = "Demography_SEQ")
    @Column(name = "id")
    private long id;

    @Column(name = "foreign_national")
    private Boolean foreignNational;
    @Column(name = "country")
    private String counry;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birthDate")
    private String birthDate;
    @Column(name = "job")
    private String job;
    @Column(name = "define_date")
    private String defineDate;
    @Column(name = "matrimony")
    private String matrimony;
    @Column(name = "date_of_marriage")
    private String dateOfMarriage;
    @Column(name = "number_of_children")
    private String  numberOfChildren;
    @Column(name = "children_description")
    private String childrenDescription;
    @Column(name = "education")
    private String education;
    @Column(name = "education_group")
    private String eductionGroup;
    @Column(name = "study")
    private String study;
    @Column(name = "cellPhone_type")
    private String cellPhoneType;

    @OneToOne
    @JoinColumn(name="actualCustomer_id" , referencedColumnName ="id")
    private ActualCustomer  actualCustomer = new ActualCustomer();

    public Demography() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getForeignNational() {
        return foreignNational;
    }

    public void setForeignNational(Boolean foreignNational) {
        this.foreignNational = foreignNational;
    }

    public String getCounry() {
        return counry;
    }

    public void setCounry(String counry) {
        this.counry = counry;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDefineDate() {
        return defineDate;
    }

    public void setDefineDate(String defineDate) {
        this.defineDate = defineDate;
    }

    public String getMatrimony() {
        return matrimony;
    }

    public void setMatrimony(String matrimony) {
        this.matrimony = matrimony;
    }

    public String getDateOfMarriage() {
        return dateOfMarriage;
    }

    public void setDateOfMarriage(String dateOfMarriage) {
        this.dateOfMarriage = dateOfMarriage;
    }

    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getChildrenDescription() {
        return childrenDescription;
    }

    public void setChildrenDescription(String childrenDescription) {
        this.childrenDescription = childrenDescription;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEductionGroup() {
        return eductionGroup;
    }

    public void setEductionGroup(String eductionGroup) {
        this.eductionGroup = eductionGroup;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getCellPhoneType() {
        return cellPhoneType;
    }

    public void setCellPhoneType(String cellPhoneType) {
        this.cellPhoneType = cellPhoneType;
    }

    public ActualCustomer getActualCustomer() {
        return actualCustomer;
    }

    public void setActualCustomer(ActualCustomer actualCustomer) {
        this.actualCustomer = actualCustomer;
    }


}
