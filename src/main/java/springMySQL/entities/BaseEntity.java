package springMySQL.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    private Integer id;
}
