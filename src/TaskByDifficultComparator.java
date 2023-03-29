//package src;

import java.util.Comparator;

public class TaskByDifficultComparator implements Comparator<Task> {

  @Override
  public int compare(Task task1, Task task2) {
    if (task1.getDifficult() && !task2.getDifficult()) {
      return 1;
    } else if (!task1.getDifficult() && task2.getDifficult()) {
      return -1;
    }
    return task1.getTitle().compareTo(task2.getTitle());
  }
}
