import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

// Singleton Pattern
class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private TaskFactory taskFactory;
    private Logger logger;
    private Scanner scanner;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        taskFactory = new TaskFactory();
        logger = Logger.getLogger(ScheduleManager.class.getName());
        scanner = new Scanner(System.in);
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void run() {
        handleUserInput();
    }

    private void handleUserInput() {
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. View Tasks");
        System.out.println("4. Edit Task");
        System.out.println("5. Mark Task as Completed");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
        
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (option) {
            case 1:
                addTask();
                break;
            case 2:
                removeTask();
                break;
            case 3:
                viewTasks();
                break;
            case 4:
                editTask();
                break;
            case 5:
                markTaskAsCompleted();
                break;
            case 6:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid option. Please choose again.");
        }

        // Recursive call to handle next user input
        handleUserInput();
    }

    private void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter start time (HH:mm): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter end time (HH:mm): ");
        String endTime = scanner.nextLine();
        System.out.print("Enter priority (High/Medium/Low): ");
        String priority = scanner.nextLine();

        Task task = taskFactory.createTask(description, startTime, endTime, priority);
        if (task != null) {
            if (isTaskConflict(task)) {
                logger.warning("Task conflicts with existing task");
                System.out.println("Error: Task conflicts with existing task");
            } else {
                tasks.add(task);
                logger.info("Task added successfully");
                System.out.println("Task added successfully. No conflicts.");
            }
        } else {
            logger.warning("Invalid task");
            System.out.println("Error: Invalid task");
        }
    }

    private boolean isTaskConflict(Task task) {
        for (Task existingTask : tasks) {
            if (task.getStartTime().isBefore(existingTask.getEndTime()) && task.getEndTime().isAfter(existingTask.getStartTime())) {
                return true;
            }
        }
        return false;
    }

    private Task getTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                return task;
            }
        }
        return null;
    }

    private void removeTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        Task task = getTask(description);
        if (task != null) {
            tasks.remove(task);
            logger.info("Task removed successfully");
            System.out.println("Task removed successfully");
        } else {
            logger.warning("Task not found");
            System.out.println("Error: Task not found");
        }
    }

    private void viewTasks() {
        if (tasks.isEmpty()) {
            logger.info("No tasks scheduled for the day");
            System.out.println("No tasks scheduled for the day");
        } else {
            tasks.sort((t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()));
            for (Task task : tasks) {
                System.out.println(task.toString());
            }
        }
    }

    private void editTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        Task task = getTask(description);
        if (task != null) {
            System.out.print("Enter new start time (HH:mm): ");
            String newStartTime = scanner.nextLine();
            System.out.print("Enter new end time (HH:mm): ");
            String newEndTime = scanner.nextLine();
            System.out.print("Enter new priority (High/Medium/Low): ");
            String newPriority = scanner.nextLine();

            task.setStartTime(LocalTime.parse(newStartTime));
            task.setEndTime(LocalTime.parse(newEndTime));
            task.setPriority(newPriority);
            logger.info("Task updated successfully");
            System.out.println("Task updated successfully");
        } else {
            logger.warning("Task not found");
            System.out.println("Error: Task not found");
        }
    }

    private void markTaskAsCompleted() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        Task task = getTask(description);
        if (task != null) {
            task.setCompleted(true);
            logger.info("Task marked as completed");
            System.out.println("Task marked as completed");
        } else {
            logger.warning("Task not found");
            System.out.println("Error: Task not found");
        }
    }
}
