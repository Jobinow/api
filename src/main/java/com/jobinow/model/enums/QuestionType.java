package com.jobinow.model.enums;

/**
 * Enumeration of different types of quiz questions.
 * This enumeration facilitates the categorization of questions based on their format
 * and response mechanism.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
public enum QuestionType {
    /**
     * Questions with multiple choices, only one correct answer.
     */
    MULTIPLE_CHOICE,

    /**
     * Questions with only two choices: true or false.
     */
    TRUE_FALSE,

    /**
     * Questions with multiple choices where more than one answer can be correct.
     */
    MULTIPLE_RESPONSE,

    /**
     * Questions where the participant must fill in one or more blanks.
     */
    FILL_IN_THE_BLANK,

    /**
     * Questions where the participant must select a single item from a list.
     */
    SINGLE_CHOICE,

    /**
     * Questions where the participant must match items from two lists.
     */
    MATCHING,

    /**
     * Open-ended questions requiring a short, precise answer.
     */
    SHORT_ANSWER,

    /**
     * Questions asking for an extended response, often used to assess critical thinking.
     */
    ESSAY,

    EASY
}
