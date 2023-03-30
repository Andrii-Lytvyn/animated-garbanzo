//package src;

import java.io.*;
import java.util.*;

public class Task {
  final public static String SEP = ",";
  private int ID;
  private String author;
  private String executor;
  private String title;
  private String startTime;
  private String finishTime;
  private boolean priority;
  private boolean difficult;
  private boolean status;
  private boolean deleted;
  public List<Task> tasks = new ArrayList<>();
  public List<String> userNames = new ArrayList<>();
  private static boolean general = false;
  private static String userName;

  public Task(int ID, String author, String executor, String title, String startTime,
              String finishTime, boolean priority, boolean difficult, boolean status,
              boolean deleted) {
    this.ID = ID;
    this.author = author;
    this.executor = executor;
    this.title = title;
    this.startTime = startTime;
    this.finishTime = finishTime;
    this.priority = priority;
    this.difficult = difficult;
    this.status = status;
    this.deleted = deleted;
  }

  public Task() {
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setExecutor(String executor) {
    this.executor = executor;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public void setFinishTime(String finishTime) {
    this.finishTime = finishTime;
  }

  public void setPriority(boolean priority) {
    this.priority = priority;
  }

  public void setDifficult(boolean difficult) {
    this.difficult = difficult;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public static String getUserName() {
    return userName;
  }

  public static void setUserName(String userName) {
    Task.userName = userName;
  }

  public static void setGeneral(boolean general) {
    Task.general = general;
  }

  public static boolean getGeneral() {
    return general;
  }

  public String getAuthor() {
    return author;
  }

  public int getID() {
    return ID;
  }

  public String getTitle() {
    return title;
  }

  public boolean getPriority() {
    return priority;
  }

  public boolean getDifficult() {
    return difficult;
  }

  public String getExecutor() {
    return executor;
  }

  public String getStartTime() {
    return startTime;
  }

  public String getFinishTime() {
    return finishTime;
  }

  public boolean getStatus() {
    return status;
  }

  public boolean getDeleted() {
    return deleted;
  }

  @Override
  public String toString() {
    return "Task " +
        "ID=" + ID +
        ", author='" + author + '\'' +
        ", executor='" + executor + '\'' +
        ", title='" + title + '\'' +
        ", startTime='" + startTime + '\'' +
        ", finishTime='" + finishTime + '\'' +
        ", priority=" + priority +
        ", difficult=" + difficult +
        ", status=" + status +
        ", deleted=" + deleted +
        ' ';
  }

  public void showLogin(File usersFile, File tasksFile) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader inputFileReader = new BufferedReader(new FileReader(usersFile));
    Map<String, String> users = new HashMap<>();
    for (String row = inputFileReader.readLine(); row != null; row = inputFileReader.readLine()) {
      String[] temp = row.split(SEP);
      users.put(temp[0], temp[1]);
      userNames.add(temp[0]);
    }
    System.out.print("Login: ");
    String login = br.readLine();
    System.out.print("Password: ");
    String password = br.readLine();
    try {
      if (users.get(login).equals(password)) {
        System.out.println("Passed");
        setUserName(login);
        parseTaskFromFile(tasksFile);
        if (login.equals("general")) {
          setGeneral(true);
        }
      } else {
        System.err.print("Incorrect password.");
        System.exit(1);
      }
    } catch (NullPointerException e) {
      System.err.print("User is not detected.");
      System.exit(1);
    }
    //TODO перед изменением пользователя записать файл
  }

  public void parseTaskFromFile(File tasksFile) throws IOException {
    BufferedReader inputFileReader = new BufferedReader(new FileReader(tasksFile));
    for (String row = inputFileReader.readLine(); row != null; row = inputFileReader.readLine()) {
      String[] temp = row.split(SEP);
      Task task = new Task();
      task.setID(Integer.parseInt(temp[0]));
      task.setAuthor(temp[1]);
      task.setExecutor(temp[2]);
      task.setTitle(temp[3]);
      task.setStartTime(temp[4]);
      task.setFinishTime(temp[5]);
      task.setPriority(Boolean.parseBoolean(temp[6]));
      task.setDifficult(Boolean.parseBoolean(temp[7]));
      task.setStatus(Boolean.parseBoolean(temp[8]));
      task.setDeleted(Boolean.parseBoolean(temp[9]));
      tasks.add(task);
    }
    inputFileReader.close();
  }

  public int getNewTaskId(List<Task> tasks) {
    int max = 1;
    for (Task t : tasks) {
      if (t.ID >= max) {
        max = t.ID + 1;
      }
    }
    return max;
  }

  public String listToFile() {
    return ID + "," + author + "," + executor + "," + title + "," + startTime + "," + finishTime + "," +
        priority + "," + difficult + "," + status + "," + deleted + "\n";
  }


  public void makeOutputFile(List<Task> tasks) throws IOException {
    try {
      FileWriter fileWriter = new FileWriter("src/rsc/Tasks.txt");
      for (Task t : tasks) {
        if (!t.deleted) {
          fileWriter.write(t.listToFile());
        }
      }
      fileWriter.close();
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Input/output exception: " + e.getMessage());
    }
  }
}