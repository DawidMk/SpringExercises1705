package springMySQL.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springMySQL.*;
import springMySQL.entities.Address;
import springMySQL.entities.Person;
import springMySQL.entities.Pseudonym;
import springMySQL.entities.User;

import java.util.ArrayList;

@Service
public class addService {

    @Autowired
    OneDAO oneDAO;
    OneRepository oneRepository;

    public void addHuman(AddDTO dto) {
     /*   if (userExists(dto)) {
            throw new UserExistsException("user " + dto.getEmail() + " already exists!");
        } else {*/
        User user = new User();
        Person person = new Person();
        Pseudonym pseudonym = new Pseudonym();
        Address address = new Address();


        rewriteData(dto, user, person, pseudonym, address);
        oneDAO.addToDb(dto, user);
        oneDAO.addToDb(dto, person);
        oneDAO.addToDb(dto, pseudonym);
        oneDAO.addToDb(dto, address);

    }

    private void rewriteData(AddDTO dto, User user, Person person, Pseudonym pseudonym, Address address) {
        //user
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setPerson(person);

        //person
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddress(address);
        person.setUser(user);

        if (person.getPseudonyms() != null && !person.getPseudonyms().contains(pseudonym)) {
            person.getPseudonyms().add(pseudonym);
        }
        //pseudonym
        pseudonym.setPseudonym(dto.getPseudonym());
        if (pseudonym.getPersons() != null && !pseudonym.getPersons().contains(person)) {
            pseudonym.getPersons().add(person);
        }

        //address
        address.setStreetName(dto.getStreet());
        address.setHouseNumber(dto.getHouseNumber());
        if (address.getPersons() != null && !address.getPersons().contains(person)) {
            address.getPersons().add(person);
        }


    }

    public void addPerson(AddDTO dto) {
        Person person = new Person();
        oneDAO.addToDb(dto, person);


    }

    public void addPseudonym(AddDTO dto) {
        Pseudonym pseudonym = new Pseudonym();
        oneDAO.addToDb(dto, pseudonym);
    }

    public void addAddress(AddDTO dto) {
        Address address = new Address();
        rewriteDataToAddress(dto, address);
        oneDAO.addToDb(dto, address);

    }


    private void rewriteDataToUser(AddDTO dto, User u) {
        u.setLogin(dto.getLogin());
        u.setEmail(dto.getEmail());
    }

    private void rewriteDataToAddress(AddDTO dto, Address a) {
        a.setStreetName(dto.getStreet());
        a.setHouseNumber(dto.getHouseNumber());
    }


    private void rewriteDataToPseudonym(AddDTO dto, Pseudonym p) {
        p.setPseudonym(dto.getPseudonym());
    }

    private boolean userExists(AddDTO dto) {
        return oneRepository.findByEmail(dto.getEmail()) != null;
    }

}

