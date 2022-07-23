package backend.duka_kuu.domain;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;
import static javax.persistence.FetchType.EAGER;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long userId;

    @Column(nullable = false)
    @NotNull
    private String username;

//    @Column(nullable = false, unique = true)
//    private String email;

    @Column(nullable = false, unique = true)
    @NotNull
    private String password;

    @OneToMany(mappedBy = "user")
    @Column(nullable = true)
    private Collection<Order> userOrders = new ArrayList<>();

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();


}
