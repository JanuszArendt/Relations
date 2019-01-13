package pl.coderslab.relations.tests.one_to_many;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.coderslab.relations.ApplicationConfig;
import pl.coderslab.relations.dao.PersonDao;
import pl.coderslab.relations.dao.PhoneDao;
import pl.coderslab.relations.model.Person;
import pl.coderslab.relations.model.Phone;

public class SavePhonesWithPerson {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        save(context);

        // If you add CascadeType.PERSIST, to Phone.person use the method bellow
//        saveWithCascadePersist(context);

        context.close();
    }

    private static void save(AnnotationConfigApplicationContext context) {
        PersonDao personDao = (PersonDao) context.getBean("personDao");
        PhoneDao phoneDao = (PhoneDao) context.getBean("phoneDao");

        Person person = new Person();
        personDao.save(person);

        Phone phone1 = new Phone();
        phone1.setPerson(person);
        phoneDao.save(phone1);

        Phone phone2 = new Phone();
        phone2.setPerson(person);
        phoneDao.save(phone2);
    }

    private static void saveWithCascadePersist(AnnotationConfigApplicationContext context) {
        PersonDao personDao = (PersonDao) context.getBean("personDao");
        PhoneDao phoneDao = (PhoneDao) context.getBean("phoneDao");

        Person person = new Person();
        personDao.save(person);

        Phone phone1 = new Phone();
        phoneDao.save(phone1);

        Phone phone2 = new Phone();
        phoneDao.save(phone2);

        phone1.setPerson(person);
        phone2.setPerson(person);

        phoneDao.update(phone1);
        phoneDao.update(phone2);
    }
}