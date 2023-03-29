import src.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    try {
      File inputFile = new File("src/rsc/Tasks.txt");
      Task task = new Task();
      task.parseTaskFromFile(inputFile);
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    }catch (IOException e){
      System.err.println("Input/output exception: " + e.getMessage());
    }

  }
}
