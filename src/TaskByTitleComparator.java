package src;

import java.util.Comparator;

public class TaskByTitleComparator implements Comparator<Task> {

  @Override
  public int compare(Task task1, Task task2) {
    if (!task1.getTitle().equals(task2.getTitle())) {
      return task1.getTitle().compareTo(task2.getTitle());
    }
    return task1.getAuthor().compareTo(task2.getAuthor());
  }
}
