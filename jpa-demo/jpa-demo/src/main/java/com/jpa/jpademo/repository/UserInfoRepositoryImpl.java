package com.jpa.jpademo.repository;

import com.jpa.jpademo.model.User;
import com.jpa.jpademo.model.UserInfo;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Gran1 on 28/02/2018.
 */
@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserInfo save(UserInfo userInfo) {
        entityManager.persist(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        return entityManager.merge(userInfo);
    }

    @Override
    public UserInfo findByUserId(Integer userInfoId) {
        return entityManager.find(UserInfo.class, userInfoId);
    }

    @Override
    public UserInfo findByUser(User user) {
        String jpql = "from UserInfo uf where uf.user.id = :userId";
        List users = entityManager
                .createQuery(jpql)
                .setParameter("userId", user.getId())
                .getResultList();

        if(users.isEmpty()){
            return null;
        }
        return (UserInfo) users.get(0);
    }

    @Override
    public List<UserInfo> findAll() {
        return entityManager.createQuery("from UserInfo ui").getResultList();
    }

    @Override
    public void deleteUserInfo(Integer id) {
        entityManager.remove(findByUserId(id));
    }
}
