package mailapi.testmailapi.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mailapi.testmailapi.custom_enums.TrackingStatus;
import mailapi.testmailapi.embeddable.AddressInfo;
import mailapi.testmailapi.embeddable.PackageInfo;
import mailapi.testmailapi.embeddable.PersonInfo;
import mailapi.testmailapi.pojos.ShippingInformation;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Package_Tracking")
@Entity
public class PackageTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    private String officeEndpoint;

    @Enumerated(value = EnumType.STRING)
    private TrackingStatus status = TrackingStatus.CREATED;

    @OneToOne(cascade = {CascadeType.MERGE})
    @MapsId
    private Postage postage;

    @Column(name = "shipping_information")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<ShippingInformation> shippingInformation = new ArrayList<>();

//    @JsonCreator
//    public PackageTracking(@JsonProperty(value = "id") Long id,
//                   @JsonProperty(value = "officeEndpoint") String officeEndpoint,
//                   @JsonProperty(value = "postage") Postage postage){
//        this.id = id;
//        this.officeEndpoint = officeEndpoint;
//        this.postage = postage;
//    }
}
