package com.example.furkan_asus.reminderdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView mListView;
    private TextView mEditText;
    private List<Reminder> mReminders;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = findViewById(R.id.listView);
        mEditText = findViewById(R.id.editText);
        mReminders = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mReminders);
        mListView.setAdapter(mAdapter);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = mEditText.getText().toString();
                Reminder newReminder = new Reminder(text);

                //Check if some text has been added
                if (!(TextUtils.isEmpty(text))) {
                    //Add the text to the list (datamodel)
                    mReminders.add(newReminder);

                    //Tell the adapter that the data set has been modified: the screen will be refreshed.
                    mAdapter.notifyDataSetChanged();

                    //Initialize the EditText for the next item
                    mEditText.setText("");
                } else{
                    //Show a message to the user if the textfield is empty
                    Snackbar.make(view, "Please enter some text in the textfield",
                            Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mReminders.remove(position);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });

        updateUI();

    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mReminders);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
