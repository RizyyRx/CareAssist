package com.hexaware.project.CareAssist.dto;

import jakarta.validation.constraints.NotNull;

public class PatientInsuranceDTO {

    @NotNull(message = "Plan ID is required")
    private Integer planId;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }
}
