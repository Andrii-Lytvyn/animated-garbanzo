package src;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//TODO Andrey Litvin
//fields ...
// line 43 - 60
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

  public String getAuthor() {
    return author;
  }

  public int getID() {
    return ID;
  }

  public String getTitle() {
    return title;
  }

  public boolean getPriority() {return priority;}

  public boolean getDifficult() {return difficult;}

  public String getExecutor() {
    return executor;
  }

  public String getStartTime() {
    return startTime;
  }

  public String getFinishTime() {
    return finishTime;
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

  public void ShowLogin(File usersFile) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader inputFileReader = new BufferedReader(new FileReader(usersFile));
    Map<String, String> users = new HashMap<>();

    for (String row = inputFileReader.readLine(); row != null; row = inputFileReader.readLine()) {
      String[] temp = row.split(SEP);
      users.put(temp[0], temp[1]);
    }
    System.out.print("Введите логин: ");
    String login = br.readLine();
    System.out.print("Введите пароль: ");
    String password = br.readLine();
    if (users.get(login).equals(password)) {
      System.out.println("Проверка пройдена");
    } else {
      System.err.println("Не верный логин или пароль");
    }
    //TODO добавить что не верно. Добавить тест
    //TODO Исключение одинаковых пользователей
  }

  public void parseTaskFromFile(File tasksFile) throws IOException {

    BufferedReader inputFileReader = new BufferedReader(new FileReader(tasksFile));
    for (String row = inputFileReader.readLine(); row != null; row = inputFileReader.readLine()) {
      String[] temp = row.split(SEP);
      setID(Integer.parseInt(temp[0]));
      setAuthor(temp[1]);
      setExecutor(temp[2]);
      setTitle(temp[3]);
      setStartTime(temp[4]);
      setFinishTime(temp[5]);
      setPriority(Boolean.parseBoolean(temp[6]));
      setDifficult(Boolean.parseBoolean(temp[7]));
      setStatus(Boolean.parseBoolean(temp[8]));
      setDeleted(Boolean.parseBoolean(temp[9]));
    }
  }
}