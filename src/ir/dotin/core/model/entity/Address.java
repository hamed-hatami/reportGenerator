package ir.dotin.core.model.entity;

import ir.dotin.core.model.entity.generic.BaseEntity;

import javax.persistence.*;

/**
 * Created by h.safyari on 8/10/2016.
 */
@Entity
@Table(name = "tb_Address")
@NamedQueries({
        @NamedQuery(
                name = "Address.list",
                query = "select a from Address a where a.deleted='0'"
        ),
        @NamedQuery(
                name = "Address.findById",
                query = "select a from Address a where a.id=:id"
        ),
        @NamedQuery(
                name = "Address.exist",
                query = "select a from Address a where a.postalCode=:postalCode and a.deleted = '0'"
        ),
        @NamedQuery(
                name = "Address.findByCustomerId",
                query = "select a from Address a where a.actualCustomer.id=:customerId and a.deleted = '0'"
        )
})
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Address_SEQ")
    @SequenceGenerator(name = "Address_SEQ", sequenceName = "Address_SEQ")
    @Column(name = "id")
    private long id;

    @Column(name = "address_Type")
    private String addressType;

    @Column(name = "squar")
    private String squar;

    @Column(name = "street")
    private String street;

    @Column(name = "highway")
    private String highway;

    @Column(name = "alley")
    private String alley;

    @Column(name = "plaque")
    private String plaque;

    @Column(name = "block")
    private String block;

    @Column(name = "unit")
    private String unit;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "postalCode")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "id")
    private ActualCustomer actualCustomer = new ActualCustomer();

    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city = new City();

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public ActualCustomer getActualCustomer() {
        return actualCustomer;
    }

    public void setActualCustomer(ActualCustomer actualCustomer) {
        this.actualCustomer = actualCustomer;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getSquar() {
        return squar;
    }

    public void setSquar(String squar) {
        this.squar = squar;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHighway() {
        return highway;
    }

    public void setHighway(String highway) {
        this.highway = highway;
    }

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(city.getState().getName() + '-');
        stringBuilder.append(city.getName() + '-');

        if (!squar.isEmpty())
            stringBuilder.append(squar + '-');
        if (!street.isEmpty())
            stringBuilder.append(street + '-');
        if (!highway.isEmpty())
            stringBuilder.append(highway + '-');
        if (!alley.isEmpty())
            stringBuilder.append(alley + '-');
        if (!plaque.isEmpty())
            stringBuilder.append(plaque + '-');
        if (!block.isEmpty())
            stringBuilder.append(block + '-');
        if (!unit.isEmpty())
            stringBuilder.append(unit);
        return stringBuilder.toString();
    }
}
