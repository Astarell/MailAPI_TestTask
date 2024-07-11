package mailapi.testmailapi.pojos;

import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mailapi.testmailapi.embeddable.AddressInfo;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ShippingInformation {
    private String arrivedAt;
    private String departuredAt;
    private String postOfficeName;
    private AddressInfo postOfficeAddress;

    public ShippingInformation(ShipInfoBuilder builder){
        this.arrivedAt = builder.arrivedAt;
        this.postOfficeName = builder.postOfficeName;
        this.postOfficeAddress = builder.postOfficeAddress;
    }


    public static class ShipInfoBuilder{
        private String arrivedAt;
        private String departuredAt;
        private String postOfficeName;
        private AddressInfo postOfficeAddress;

        public ShipInfoBuilder setPostOfficeAddress(AddressInfo postOfficeAddress){
            this.postOfficeAddress = postOfficeAddress;
            return this;
        }
        public ShipInfoBuilder setPostOfficeName(String postOfficeName){
            this.postOfficeName = postOfficeName;
            return this;
        }

        public ShipInfoBuilder setArrivedAt(String arrivedAt){
            this.arrivedAt = arrivedAt;
            return this;
        }

        public ShipInfoBuilder setDeparturedAt(String departuredAt){
            this.departuredAt = departuredAt;
            return this;
        }

        public ShippingInformation build(){
            this.arrivedAt = String.valueOf(LocalDateTime.now());
            return new ShippingInformation(this);
        }
    }
}
