/**
 * 
 */
package com.all_union.es.esalarm.pojo.schedule;

import java.io.Serializable;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import com.all_union.es.esalarm.dao.Query;

/** 
 * @Description: schedule 查询条件 Query.java自带分页
 * @author kwm
 * @date 2017年2月23日 下午1:18:21 
 * @version V1.0 
 * 
*/
public class ScheduleQuery extends Query implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4535295411450741857L;
	
	// 开始时间段的起始时间
	private String gmtStart1;

	// 开始时间段的结束时间
	private String gmtEnd1;
	
	// 结束时间段的起始时间
	private String gmtStart2;

	// 结束时间段的结束时间
	private String gmtEnd2;
	
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
    
    private String TriggerName;
    
    private String TriggerGroup;
    
    public String getGmtStart1() {
		return gmtStart1;
	}

	public void setGmtStart1(String gmtStart1) {
		this.gmtStart1 = gmtStart1;
	}

	public String getGmtEnd1() {
		return gmtEnd1;
	}

	public void setGmtEnd1(String gmtEnd1) {
		this.gmtEnd1 = gmtEnd1;
	}

	public String getGmtStart2() {
		return gmtStart2;
	}

	public void setGmtStart2(String gmtStart2) {
		this.gmtStart2 = gmtStart2;
	}

	public String getGmtEnd2() {
		return gmtEnd2;
	}

	public void setGmtEnd2(String gmtEnd2) {
		this.gmtEnd2 = gmtEnd2;
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

	public String getTriggerName() {
		return TriggerName;
	}

	public void setTriggerName(String triggerName) {
		TriggerName = triggerName;
	}

	public String getTriggerGroup() {
		return TriggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		TriggerGroup = triggerGroup;
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

	public String getTriggerStatus() {
		return triggerStatus;
	}

	public void setTriggerStatus(String triggerStatus) {
		this.triggerStatus = triggerStatus;
	}

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
	

}
