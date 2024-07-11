package mailapi.testmailapi.swagger_support_schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mailapi.testmailapi.embeddable.AddressInfo;
import mailapi.testmailapi.embeddable.PackageInfo;
import mailapi.testmailapi.embeddable.PersonInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostageSchemaMain {
    private Long id;
    private PackageInfo info;
    private PostOfficeSchemaMinor office;
    private AddressInfo receiverAddress;
    private PersonInfo receiver;
}
