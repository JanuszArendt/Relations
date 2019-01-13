package pl.coderslab.relations.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.relations.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class PersonDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public void save(Person entity) {
        entityManager.persist(entity);
    }

    public Person findById(long id) {
        return entityManager.find(Person.class, id);
    }

    public void update(Person entity) {
        entityManager.merge(entity);
    }

    public void delete(long id) {
        Person entity = findById(id);

        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
