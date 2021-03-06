package com.scxys.activiti.bean.workflowBean;

import java.io.Serializable;

public class TaskNodeInfo implements Serializable{

    private static final long serialVersionUID = 4085005631935986462L;
    private String assignee;

    private String taskId;

    private String taskName;

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
