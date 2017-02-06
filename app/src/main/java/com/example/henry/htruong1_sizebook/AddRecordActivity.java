package com.example.henry.htruong1_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.io.FileInputStream;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static com.example.henry.htruong1_sizebook.R.id.nameText;
import static java.io.FileDescriptor.out;
//*This class handles the adding of new data for people when entered into the app*?

public class AddRecordActivity extends AppCompatActivity {
    private static final String FILENAME = "save.sav";
    FileOutputStream outputStream;
// fixes the nullpointer error
// http://stackoverflow.com/questions/28409089/nullpointerexception-when-adding
// -an-object-to-arraylist-in-android
    private ArrayList<getData> peopleList ;//= new ArrayList<getData>();

    //private ArrayAdapter<getData> adapter;


    private EditText nameInfo;
    private EditText dateInfo;
    private EditText neckInfo;
    private EditText bustInfo;
    private EditText chestInfo;
    private EditText waistInfo;
    private EditText hipInfo;
    private EditText inseamInfo;
    private EditText commentInfo;
    private getData entry;
    private Integer pos;
    @Override
    //*Finds the information from the EditText Boxes */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
       // peopleList.add(null);
        Intent intent = getIntent();
       // Button saveButton = (Button) findViewById(R.id.save);
       // pos = getIntent.getIntExtra("posOfEntry",0);
        loadFromFile();
        nameInfo = (EditText) findViewById(R.id.nameText);
        dateInfo = (EditText) findViewById(R.id.dateText);
        neckInfo = (EditText) findViewById(R.id.neckData);
        bustInfo = (EditText) findViewById(R.id.bustData);
        chestInfo = (EditText) findViewById(R.id.chestData);
        waistInfo = (EditText) findViewById(R.id.waistData);
        hipInfo = (EditText) findViewById(R.id.hipData);
        inseamInfo = (EditText) findViewById(R.id.inseamData);
        commentInfo = (EditText) findViewById(R.id.commentData);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_add_record);
    }
    //*Function converts info to data to be saved *?
    public void saveEntry(View v) {
                setResult(RESULT_OK);
                //setting value
                String Name = nameInfo.getText().toString();
                String Date = dateInfo.getText().toString();
                String Neck = neckInfo.getText().toString();
                String Bust = bustInfo.getText().toString();
                String Chest = chestInfo.getText().toString();
                String Waist = waistInfo.getText().toString();
                String Hip = hipInfo.getText().toString();
                String Inseam = inseamInfo.getText().toString();
                String Comment = commentInfo.getText().toString();


                if(nameInfo.getText().toString().length() == 0) { //Makes name field required if 0
                    //nameInfo.setError("Name field is required!");
                    Toast.makeText(getApplicationContext(), "Name required",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplication(),AddRecordActivity.class);
                    startActivity(intent);
                }
                dataInput(Name,Date,Neck,Bust,Chest,Waist,Hip,Inseam,Comment);
                //person individual = new person(Name, Date, Neck, Bust, Chest, Waist, Hip, Inseam, Comment);
               // adapter.notifyDataSetChanged();
               /* person = new getData();
                person.setName(Name);
                person.setDate(Date);
                person.setNeck(Neck);
                person.setBust(Bust);
                person.setChest(Chest);
                person.setWaist(Waist);
                person.setHip(Hip);
                person.setInseam(Inseam);
                person.setComment(Comment);
                peopleList.add(person);*/

               /* saveInFile();
               // loadFile();
                finish();*/



            }
    //*Class that handles the saving of data. Achieved through classes and setters*/
    public void dataInput(String Name, String Date, String Neck,
                          String Bust, String Chest,
                          String Waist, String Hip, String Inseam,
                          String Comment){
        entry = new getData();
        entry.setName(Name);
        entry.setDate(Date);
        entry.setNeck(Neck);
        entry.setBust(Bust);
        entry.setChest(Chest);
        entry.setWaist(Waist);
        entry.setHip(Hip);
        entry.setInseam(Inseam);
        entry.setComment(Comment);
        peopleList.add(entry);
        saveInFile();
        Context context = getApplicationContext();
        Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show();
        finish();

    }
    //*This function saves the file to be used for storage
    public void saveInFile(){
        try{ //saving
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();

            gson.toJson(peopleList,out);
            out.close();
           //Log.d("my tag", "name: " + person.getName());
            fos.close();



        }
        catch (FileNotFoundException e) {
            throw new RuntimeException();

        } catch(IOException e){
            throw new RuntimeException();
        }

    }
    //*Loads the function*/
    private void loadFromFile() {
        try {
            Log.d("First load", "This section runs:");
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType =  new TypeToken<ArrayList<getData>>() {
            }.getType();
            peopleList = gson.fromJson(in,listType);
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





