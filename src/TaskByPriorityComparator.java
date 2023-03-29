

import java.util.Comparator;

public class TaskByPriorityComparator implements Comparator<Task> {

  @Override
  public int compare(Task task1, Task task2) {
    if (task1.getPriority() && !task2.getPriority()) {
      return 1;
    } else if (!task1.getPriority() && task2.getPriority()) {
      return -1;
    }
    return task1.getTitle().compareTo(task2.getTitle());
  }
}
