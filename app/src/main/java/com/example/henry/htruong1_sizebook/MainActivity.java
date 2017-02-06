package com.example.henry.htruong1_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;
//*Main Activity that starts when app is first loaded */
public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "save.sav";
    private ArrayAdapter ArrayAdapter;
    private ListView records;
    private ArrayList<getData> peopleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        records = (ListView) findViewById(R.id.viewList);

    }
    //*When the add button is clicked, starts the add record activity to add information*/
    public void addRecord(View view) {
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivity(intent);

    }
    //*This class updates the list of items on the mainActivity scren */
    public void updateScreen(ArrayList<getData> peopleList){
        records = (ListView) findViewById(R.id.viewList);
        ArrayAdapter<getData> ArrayAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, peopleList);
        records.setAdapter(ArrayAdapt);
        ArrayAdapt.notifyDataSetChanged();

    }
    //*On the start of the app, this section loads any files from the internal storage *?
    @Override

    protected void onStart() {
        //Log.d("onSTart code", "This section runs");
        super.onStart();
        loadFromFile();
       // adapter = new ArrayAdapter<getData>(this, R.layout.list_person, peopleList);
//        records.setAdapter(adapter);
    }
    //*Function that loads any file internally for viewing*?
    private void loadFromFile() {
        try {
            Log.d("First load", "This section runs:");
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType =  new TypeToken<ArrayList<getData>>() {
            }.getType();
            peopleList = gson.fromJson(in,listType);
            updateScreen(peopleList);
            fis.close();

        } catch (FileNotFoundException e) {
            peopleList = new ArrayList<>();
            saveInFile();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    //*Function when called saves any addition to the app*?
    public void saveInFile(){
        try{ //saving
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();

            gson.toJson(peopleList,out);
            out.flush();
            //Log.d("my tag", "name: " + person.getName());
            fos.close();



        }
        catch (FileNotFoundException e) {
            throw new RuntimeException();

        } catch(IOException e){
            throw new RuntimeException();
        }

    }

}


