

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {

    try {
      Task task = new Task();
      MenuTui menu = new MenuTui();
      File tasksFile = new File("src/rsc/Tasks.txt");
      File usersFile = new File("src/rsc/Users.txt");
      System.out.println();
      System.out.println(ConsoleColors.WHITE_UNDERLINED + "Welcome to TODO list!" + ConsoleColors.RESET);
      System.out.println();
      task.showLogin(usersFile, tasksFile);
      while (true) {
        redrawAll(task);
      }
      } catch(FileNotFoundException e){
        System.err.println("File not found: " + e.getMessage());
      } catch(IOException | ParseException e){
        System.err.println("Input/output exception: " + e.getMessage());
      }
    }

    public static void redrawAll (Task task) throws IOException, ParseException {
      MenuTui menu = new MenuTui();
      int delay = 200; // number of milliseconds to sleep
      long start = System.currentTimeMillis();
      while (start >= System.currentTimeMillis() - delay) ; // do nothing
      menu.printHeader();
      menu.showAll(task.tasks);
      menu.showAllMenu();
      menu.taskMenu(task.tasks);
    }
  }
