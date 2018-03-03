package com.jpa.jpademo.repository;

import com.jpa.jpademo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Gran1 on 28/02/2018.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User findById(Integer userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User u").getResultList();
    }

    @Override
    public User findByUserName(String username) {
        List users = entityManager.createQuery("from User u where u.username = :username")
                .setParameter("username", username)
                .getResultList();
        if(users.isEmpty()){
            return null;
        }
        return (User) users.get(0);
    }

    @Override
    public void deleteUser(Integer userId) {
        entityManager.remove(findById(userId));
    }

}
