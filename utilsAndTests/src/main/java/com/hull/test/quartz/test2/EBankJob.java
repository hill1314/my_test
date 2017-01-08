package com.hull.test.quartz.test2;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Administrator on 2017/1/4.
 */
public class EBankJob extends QuartzJobBean {
    private EBankJobBean eBankJobBean;

    public void setEBankJobBean(EBankJobBean eBankJobBean) {
        this.eBankJobBean = eBankJobBean;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        eBankJobBean.printAnotherMessage();
    }
}
