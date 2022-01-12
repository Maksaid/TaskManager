import java.util.ArrayList;

public class Tasks {
    private static int id_counter = 0;
    private int id;
    private String command_name;
    private String command_params;
    private int completed;
    {
        completed = -1;
        id = id_counter;
        id_counter++;
        command_params = "";
    }
    public Tasks(String[] command_line){
        if(command_line.length < 2){
            command_name = command_line[0];
            command_params = "none";
        }
        else {
            command_name = command_line[0];
            for (int i = 1; i < command_line.length; i++) {
                command_params += command_line[i];
                command_params += " ";
            }

        }

    }
    public Tasks(String command_params){
        this.command_params =command_params;
        command_name = "load";
    }

    public String getCommand_params() {
        return command_params;
    }

    public int getId() {
        return id;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getCompleted() {
        return completed;
    }
}
