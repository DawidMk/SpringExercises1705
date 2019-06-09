package springMySQL.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
//    @Column(name = "id")
//    private Integer id;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    @OneToOne(mappedBy = "person", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "persons_pseudonyms",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "pseudonym_id"))
    private List<Pseudonym> pseudonyms = new ArrayList<>();
}
