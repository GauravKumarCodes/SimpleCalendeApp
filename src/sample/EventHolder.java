package sample;

//A simple calendar app. Where you can enter the date and description of events,
//and on another side of the screen, the events for today are displayed in order of
//time. Uses priority list, date time, panes.
//Your project must also use at least one inner class and one lambda expression.

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Function;

public class EventHolder {

    public static class CalendarEvent { //inner class
        LocalDateTime dateTime;
        String info;
        public CalendarEvent(String dateTime, String info){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            this.dateTime = LocalDateTime.parse(dateTime, formatter);
            this.info = info;
        }
        @Override
        public String toString(){
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            return this.dateTime.format(fmt) + "-" + this.info;
        }
    }

    private PriorityQueue<CalendarEvent> eventList; // events not stored in any particular order
    private LocalDate selectedDate;
    public EventHolder() {
        this.eventList = new PriorityQueue<>((CalendarEvent e1, CalendarEvent e2) -> e1.dateTime.compareTo(e2.dateTime));
        selectedDate = java.time.LocalDate.now(); //default to today
    }

    public PriorityQueue<CalendarEvent> getEventList(){
        return this.eventList;
    }

    public void addEvent(CalendarEvent calendarEvent){
        this.eventList.add(calendarEvent);
    }

    public ArrayList<String> getDesiredDayEvents(){ //takes out events of desired date (set by set method) for text view
        PriorityQueue<CalendarEvent> tempQueue = new PriorityQueue<CalendarEvent>(this.eventList); //copy list
        ArrayList<String> ret = new ArrayList<String>();
        while(tempQueue.size() > 0){
            CalendarEvent evt = tempQueue.poll(); // pop off one event at a time so the comparator orders the objects
            if (evt.dateTime.toLocalDate().isEqual(this.selectedDate)) { //date extraction
                ret.add(evt.toString());
            }
        }
        return ret;
    }
    public void setDesiredDay(LocalDate date){ //update the display date
        this.selectedDate = date;
    }
}
