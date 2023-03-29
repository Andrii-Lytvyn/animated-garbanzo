package src;

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


  public void printTask(String taskText, int start, int finish, int priority, int complexity) {
    String complSign = "---";
    String lowPriorityColor = "\u001B[46m"; // cyan background default
    String highPriorityColor = "\u001B[43m"; // yellow background default
    String extraPriorityColor = "\u001B[45m"; // purple background default
    String color = "\u001B[46m";
    String colorReset = "\u001B[0m";

    if (priority == 0) {
      color = lowPriorityColor;
    } else if (priority == 1) {
      color = highPriorityColor;
    } else if (priority == 2) {
      color = extraPriorityColor;
    }

    String[] line = new String[32];
    for (int i = 1; i < line.length; ++i) {
      line[i] = "   ";
    }
    line[0] = String.format("|%25s|", taskText);

    complSign = color +complSign + colorReset;
    if (complexity == 0) {
      complSign = color + "---" + colorReset;
    } else if (complexity == 1) {
      complSign = color + "===" + colorReset;
    }
    if (complexity == 2) {
      complSign = color + "###" + colorReset;
    }

    for (int k = start; k <= finish; ++k) {
      line[k] = complSign;
    }

    for (int j = 0; j < line.length; ++j) {
      System.out.print(line[j]);
    }
    System.out.println("\u001B[0m");
  }
}
