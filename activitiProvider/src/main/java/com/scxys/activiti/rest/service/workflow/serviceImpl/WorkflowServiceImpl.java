package com.scxys.activiti.rest.service.workflow.serviceImpl;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.scxys.activiti.service.WorkflowService;

import javax.validation.constraints.NotNull;


/**
 * @author 作者:
 * @version 创建时间:2017年3月22日 下午2:12:07
 * @description 说明:
 */
@Service(version = "1.0.0")
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	FormService formService;
	@Autowired
	HistoryService historyService;
	@Autowired
	IdentityService identityService;
	
	@Override
	public void delegateTasks(String afterDate,String beforeDate,String assignee,String delegateUser) {
		DateFormat dateFormat=DateFormat.getDateInstance();
		Date after= null;
		Date before= null;
		try {
			after = dateFormat.parse(afterDate);
			before = dateFormat.parse(beforeDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Task> taskList;
		if(("").equals(assignee)){
			taskList=taskService.createTaskQuery().taskCreatedAfter(after).taskCreatedBefore(before)
					.list();
		}else {
			taskList=taskService.createTaskQuery().taskCreatedAfter(after).taskCreatedBefore(before)
					.taskAssignee(assignee).list();
		}
		for(Task task:taskList) {
			taskService.delegateTask(task.getId(), delegateUser);
		}
	}

	@Override
	public void deployment(String name,String diagramData,String svgData) {
		//String classpath=WorkflowServiceImpl.class.getResource("/").toString().substring(6);
		/*本地文件地址*/
		//String diagramFilepath=classpath+"processes/"+name+".bpmn";
		//String svgFilepath=classpath+"processes/"+name+".svg";
		/*服务器上文件地址*/
		String diagramFilepath="/root/workflow/processes/"+name+".bpmn";
		String svgFilepath="/root/workflow/processes/"+name+".svg";
		PrintStream psDiagram = null;
		PrintStream psSvg = null;
		try {
			File diagramXml = new File(diagramFilepath);
			File svg = new File(svgFilepath);
			psDiagram = new PrintStream(new FileOutputStream(diagramXml),true);
			psSvg = new PrintStream(new FileOutputStream(svg),true);
			psDiagram.println(diagramData);// 往文件里写入字符串
			psSvg.println(svgData);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(psDiagram!=null&&psSvg!=null){
				psDiagram.close();
				psSvg.close();
			}
		}
		repositoryService.createDeployment()//创建部署对象
						.name(name)//添加部署名称
						.addClasspathResource("processes/"+name+".bpmn")
						.addClasspathResource("processes/"+name+".svg")
						.deploy();
	}

	@Override
	public String findBusinessKeyByPiId(String processInstanceId) {
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
				.processInstanceId(processInstanceId)//使用流程实例ID查询
				.singleResult();
		if(pi!=null){
			return pi.getBusinessKey();
		}
		return null;
	}

	/**使用任务ID，获取当前任务节点中对应的Form key中的连接的值*/
	public String findTaskFormKeyByTaskId(String taskId) {
		TaskFormData formData = formService.getTaskFormData(taskId);
		//获取Form key的值
		return formData.getFormKey();
	}

}
