

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
      task.showLogin(usersFile, tasksFile);
      MenuTui menu = new MenuTui();
      mainMenu(menu, task);


    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Input/output exception: " + e.getMessage());
    } catch (ParseException | AWTException e) {
      throw new RuntimeException(e);
    }
  }

  public static void mainMenu(MenuTui menu, Task task) throws IOException, ParseException, AWTException {
    menu.clearAll();
    menu.printHeader();
    menu.showAll(task.tasks);
    menu.showAllMenu();
    menu.taskMenu(task.tasks);
  }
}
