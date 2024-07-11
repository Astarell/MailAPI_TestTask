package mailapi.testmailapi.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mailapi.testmailapi.embeddable.AddressInfo;
import mailapi.testmailapi.embeddable.PersonInfo;
import org.springframework.beans.factory.annotation.Value;

@JsonPropertyOrder({"id", "info", "receiver", "receiverAddress", "office"})
public interface PostageView {
    Long getId();
    @Value("#{target.info.type}")
    String getInfo();
    PostOfficeView getOffice();
    AddressInfo getReceiverAddress();
    PersonInfo getReceiver();
}
