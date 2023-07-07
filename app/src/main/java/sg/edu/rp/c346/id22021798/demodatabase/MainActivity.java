package sg.edu.rp.c346.id22021798.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView listView;

    EditText etTask, etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        listView=findViewById(R.id.lv);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        etTask = findViewById(R.id.editTextTask);
        etDate = findViewById(R.id.editTextDate);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                String newTask=etTask.getText().toString();
                String newDate=etDate.getText().toString();
                etTask.setText(null);
                etDate.setText(null);
                // Insert a task
                db.insertTask(newTask, newDate);

            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();

                ArrayList<Task> taskArray = db.getTasks();


                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
                ArrayAdapter<Task> aaTaskTips = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_list_item_1, taskArray);
                listView.setAdapter(aaTaskTips);

            }
        });

    }
}

