package springMySQL.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pseudonyms")
@Getter
@Setter
public class Pseudonym  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pseudonym")
    private String pseudonym;

    @ManyToMany(mappedBy = "pseudonyms")
    private List<Person> persons;
}
