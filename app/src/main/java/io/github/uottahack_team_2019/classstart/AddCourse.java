package io.github.uottahack_team_2019.classstart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCourse extends AppCompatActivity {
    Button b;
    EditText cName;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse);
        b = (Button) findViewById(R.id.adder);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cName = (EditText) findViewById(R.id.enterCourse);
                Log.d("test ", "test");
                String courseName= cName.getText().toString();


            }
        });
    }






    /*public boolean onKey(View v, int keyCode, KeyEvent event)
    {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
        {
            switch (keyCode)
            {
                //case KeyEvent.KEYCODE_DPAD_CENTER:
                case KeyEvent.KEYCODE_ENTER:
                    String str = writeText();
                    System.out.println("test");
                    return true;
                default:
                    break;
            }
        }
        return false;
    }*/

    /*public String writeText(){
        EditText edText1 = (EditText) activity.findViewById(R.id.enterCourse);

        edText1.setInputType(InputType.TYPE_CLASS_TEXT);

        String str = edText1.getText().toString();
        return str ;
    }*/

    public AddCourse(MainActivity activity) {

    }
}


