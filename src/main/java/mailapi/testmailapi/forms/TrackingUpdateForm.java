package mailapi.testmailapi.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingUpdateForm {
    private Long trackId;
    private Long officeId;
    private Boolean received = false;

    public Boolean isReceived(){
        return received;
    }
}
