package pl.coderslab.relations.tests.one_to_one;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.coderslab.relations.ApplicationConfig;
import pl.coderslab.relations.dao.AddressDao;
import pl.coderslab.relations.dao.PersonDao;
import pl.coderslab.relations.dao.PhoneDao;
import pl.coderslab.relations.dao.PhoneDetailsDao;
import pl.coderslab.relations.model.Address;
import pl.coderslab.relations.model.Person;
import pl.coderslab.relations.model.Phone;
import pl.coderslab.relations.model.PhoneDetails;

public class SavePhoneWithPhoneDetail {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        saveCascade(context);

        // If you remove cascade = {CascadeType.PERSIST, CascadeType.MERGE}
        // from Phone.details you have to uncomment the following line:
//        saveWithoutCascade(context);
        context.close();
    }

    private static void saveCascade(AnnotationConfigApplicationContext context) {
        PhoneDao phoneDao = (PhoneDao) context.getBean("phoneDao");

        PhoneDetails phoneDetails = new PhoneDetails();

        Phone phone = new Phone();
        phoneDetails.setPhone(phone);
        phone.setDetails(phoneDetails);

        phoneDao.save(phone);
    }

    private static void saveWithoutCascade(AnnotationConfigApplicationContext context) {
        PhoneDao phoneDao = (PhoneDao) context.getBean("phoneDao");
        PhoneDetailsDao phoneDetailsDao = (PhoneDetailsDao) context.getBean("phoneDetailsDao");

        PhoneDetails phoneDetails = new PhoneDetails();
        phoneDetailsDao.save(phoneDetails);

        Phone phone = new Phone();
        phoneDao.save(phone);

        phoneDetails.setPhone(phone);
        phoneDetailsDao.update(phoneDetails);
    }
}
