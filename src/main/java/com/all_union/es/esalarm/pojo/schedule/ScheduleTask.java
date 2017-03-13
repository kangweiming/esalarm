/**
 * 
 */
package com.all_union.es.esalarm.pojo.schedule;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

/** 
 * @Description: 集成“任务的概念”，成为一般意义上的任务
 * @author kwm
 * @date 2017年2月22日 下午1:40:52 
 * @version V1.0 
 * 
*/
public class ScheduleTask {
	
    /** 任务id */
    private String jobId;
    /**
     * JobJey 可获得job的name group 
     */
    private JobKey jobKey;
    /**
     * JobDetail 可获得job desc等
     */
    private JobDetail jobDetail;
    /**
     * TriggerKey 可获得trigger name group
     */
    private TriggerKey triggerKey;
    /**
     * Trigger 
     */
    private Trigger trigger;
    
    /** 
     * 若是CronTrigger 任务运行时间表达式 
     */
    private String cronExpression;
    
    /**
     * trigger status
     */
    private String triggerStatus;
    
	public String getTriggerStatus() {
		return triggerStatus;
	}

	public void setTriggerStatus(String triggerStatus) {
		this.triggerStatus = triggerStatus;
	}

	/**
     * elatiscsearch dsl 必须是String ，JobDataMap执行传String类型参数
     */
    private String DSL;
    /**
     * trigger出发次数
     */
    private int fireCount;
    

    public int getFireCount() {
		return fireCount;
	}

	public void setFireCount(int fireCount) {
		this.fireCount = fireCount;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public JobKey getJobKey() {
		return jobKey;
	}

	public void setJobKey(JobKey jobKey) {
		this.jobKey = jobKey;
	}

	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	public TriggerKey getTriggerKey() {
		return triggerKey;
	}

	public void setTriggerKey(TriggerKey triggerKey) {
		this.triggerKey = triggerKey;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDSL() {
		return DSL;
	}

	public void setDSL(String dSL) {
		DSL = dSL;
	}

        
}
