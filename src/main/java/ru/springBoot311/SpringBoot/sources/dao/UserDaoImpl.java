package ru.springBoot311.SpringBoot.sources.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.springBoot311.SpringBoot.sources.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    /**пришлось убрать коструктор с параметром EntityManager потому что после перехода на SpringBoot
     * от нас ушёл файл конфигурации с определением бина EntityManager, и сейчас
     * в реализованном кострукторе с этим параметром идет мерзкое красное подчёркивание
     * бесит
     * */
   @PersistenceContext
    private   EntityManager entityManager;

    @Override
    @Transactional
    public void addUserToDatabase(User user) {
        entityManager.persist(user);
    }

    @Override
    /**Убрал @Transactional, так как запрос в единственном числе(нет нескольких запросов), но и не требуется для чтения данных,
     * так как в Базу данных не будет внесено никаких измнений, чтобы в случае чего вызывать Rollback, хотя может я и не прав */
    public List<User> getAllUsersFromDatabase() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();

    }

    @Override
    @Transactional
    public void deleteUserFromDatabase(long id) {
        entityManager.remove(getUserByIdFromDatabase(id));
    }

    @Override  /**Убрал @Transactional, так как запрос в единственном числе(нет нескольких запросов), но и не требуется для чтения данных,
     * так как в Базу данных не будет внесено никаких измнений, чтобы в случае чего вызывать Rollback, хотя может я и не прав */
    public User getUserByIdFromDatabase(long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void updateUserInDatabase(User user) {
        entityManager.merge(user);
    }
}
