package mailapi.testmailapi.swagger_support_schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageTrackingSchema {
    private Long id;
    private String officeEndpoint;
    private PostageSchemaMinor postage;
}
