package ir.dotin.core.model.entity.generic;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_user")
@NamedQueries({
        @NamedQuery(
                name = "User.findByUsernameAndPassword",
                query = "select u from User u where u.username=:username and u.password=:password"
        ),
        @NamedQuery(
                name = "User.findById",
                query = "select u from User u where u.id=:id"
        ),
        @NamedQuery(
                name = "User.findByWorkGroupId",
                query = "select u from User u join u.workgroups w where w.id in (:workGroupId)"
        ),
        @NamedQuery(
                name = "User.list",
                query = "select u from User u where u.deleted = '0'"
        ),
        @NamedQuery(
                name = "User.exist",
                query = "select u from User u where u.username=:username and u.deleted = '0'"
        ),
        @NamedQuery(
                name = "User.findByUsername",
                query = "select u from User u where u.username=:username and u.deleted = '0'"
        ), @NamedQuery(
        name = "User.maximum",
        query = "select max(u.id) from User u"
)
})
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_SEQ")
    @SequenceGenerator(name = "User_SEQ", sequenceName = "User_SEQ")
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "nationalCode")
    private String nationalCode;
    @Column(name = "is_First_Login")
    private String isFirstLogin;
    @Column(name = "is_active")
    private Boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_workgroup", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "workgroup_id", referencedColumnName = "id", nullable = false)})
    private Set<Workgroup> workgroups;


    public User() {
    }

    public User(String username, String password, String isFirstLogin, Set<Workgroup> workgroups) {
        this.username = username;
        this.password = password;
        this.isFirstLogin = isFirstLogin;
        this.workgroups = workgroups;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(String isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public Set<Workgroup> getWorkgroups() {
        return workgroups;
    }

    public void setWorkgroups(Set<Workgroup> workgroups) {
        this.workgroups = workgroups;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
