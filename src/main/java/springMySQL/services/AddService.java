package springMySQL.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springMySQL.*;
import springMySQL.entities.Address;
import springMySQL.entities.AppUser;
import springMySQL.entities.Person;
import springMySQL.entities.Pseudonym;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class AddService {

    @Autowired
    OneDAO oneDAO;
    OneRepository oneRepository;

    public void addHuman(AddDTO dto) {
     /*   if (userExists(dto)) {
            throw new UserExistsException("appUser " + dto.getEmail() + " already exists!");
        } else {*/
         AppUser appUser = new AppUser();
     if(!oneRepository.existsByEmail()) {
         appUser = new AppUser();
     }else{
         Stream appUserStream = StreamSupport.stream(oneRepository.findByEmail(dto.getEmail()).spliterator(), false);
         appUser = new AppUser();
//         appUser = appUserStream.filter()
     }
        Person person = new Person();
        Pseudonym pseudonym = new Pseudonym();
        Address address = new Address();


        rewriteData(dto, appUser, person, pseudonym, address);
        oneDAO.addToDb(dto, appUser);
        oneDAO.addToDb(dto, person);
        oneDAO.addToDb(dto, pseudonym);
        oneDAO.addToDb(dto, address);

    }

    private void rewriteData(AddDTO dto, AppUser appUser, Person person, Pseudonym pseudonym, Address address) {
        //appUser
        appUser.setLogin(dto.getLogin());
        appUser.setEmail(dto.getEmail());
        appUser.setPerson(person);

        //person
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddress(address);
        person.setAppUser(appUser);

        if (person.getPseudonyms() == null) {
            person.setPseudonyms(new ArrayList<>());
            person.getPseudonyms().add(pseudonym);
        }
        if (!person.getPseudonyms().contains(pseudonym)) {
            person.getPseudonyms().add(pseudonym);
        }

        //pseudonym
        pseudonym.setPseudonym(dto.getPseudonym());
        if (pseudonym.getPersons() != null) {
            if (!pseudonym.getPersons().contains(person)) {
                pseudonym.getPersons().add(person);
            }
        } else {
            pseudonym.setPersons(new ArrayList<>());
            pseudonym.getPersons().add(person);

        }


        //address
        address.setStreetName(dto.getStreet());
        address.setHouseNumber(dto.getHouseNumber());
        if (address.getPersons() != null) {
            if (!address.getPersons().contains(person))

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


    private void rewriteDataToUser(AddDTO dto, AppUser u) {
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

