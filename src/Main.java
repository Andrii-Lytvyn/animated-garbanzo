

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Main {

  public static void main(String[] args) throws IOException {

    try {
      Task task = new Task();
      File tasksFile = new File("src/rsc/Tasks.txt");
      File usersFile = new File("src/rsc/Users.txt");
      System.out.println();
      System.out.println(ConsoleColors.WHITE_UNDERLINED + "Welcome to TODO list!" + ConsoleColors.RESET);
      System.out.println();
      task.showLogin(usersFile,tasksFile);
      MenuTui menu = new MenuTui();
      int delay = 200; // number of milliseconds to sleep
      long start = System.currentTimeMillis();
      while(start >= System.currentTimeMillis() - delay); // do nothing


      menu.printHeader();
      menu.showAll(task.tasks);
      menu.showAllMenu();
      menu.taskMenu(task.tasks);


    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Input/output exception: " + e.getMessage());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
}
