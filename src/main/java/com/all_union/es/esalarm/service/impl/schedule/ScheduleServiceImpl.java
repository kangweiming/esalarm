/**
 * 
 */
package com.all_union.es.esalarm.service.impl.schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.NameMatcher;
import org.springframework.stereotype.Service;

import com.all_union.es.esalarm.common.ScheduleConstants;
import com.all_union.es.esalarm.pojo.schedule.ScheduleQuery;
import com.all_union.es.esalarm.pojo.schedule.ScheduleTask;
import com.all_union.es.esalarm.service.schedule.ScheduleService;
import com.kwm.common.lang.DateUtil;
import com.kwm.common.lang.StringUtil;

/** 
 * @Description: quartz2 scheduler service
 * @author kwm
 * @date 2017年2月21日 下午7:49:43 
 * @version V1.0 
 * 
*/
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	private static Logger logger = LogManager.getLogger(ScheduleServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.schedule.ScheduleService#init()
	 */
	@Override
	@PostConstruct //容器启动时执行
	public void init() {
		logger.debug("init quartz2 scheduler with JDBC ...");
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();			
		}
		catch(SchedulerException ex){
			logger.error(ex.getMessage(),ex);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.schedule.ScheduleService#destory()
	 */
	@Override
	@PreDestroy
	public void destory() {
		logger.debug("destroy quartz2 scheduler ...");
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			if(!scheduler.isShutdown())
				scheduler.shutdown();
		}
		catch(SchedulerException ex){
			logger.error(ex.getMessage(),ex);
		}
	}

	@Override
	public List<? extends Trigger> listTriggers() {
		logger.debug("list all triggers ");
		
		List<Trigger> list = new ArrayList<Trigger>();
		
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			GroupMatcher<TriggerKey> matcher = GroupMatcher.anyTriggerGroup();
			
			Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(matcher);
			
			for(TriggerKey triggerKey : triggerKeys){
				list.add(scheduler.getTrigger(triggerKey));					
			}			
		}
		catch(SchedulerException ex){
			logger.error(ex.getMessage(),ex);
			return null;
		}
		
		return list;
	}

	@Override
	public List<? extends JobDetail> listJobs() {
		logger.debug("list all jobs ");
		
		List<JobDetail> list = new ArrayList<JobDetail>();
		
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			
			for(JobKey jobKey : jobKeys){
				list.add(scheduler.getJobDetail(jobKey));					
			}			
		}
		catch(SchedulerException ex){
			logger.error(ex.getMessage(),ex);
			return null;
		}
		
		return list;
	}

	@Override
	public List<ScheduleTask> listTasks(ScheduleQuery query) {
		
		logger.debug("list all tasks ");
		List<ScheduleTask> list = new ArrayList<ScheduleTask>();
		
//		try{
//			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
//			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
//			
//			for (JobKey jobKey : jobKeys) {
//			    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
//			    for (Trigger trigger : triggers) {
//			    	ScheduleTask task = new ScheduleTask();
//			        
//			    	task.setJobKey(jobKey);
//			    	task.setJobDetail(scheduler.getJobDetail(jobKey));
//			    	task.setTriggerKey(trigger.getKey());
//			    	task.setTrigger(trigger);
//			    	task.setTriggerStatus(scheduler.getTriggerState(trigger.getKey()).name());
//			    	//scheduler.pauseJob(jobKey);
//			    	//scheduler.pauseTrigger(triggerKey);
//			    	// dsl
//			    	task.setDSL(scheduler.getJobDetail(jobKey).getJobDataMap().getString(ScheduleConstants.ES_QUERY_CONDITION));
//			    	// fire count
//			    	task.setFireCount(scheduler.getJobDetail(jobKey).getJobDataMap().getInt(ScheduleConstants.FIRE_COUNT));
//			    	
//			        if (trigger instanceof CronTrigger) {
//			            CronTrigger cronTrigger = (CronTrigger) trigger;
//			            task.setCronExpression(cronTrigger.getCronExpression());
//			        }
//			        
//			        list.add(task);
//			    }
//			}			
//		}
//		catch(SchedulerException ex){
//			logger.error(ex.getMessage(),ex);
//			return null;
//		}

		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			GroupMatcher<TriggerKey> matcher ;
			
			// GroupMatcher没有notEquals？？？	
			// 匹配trigger group
			if(StringUtil.isNotBlank(query.getTriggerGroup()))
				matcher = GroupMatcher.triggerGroupContains(query.getTriggerGroup());
			else
				matcher = GroupMatcher.anyTriggerGroup();
						
			Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(matcher);
			
			
			for(TriggerKey triggerKey : triggerKeys){
							
				Trigger trigger = scheduler.getTrigger(triggerKey);
				
				// 过滤掉trigger的DEFAULT组，这个组标识一般用于临时任务，如scheduler.triggerJob()方法
				if(triggerKey.getGroup().equals("DEFAULT"))
					continue;
				// 匹配trigger name
				if(!StringUtil.isBlank(query.getTriggerName()))
					if(!StringUtil.contains(triggerKey.getName(), query.getTriggerName()))
						continue;
				// 匹配trigger status
				if(!StringUtil.isBlank(query.getTriggerStatus()))
					if(!scheduler.getTriggerState(trigger.getKey()).name().equals(query.getTriggerStatus()))
						continue;
				
				// 时间过滤 开始时间
				if(!StringUtil.isBlank(query.getGmtStart1()))
					if(trigger.getStartTime().getTime() < DateUtil.getYmdDate(query.getGmtStart1()).getTime())
						continue;
				if(!StringUtil.isBlank(query.getGmtEnd1()))
					if(trigger.getStartTime().getTime() > DateUtil.getYmdDate(query.getGmtEnd1()).getTime())
						continue;
				
				// 时间过滤 结束时间
				if(trigger.getEndTime() != null){
					if(!StringUtil.isBlank(query.getGmtStart2()))
						if(trigger.getEndTime().getTime() < DateUtil.getYmdDate(query.getGmtStart2()).getTime())
							continue;				
					if(!StringUtil.isBlank(query.getGmtEnd2()))
						if(trigger.getEndTime().getTime() > DateUtil.getYmdDate(query.getGmtEnd2()).getTime())
							continue;					
				}
					
				
				ScheduleTask task = new ScheduleTask();
				
				JobKey jobKey = trigger.getJobKey();
				
		    	task.setJobKey(jobKey);
		    	task.setJobDetail(scheduler.getJobDetail(jobKey));
		    	task.setTriggerKey(trigger.getKey());
		    	task.setTrigger(trigger);
		    	task.setTriggerStatus(scheduler.getTriggerState(trigger.getKey()).name());
		    	// dsl
		    	task.setDSL(scheduler.getJobDetail(jobKey).getJobDataMap().getString(ScheduleConstants.ES_QUERY_CONDITION));
		    	// fire count
		    	task.setFireCount(scheduler.getJobDetail(jobKey).getJobDataMap().getInt(ScheduleConstants.FIRE_COUNT));
		    	
		        if (trigger instanceof CronTrigger) {
		            CronTrigger cronTrigger = (CronTrigger) trigger;
		            task.setCronExpression(cronTrigger.getCronExpression());
		        }
		        
		        list.add(task);				
			}			
		}
		catch(SchedulerException ex){
			logger.error(ex.getMessage(),ex);
			return null;
		}
		
		// 设置总数
		query.setTotalItem(list.size());
		
		// 只保留当前页的task对象
		// limit #{pageFristItemZero},#{pageSize}		
		return list.subList(query.getPageFristItemZero(), query.getPageLastItem());
		
		//return list;		
		
	}

	@Override
	public void pauseTrigger(String name, String group) {
		logger.debug("pause trigger ");
		
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();			
			scheduler.pauseTrigger(new TriggerKey(name,group));			
		}
		catch(SchedulerException ex){
			logger.error(ex.getMessage(),ex);			
		}
			
	}

	@Override
	public void resumeTrigger(String name, String group) {
		logger.debug("resume trigger ");
		
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();			
			scheduler.resumeTrigger(new TriggerKey(name,group));			
		}
		catch(SchedulerException ex){
			logger.error(ex.getMessage(),ex);			
		}	
	}

	@Override
	public void runTrigger(String name, String group) {
		logger.debug("run job immediately ");
		
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();	
			JobKey jobKey = new JobKey(name,group);
			
//			JobDataMap map = scheduler.getJobDetail(jobKey).getJobDataMap();			
//			logger.info(map.getString(ScheduleConstants.ES_QUERY_CONDITION));
//			logger.info(map.getInt(ScheduleConstants.FIRE_COUNT));
			
			// 不用担心 JobDataMap的问题，里面的数据仍然保持
			// 不用手动删除新生成的临时trigger，会自动删除，但是删除的有点慢
			scheduler.triggerJob(jobKey);			
		}
		catch(SchedulerException ex){
			logger.error(ex.getMessage(),ex);			
		}			
	}

	@Override
	public boolean deleteTrigger(String name, String group) {
		logger.debug("delete trigger ");
		
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();	
						
			// 如果job没有关联的trigger，并且job是非持久化的，job也会被删除,job持久化指的的是jobDetail的storeDurably()
			return scheduler.unscheduleJob(new TriggerKey(name,group));	
			
		}
		catch(SchedulerException ex){
			logger.error(ex.getMessage(),ex);	
			return false;
		}		
	}

	@Override
	public Date updateCronExcepression(String name, String group, String expression) {
		// 还未测试，不建议使用，更新操作可以采用，删除旧的，再创建的方式
		logger.debug("update cronTrigger expression ");
		
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();	
			TriggerKey triggerKey = TriggerKey.triggerKey(name, group);	
			
			Trigger trigger = scheduler.getTrigger(triggerKey);
			
			if(trigger instanceof CronTrigger){
				
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(expression);
				
				CronTrigger ct = (CronTrigger) TriggerBuilder.newTrigger()
						.withIdentity(triggerKey)
					    .withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing())			    
					    .build();
				
				return scheduler.rescheduleJob(triggerKey, ct);
			}
			else{
				return null;
			}
			
		}
		catch(SchedulerException ex){
			logger.error(ex.getMessage(),ex);	
			return null;
		}
	}


}
