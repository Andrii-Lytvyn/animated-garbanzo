import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MenuTui {
  public static DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
  public static final String LINE = "=============================================================================================================================";
  public static final String HEADER = "| ID |  Author  | Executor |              Title                |   Start   |  Finish   |  Priority  |  Difficult  |  Status |";
  public static final String SHOW_ALL_MENU1 = ""
      + ConsoleColors.YELLOW + "|SORT BY:         " + ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " 1-Author " + ConsoleColors.RESET + " "
      + ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " 2-Executor " + ConsoleColors.RESET + " "
      + ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " 3-Title " + ConsoleColors.RESET + " "
      + ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " 4-Priority " + ConsoleColors.RESET + " "
      + ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " 5-Difficult " + ConsoleColors.RESET + " "
      + ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " 6-Status " + ConsoleColors.RESET + " "
      + ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " 7-StartDate " + ConsoleColors.RESET + " "
      + ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " 8-FinishDate " + ConsoleColors.RESET + " "
      + ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " 9-Id " + ConsoleColors.RESET + " ";
  public static final String SHOW_ALL_MENU_GENERAL = ConsoleColors.BLUE + "|COMMANDS:        "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " C-CHANGE USER " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " R-READ " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " A-ADD " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " D-DEL " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " F-FINISH " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " G-GUNT " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " Q-QUIT " + ConsoleColors.RESET + " ";
  public static final String SHOW_ALL_MENU_USER = ConsoleColors.BLUE + "|COMMANDS:        "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " C-CHANGE USER " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " R-READ " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " A-ADD " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " D-DEL " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " F-FINISH " + ConsoleColors.RESET + " "
      + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + " Q-QUIT " + ConsoleColors.RESET + " ";

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

  public void taskMenu(List<Task> tasks) throws IOException, ParseException, AWTException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Choose command: " + ConsoleColors.RESET);
      String key = br.readLine();
      switch (key) {
        case "1": {
          Comparator<Task> comparator = new TaskByAutorComparator();
          tasks.sort(comparator);
          refresh(tasks);
          break;
        }
        case "2": {
          Comparator<Task> comparator = new TaskByExecutorComparator();
          Collections.sort(tasks, comparator);
          refresh(tasks);
          break;
        }
        case "3": {
          Comparator<Task> comparator = new TaskByTitleComparator();
          Collections.sort(tasks, comparator);
          refresh(tasks);
          break;
        }
        case "4": {
          Comparator<Task> comparator = new TaskByPriorityComparator();
          tasks.sort(comparator);
          refresh(tasks);
          break;
        }
        case "5": {
          Comparator<Task> comparator = new TaskByDifficultComparator();
          tasks.sort(comparator);
          refresh(tasks);
          break;
        }
        case "6": {
          Comparator<Task> comparator = new TaskByStatusComparator();
          tasks.sort(comparator);
          refresh(tasks);
          break;
        }
        case "7": {
          Comparator<Task> comparator = new TaskByStartDateComparator();
          tasks.sort(comparator);
          refresh(tasks);
          break;
        }
        case "8": {
          Comparator<Task> comparator = new TaskByFinishDateComparator();
          tasks.sort(comparator);
          refresh(tasks);
          break;
        }
        case "9": {
          Comparator<Task> comparator1 = new TaskByIdComparator();
          tasks.sort(comparator1);
          refresh(tasks);
          break;
        }
        case "c": {
          changeUser(tasks);
          refresh(tasks);
          break;
        }
        case "a": {
          addTask(tasks);
          refresh(tasks);
          break;
        }
        case "d": {
          deleteTask(tasks);
          refresh(tasks);
          break;
        }
        case "f": {
          finishTask(tasks);
          refresh(tasks);
          break;
        }
        case "r": {
          System.out.println();
          System.out.print(ConsoleColors.BLUE_BOLD + "Input task ID to READ: " + ConsoleColors.RESET);
          int id = Integer.parseInt(br.readLine());
          if (checkIdInRange(tasks, id)) {
            readTask(tasks, id);
          }
          refresh(tasks);
          break;
        }
        case "g": {
//          refresh(tasks);
          if (Task.getGeneral()) {
            Gant gant = new Gant();
            gant.printHead();
//            tasks.sort(new TaskByIdComparator());
            for (Task task : tasks) {
              gant.printTask(task);
            }
            gant.printLine();
            System.out.println();
            System.out.println("Q - Quit");
            while (true) {
              String quit = br.readLine();
              if (quit != "") {
                break;
              }
            }
          }
          refresh(tasks);
        }
        case "q": {
          task.makeOutputFile(tasks);
          System.exit(0);

        }
      }
    }
  }

  public static void printHeader() {
    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + LINE + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + HEADER + ConsoleColors.RESET);
    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + LINE + ConsoleColors.RESET);
  }

  public void showAllMenu() {
    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + LINE + ConsoleColors.RESET);
    System.out.println(SHOW_ALL_MENU1);
    System.out.println();
    if (Task.getGeneral()) {
      System.out.println(SHOW_ALL_MENU_GENERAL);
    } else {
      System.out.println(SHOW_ALL_MENU_USER);
    }
    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + LINE + ConsoleColors.RESET);
  }

  public static void changeUser(List<Task> tasks) throws IOException {
    Task task = new Task();
    File usersFile = new File("src/rsc/Users.txt");
    File tasksFile = new File("src/rsc/Tasks.txt");
    task.makeOutputFile(tasks);
    task.showLogin(usersFile, tasksFile);
  }

  public static void showAll(List<Task> tasks) throws IOException, ParseException {

//    tasks.sort(new TaskByIdComparator());
    for (Task task : tasks) { //cut long Titles
      String title = task.getTitle();
      if (title.length() > 33) {
        title = title.substring(0, 29) + "...";
      }
        String priority = "Low";
        String difficult = "Low";
        String status = "Process";
        if (!Task.getGeneral()) {
          if (task.getExecutor().equals("general")) {
            continue;
          }
        }
        if (title.length() > 25) {
          title = title.substring(0, 22) + "...";
        }
        if (task.getDeleted()) {
          continue;
        }
        if (task.getPriority()) {
          priority = "High";
        }
        if (task.getDifficult()) {
          difficult = "High";
        }
        if (task.getStatus()) {
          status = "Finished";
        }
        String taskRow = String.format("|%4d|%10s|%10s|%35s|%11s|%11s|%12s|%13s|%9s|",
            task.getID(), task.getAuthor(), task.getExecutor(), title, task.getStartTime(),
            task.getFinishTime(), priority, difficult, status);
        System.out.println(taskRow);
      }
    }


  public static void readTask(List<Task> tasks, int id) throws IOException {
    for (Task task : tasks) {
      if (task.getID() == id) {
        System.out.println(ConsoleColors.BLUE + "Task ID:            "+ ConsoleColors.RESET + ConsoleColors.YELLOW+task.getID()+ ConsoleColors.RESET);
        System.out.print(ConsoleColors.BLUE + "Title:              ");
        System.out.println(ConsoleColors.RESET + ConsoleColors.YELLOW+task.getTitle());
        System.out.printf(ConsoleColors.BLUE + "Author:             %s%n", ConsoleColors.RESET + ConsoleColors.YELLOW+task.getAuthor()+ ConsoleColors.RESET);
        System.out.printf(ConsoleColors.BLUE + "1 - Executor:       %s%n", ConsoleColors.RESET + ConsoleColors.YELLOW+task.getExecutor()+ ConsoleColors.RESET);
        System.out.printf(ConsoleColors.BLUE + "2 - Start date:     %s%n", ConsoleColors.RESET + ConsoleColors.YELLOW+task.getStartTime()+ ConsoleColors.RESET);
        System.out.printf(ConsoleColors.BLUE + "3 - Finish date:    %s%n", ConsoleColors.RESET + ConsoleColors.YELLOW+task.getFinishTime()+ ConsoleColors.RESET);
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
        System.out.printf(ConsoleColors.BLUE + "4 - Priority:       %s%n", ConsoleColors.RESET + ConsoleColors.YELLOW+priority+ ConsoleColors.RESET);
        System.out.printf(ConsoleColors.BLUE + "5 - Difficult:      %s%n", ConsoleColors.RESET + ConsoleColors.YELLOW+difficult+ ConsoleColors.RESET);
        System.out.printf(ConsoleColors.BLUE + "Status:             %s%n", ConsoleColors.RESET + ConsoleColors.YELLOW+status+ ConsoleColors.RESET);
        System.out.println();
        System.out.println(ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + " 1-5 - EDIT Fields "+ ConsoleColors.RESET + " "
                + ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + " Q-EXIT " +ConsoleColors.RESET);

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
    System.out.println(ConsoleColors.BLUE +"Task ID:         " + id);
    System.out.printf(ConsoleColors.BLUE +"Author:          %s%n", Task.getUserName()); //
    System.out.print(ConsoleColors.BLUE + "Input title:     ");
    String title = br.readLine();

    if (Task.getGeneral()) {
      System.out.print(ConsoleColors.BLUE +"Input Executor:");
      executor = br.readLine();
      if (!Task.userNames.contains(executor)) {
        executor = Task.getUserName();
      }
    }
    System.out.print(ConsoleColors.BLUE +"Input Start date (dd.MM.yyyy):  ");
    String startDate = br.readLine();
    Date stDate = formatter.parse(startDate);
    String finishDate;
    Date finDate;
    do {
      System.out.print(ConsoleColors.BLUE +"Input Finish date (dd.MM.yyyy): ");
      finishDate = br.readLine();
      finDate = formatter.parse(startDate);
    }
    while (stDate.before(finDate));

    System.out.print("Input Priority (Low/High):      ");
    String prior = br.readLine();
    if (prior.equalsIgnoreCase("High")) {
      priority = true;
    }
    System.out.print("Input Difficult (Low/High):     ");
    String diff = br.readLine();
    if (diff.equalsIgnoreCase("High")) {
      difficult = true;
    }
    System.out.println();
    System.out.print(ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + " s - SAVE " + ConsoleColors.RESET + " " + ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + " q-EXIT: " + ConsoleColors.RESET);
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
    Task taskTemp;
    while (true) {
      String key = br.readLine();
      switch (key) {
        case "1": {
          for (Task task : tasks) {
            if (task.getID() == id) {
              if (Task.getGeneral()) {
                System.out.printf("Executor: %s%n", task.getExecutor());
                System.out.print("Input new Executor: ");
                String executor = br.readLine();
                if (!Task.userNames.contains(executor)) {
                  executor = Task.getUserName();
                }
                task.setExecutor(executor);
              } else {
                System.out.println("You are not allowed to change Executor");
              }
            }
          }
        }
        case "2": {
          for (Task task : tasks) {
            if (task.getID() == id) {
              System.out.printf("Start date: %s%n", task.getStartTime());
              System.out.print("input new Start date: ");
              String newStartDate = br.readLine();
              task.setStartTime(newStartDate);
            }
          }
          break;
        }
        case "3": {
          for (Task task : tasks) {
            if (task.getID() == id) {
              System.out.printf("Finish date: %s%n", task.getFinishTime());
              System.out.print("input new Finish date: ");
              String newFinishDate = br.readLine();
              task.setFinishTime(newFinishDate);
              System.out.println("id: " + id);
            }
          }
          break;
        }
        case "4": {
          for (Task task : tasks) {
            if (task.getID() == id) {
              String priority = "Low";
              if (task.getPriority()) {
                priority = "High";
              }
              System.out.printf("Priority: %s%n", priority);
              System.out.print("Input new Priority (High/Low): ");
              String newPriority = br.readLine();
              boolean prior = false;
              if (newPriority.equalsIgnoreCase("High")) {
                prior = true;
              }
              task.setPriority(prior);
            }
          }
          System.out.println("N1");
          break;
        }
        case "5": {
          for (Task task : tasks) {
            if (task.getID() == id) {
              String difficult = "Low";
              if (task.getDifficult()) {
                difficult = "High";
              }
              System.out.printf("Difficult: %s%n", difficult);
              System.out.print("Input new Difficult (High/Low): ");
              String newDifficult = br.readLine();
              boolean diffic = false;
              if (newDifficult.equalsIgnoreCase("High")) {
                diffic = true;
              }
              task.setDifficult(diffic);
            }
          }
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
    System.out.println();
    System.out.print(ConsoleColors.BLUE+ "Input task ID to mark FINISHED: " + ConsoleColors.RESET);
    int id = Integer.parseInt(br.readLine());
    if (checkIdInRange(tasks, id)) {
      for (Task task : tasks) {
        if (task.getID() == id) {
          task.setStatus(true);
        }
      }
    }
  }

  public static void deleteTask(List<Task> tasks) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println();
    System.out.print(ConsoleColors.BLUE_BOLD + "Input task ID to DELETE: " + ConsoleColors.RESET);
    int id = Integer.parseInt(br.readLine());
    if (checkIdInRange(tasks, id)) {
      for (Task task : tasks) {
        if (task.getID() == id) {
          task.setDeleted(true);
        }
      }
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

  public void refresh(List<Task> tasks) throws IOException, ParseException, AWTException {
    clearAll();
    int delay = 200; // number of milliseconds to sleep
    long start = System.currentTimeMillis();
    while (start >= System.currentTimeMillis() - delay) ; // do nothing
    printHeader();
    showAll(tasks);
    showAllMenu();
    taskMenu(tasks);

  }
}


