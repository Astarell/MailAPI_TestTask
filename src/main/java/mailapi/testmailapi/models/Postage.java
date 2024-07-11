package mailapi.testmailapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mailapi.testmailapi.embeddable.AddressInfo;
import mailapi.testmailapi.embeddable.PackageInfo;
import mailapi.testmailapi.embeddable.PersonInfo;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Postage")
@Entity
public class Postage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private PackageInfo info;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private PostOffice office;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipCode", column = @Column(name = "rcvr_addr_zip_code")),
            @AttributeOverride(name = "city", column = @Column(name = "rcvr_addr_city")),
            @AttributeOverride(name = "street", column = @Column(name = "rcvr_addr_street")),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "rcvr_addr_house_number")),
            @AttributeOverride(name = "apartmentNumber", column = @Column(name = "rcvr_addr_apart_number"))
    })
    private AddressInfo receiverAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "rcvr_first_name")),
            @AttributeOverride(name = "middleName", column = @Column(name = "rcvr_middle_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "rcvr_last_name")),
    })
    private PersonInfo receiver;

    @JsonCreator
    public Postage(@JsonProperty(value = "id") Long id,
                   @JsonProperty(value = "info") PackageInfo info,
                   @JsonProperty(value = "office") PostOffice office,
                   @JsonProperty(value = "receiver") PersonInfo receiver,
                   @JsonProperty(value = "receiverAddress") AddressInfo receiverAddress){
        this.id = id;
        this.info = info;
        this.office = office;
        this.receiver = receiver;
        this.receiverAddress = receiverAddress;
    }
}
