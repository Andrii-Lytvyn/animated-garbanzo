

//line 64 - 76


import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.text.Format;
import java.util.List;

public class MenuTui {
  public static final String LINE = "------------------------------------------------------------------------------------------------------";
  public static final String HEADER = "| ID |  Author  | Executor |         Title           |   Start  |  Finish  |Priority|Difficult|Status|";
  public static final String SHOW_ALL_MENU1 = "|SORT BY: 1-Author 2-Executor 3-Title 4-Priority 5-Difficult 6-Status 7- Start date 8 - Finish date |";
  public static final String SHOW_ALL_MENU2 = "|COMMANDS: 1-8 SORT                                Q-QUIT  R-READ   A-ADD   D-DEL  F-FINISH   G-GUNT|";

  public void clearAll() throws AWTException {
    Robot r = new Robot();
    r.keyPress(KeyEvent.VK_CONTROL);
    r.keyPress(KeyEvent.VK_Q);
    r.keyRelease(KeyEvent.VK_CONTROL);
    r.keyRelease(KeyEvent.VK_Q);
  }

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
    System.out.println(SHOW_ALL_MENU1);
    System.out.println(SHOW_ALL_MENU2);
    System.out.println(LINE);
  }

  public void showAll(List<Task> tasks) {
    tasks.sort(new TaskByIdComparator());

    for (Task task : tasks) { //cut long Titles
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
        System.out.println("1 - Task ID: " + task.getID());
        System.out.println("Title: ");
        System.out.println(task.getTitle());
        System.out.printf("2 - Author: %s%n ", task.getAuthor());
        System.out.printf("3 - Executor: %s%n", task.getExecutor());
        System.out.printf("4 - Start date: %s%n", task.getStartTime());
        System.out.printf("5 - Finish date: %s%n", task.getFinishTime());
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
        System.out.printf("6 - Priority: %s%n", priority);
        System.out.printf("7 - Difficult: %s%n", difficult);
        System.out.printf("8 - Status: %s%n", status);
        System.out.println();
        System.out.println("1-8 - EDIT Fields Q-EXIT");
      }
    }
  }

  public void addTask(List<Task> tasks) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Task task = new Task();
    boolean priority = false;
    boolean difficult = false;
    boolean status = false;
    boolean deleted = false;
    String executor = Task.getUserName();

    System.out.println();
    int id = task.getNewTaskId(tasks);
    System.out.println("Task ID: " + id);
    System.out.printf("Author: %s%n", Task.getUserName()); //
    System.out.print("Input title: ");
    String title = br.readLine();

    if (Task.getGeneral()) {
      System.out.print("Input Executor: ");
      executor = br.readLine();
    }
    System.out.print("Input Start date (dd.MM.yyyy): ");
    String startDate = br.readLine();
    System.out.print("Input Finish date (dd.MM.yyyy): ");
    String finishDate = br.readLine();
    System.out.print("Input Priority (Low/High): ");
    String prior = br.readLine();
    if (prior.equalsIgnoreCase("High")) {
      priority = true;
    }
    System.out.print("Input Difficult (Low/High): ");
    String diff = br.readLine();
    if (diff.equalsIgnoreCase("High")) {
      difficult = true;
    }
    boolean cycle = true;
    System.out.print("S - SAVE Q-EXIT: ");
    while (cycle) {
      String command = br.readLine();
      if(command.equals("Q")) {
        return;
      } else if (command.equals("S")) {
        task.setID(id);
        task.setAuthor(Task.getUserName());

        task.setExecutor(executor);

        task.setTitle(title);
        task.setStartTime(startDate);
        task.setFinishTime(finishDate);
        task.setPriority(priority);
        task.setDifficult(difficult);
        task.setStatus(status);
        task.setDeleted(deleted);
        tasks.add(task);
        return;
      }
    }


  }

}
