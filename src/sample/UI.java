package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import java.time.LocalDate;
import java.util.ArrayList;

public class UI extends Application {


    EventHolder calendarEvents;
    Controller controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        calendarEvents = new EventHolder();
        controller = new Controller();
        controller.load_data(calendarEvents);

        primaryStage.setTitle("Calendar App");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Calendar App");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        Label wl = new Label("Date&time (ex mm/dd/yyyy 16:23):");
        grid.add(wl, 0, 1);
        TextField eventDate = new TextField();
        grid.add(eventDate, 1, 1);
        Label hl = new Label("Description of event:");
        grid.add(hl, 0, 2);
        TextField eventInfo = new TextField();
        grid.add(eventInfo, 1, 2);
        Button add_btn = new Button("Add");
        HBox hbBtn = new HBox(10);

        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(add_btn);
        grid.add(hbBtn, 1, 3);

        final Text list_title = new Text(); //Print location
        grid.add(list_title, 3, 1);
        list_title.setText("Today's Events");

        Label display_date_L = new Label("Display date (mm/dd/yyyy):");
        grid.add(display_date_L, 0, 4);
        TextField display_date_field = new TextField();
        grid.add(display_date_field, 1, 4);


        Button updateButton = new Button("Update display date");
        HBox hBtUpdate = new HBox(10);
        hBtUpdate.setAlignment(Pos.TOP_RIGHT);
        hBtUpdate.getChildren().add(updateButton);
        grid.add(hBtUpdate, 1, 5);

        ListView<String> listViewReference = new ListView<String>();
        listViewReference.setOrientation(Orientation.VERTICAL);
        grid.add(listViewReference, 3, 2, 2, 4);
        // populate listview if we loaded events from file
        ArrayList<String> selected_day_events = calendarEvents.getDesiredDayEvents();
        listViewReference.getItems().clear();
        for(String evt: selected_day_events){
            listViewReference.getItems().add(evt);
        }

        // Add an event to the button.
        add_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) { //populating the list view
                controller.addEvent(eventDate.getText(), eventInfo.getText(), calendarEvents);
                ArrayList<String> todaysEvents = calendarEvents.getDesiredDayEvents();
                listViewReference.getItems().clear();
                for(String evt: todaysEvents){
                    listViewReference.getItems().add(evt);
                }
                controller.save_data(calendarEvents);
            }
        });
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) { //update list view date
                LocalDate update_date;
                try {
                    update_date = controller.updateDate(display_date_field.getText(), calendarEvents);
                    ArrayList<String> selected_day_events = calendarEvents.getDesiredDayEvents();
                    listViewReference.getItems().clear();
                    for(String evt: selected_day_events){
                        listViewReference.getItems().add(evt);
                    }
                    list_title.setText("Events on :" + update_date.toString());
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
        });
        primaryStage.setScene(new Scene(grid));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}

