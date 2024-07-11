package lk.ijse.Controller;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFormController {
    public Label lblTime;
    public Label lblWish;

    public void initialize(){
        TimeNow();
        time();
    }

    private void TimeNow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH");
            while (!false) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                final int hour = Integer.parseInt(sdf.format(new Date()));
                String greeting;
                if (hour >= 0 && hour < 12) {
                    greeting = "Good Morning";
                } else if (hour >= 12 && hour < 18) {
                    greeting = "Good Afternoon";
                } else {
                    greeting = "Good Evening";
                }
                Platform.runLater(() -> {
                    lblWish.setText(greeting);
                });
            }
        });
        thread.start();
    }

    private void time(){
        Thread thread = new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            while (!false){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() ->{
                    lblTime.setText(timenow);
                });
            }
        });
        thread.start();
    }

}
