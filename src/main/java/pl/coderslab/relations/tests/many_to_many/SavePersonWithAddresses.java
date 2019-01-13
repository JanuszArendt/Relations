package pl.coderslab.relations.tests.many_to_many;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.coderslab.relations.ApplicationConfig;
import pl.coderslab.relations.dao.AddressDao;
import pl.coderslab.relations.dao.PersonDao;
import pl.coderslab.relations.model.Address;
import pl.coderslab.relations.model.Person;

public class SavePersonWithAddresses {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
<<<<<<< Updated upstream
        saveCascade(context);
=======
       saveCascade(context);
>>>>>>> Stashed changes

        // If you remove (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        // from Person.addresses you have to uncomment the following line:
//         saveWithoutCascade(context);
        context.close();
    }

    private static void saveCascade(AnnotationConfigApplicationContext context) {
        PersonDao personDao = (PersonDao) context.getBean("personDao");

        Address address1 = new Address();
        Address address2 = new Address();
        Address address3 = new Address();

        Person person1 = new Person();
        person1.getAddresses().add(address1);
        person1.getAddresses().add(address2);
        person1.getAddresses().add(address3);
        personDao.save(person1);
    }

    private static void saveWithoutCascade(AnnotationConfigApplicationContext context) {
        PersonDao personDao = (PersonDao) context.getBean("personDao");
        AddressDao addressDao = (AddressDao) context.getBean("addressDao");

        Address address1 = new Address();
        Address address2 = new Address();
        Address address3 = new Address();

        addressDao.save(address1);
        addressDao.save(address2);
        addressDao.save(address3);

        Person person1 = new Person();
        person1.getAddresses().add(address1);
        person1.getAddresses().add(address2);
        person1.getAddresses().add(address3);
        personDao.save(person1);
    }

}
