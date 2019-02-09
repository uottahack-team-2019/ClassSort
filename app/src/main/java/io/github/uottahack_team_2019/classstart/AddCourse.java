package io.github.uottahack_team_2019.classstart;

import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
public class AddCourse {
    MainActivity activity;

    public AddCourse(MainActivity activity) {
        activity.setContentView(R.layout.activity_addcourse);
        this.activity = activity;


            }


        public boolean onKey(View v, int keyCode, KeyEvent event)
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
        }

        public String writeText(){
            EditText edText1 = (EditText) activity.findViewById(R.id.enterCourse);

            edText1.setInputType(InputType.TYPE_CLASS_TEXT);

            String str = edText1.getText().toString();
            return str;
        }
    }




