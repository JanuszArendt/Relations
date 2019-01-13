package pl.coderslab.relations.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.relations.model.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class AddressDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public void save(Address entity) {
        entityManager.persist(entity);
    }

    public Address findById(long id) {
        return entityManager.find(Address.class, id);
    }

    public void update(Address entity) {
        entityManager.merge(entity);
    }

    public void delete(long id) {
        Address entity = findById(id);

        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
