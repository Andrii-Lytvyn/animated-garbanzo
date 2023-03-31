

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class TaskByExecutorComparator implements Comparator<Task> {
  public static DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
  @Override
  public int compare(Task task1, Task task2) {
    if (!task1.getExecutor().equals(task2.getExecutor())) {
      return task1.getExecutor().compareTo(task2.getExecutor());
    }
    try {
      Date date1 = formatter.parse(task1.getStartTime());
      Date date2 = formatter.parse(task2.getStartTime());
      if (date1.before(date2)) {
        return -1;
      } else if (date2.before(date1)) {
        return 1;
      }
      return 0;
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
//    return task1.getTitle().compareTo(task2.getTitle());
  }
}
