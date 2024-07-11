package mailapi.testmailapi.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mailapi.testmailapi.embeddable.AddressInfo;


import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Post_Office")
@Entity
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private AddressInfo officeAddress;

    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "office")
    private Collection<Postage> postages;

    @Override
    public String toString() {
        return "PostOffice{" +
                "id=" + id +
                ", officeAddress=" + officeAddress +
                ", name='" + name + '\'' +
                '}';
    }
}
