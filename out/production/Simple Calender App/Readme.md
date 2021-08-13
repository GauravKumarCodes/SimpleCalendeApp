##Simple Calendar App
This app allows users to enter the date and description of events,
and on another side of the screen, a day's events are displayed in order of
time. The user is also able to view the events for any other day they wish. 

The app creates a text file that stores the input events. 
Each time you add an event, it is added to the text file line by line.  
The application defaults to displaying today's events when launching.

To delete all items in the calendar, first end the program, delete data.txt, and rerun.
If you wish to delete only some items, end the program, delete individual lines
from data.txt, then rerun. 
###Technical Section
The project is using MVC:
* model is EventHolder.java
* view is UI.java
* controller is Controller.java

The inner class is EventHolder.CalendarEvent 

The lambda expression is in the EventHolder constructor

Priority list is used in Pane used is GridPane in UI.java, 
Topics not covered in class: JavaFX

To run the program, run UI.main.

