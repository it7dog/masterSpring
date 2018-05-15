package com.smart.basic.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

/**
 * @author wangw
 */
public class CronTriggerRunner {

  public static void main(String[] args) {
    JobDetail jobDetail = new JobDetail("job1_2", "jGroup1_2", SimpleJob.class);

    //创建CronTrigger 并指定组及名称
    CronTrigger cronTrigger = new CronTrigger("trigger1_2", "tgroup1");
    try {
      //定义执行表达式
      CronExpression cronExpression = new CronExpression("0/5 * * * * ?");
      cronTrigger.setCronExpression(cronExpression);

      SchedulerFactory schedulerFactory = new StdSchedulerFactory();
      Scheduler scheduler = schedulerFactory.getScheduler();
      scheduler.scheduleJob(jobDetail, cronTrigger);
      scheduler.start();

    } catch (ParseException e) {
      e.printStackTrace();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
}
