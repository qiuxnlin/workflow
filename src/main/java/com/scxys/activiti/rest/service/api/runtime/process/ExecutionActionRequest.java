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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.scxys.activiti.rest.service.api.RestActionRequest;
import com.scxys.activiti.rest.service.api.engine.variable.RestVariable;

/**
 * @author Frederik Heremans
 */
public class ExecutionActionRequest extends RestActionRequest {

  public static final String ACTION_SIGNAL = "signal";
  public static final String ACTION_SIGNAL_EVENT_RECEIVED = "signalEventReceived";
  public static final String ACTION_MESSAGE_EVENT_RECEIVED = "messageEventReceived";
  
  protected String signalName;
  protected String messageName;
  protected List<RestVariable> variables;
  
  public void setVariables(List<RestVariable> variables) {
    this.variables = variables;
  }
  
  @JsonTypeInfo(use=Id.CLASS, defaultImpl=RestVariable.class)
  public List<RestVariable> getVariables() {
    return variables;
  }
  
  public String getSignalName() {
    return signalName;
  }
  
  public void setSignalName(String signalName) {
    this.signalName = signalName;
  }
  
  public String getMessageName() {
    return messageName;
  }
  
  public void setMessageName(String messageName) {
    this.messageName = messageName;
  }
}
