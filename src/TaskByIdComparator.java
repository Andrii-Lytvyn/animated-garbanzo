

import java.util.Comparator;

public class TaskByIdComparator implements Comparator<Task> {

  @Override
  public int compare(Task task1, Task task2) {
    return task1.getID() - task2.getID();
  }
}
