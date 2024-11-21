package com.task.task_management.service;

import com.task.task_management.entity.Task;
import com.task.task_management.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DueDateReminderService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 9 * * ?") // Every day at 9 AM
    public void sendDueDateReminders() {
        log.info("Starting due date reminder process.");

        // Define the reminder window (tasks due tomorrow)
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfReminderWindow = now.plusDays(1).toLocalDate().atStartOfDay();
        LocalDateTime endOfReminderWindow = startOfReminderWindow.plusDays(2);

        log.info("Reminder window: {} to {}", startOfReminderWindow, endOfReminderWindow);

        // Fetch tasks due within the reminder window
//        List<Task> tasks = taskRepository.findTasksByDueDateBetween(startOfReminderWindow, endOfReminderWindow);
        List<Task> tasks = taskRepository.findTasksByDueDateBefore(startOfReminderWindow);

        log.info("Total tasks found: {}", tasks.size());

        for (Task task : tasks) {
            log.info("Processing task: {}", task.getTitle());

            // Compare dates to ensure accurate reminder
            LocalDate today = LocalDate.now();
            LocalDate dueDate = task.getDueDate().toLocalDate();

            if (today.plusDays(1).getDayOfMonth() == dueDate.getDayOfMonth()) { // Ensure the task is due tomorrow
                emailService.sendEmail(
                        "chulbulji67@gmail.com", // Replace with dynamic recipient email
                        "Task Due Date Reminder",
                        "Your task '" + task.getTitle() + "' is due tomorrow."
                );
                log.info("Reminder sent for task: {}", task.getTitle());
            } else {
                log.info("Task not due tomorrow, skipping reminder for: {}", task.getTitle());
            }
        }
    }

}

