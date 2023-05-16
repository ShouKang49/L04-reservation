package sg.edu.rp.c346.id22013080.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name;
        EditText number;
        DatePicker dp;
        TimePicker tp;
        Button submit;
        Button reset;
        EditText group;
        RadioGroup table;

        name = findViewById(R.id.editTextName);
        number = findViewById(R.id.editTextNumber);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        submit = findViewById(R.id.submit);
        reset = findViewById(R.id.reset);
        group = findViewById(R.id.editTextGroupSize);
        table = findViewById(R.id.tableSelection);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = tp.getCurrentHour();
                int minute = tp.getCurrentMinute();
                int month = dp.getMonth();
                month ++;
                int day = dp.getDayOfMonth();
                int year = dp.getYear();
                String cusName = "";
                String phoneNo = "";
                String displayDetails = "Reservation Details";
                int groupNo = 0;
                int tableType = table.getCheckedRadioButtonId();

                if(name.getText().toString().trim().length() != 0){

                    cusName = name.getText().toString();

                    if(number.getText().toString().trim().length() != 0){
                        phoneNo = number.getText().toString();

                        if(group.getText().toString().trim().length() != 0){
                            groupNo = Integer.parseInt(group.getText().toString());
                            if(groupNo > 0 ){
                                displayDetails += String.format("\nName: %s\nPhone Number: %s\nGroup Size: %d", cusName, phoneNo, groupNo);

                                if(tableType == R.id.smoking){
                                    displayDetails += String.format("\nTable: %s", "Smoking");
                                }
                                else{
                                    displayDetails += String.format("\nTable: %s", "Non-Smoking");
                                }

                                if(month < 10){
                                    if(day < 10){
                                        displayDetails += String.format("\nDate: 0%d/0%d/%d", day, month, year);
                                    }
                                    else{
                                        displayDetails += String.format("\nDate: 0%d/%d/%d", day, month, year);
                                    }
                                }
                                else{
                                    if(day < 10){
                                        displayDetails += String.format("\nDate: %d/0%d/%d", day, month, year);
                                    }
                                    else{
                                        displayDetails += String.format("\nDate: %d/%d/%d", day, month, year);
                                    }
                                }

                                if(hour > 13){
                                    int DisplayHour = hour - 12;
                                    if(minute <= 9){
                                        displayDetails += String.format("\nTime: %d:0%dPM", DisplayHour, minute);
                                    }
                                    else{
                                        displayDetails += String.format("\nTime: %d:%dPM", DisplayHour, minute);
                                    }
                                }
                                else{
                                    if(minute <= 9){
                                        displayDetails += String.format("\nTime: 0%d:0%dAM", hour, minute);
                                    }
                                    else{
                                        displayDetails += String.format("\nTime: 0%d:%dAM", hour, minute);
                                    }

                                }

                                Toast.makeText(MainActivity.this, displayDetails, Toast.LENGTH_SHORT).show();


                            }
                            else{
                                Toast.makeText(MainActivity.this, "Group size cannot be 0",Toast.LENGTH_SHORT).show();
                                number.setError("Group size cannot be 0");
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Please enter Group sizer",Toast.LENGTH_SHORT).show();
                            group.setError("Cannot be left blank");
                        }
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Please enter Your phone number",Toast.LENGTH_SHORT).show();
                                number.setError("Cannot be left blank");
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Please enter Your name",Toast.LENGTH_SHORT).show();
                            name.setError("Cannot be left blank");
                        }



            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                number.setText("");
                dp.updateDate(2023, 5, 1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                group.setText("");
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                 int hour = tp.getCurrentHour();
                 int minuteOfDay = tp.getCurrentMinute();

                 if(hour < 8){
                     tp.setCurrentHour(8);
                     tp.setCurrentMinute(0);
                     Toast.makeText(MainActivity.this, "Store only opens at 8 AM",Toast.LENGTH_SHORT).show();
                 }
                 else if(hour > 20){
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(59);
                    Toast.makeText(MainActivity.this, "Store closes at 9 AM",Toast.LENGTH_SHORT).show();
                 }

            }
        });


    }
}