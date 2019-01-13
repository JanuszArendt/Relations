package pl.coderslab.relations.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.relations.model.Phone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class PhoneDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public void save(Phone entity) {
        entityManager.persist(entity);
    }

    public Phone findById(long id) {
        return entityManager.find(Phone.class, id);
    }

    public void update(Phone entity) {
        entityManager.merge(entity);
    }

    public void delete(long id) {
        Phone entity = findById(id);

        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
