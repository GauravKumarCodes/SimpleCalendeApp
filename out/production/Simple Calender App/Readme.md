##Simple Calendar App
This calendar application allows users to enter the date, time and description of events,
and on another side of the screen, a day's events are displayed in order of
time. Upon launch, the app defaults to displaying today's events. 
The user is able to enter a date and view the events of that day. 

The app creates a text file that stores the input events. 
Each time you add an event, it is added to the text file line by line.  
The application defaults to displaying today's events when launching.

Upon opening the application, entries in data.txt are loaded onto the calendar. 
To delete all items in the calendar, first end the program, delete data.txt, and rerun.
If you wish to delete only some items, end the program, delete individual lines
from data.txt, then rerun. 

###Technical Section
The project is using MVC:
* Model is EventHolder.java
* View is UI.java
* Controller is Controller.java

The inner class is EventHolder.CalendarEvent 

The lambda expression and the priority queue are
in the EventHolder constructor.
The Pane used is GridPane in UI.java.

Topics not covered in class: JavaFX

To run the program: UI.main 

