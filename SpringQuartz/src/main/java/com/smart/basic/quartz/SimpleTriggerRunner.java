package com.smart.basic.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author wangw
 */
public class SimpleTriggerRunner {
  public static void main(String[] args) {
    //创建一个jobDetail实例
    JobDetail jobDetail = new JobDetail("job1_1","jgroup1",SimpleJob.class);
    //通过SimpleTrigger定义调度规则：马上启动，每2秒执行一次，共运行100次
    SimpleTrigger simpleTrigger = new SimpleTrigger("trigger1_1","tgroup1");
    simpleTrigger.setStartTime(new Date());
    simpleTrigger.setRepeatInterval(2000);
    simpleTrigger.setRepeatCount(100);
    //通过ScheduleFactory获取一个调度器实例
    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    try {
      Scheduler scheduler = schedulerFactory.getScheduler();

      //注册并进行调度
      scheduler.scheduleJob(jobDetail,simpleTrigger);
      //调度启动
      scheduler.start();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
}
