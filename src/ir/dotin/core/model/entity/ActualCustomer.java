package ir.dotin.core.model.entity;

import ir.dotin.core.model.dao.generic.BaseDAOImpl;
import ir.dotin.core.model.entity.generic.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by h.safyari on 11/12/2016.
 */
@Entity
@Table(name = "tb_actualCustomer")
@NamedQueries({
        @NamedQuery(
                name = "ActualCustomer.list",
                query = "select a from ActualCustomer a where a.deleted='0' "
        ),
        @NamedQuery(
                name = "ActualCustomer.findById",
                query = "select a from ActualCustomer a where a.id=:id"

        ),
        @NamedQuery(
                name = "ActualCustomer.findByNationalCode",
                query = "select a from ActualCustomer a where a.nationalCode=:nationalCode"

        ),
        @NamedQuery(
                name = "ActualCustomer.exist",
                query = "select a from ActualCustomer a where a.nationalCode=:nationalCode and a.deleted = '0'"
        )
})
public class ActualCustomer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ActualCustomer_SEQ")
    @SequenceGenerator(name = "ActualCustomer_SEQ", sequenceName = "ActualCustomer_SEQ")
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "customer_number")
    private String customerNumber;
    @Column(name = "national_code")
    private String nationalCode;
    @Column(name = "cellPhone_Number")
    private String cellPhoneNumber;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "actualCustomer", targetEntity = Address.class)
    private Set<Address> addressSet;

    @OneToOne(mappedBy = "actualCustomer", targetEntity = Demography.class)
    private  Demography demography;

    @OneToOne(mappedBy = "actualCustomer", targetEntity = PipeLine.class)
    private  PipeLine pipeLine;

    @ManyToMany(mappedBy = "actualCustomerSet",fetch = FetchType.LAZY,targetEntity = Interests.class,cascade = CascadeType.MERGE)
    private Set<Interests>  interestsSet;

    public ActualCustomer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

    public Demography getDemography() {
        return demography;
    }

    public void setDemography(Demography demography) {
        this.demography = demography;
    }

    public Set<Interests> getInterestsSet() {
        return interestsSet;
    }

    public void setInterestsSet(Set<Interests> interestsSet) {
        this.interestsSet = interestsSet;
    }

    public PipeLine getPipeLine() {
        return pipeLine;
    }

    public void setPipeLine(PipeLine pipeLine) {
        this.pipeLine = pipeLine;
    }
}
