package com.maxlong.study.springBatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/** 
* @author 作者 maxlong: 
* @version 创建时间：2016年7月22日 上午10:54:35 
* 类说明 
*/
public class writeTasklet implements Tasklet {

    /** Message */
    private String message;

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
            throws Exception {
    	StepContext sc = arg1.getStepContext();
    	sc.setAttribute("parm", "maxlong");
    	String sdf = sc.getStepName();
    	System.out.println(sdf);
    	if(sdf.equals("step_world")){
    		System.out.println(sc.getAttribute("parm"));
    	}
        System.out.println(message);
        return RepeatStatus.FINISHED;
    }
}
 