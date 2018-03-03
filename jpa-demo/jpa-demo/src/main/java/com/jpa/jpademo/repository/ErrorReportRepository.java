package com.jpa.jpademo.repository;

import com.jpa.jpademo.model.ErrorReport;

public interface ErrorReportRepository {
    void createError(ErrorReport errorReport);
}
