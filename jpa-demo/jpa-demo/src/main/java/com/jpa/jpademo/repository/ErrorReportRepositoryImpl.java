package com.jpa.jpademo.repository;

import com.jpa.jpademo.model.ErrorReport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ErrorReportRepositoryImpl implements ErrorReportRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createError(ErrorReport errorReport) {
        entityManager.persist(errorReport);
    }
}
