package springMySQL;

import lombok.Data;

@Data
public class AddDTO {
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String houseNumber;
    private String pseudonym;


}
