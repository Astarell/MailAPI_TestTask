package mailapi.testmailapi.swagger_support_schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mailapi.testmailapi.embeddable.AddressInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostOfficeSchemaMain {
    private Long id;
    private AddressInfo officeAddress;
    private String name;
}
