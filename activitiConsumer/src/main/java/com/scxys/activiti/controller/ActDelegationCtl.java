package com.scxys.activiti.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.scxys.activiti.bean.ActDelegation;
import com.scxys.activiti.bean.ActFlowclassify;
import com.scxys.activiti.service.ActDelegationService;

/** 
* @author 作者:qiuxinlin 
* @version 创建时间:2017年7月17日 下午2:32:57 
* @description 说明:
*/
@RestController
public class ActDelegationCtl {

	@Reference(version="1.0.0")
	ActDelegationService delegationService;
	
	@RequestMapping(value="/actDelegation", method = RequestMethod.POST, produces = "application/json")
	public void add(@RequestBody ActDelegation delegation, HttpServletRequest request, HttpServletResponse response){
		delegationService.add(delegation);
	}
	@RequestMapping(value="/actDelegation", method=RequestMethod.GET)
	public List<ActDelegation> findAll(){
		List<ActDelegation> list=delegationService.findAll();
		return list;
	}
}
 