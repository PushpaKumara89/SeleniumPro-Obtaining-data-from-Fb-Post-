package sampleapp.service.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Scheduler {
    public void RunAllUrlsUpdate(){
        JobDetail j= JobBuilder.newJob(JobObject.class).build();
        Trigger t = TriggerBuilder.newTrigger().withIdentity("CroneTrigger").withSchedule(
                SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24)
                        .repeatForever()).build();

        try {
            org.quartz.Scheduler s = StdSchedulerFactory.getDefaultScheduler();

            s.start();
            s.scheduleJob(j, t);
        } catch (ObjectAlreadyExistsException e){

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
