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

package com.scxys.activiti.rest.service.api.runtime.process;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scxys.activiti.rest.common.api.DataResponse;



/**
 * @author Frederik Heremans
 */
@RestController
public class ProcessInstanceQueryResource extends BaseProcessInstanceResource {

  @RequestMapping(value="/query/process-instances", method = RequestMethod.POST, produces="application/json")
  public DataResponse queryProcessInstances(@RequestBody ProcessInstanceQueryRequest queryRequest, 
      @RequestParam Map<String,String> allRequestParams, HttpServletRequest request) {
    
    return getQueryResponse(queryRequest, allRequestParams);
  }
}
