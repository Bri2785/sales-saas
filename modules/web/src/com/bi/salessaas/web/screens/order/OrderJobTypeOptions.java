package com.bi.salessaas.web.screens.order;

import com.bi.salessaas.entity.JobType;
import com.haulmont.cuba.gui.screen.ScreenOptions;

public class OrderJobTypeOptions implements ScreenOptions {
    private JobType orderJobType;

    public JobType getOrderJobType() {
        return orderJobType;
    }

    public void setOrderJobType(JobType orderJobType) {
        this.orderJobType = orderJobType;
    }
}
