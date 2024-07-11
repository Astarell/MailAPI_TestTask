package mailapi.testmailapi.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mailapi.testmailapi.models.Postage;
import mailapi.testmailapi.pojos.ShippingInformation;

import java.util.Collection;

@JsonPropertyOrder({"id", "createdAt", "status", "officeEndpoint", "postage", "shippingInformation"})
public interface PackageTrackingView {
    Long getId();
    String getCreatedAt();
    String getOfficeEndpoint();
    String getStatus();
    PostageView getPostage();
    Collection<ShippingInformation> getShippingInformation();
}
