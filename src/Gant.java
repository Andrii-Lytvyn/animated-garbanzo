

public class Gant {
  private int dataStart; //число месяца
  private int dataStop;

  public int getDataStart() {
    return dataStart;
  }

  public void setDataStart(int dataStart) {
    this.dataStart = dataStart;
  }

  public int getDataStop() {
    return dataStop;
  }

  public void setDataStop(int dataStop) {
    this.dataStop = dataStop;
  }

  public Gant(int start, int finish) {
    this.dataStart = start;
    this.dataStop = finish;
  }

  public void printHead() {
    for (int i = 0; i < 117; ++i) {
      System.out.print("-");
    }
    System.out.println();
    System.out.print("|        Task title       |");
    for (int i = 1; i <= 31; i++) {
      if (i < 10) {
        System.out.print("0" + i + "|");
      } else {
        System.out.print("" + i + "|");
      }
    }
    System.out.println();
    for (int i = 0; i < 117; ++i) {
      System.out.print("-");
    }
    System.out.println();
  }


  public void printTask(Task task) {
    String complSign = "---";
    String lowPriorityColor = "\u001B[46m"; // cyan background default
    String highPriorityColor = "\u001B[43m"; // yellow background default
    String extraPriorityColor = "\u001B[45m"; // purple background default
    String color = "\u001B[46m";
    String colorReset = "\u001B[0m";

    String[] line = new String[32]; // 32 cells in one row
    for (int i = 1; i < line.length; ++i) {
      line[i] = "   ";
    }

    if (task.getPriority() == false) {
      color = lowPriorityColor;
    } else {
      color = highPriorityColor;
    }

    String title = task.getTitle();
    if (title.length() > 25) {
      title = title.substring(0, 22) + "...";
    }
    line[0] = String.format("|%25s|", title);

    complSign = color +complSign + colorReset;
    if (task.getDifficult() == false) {
      complSign = color + "---" + colorReset;
    } else {
      complSign = color + "===" + colorReset;
    }

    for (int k = 1; k <= 31; ++k) {
      line[k] = complSign;
    }

    for (int j = 0; j < line.length; ++j) {
      System.out.print(line[j]);
    }
    System.out.println("\u001B[0m");
  }

  public void gantMenu(){
    System.out.println("S - SHOW ALL TASKS,    Q - QUIT");
  }
}

