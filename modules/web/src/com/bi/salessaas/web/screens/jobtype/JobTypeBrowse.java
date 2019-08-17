package com.bi.salessaas.web.screens.jobtype;

import com.haulmont.cuba.gui.screen.*;
import com.bi.salessaas.entity.JobType;

@UiController("salessaas_JobType.browse")
@UiDescriptor("job-type-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class JobTypeBrowse extends MasterDetailScreen<JobType> {
}