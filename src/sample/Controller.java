package sample;

import javax.swing.text.DateFormatter;
import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Controller {// Accepts input and converts it to commands for the model or view. It glues the Model and the View.
    final private String file_name = "data.txt";
    public void addEvent(String when, String what, EventHolder holder){
        holder.addEvent(new EventHolder.CalendarEvent(when, what)); //takes UI input and converts to
    }
    public LocalDate updateDate(String date, EventHolder holder) throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date_obj = LocalDate.parse(date,dtf);
        holder.setDesiredDay(date_obj);
        return date_obj;
    }
    public void load_data(EventHolder holder){
        try {
            File reader = new File(file_name);
            Scanner myReader = new Scanner(reader);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                String[] data_split = data.split("-");
                this.addEvent(data_split[0], data_split[1], holder);
            }
        }  catch (IOException e){
            System.out.println("Error loading data.txt file");
        }
    }
    public void save_data(EventHolder holder){
        this.clear_file();
        try {
            FileWriter writer = new FileWriter(file_name);
            for (EventHolder.CalendarEvent evt: holder.getEventList()){
                System.out.println(evt);
                writer.write(evt.toString() + "\n");
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Error saving data.txt file");
        }
    }

    public void clear_file(){
        try {
            FileWriter fwOb = new FileWriter(file_name, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        }
        catch (IOException e){
            System.out.println("Error clearing data.txt file");
        }
    }
}
