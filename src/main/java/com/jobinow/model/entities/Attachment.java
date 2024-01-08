package com.jobinow.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

/**
 * Represents an attachment entity in the system.
 * This class includes information about an attachment, such as a cover letter, associated with a job application.
 *
 * @version 1.0
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment extends AbstractEntity {

    /**
     * The URL to the cover letter attachment.
     */
    @URL(message = "Attachment URL must be valid")
    private String attachmentUrl;

    /**
     * The job application to which the attachment belongs.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "attachment_apply")
    private Apply apply;

    /**
     * The company associated with the attachment.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "attachment_company")
    private Company company;
}