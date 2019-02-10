package io.github.uottahack_team_2019.classstart;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ClassSort extends AppCompatActivity {

    private static MainActivity activity1;

    public void makeBtn(MainActivity activity, Button classes[], int i) {


        classes[i] = new Button(activity);


        classes[i].setX(32);
        classes[i].setY(450 + 125*i);
        classes[i].setText(activity.fileManager.courseCodes.get(i/2));


        ConstraintLayout b1 = (ConstraintLayout)activity.findViewById(R.id.classSortID);
        ConstraintLayout.LayoutParams l1 = new ConstraintLayout.LayoutParams(800, 200);
        b1.addView(classes[i], l1);



        classes[i+1] = new Button(activity);

        classes[i+1].setX(850);
        classes[i+1].setY(450 + 125*i);
        classes[i+1].setText("X");
        classes[i+1].setTextColor(Color.RED);

        ConstraintLayout b2 = (ConstraintLayout)activity.findViewById(R.id.classSortID);
        ConstraintLayout.LayoutParams l2 = new ConstraintLayout.LayoutParams(200, 200);
        b2.addView(classes[i+1], l2);

    }

    public ClassSort(MainActivity activity) {
        activity.setContentView(R.layout.activity_class_sort);

        activity1 = activity;
        Button addCrs = (Button) activity.findViewById(R.id.addCrs);

        addCrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddCourse(activity1);
            }
        });

        Button classes[] = new Button[activity.fileManager.courseCodes.size()*2];

        for(int i=0; i<activity.fileManager.courseCodes.size()*2-1; i=i+2)
        {
            makeBtn(activity, classes, i);
            final int courseIndex = i / 2;
            classes[i+1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity1.fileManager.removeCourse(activity1.fileManager.courseCodes.get(courseIndex));
                    new ClassSort(activity1);
                }
            });
        }


    }



}
