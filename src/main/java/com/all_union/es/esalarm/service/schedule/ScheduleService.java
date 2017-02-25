/**
 * 
 */
package com.all_union.es.esalarm.service.schedule;

import java.util.Date;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Trigger;

import com.all_union.es.esalarm.pojo.schedule.ScheduleQuery;
import com.all_union.es.esalarm.pojo.schedule.ScheduleTask;

/** 
 * @Description: quartz2 scheduler services interface
 * @author kwm
 * @date 2017年2月21日 下午7:46:39 
 * @version V1.0 
 * 
*/
public interface ScheduleService {
	/**
	 * 初始化方法
	 */
	public void init();
	/**
	 * 回收方法
	 */
	public void  destory();
	
	/**
	 * 列出所有的Trigger
	 * @return
	 */
	public List<? extends Trigger> listTriggers();
	/**
	 * 列出所有的Job
	 * @return
	 */
	public List<? extends JobDetail> listJobs();
	/**
	 * 列出所有的任务
	 * @return
	 */
	public List<ScheduleTask> listTasks(ScheduleQuery query);
	/**
	 * trigger暂停
	 * @param name
	 * @param group
	 */
	public void pauseTrigger(String name,String group);
	/**
	 * 恢复trigger
	 * @param name
	 * @param group
	 */
	public void resumeTrigger(String name,String group);
	/**
	 * 立即运行一次job，使用的是临时的trigger
	 * @param name
	 * @param group
	 */
	public void runTrigger(String name,String group);
	/**
	 * 删除trigger
	 * @param name
	 * @param group
	 * @return
	 */
	public boolean deleteTrigger(String name,String group);
	/**
	 * 更新指定CronTrigger的expression
	 * @param name
	 * @param group
	 * @param expression
	 * @return
	 */
	public Date updateCronExcepression(String name,String group,String expression);
}
