package springMySQL.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
//    @Column(name = "id")
//    private Integer id;

    @Column(name = "street_name")
    private String streetName;


    @Column(name = "house_number")
    private String houseNumber;

    @OneToMany(mappedBy = "address", cascade = {CascadeType.ALL})
    private List<Person> persons = new ArrayList<>();
}
