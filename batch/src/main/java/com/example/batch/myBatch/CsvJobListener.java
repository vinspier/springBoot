package com.example.batch.myBatch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CsvJobListener implements JobExecutionListener {

    long startTime;
    long endTime;
    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        System.out.println("任务处理开始---------"+startTime);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        System.out.println("任务处理结束---------"+endTime + "   耗时--------"+(endTime-startTime));
    }
}
