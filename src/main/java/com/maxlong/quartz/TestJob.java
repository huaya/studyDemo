package com.maxlong.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月17日 下午6:15:56
 * 类说明
 */
public class TestJob implements Job {

	SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date d = new Date();
	String returnstr = DateFormat.format(d);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		System.out.println(returnstr+"★★★★★★★★★★★");
	}
}
 