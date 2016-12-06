package ir.dotin.core.model.entity;

import ir.dotin.core.model.entity.generic.BaseEntity;

import javax.persistence.*;

/**
 * Created by h.safyari on 11/12/2016.
 */
@Entity
@Table(name = "tb_pipeLine")
@NamedQueries({
        @NamedQuery(
                name = "PipeLine.list",
                query = "select p from PipeLine p where p.deleted='0' "
        ),
        @NamedQuery(
                name = "PipeLine.findById",
                query = "select p from PipeLine p where p.id=:id"
        )
})
public class PipeLine extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PipeLine_SEQ")
        @SequenceGenerator(name = "PipeLine_SEQ", sequenceName = "PipeLine_SEQ")
        @Column(name = "id")
        private long id;

        @Column(name = "pipeLine_Type")
        private String pipeLineType;

        @Column(name = "pasargad_advantage")
        private String pasargadAdvantage;

        @Column(name = "friends")
        private String friends;

        @Column(name = "member_Of_group")
        private String memberOfGroup;

        @Column(name = "social_network")
        private String socialNetwork;

        @Column(name = "requirements")
        private String requirements;

        @OneToOne(cascade = {CascadeType.ALL})
        @JoinColumn(name="actualCustomer_id" , referencedColumnName ="id")
        private ActualCustomer  actualCustomer = new ActualCustomer();

        public PipeLine() {
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getPipeLineType() {
                return pipeLineType;
        }

        public void setPipeLineType(String pipeLineType) {
                this.pipeLineType = pipeLineType;
        }

        public String getPasargadAdvantage() {
                return pasargadAdvantage;
        }

        public void setPasargadAdvantage(String pasargadAdvantage) {
                this.pasargadAdvantage = pasargadAdvantage;
        }

        public String getFriends() {
                return friends;
        }

        public void setFriends(String friends) {
                this.friends = friends;
        }

        public String getMemberOfGroup() {
                return memberOfGroup;
        }

        public void setMemberOfGroup(String memberOfGroup) {
                this.memberOfGroup = memberOfGroup;
        }

        public String getSocialNetwork() {
                return socialNetwork;
        }

        public void setSocialNetwork(String socialNetwork) {
                this.socialNetwork = socialNetwork;
        }

        public String getRequirements() {
                return requirements;
        }

        public void setRequirements(String requirements) {
                this.requirements = requirements;
        }

        public ActualCustomer getActualCustomer() {
                return actualCustomer;
        }

        public void setActualCustomer(ActualCustomer actualCustomer) {
                this.actualCustomer = actualCustomer;
        }


}
