package mailapi.testmailapi.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mailapi.testmailapi.embeddable.AddressInfo;
import mailapi.testmailapi.embeddable.PackageInfo;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collection;

@JsonPropertyOrder({"id", "name", "officeAddress"})
public interface PostOfficeView {
    Long getId();
    String getName();
    AddressInfo getOfficeAddress();
}
