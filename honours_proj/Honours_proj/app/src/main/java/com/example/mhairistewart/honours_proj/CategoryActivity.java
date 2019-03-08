package com.example.mhairistewart.honours_proj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import model.Category;

public class CategoryActivity extends AppCompatActivity {

    private DatabaseReference childRef;
    private List<DataSnapshot> snapshots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String language = extras.getString("language");

//        childRef = FirebaseReference.getRootRef().child("category");


        String myUrl = "https://mhairi-honours.firebaseio.com/category.json?orderBy=" + "\"Language\"" + "&equalTo=" + "\"" + language.toUpperCase() + "\"";

        //String to place our result in
        String result = "";
        //Instantiate new instance of our class
        HttpRequest request = new HttpRequest();
        //Perform the doInBackground method, passing in our url
        try {
            result = request.execute(myUrl).get();
            Gson g = new Gson();

            List<Category> categories = g.fromJson(result, new TypeToken<List<Category>>(){}.getType());

            System.out.print(categories);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        TextView txt = findViewById(R.id.text_view_id);
//        txt.setText(result);
        System.out.println(result);

        System.out.println(childRef);
        System.out.println(snapshots);

    }

}
