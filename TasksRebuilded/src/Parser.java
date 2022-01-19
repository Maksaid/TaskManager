public class Parser {
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    public String findCommandName() {
        String[] commandname = command.split(" ", 2);
        return commandname[0];
    }

    public String findCommandBody() {
        String[] commandname = command.split(" ", 2);
        return commandname.length > 1 ? commandname[1] : "-";
    }
}
