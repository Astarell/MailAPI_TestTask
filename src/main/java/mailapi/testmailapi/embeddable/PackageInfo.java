package mailapi.testmailapi.embeddable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mailapi.testmailapi.custom_enums.PostageType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PackageInfo {
    @Enumerated(EnumType.STRING)
    private PostageType type;
//    private Double weight;
}
