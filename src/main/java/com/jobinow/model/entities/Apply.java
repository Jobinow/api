package com.jobinow.model.entities;

import com.jobinow.model.enums.ApplyStatus;
import com.jobinow.model.enums.ApplyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.util.Set;

/**
 * Represents a job application entity in the system.
 * This class includes information about a job application,
 * such as the job seeker, associated job offer, and resume attachments.
 *
 * @version 1.0
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Apply extends AbstractEntity {
    /**
     * Application status.
     */
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ApplyStatus status;

    /**
     * The job seeker who submitted the application.
     */
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User jobSeeker;

    /**
     * The job offer to which the application is submitted.
     */
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Offer offer;

    /**
     * The type of application (online or offline ...).
     */
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ApplyType applyType = ApplyType.OFFLINE;

    /**
     * The set of resume attachments (PDFs) associated with the application.
     */
    @OneToMany(
            mappedBy = "apply",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Attachment> resumePdfs;

    /**
     * Ensures that the status is set to PENDING before persisting if it is null.
     */
    @PrePersist
    private void isStatusNull() {
        if (status == null) this.status = ApplyStatus.PENDING;
    }
}