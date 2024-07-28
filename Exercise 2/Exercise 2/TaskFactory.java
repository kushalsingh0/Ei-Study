import java.time.LocalTime;

class TaskFactory {
    public Task createTask(String description, String startTime, String endTime, String priority) {
        try {
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);
            return new Task(description, start, end, priority);
        } catch (Exception e) {
            return null;
        }
    }
}