public class Line {
    private String commandbody;
    private String commandname;
    private int id;
    private int completed;
    private static int currentid = 0;

    public Line(String command) {
        Parser parser = new Parser(command);
        commandbody = parser.findCommandBody();
        commandname = parser.findCommandName();
        id = currentid;
        currentid++;
        completed = 0;
    }

    public Line(String commandname, String commandbody) {
        this.commandname = commandname;
        this.commandbody = commandbody;
        id = currentid;
        currentid++;
        completed = 0;
    }

    public String getCommandbody() {
        return commandbody;
    }

    public void setCommandbody(String commandbody) {
        this.commandbody = commandbody;
    }

    public String getCommandname() {
        return commandname;
    }

    public void setCommandname(String commandname) {
        this.commandname = commandname;
    }

    public int getId() {
        return id;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}
