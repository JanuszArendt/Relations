package pl.coderslab.relations.tests.one_to_many;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.coderslab.relations.ApplicationConfig;
import pl.coderslab.relations.dao.AddressDao;
import pl.coderslab.relations.dao.PersonDao;
import pl.coderslab.relations.dao.PhoneDao;
import pl.coderslab.relations.model.Address;
import pl.coderslab.relations.model.Person;
import pl.coderslab.relations.model.Phone;

public class SavePersonWithPhones {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        saveCascade(context);

        // If you remove cascade = {CascadeType.PERSIST, CascadeType.MERGE}
        // from Person.phones you have to uncomment the following line:
//         saveWithoutCascade(context);

        context.close();
    }

    private static void saveCascade(AnnotationConfigApplicationContext context) {
        PersonDao personDao = (PersonDao) context.getBean("personDao");

        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        Person person = new Person();
        person.getPhones().add(phone1);
        phone1.setPerson(person);
        person.getPhones().add(phone2);
        phone2.setPerson(person);
        personDao.save(person);
    }

    private static void saveWithoutCascade(AnnotationConfigApplicationContext context) {
        PersonDao personDao = (PersonDao) context.getBean("personDao");
        PhoneDao phoneDao = (PhoneDao) context.getBean("phoneDao");

        Phone phone1 = new Phone();
        phoneDao.save(phone1);

        Phone phone2 = new Phone();
        phoneDao.save(phone2);

        Person person = new Person();
        personDao.save(person);

        phone1.setPerson(person);
        phoneDao.update(phone1);

        phone2.setPerson(person);
        phoneDao.update(phone2);
    }
}