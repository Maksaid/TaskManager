import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class WorkingMachine {
    public static int command_id = 0;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        //Stack<String> command_stack = new Stack<>();
        ArrayList<Tasks> taskslist = new ArrayList<>();
        ArrayList<Tasks> completed_taskslist = new ArrayList<>();
        while(true){
            String[] current_command = in.nextLine().split(" ");
            switch (current_command[0]){
                case "add":
                    taskslist.add(new Tasks(current_command));
                    break;
                case "all":
                    console_print(taskslist,completed_taskslist,"all");
                    break;
                case "delete":
                    int a = findIndexTask(taskslist,Integer.parseInt(current_command[1]));
                    int c = findIndexTask(completed_taskslist,Integer.parseInt(current_command[1]));
                    if(a>=0)
                   taskslist.remove(a);
                    else
                       if(c>=0)
                           completed_taskslist.remove(c);
                    break;
                case "completed":
                    if (current_command.length >1){
                    int b = findIndexTask(taskslist,Integer.parseInt(current_command[1]));
                     if(b>=0){
                        completed_taskslist.add(taskslist.get(b));
                        taskslist.remove(b);
                    }}
                    else{
                        console_print(taskslist,completed_taskslist,"completed_only");
                    }
                    break;
                case "save":
                    String filename = current_command[1];
                    PrintStream out = new PrintStream(filename);
                    file_print(out,taskslist,completed_taskslist);
                    break;
                case "load":
                    String loadfilename = current_command[1];
                    Scanner loader = new Scanner(new FileReader(loadfilename));
                    String line;
                    while(loader.hasNextLine()){
                        line = loader.nextLine();
                    taskslist.add(new Tasks(line));
                    }

                    break;
                case "stop":
                    System.exit(0);
             default:break;
            }
        }}

    private static int findIndexTask(ArrayList<Tasks> s, int id) {
        for (Tasks task: s){
            if(task.getId() == id)
                return s.indexOf(task);
        }
        return -1;
    }
    private static void console_print(ArrayList<Tasks> taskslist,ArrayList<Tasks> completed_taskslist, String mode){
        Collections.sort(taskslist,Comparator.comparing(Tasks::getId));
        Collections.sort(completed_taskslist,Comparator.comparing(Tasks::getId));
        System.out.println("___________________________________________________________");
        if(!mode.equals("completed_only"))
        for (Tasks task: taskslist) {
            System.out.println("#" + task.getId() + " |" + task.getCommand_params() + " |");
            System.out.println("_____________________________________________________________");
        }
        for (Tasks task: completed_taskslist) {
            System.out.println("#" + task.getId() + " |" + task.getCommand_params() + " ✓");
            System.out.println("______________________________________________________________");
        }
    }
    private static void file_print(PrintStream out,ArrayList<Tasks> taskslist,ArrayList<Tasks> completed_taskslist){
        for (Tasks task: taskslist) {
            if(task.getCompleted() == -1)
                out.println("#" + task.getId() + " " + task.getCommand_params());
        }
        for (Tasks task: completed_taskslist) {
            if(task.getCompleted() == 1)
                out.println("#" + task.getId() + " " + task.getCommand_params() + " ✓");
        }
    }
}

