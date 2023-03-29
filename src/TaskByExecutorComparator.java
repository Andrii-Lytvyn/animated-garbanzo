package src;

import java.util.Comparator;

public class TaskByExecutorComparator implements Comparator<Task> {

  @Override
  public int compare(Task task1, Task task2) {
    if (!task1.getExecutor().equals(task2.getExecutor())) {
      return task1.getExecutor().compareTo(task2.getExecutor());
    }
    return task1.getTitle().compareTo(task2.getTitle());
  }
}
