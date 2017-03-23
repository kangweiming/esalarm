/**
 * 
 */
package com.all_union.es.esalarm.test.schedule;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.all_union.es.esalarm.common.ScheduleConstants;
import com.all_union.es.esalarm.pojo.schedule.ESQueryJob;
import com.all_union.es.esalarm.service.schedule.ScheduleService;

/** 
 * @Description: jobDataMap测试类
 * @author kwm
 * @date 2017年2月20日 下午2:43:03 
 * @version V1.0 
 * 
*/
//表示继承了SpringJUnit4ClassRunner类  
@RunWith(SpringJUnit4ClassRunner.class) 
//指定配置文件
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class PassParameterByJobDataMap {
	
	@Resource  
    private ScheduleService scheduleService; 
	
	public static void deleteJobAll() throws SchedulerException{
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<JobKey> keys = new ArrayList<JobKey>();		
		
		for (JobKey jobKey : jobKeys) {
			keys.add(jobKey);
		}
		
		scheduler.deleteJobs(keys);

	}
	
	public static void deleteJob(String jobName,String jobGroup,String triggerName,String triggerGroup) throws SchedulerException{
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		
		// 定义trigger key
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,triggerGroup);
		
		// 定义job key
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		
		if(scheduler.checkExists(triggerKey) && scheduler.checkExists(jobKey)){
			// 暂停触发器  
			scheduler.pauseTrigger(triggerKey);
			// 移除触发器
			scheduler.unscheduleJob(triggerKey);  
			// 删除任务 	
			scheduler.deleteJob(jobKey);				
		}
		else{
			System.err.println("========== can not find trigger key or job key");	
		}
		
		
	}
	
	public static void start() throws SchedulerException{
		System.err.println("start quartz2 ...");
		// 创建scheduler factory,默认从配置文件里读取相关信息-quartz.properties
		//SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		//deleteJob("ramjob","ourgroup","ramtrigger","ourgroup");
		
		// 删除所有任务
		//scheduler.clear();
		deleteJobAll();		
		
		//create first JobDetail and Trigger
		JobDetail jobDetail = JobBuilder.newJob(ESQueryJob.class)
				.withIdentity("JOB-1", "JGROUP-1")
				.withDescription("Job-1 for test")	
				.storeDurably()//job是否持久化，如果不是，没trigger关联时，这个job会自动被删除
				.build();
		
		// 忽略misfire, misfire产生的条件 注意配置 org.quartz.jobStore.misfireThreshold
		SimpleTrigger st = TriggerBuilder.newTrigger().withIdentity("ST-1", "STGROUP-1")
			    .startAt(new Date(Calendar.getInstance().getTimeInMillis()+ 5000))
			    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30)
			    		.repeatForever().withMisfireHandlingInstructionNextWithRemainingCount()).build();

		//add passing parameters to JobDataMap for first JobDetail 
		// 这个cout可以作为触发总数记录下来
		jobDetail.getJobDataMap().put(ScheduleConstants.FIRE_COUNT, 0);
		// 设置DSL
		jobDetail.getJobDataMap().put(ScheduleConstants.ES_QUERY_CONDITION,"es query ds1");
		
		scheduler.scheduleJob(jobDetail, st);
		
		jobDetail = JobBuilder.newJob(ESQueryJob.class)
				.withIdentity("JOB-2", "JGROUP-2")
				.withDescription("Job-2 for test")
				.storeDurably()
				.build();
		
		// // 忽略misfire, misfire产生的条件 注意配置 org.quartz.jobStore.misfireThreshold
		//Executes after every 10 second 0/10 * * * * ? 
		CronTrigger ct = (CronTrigger) TriggerBuilder.newTrigger()
				.withIdentity("CT-1", "CTGROUP-1")
			    .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?").withMisfireHandlingInstructionDoNothing())			    
			    .build();
		
		//add passing parameters to JobDataMap for second JobDetail 		
		// 这个cout可以作为触发总数记录下来
		jobDetail.getJobDataMap().put(ScheduleConstants.FIRE_COUNT, 0);
		// 设置DSL
		jobDetail.getJobDataMap().put(ScheduleConstants.ES_QUERY_CONDITION,"es query ds2");
		
		scheduler.scheduleJob(jobDetail, ct);		
		
		scheduler.start();
			
		try {
			//wait for 30 seconds to finish the job
			Thread.sleep(5 * 1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//shutdown scheduler gracefully
		scheduler.shutdown(true);		
	}
	
	@Test
	public void test() {
		try{
			start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		List<? extends JobDetail> jobs = this.scheduleService.listJobs();
		for(JobDetail job : jobs){
			System.err.println(" ============ " + job.getKey().toString());
		}
		
	}
}
