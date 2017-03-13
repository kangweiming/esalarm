/**
 * 
 */
package com.all_union.es.esalarm.pojo.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;

import com.all_union.es.esalarm.common.ScheduleConstants;

/** 
 * @Description: with annotations @PersistJobDataAfterExecution @DisallowConcurrentExecution
 * elasticsearch query job 
 * 
 * @author kwm
 * @date 2017年2月20日 下午2:37:13 
 * @version V1.0 
 * 
*/
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ESQueryJob implements Job{
	
	private static Logger logger = LogManager.getLogger(ESQueryJob.class);
	
	// 定义参数名
	//public static final String NAME = "name";

	// job私有变量 只在execute方法内有效
	//private String flag = "new object";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobKey jobKey =  null;
		try{
			JobDataMap dataMap = context.getJobDetail().getJobDataMap();
			
			//fetch parameters from JobDataMap			
			//获取job执行次数
			int count = dataMap.getInt(ScheduleConstants.FIRE_COUNT);
			// es query的参数也需要使用JobDataMap传进来，参数会自动被持久化
			String dsl = dataMap.getString(ScheduleConstants.ES_QUERY_CONDITION);
						
			jobKey = context.getJobDetail().getKey();
			
			//job执行成功后累加
			count++;
			
			//logger.info(jobKey+": "+ name+"-"+count+": flag="+flag);		
			//System.err.println(jobKey+" - "+ "Count: " + count + "  dsl : " + dsl);

			//add next counter to JobDataMap
			dataMap.put(ScheduleConstants.FIRE_COUNT, count);
			
			//flag= "object changed";			
		}
		catch(Exception ex){
			logger.error(jobKey+": error occured.");
			//System.err.println(jobKey+": error occured.");
			JobExecutionException jee = new JobExecutionException(ex);
			
			// job不重新执行
			jee.setRefireImmediately(false);
			//jee.refireImmediately();
			logger.info(jobKey+": not refire");
			
			// 停掉所有的trigger
			//jee.setUnscheduleAllTriggers(true);
			//logger.info(jobKey+": cancel all triggers");
			
			throw jee;			
		}
		

	}
}
