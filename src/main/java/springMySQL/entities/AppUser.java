package springMySQL.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "app_users")
@Getter
@Setter
public class AppUser extends BaseEntity {

    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;

//    @JsonManagedReference
    @JsonIgnore
    @OneToOne(mappedBy = "appUser", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "person_id")
    private Person person;

}
