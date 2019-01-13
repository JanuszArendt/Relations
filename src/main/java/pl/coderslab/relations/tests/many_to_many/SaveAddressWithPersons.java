package pl.coderslab.relations.tests.many_to_many;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.coderslab.relations.ApplicationConfig;
import pl.coderslab.relations.dao.AddressDao;
import pl.coderslab.relations.dao.PersonDao;
import pl.coderslab.relations.model.Address;
import pl.coderslab.relations.model.Person;

public class SaveAddressWithPersons {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        saveCascade(context);

        // If you remove cascade = {CascadeType.PERSIST, CascadeType.MERGE}
        // from Address.persons you have to uncomment the following line:
//         saveWithoutCascade(context);
        context.close();
    }

    private static void saveCascade(AnnotationConfigApplicationContext context) {
        AddressDao addressDao = (AddressDao) context.getBean("addressDao");

        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();

        Address address1 = new Address();
        address1.getPersons().add(person1);
        address1.getPersons().add(person2);
        address1.getPersons().add(person3);

        person1.getAddresses().add(address1);
        person2.getAddresses().add(address1);
        person3.getAddresses().add(address1);

        addressDao.save(address1);
    }

    private static void saveWithoutCascade(AnnotationConfigApplicationContext context) {
        PersonDao personDao = (PersonDao) context.getBean("personDao");
        AddressDao addressDao = (AddressDao) context.getBean("addressDao");

        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        personDao.save(person1);
        personDao.save(person2);
        personDao.save(person3);

        Address address1 = new Address();
        addressDao.save(address1);

        person1.getAddresses().add(address1);
        person2.getAddresses().add(address1);
        person3.getAddresses().add(address1);

        personDao.update(person1);
        personDao.update(person2);
        personDao.update(person3);
    }
}
