package springMySQL.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//            , generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    @Getter
    @Setter
    protected Integer id;
}
