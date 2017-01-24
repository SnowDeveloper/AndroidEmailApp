package com.example.snowwhite.mad_2016_lect2_homework;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GreetActivity extends AppCompatActivity {

    //private android.net.Uri Uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greet);

        Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.textView2);
        String name = intent.getStringExtra("Name");
        textView.setText("Hello " + name + ". Kindly compose the Email!");

    }

    public void sendEmail(View view) {

        EditText editTextSub = (EditText) findViewById(R.id.editTextSub);
        EditText editTextRID = (EditText) findViewById(R.id.editTextRID);
        EditText editTextBdy = (EditText) findViewById(R.id.editTextBdy);
        Log.v("Subject: ", editTextSub.getText().toString());
        Log.v("RID: ", editTextRID.getText().toString());
        Log.v("Body: ", editTextBdy.getText().toString());
        String eSub = editTextSub.getText().toString();
        String[] eRID = {editTextRID.getText().toString()}; //String, as email recipients can be many
        String eBody = editTextBdy.getText().toString();
        String result = "RESULT_OK"; //Setting activity result to be "RESULT_OK"

        //If the subject or recipient or body are empty then user is prompted to fill all textBox
        AlertDialog.Builder builder = new AlertDialog.Builder(GreetActivity.this);
        if (editTextSub.getText().toString().equals("") ||
                editTextRID.getText().toString().equals("") ||
                editTextBdy.getText().toString().equals("")) {
            Log.v("sendEmailButton_onClick", "Invalid input provided");
            builder.setMessage("You have not entered all data. Please retry!").show();
            return;
        }
        //If the subject or recipient or body are not empty
        else {
            Log.w("sendEmailButton_onClick", "All data entered");
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.setData(Uri.parse("mailto:"));
            intent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, eSub);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, eRID);
            emailIntent.putExtra(Intent.EXTRA_TEXT, eBody);
            Log.w("sendEmailButton_onClick", "All data success");
            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",result);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Log.w("sendEmailButton_onClick", "Not Sent");
                Toast.makeText(getApplicationContext(),
                        "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
