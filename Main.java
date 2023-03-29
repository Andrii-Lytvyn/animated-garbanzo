import src.Task;

import java.io.File;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    File inputFile = new File("src/rsc/Tasks.txt");
    Task task = new Task();
    task.parseTaskFromFile(inputFile);

  }
}
