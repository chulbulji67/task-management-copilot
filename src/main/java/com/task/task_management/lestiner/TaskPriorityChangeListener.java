package com.task.task_management.lestiner;

import com.task.task_management.dto.TaskPriorityChangeEvent;
import com.task.task_management.entity.Task;
import com.task.task_management.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskPriorityChangeListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    public void handleTaskPriorityChange(TaskPriorityChangeEvent event) {
        // Here, you could send an email, log the event, or notify via a different channel
        System.out.println("Task " + event.getTaskId() + " priority changed from " + event.getOldPriority() + " to " + event.getNewPriority());

        // You can implement an email or SMS notification here.
        log.info("Task " + event.getTaskId() + " priority changed from " + event.getOldPriority() + " to " + event.getNewPriority());
//        String assigneeEmail = event.get
//        emailService.sendEmail(
//                task.getAssigneeEmail(),
//                "Task Due Date Reminder",
//                "Your task '" + task.getTitle() + "' is due tomorrow."
//        );
    }
}
