package com.jpa.jpademo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ERRORS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorReport extends RuntimeException {

    public ErrorReport(String message) {
        super(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String exceptionType;

    @Column(nullable = false)
    private String message;

    @CreationTimestamp
    private Date timeOccurred;
}
