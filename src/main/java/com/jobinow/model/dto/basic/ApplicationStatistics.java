package com.jobinow.model.dto.basic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ApplicationStatistics {
    private Long count;
    private LocalDate applicationDate;

    /**
     * custom constructor created to retrieve ApplicationRepository query method for retrieving application statistics from Object[] to ApplicationStatistics
     *
     * @param results object passed from query method
     */
    public ApplicationStatistics(Object[] results) {
        this.count = (Long) results[0];
        this.applicationDate = LocalDate.parse(results[1].toString());
    }
}
