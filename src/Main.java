

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {

    try {
      Task task = new Task();
      File tasksFile = new File("src/rsc/Tasks.txt");
      File usersFile = new File("src/rsc/Users.txt");

      task.showLogin(usersFile);


      task.parseTaskFromFile(tasksFile);
      MenuTui menu = new MenuTui();
      menu.printHeader();
      menu.showAll(task.tasks);
      menu.showAllMenu();
      menu.changeUser();

      menu.addTask(task.tasks);
      task.makeOutputFile(task.tasks);
      menu.showAll(task.tasks);
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Input/output exception: " + e.getMessage());
    }
  }
}
