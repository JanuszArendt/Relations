package pl.coderslab.relations.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.relations.model.PhoneDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class PhoneDetailsDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public void save(PhoneDetails entity) {
        entityManager.persist(entity);
    }

    public PhoneDetails findById(long id) {
        return entityManager.find(PhoneDetails.class, id);
    }

    public void update(PhoneDetails entity) {
        entityManager.merge(entity);
    }

    public void delete(long id) {
        PhoneDetails entity = findById(id);

        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
