package pl.coderslab.relations.tests.one_to_one;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.coderslab.relations.ApplicationConfig;
import pl.coderslab.relations.dao.PhoneDao;
import pl.coderslab.relations.dao.PhoneDetailsDao;
import pl.coderslab.relations.model.Phone;
import pl.coderslab.relations.model.PhoneDetails;

public class SavePhoneDetailWithPhone {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        saveCascade(context);

        // If you remove (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        // from PhoneDetails.phone you have to uncomment the following line:
//        saveWithoutCascade(context);
        context.close();
    }

    private static void saveCascade(AnnotationConfigApplicationContext context) {
        PhoneDetailsDao phoneDetailsDao = (PhoneDetailsDao) context.getBean("phoneDetailsDao");

        Phone phone = new Phone();

        PhoneDetails phoneDetails = new PhoneDetails();
        phoneDetails.setPhone(phone);

        phoneDetailsDao.save(phoneDetails);
    }

    private static void saveWithoutCascade(AnnotationConfigApplicationContext context) {
        PhoneDetailsDao phoneDetailsDao = (PhoneDetailsDao) context.getBean("phoneDetailsDao");
        PhoneDao phoneDao = (PhoneDao) context.getBean("phoneDao");

        Phone phone = new Phone();
        phoneDao.save(phone);

        PhoneDetails phoneDetails = new PhoneDetails();
        phoneDetails.setPhone(phone);

        phoneDetailsDao.save(phoneDetails);
    }
}
