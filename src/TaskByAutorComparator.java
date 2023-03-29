

import java.util.Comparator;

public class TaskByAutorComparator implements Comparator<Task> {

  @Override
  public int compare(Task task1, Task task2) {
    if (!task1.getAuthor().equals(task2.getAuthor())) {
      return task1.getAuthor().compareTo(task2.getAuthor());
    }
    return task1.getTitle().compareTo(task2.getTitle());
  }
}
