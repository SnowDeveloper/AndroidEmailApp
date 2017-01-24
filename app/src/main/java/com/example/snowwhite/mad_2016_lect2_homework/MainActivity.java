package com.example.snowwhite.mad_2016_lect2_homework;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sayHello(View view) {
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.editText);
        String name = editText.getText().toString();
        textView.setText("Hello " + name + "!");

        Intent intent = new Intent(this, GreetActivity.class);
        intent.putExtra("Name", name);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            //If the email has been sent successfully
            if (resultCode == Activity.RESULT_OK) {
                //Resets the MainActivity page
                TextView textView = (TextView) findViewById(R.id.textView);
                EditText editText = (EditText) findViewById(R.id.editText);
                textView.setText("Hello!");
                editText.setText("");
                //Gives user a message of successful message sent
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Email Sent Successfully!").show();
                return;
            }
            //If the email was not sent
            if (resultCode == Activity.RESULT_CANCELED) {
                //Gives user a message of unsuccessful message sent
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Email not sent! Kindly retry after login to your gmail " +
                        "from your Mobile!").show();
                return;
            }
        }
    }

}
