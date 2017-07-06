package com.scxys.activiti.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.scxys.activiti.bean.ActFlowclassify;
import com.scxys.activiti.bean.ActProcess;
import com.scxys.activiti.service.ActProcessService;

/**
 * @author 作者:qiuxinlin
 * @version 创建时间:2017年7月4日 下午2:36:12
 * @description 说明:流程controller
 */
@RestController
public class ActProcessCtl {

	@Reference(version = "1.0.0")
	ActProcessService actProcessService;

	@RequestMapping(value = "/actProcess", method = RequestMethod.POST, produces = "application/json")
	public void addProcess(@RequestBody ActProcess actProcess, HttpServletRequest request,
			HttpServletResponse response) {
		actProcessService.addProcess(actProcess);
	}

	@RequestMapping(value = "/actProcess", method = RequestMethod.GET, produces = "application/json")
	public List<Map> getProcess(String pid) {
		return actProcessService.findTreeByPid(pid);
	}
	
	@RequestMapping(value = "/actProcess/{processCode}", method = RequestMethod.GET, produces = "application/json")
	public ActProcess getProcessById(@PathVariable("processCode") String processCode) {
		ActProcess aa=actProcessService.findProcessByCode(processCode);
		return actProcessService.findProcessByCode(processCode);
	}
}