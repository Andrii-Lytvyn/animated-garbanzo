

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {

    try {
      Task task = new Task();
      File tasksFile = new File("src/rsc/Tasks.txt");
      File usersFile = new File("src/rsc/Users.txt");

      task.ShowLogin(usersFile);


      task.parseTaskFromFile(tasksFile);
      MenuTui menu = new MenuTui();
      menu.printHeader();
      menu.showAll(task.tasks);
      menu.showAllMenu();
      task.makeOutputFile(task.tasks);

    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Input/output exception: " + e.getMessage());
    }
  }
}
