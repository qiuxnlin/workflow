/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.scxys.activiti.rest.service.api.history;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scxys.activiti.rest.service.api.RestResponseFactory;


/**
 * @author Tijs Rademakers
 */
@RestController
public class HistoricProcessInstanceResource {
  
  @Autowired
  protected RestResponseFactory restResponseFactory;
  
  @Autowired
  protected HistoryService historyService;

  @RequestMapping(value="/history/historic-process-instances/{processInstanceId}", method = RequestMethod.GET, produces = "application/json")
  public HistoricProcessInstanceResponse getProcessInstance(@PathVariable String processInstanceId, HttpServletRequest request) {
    return restResponseFactory.createHistoricProcessInstanceResponse(getHistoricProcessInstanceFromRequest(processInstanceId));
  }
  
  @RequestMapping(value="/history/historic-process-instances/{processInstanceId}", method = RequestMethod.DELETE)
  public void deleteProcessInstance(@PathVariable String processInstanceId, HttpServletResponse response) {
    historyService.deleteHistoricProcessInstance(processInstanceId);
    response.setStatus(HttpStatus.NO_CONTENT.value());
  }
  
  protected HistoricProcessInstance getHistoricProcessInstanceFromRequest(String processInstanceId) {
    HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
           .processInstanceId(processInstanceId).singleResult();
    if (processInstance == null) {
      throw new ActivitiObjectNotFoundException("Could not find a process instance with id '" + processInstanceId + "'.", HistoricProcessInstance.class);
    }
    return processInstance;
  }
}
