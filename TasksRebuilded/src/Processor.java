

public class Processor {
    TaskList taskList = new TaskList();
    public void proceed(String currentCommand)  {
        Line line = new Line(currentCommand);
        switch (line.getCommandname()) {
            case "add":
                taskList.add(line);
                break;
            case "all":
                System.out.print(taskList.all(false));
                break;
            case "delete":
                taskList.delete(Integer.parseInt(line.getCommandbody()));
                break;
            case "completed":
                if(line.getCommandbody() != "-")
                taskList.complete(Integer.parseInt(line.getCommandbody()));
                else
                    taskList.all(true);
                break;
            case "save":
                taskList.save(line.getCommandbody());
                break;
            case "load":
                taskList.load(line);
                break;
            case "stop":
                System.exit(0);
                break;
            default:
                break;

        }
    }
}
