package com.example.b_quest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Timer;
import java.util.TimerTask;

public class AnimalQuest extends AppCompatActivity implements View.OnClickListener {

    String [] data = new String[5];

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private RadioButton questOne;
    private RadioButton questTwo;
    private RadioButton questThree;
    private RadioButton questFour;
    private RadioButton questFive;

    private String name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quest_list);

        questOne = findViewById(R.id.quest_1);
        questOne.setOnClickListener(this);
        questTwo = findViewById(R.id.quest_2);
        questTwo.setOnClickListener(this);
        questThree = findViewById(R.id.quest_3);
        questThree.setOnClickListener(this);
        questFour = findViewById(R.id.quest_4);
        questFour.setOnClickListener(this);
        questFive = findViewById(R.id.quest_5);
        questFive.setOnClickListener(this);

        Button goBackArrow = findViewById(R.id.go_back_arrow);
        goBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalQuest.this, QuestCategory.class);
                finish();
                startActivity(intent);
            }
        });



        Button done = findViewById(R.id.option_done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalQuest.this, QuestCategory.class);
//                if(getIntent().getStringExtra("spinner").equals("quest1")){
//                    intent.putExtra("questName1", name);
//                }else
//                if(getIntent().getStringExtra("spinner").equals("quest2")){
//                    intent.putExtra("questName2", name);
//                }else
//                if(getIntent().getStringExtra("spinner").equals("quest3")){
//                    intent.putExtra("questName3", name);
//                }else
//                if(getIntent().getStringExtra("spinner").equals("quest4")){
//                    intent.putExtra("questName4", name);
//                }else
//                if(getIntent().getStringExtra("spinner").equals("quest5")){
//                    intent.putExtra("questName5", name);
//                }
                intent.putExtra("passing", data);

                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.quest_1:
                gettingQuest("Kiss a dog");
                break;
            case R.id.quest_2:
                gettingQuest("Can i eat it?");
                break;
            case R.id.quest_3:
                gettingQuest("Does it swim?");
                break;
            case R.id.quest_4:
                gettingQuest("Miaw");
                break;
            case R.id.quest_5:
                gettingQuest("Feed the birds");
                break;
        }
    }

    public void gettingQuest(String questName) {
        CollectionReference ref = db.collection("quest");
        Query query = ref.whereEqualTo("questName", questName);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Quest quest = document.toObject(Quest.class);
                        String message = quest.getQuestDescription();
                        name = quest.getQuestName();
                        data[0] = name;
                        Toast.makeText(AnimalQuest.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}



