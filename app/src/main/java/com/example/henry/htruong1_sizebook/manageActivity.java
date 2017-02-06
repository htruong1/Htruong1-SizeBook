package com.example.henry.htruong1_sizebook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

/**
 * Created by Henry on 2017-02-06.
 */

public class manageActivity extends AppCompatActivity {
    private int index;
    private static final String FILENAME = "save.sav";
    private ArrayList<getData> peopleList;
    private EditText nameText;
    private EditText dateText;
    private EditText neckText;
    private EditText bustText;
    private EditText chestText;
    private EditText waistText;
    private EditText hipText;
    private EditText inseamText;
    private EditText commentText;
    ListView records;

    protected void onCreate(Bundle savedInstanceState) {

        Log.d("new activity", "in new");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manageactivity);
        Intent intent = getIntent();
        Integer index;
        index = intent.getIntExtra("index_pos",0);
        loadFromFile();
        String nameObject =   peopleList.get(index).getName();
        nameText = (EditText) findViewById(R.id.editName);
        nameText.setText(peopleList.get(index).getName());
        dateText = (EditText) findViewById(R.id.editDate);
        dateText.setText(peopleList.get(index).getDate());
        neckText = (EditText) findViewById(R.id.editNeck);
        neckText.setText(peopleList.get(index).getNeck());
        bustText =(EditText) findViewById(R.id.editBust);
        bustText.setText(peopleList.get(index).getBust());
        chestText = (EditText) findViewById(R.id.editChest);
        chestText.setText(peopleList.get(index).getChest());
        waistText = (EditText) findViewById(R.id.editWaist);
        waistText.setText(peopleList.get(index).getWaist());
        hipText = (EditText) findViewById(R.id.editHip);
        hipText.setText(peopleList.get(index).getHip());
        inseamText  = (EditText) findViewById(R.id.editInseam);
        inseamText.setText(peopleList.get(index).getInseam());
        commentText = (EditText) findViewById(R.id.editComment);
        commentText.setText(peopleList.get(index).getComment());





    }
    public void saveEdit(View view){
        setResult(RESULT_OK);
   /*     String Name = nameText.getText().toString();
        String Date = dateText.getText().toString();
        String Neck = neckText.getText().toString();
        String Bust = bustText.getText().toString();
        String Chest = chestText.getText().toString();
        String Waist = waistText.getText().toString();
        String Hip = hipText.getText().toString();
        String Inseam = inseamText.getText().toString();
        String Comment = commentText.getText().toString();*/


        updateScreen(peopleList);
        saveInFile();
        Context context = getApplicationContext();
        Toast.makeText(context,"Edited Data",Toast.LENGTH_SHORT).show();
        finish();
    }
    public void deleteRecord(View view){
        setResult(RESULT_OK);
        peopleList.remove(index);
        updateScreen(peopleList);
        saveInFile();

        finish();

    }
    public void updateScreen(ArrayList<getData> peopleList){
        records = (ListView) findViewById(R.id.viewList);
        ArrayAdapter<getData> ArrayAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, peopleList);
        records.setAdapter(ArrayAdapt);
        ArrayAdapt.notifyDataSetChanged();

    }
    public void saveInFile() {
        try { //saving
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();

            gson.toJson(peopleList, out);
            out.close();

            fos.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException();

        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private void loadFromFile() {
        try {
            Log.d("First load", "This section runs:");
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<getData>>() {
            }.getType();
            peopleList = gson.fromJson(in, listType);
            // updateScreen(peopleList);
            fis.close();

        } catch (FileNotFoundException e) {
            peopleList = new ArrayList<>();
            saveInFile();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
