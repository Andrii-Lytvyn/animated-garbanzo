import java.text.DateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;

public class MenuTui {
  public static DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
  public static final String LINE = "------------------------------------------------------------------------------------------------------";
  public static final String HEADER = "| ID |  Author  | Executor |         Title           |   Start  |  Finish  |Priority|Difficult|Status|";
  public static final String SHOW_ALL_MENU1 = "|SORT BY: 1-Author 2-Executor 3-Title 4-Priority 5-Difficult 6-Status 7-StartDate 8-FinishDate 9-Id |";
  public static final String SHOW_ALL_MENU_GENERAL = "|COMMANDS: 1-8 SORT            | C-CHANGE USER | Q-QUIT | R-READ | A-ADD | D-DEL | F-FINISH | G-GUNT|";
  public static final String SHOW_ALL_MENU_USER = "|COMMANDS: 1-8 SORT                    | C-CHANGE USER | Q-QUIT | R-READ | A-ADD | D-DEL | F-FINISH |";
  public static Task task = new Task();

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

  public void mainMenu(List<Task> tasks) throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      String key = br.readLine();
      switch (key) {
        case "1": {
          Comparator<Task> comparator = new TaskByAutorComparator();
          tasks.sort(comparator);
          printHeader();
          showAll(tasks);
          showAllMenu();
          break;
        }
        case "2": {
          Comparator<Task> comparator = new TaskByExecutorComparator();
          tasks.sort(comparator);
          printHeader();
          showAll(tasks);
          showAllMenu();
          break;
        }
        case "3": {
          Comparator<Task> comparator = new TaskByTitleComparator();
          tasks.sort(comparator);
          printHeader();
          showAll(tasks);
          showAllMenu();
          break;
        }
        case "4": {
          Comparator<Task> comparator = new TaskByPriorityComparator();
          tasks.sort(comparator);
          printHeader();
          showAll(tasks);
          showAllMenu();
          break;
        }
        case "5": {
          Comparator<Task> comparator = new TaskByDifficultComparator();
          tasks.sort(comparator);
          printHeader();
          showAll(tasks);
          showAllMenu();
          break;
        }
        case "6": {
          Comparator<Task> comparator = new TaskByStatusComparator();
          tasks.sort(comparator);
          printHeader();
          showAll(tasks);
          showAllMenu();
          break;
        }
        case "7": {
          Comparator<Task> comparator = new TaskByStartDateComparator();
          tasks.sort(comparator);
          printHeader();
          showAll(tasks);
          showAllMenu();
          break;
        }
        case "8": {
          Comparator<Task> comparator = new TaskByFinishDateComparator();
          tasks.sort(comparator);
          printHeader();
          showAll(tasks);
          showAllMenu();
          break;
        }
        case "9": {
          Comparator<Task> comparator1 = new TaskByIdComparator();
          tasks.sort(comparator1);
          printHeader();
          showAll(tasks);
          showAllMenu();
          break;
        }
        case "c": {
          changeUser(tasks);
          break;
        }
        case "a": {
          addTask(tasks);
          break;
        }
        case "d": {
          deleteTask(tasks);
          break;
        }
        case "f": {
          finishTask(tasks);
          break;
        }
        case "r": {
          System.out.print("Input task ID to READ:");
          int id = Integer.parseInt(br.readLine());
          if (checkIdInRange(tasks, id)) {
            readTask(tasks, id);
          }
          break;
        }
        case "g": {
          if (Task.getGeneral()) {
            Gant gant = new Gant();
            gant.printHead();
            tasks.sort(new TaskByIdComparator());
            for (Task task : tasks) {
              gant.printTask(task);
            }
          }
        }
        case "q": {
          return;
        }
      }
    }
  }

  public void printHeader() {
    System.out.println(LINE);
    System.out.println(HEADER);
    System.out.println(LINE);
  }

  public void showAllMenu() {
    System.out.println(LINE);
    System.out.println(SHOW_ALL_MENU1);
    if (Task.getGeneral()) {
      System.out.println(SHOW_ALL_MENU_GENERAL);
    } else {
      System.out.println(SHOW_ALL_MENU_USER);
    }
    System.out.println(LINE);
  }

  public static void changeUser(List<Task> tasks) throws IOException {
    Task task = new Task();
    File usersFile = new File("src/rsc/Users.txt");
    File tasksFile = new File("src/rsc/Tasks.txt");
    task.makeOutputFile(tasks);
    task.showLogin(usersFile, tasksFile);


  }

  public static void showAll(List<Task> tasks) {
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

  public static void readTask(List<Task> tasks, int id) throws IOException {
    for (Task task : tasks) {
      if (task.getID() == id) {
        System.out.println("Task ID: " + task.getID());
        System.out.print("Title: ");
        System.out.println(task.getTitle());
        System.out.printf("Author: %s%n", task.getAuthor());
        System.out.printf("1 - Executor: %s%n", task.getExecutor());
        System.out.printf("2 - Start date: %s%n", task.getStartTime());
        System.out.printf("3 - Finish date: %s%n", task.getFinishTime());
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
        System.out.printf("4 - Priority: %s%n", priority);
        System.out.printf("5 - Difficult: %s%n", difficult);
        System.out.printf("Status: %s%n", status);
        System.out.println();
        System.out.println("1-5 - EDIT Fields Q-EXIT");

        editTask(tasks, id);

      }
    }
  }

  public static void addTask(List<Task> tasks) throws IOException, ParseException {
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
      if (!Task.userNames.contains(executor)) {
        executor = Task.getUserName();
      }
    }
    System.out.print("Input Start date (dd.MM.yyyy): ");
    String startDate = br.readLine();
    Date stDate = formatter.parse(startDate);
    String finishDate;
    Date finDate;
    do {
      System.out.print("Input Finish date (dd.MM.yyyy): ");
      finishDate = br.readLine();
      finDate = formatter.parse(startDate);
    }
    while (stDate.before(finDate));

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
    System.out.print("s - SAVE q-EXIT: ");
    while (true) {
      String command = br.readLine();
      if (command.equalsIgnoreCase("q")) {
        return;
      } else if (command.equalsIgnoreCase("s")) {
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

  public static void editTask(List<Task> tasks, int id) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      String key = br.readLine();
      switch (key) {
        case "1": {
          if (Task.getGeneral()) {
            System.out.printf("Executor: %s%n", tasks.get(id).getExecutor());
            System.out.print("Input new Executor: ");
            String executor = br.readLine();
            if (!Task.userNames.contains(executor)) {
              executor = Task.getUserName();
            }
            tasks.get(id).setExecutor(executor);
          } else {
            System.out.println("You are not allowed to change Executor");
          }
          break;
        }
        case "2": {
          System.out.printf("Start date: %s%n", tasks.get(id).getStartTime());
          System.out.print("input new Start date: ");
          String newStartDate = br.readLine();
          tasks.get(id).setStartTime(newStartDate);
          break;
        }
        case "3": {
          System.out.printf("Finish date: %s%n", tasks.get(id).getFinishTime());
          System.out.print("input new Finish date: ");
          String newFinishDate = br.readLine();
          tasks.get(id).setFinishTime(newFinishDate);
          break;
        }
        case "4": {
          String priority = "Low";
          if (tasks.get(id).getPriority()) {
            priority = "High";
          }
          System.out.printf("Priority: %s%n", priority);
          System.out.print("Input new Priority (High/Low): ");
          String newPriority = br.readLine();
          boolean prior = false;
          if (newPriority.equalsIgnoreCase("High")) {
            prior = true;
          }
          tasks.get(id).setPriority(prior);
          break;
        }
        case "5": {
          String difficult = "Low";
          if (!tasks.get(id).getDifficult()) {
            difficult = "High";
          }
          System.out.printf("Difficult: %s%n", difficult);
          System.out.print("Input new Difficult (High/Low): ");
          String newDifficult = br.readLine();
          boolean diffic = false;
          if (newDifficult.equalsIgnoreCase("High")) {
            diffic = true;
          }
          tasks.get(id).setDifficult(diffic);
          break;
        }
        case "q": {
          task.makeOutputFile(tasks);
          return;
        }
      }
    }
  }

  public static void finishTask(List<Task> tasks) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Input task ID to mark FINISHED:");
    int id = Integer.parseInt(br.readLine());
    if (checkIdInRange(tasks, id)) {
      tasks.get(id).setStatus(true);
    }
  }

  public static void deleteTask(List<Task> tasks) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Input task ID to DELETE:");
    int id = Integer.parseInt(br.readLine());
    if (checkIdInRange(tasks, id)) {
      tasks.get(id).setDeleted(true);
    }
  }

  public static boolean checkIdInRange(List<Task> tasks, int id) {
    boolean result = false;
    for (Task task : tasks) {
      if (task.getID() == id) {
        result = true;
      }
    }
    return result;
  }
}

