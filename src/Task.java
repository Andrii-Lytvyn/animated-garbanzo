package src;
//TODO Andrey Litvin
//fields ...
// line 43 - 60
public class Task {

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

  private Task(int ID,String author,String executor,String title,String startTime,
  String finishTime,boolean priority,boolean difficult,boolean status,
  boolean deleted)
  {
    this.ID=ID;
    this.author=author;
    this.executor = executor;
    this.title=title;
    this.startTime=startTime;
    this.finishTime=finishTime;
    this.priority=priority;
    this.difficult=difficult;
    this.status=status;
    this.deleted=deleted;
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

  public String getExecutor() {
    return executor;
  }

  public String getStartTime() {
    return startTime;
  }

  public String getFinishTime() {
    return finishTime;
  }
}
