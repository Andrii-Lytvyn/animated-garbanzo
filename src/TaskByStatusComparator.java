

import java.util.Comparator;

public class TaskByStatusComparator implements Comparator<Task> {

  @Override
  public int compare(Task task1, Task task2) {
    if (task1.getStatus() && !task2.getStatus()) {
      return 1;
    } else if (!task1.getStatus() && task2.getStatus()) {
      return -1;
    }
    return task1.getTitle().compareTo(task2.getTitle());
  }
}
