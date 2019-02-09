package io.github.uottahack_team_2019.classstart;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ClassSort extends AppCompatActivity {

    private static MainActivity activity1;

    public ClassSort(MainActivity activity) {
        activity.setContentView(R.layout.activity_class_sort);

        activity1 = activity;
        Button addCrs = (Button) findViewById(R.id.addCrs);
        addCrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddCourse(activity1);
            }
        });
    }
}
