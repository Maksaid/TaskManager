import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Line> taskList= new ArrayList<Line>();
    private StringBuilder stringFormatOfList = new StringBuilder();
    public StringBuilder all(boolean FlagOnlyCompleted){
        stringFormatOfList.delete(0,stringFormatOfList.capacity());
        if(!FlagOnlyCompleted)
        for (int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).getCompleted()==0){
            stringFormatOfList.append("№" + taskList.get(i).getId() + " " + taskList.get(i).getCommandbody());
            stringFormatOfList.append("\n");}
        }
        for (int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).getCompleted()==1){
                stringFormatOfList.append("№" + taskList.get(i).getId() + " " + taskList.get(i).getCommandbody());
                stringFormatOfList.append("\n");}
        }
        return stringFormatOfList;
    }

    public StringBuilder getStringFormatOfList() {
        return stringFormatOfList;
    }
    public void add(Line line){
        taskList.add(line);
    }

    public void delete(int i) {
        for (int j = 0; j < taskList.size(); j++) {
            if(taskList.get(j).getId() == i)
                taskList.remove(j);
        }
    }

    public void load(Line taskLine) {

        Scanner loader = null;
        try {
            loader = new Scanner(new FileReader(taskLine.getCommandbody()));
            while(loader.hasNextLine()){
                String line = loader.nextLine();
                taskList.add(new Line("-",line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    public void complete(int i){
        for (int j = 0; j < taskList.size(); j++) {
            if(taskList.get(j).getId() == i)
                taskList.get(j).setCompleted(1);
        }
    }

    public void save(String FileName) {
        try {
            PrintStream out = new PrintStream(new File(FileName));
            out.print(all(false));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
