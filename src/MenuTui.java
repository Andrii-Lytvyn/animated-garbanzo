package src;

//line 64 - 76


import com.sun.tools.javac.Main;

import java.text.Format;
import java.util.List;

public class MenuTui {
  public static final String LINE = "------------------------------------------------------------------------------------------------------";
  public static final String HEADER = "| ID |  Author  | Executor |         Title           |   Start  |  Finish  |Priority|Difficult|Status|";
  public static final String SHOW_ALL_MENU = "|SORT: 1-Author 2-Executor 3-Title 4-Priority 5-Difficult 6-Status|7-READ 8-ADD 9-DEL F-FINISH G-GUNT|";

  //  AllOutputs Graph + Logic
//  Add Methods:

//  ShowLogin -- Andrii Litvin
//  ShowAll -- Sasha
//  ShowMenu -- Andrii Golik, General boolean - true; user - false
//  AddTask  -- Sasha
//  ReadTask -- Sasha
//  EditTask -- Sasha
//  Gant -- Sasha
//  FinishTask -- Sasha
//  Delete -- Sasha
//  ChangeUser -- Andrii Golik
//  Exit   -- Andrii Golik!!!

  public void printHeader() {
    System.out.println(LINE);
    System.out.println(HEADER);
    System.out.println(LINE);
  }

  public void showAllMenu() {
    System.out.println(LINE);
    System.out.println(SHOW_ALL_MENU);
    System.out.println(LINE);
  }

  public void showAll(List<Task> tasks) {
    tasks.sort(new TaskByIdComparator());

    for (Task task : tasks) {
      String title = task.getTitle();
      if (title.length() > 25) {
        title = title.substring(0, 22) + "...";
      }
      if (task.getDeleted()) {
        continue;
      }
      String taskRow = String.format("|%4d|%10s|%10s|%25s|%10s|%10s|%8s|%9s|%6s|",
          task.getID(), task.getAuthor(), task.getExecutor(), title, task.getStartTime(),
          task.getFinishTime(), task.getPriority(), task.getDifficult(), task.getStatus());
      System.out.println(taskRow);
    }
  }

  public void readTask(List<Task> tasks, int id) {
    for (Task task : tasks) {
      if (task.getID() == id) {
        System.out.println("Task ID: " + task.getID());
        System.out.println("Title: ");
        System.out.println(task.getTitle());
        System.out.printf("Author: %s Executor: %s%n", task.getAuthor(), task.getExecutor());
        System.out.printf("Start date: %s Finish date: %s%n",
            task.getStartTime(), task.getFinishTime());
        String priority = "Low";
        String difficult = "Low";
        String status = "Executing";
        if (task.getPriority()) {
          priority = "High";
        }
        if (task.getDifficult()) {
          difficult = "High";
        }
        if (task.getStatus()) {
          status = "Finished";
        }
        System.out.printf("Priority: %s Difficult: %s Status: %s%n", priority, difficult, status);
        System.out.println();
        System.out.println("1-EDIT 2-EXIT");
      }
    }
  }
}
