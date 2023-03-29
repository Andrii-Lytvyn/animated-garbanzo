import src.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {

    try {
      Task task = new Task();
      File tasksFile = new File("src/rsc/Tasks.txt");
      File usersFile = new File("src/rsc/Users.txt");

      task.ShowLogin(usersFile);


      task.parseTaskFromFile(tasksFile);
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Input/output exception: " + e.getMessage());
    }

  }
}
