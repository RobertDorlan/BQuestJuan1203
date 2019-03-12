package com.example.b_quest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class QuestCategory extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ConstraintLayout categoryLayout;

    RadioGroup rg;

    ArrayList<String> questNames = new ArrayList<>();

    RadioButton selected;

    private TextView questTile1;
    private TextView questTile2;
    private TextView questTile3;
    private TextView questTile4;
    private TextView questTile5;

    int cont = 0;


    /////////////////////////////////AnimalQuest views declaring////////////////////////////////////////////////////////////

    private ConstraintLayout animalListLayout;

    Boolean selected1 = false;
    Boolean selected2 = false;
    Boolean selected3 = false;
    Boolean selected4 = false;
    Boolean selected5 = false;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_quest_prueba);

        questNames.add("Quest1");
        questNames.add("Quest2");
        questNames.add("Quest3");
        questNames.add("Quest4");
        questNames.add("Quest5");


        final Button back = findViewById(R.id.go_back_arrow_category);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestCategory.this, CreateTreasureHunt.class);
                finish();
                startActivity(intent);
            }
        });

        rg = findViewById(R.id.radioGroup);

        categoryLayout = findViewById(R.id.category_layout);
        animalListLayout = findViewById(R.id.animal_layout);

        questTile1 = findViewById(R.id.quest_1_text_view);
        questTile2 = findViewById(R.id.quest_2_text_view);
        questTile3 = findViewById(R.id.quest_3_text_view);
        questTile4 = findViewById(R.id.quest_4_text_view);
        questTile5 = findViewById(R.id.quest_5_text_view);

        RadioButton questOne = findViewById(R.id.quest_1);
        questOne.setOnClickListener(this);
        RadioButton questTwo = findViewById(R.id.quest_2);
        questTwo.setOnClickListener(this);
        RadioButton questThree = findViewById(R.id.quest_3);
        questThree.setOnClickListener(this);
        RadioButton questFour = findViewById(R.id.quest_4);
        questFour.setOnClickListener(this);
        RadioButton questFive = findViewById(R.id.quest_5);
        questFive.setOnClickListener(this);

        //deffining the adapter for the quest spinners
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this, R.array.category, R.layout.spinner_item);
        categoryAdapter.setDropDownViewResource(R.layout.spinner_item_dropdown);

        //quest_1 spinner assigning adapter / adding listener
        final Spinner quest1 = findViewById(R.id.spinner_quest_1);
        quest1.setAdapter(categoryAdapter);
        quest1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                switch (item) {
                    case "Animals":
                        categoryLayout.setVisibility(view.GONE);
                        animalListLayout.setVisibility(view.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //quest_2 spinner assigning adapter / adding listener
        final Spinner quest2 = findViewById(R.id.spinner_quest_2);
        quest2.setEnabled(false);
        quest2.setAdapter(categoryAdapter);
        quest2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                switch (item) {
                    case "Animals":
                        categoryLayout.setVisibility(view.GONE);
                        animalListLayout.setVisibility(view.VISIBLE);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "no item selected", Toast.LENGTH_SHORT).show();
            }
        });

        //quest_3 spinner assigning adapter / adding listener
        final Spinner quest3 = findViewById(R.id.spinner_quest_3);
        quest3.setEnabled(false);
        quest3.setAdapter(categoryAdapter);
        quest3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                switch (item) {
                    case "Animals":
                        categoryLayout.setVisibility(view.GONE);
                        animalListLayout.setVisibility(view.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "no item selected", Toast.LENGTH_SHORT).show();
            }
        });

        //quest_4 spinner assigning adapter / adding listener
        final Spinner quest4 = findViewById(R.id.spinner_quest_4);
        quest4.setEnabled(false);
        quest4.setAdapter(categoryAdapter);
        quest4.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                switch (item) {
                    case "Animals":
                        categoryLayout.setVisibility(view.GONE);
                        animalListLayout.setVisibility(view.VISIBLE);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "no item selected", Toast.LENGTH_SHORT).show();
            }
        });

        //quest_5 spinner assigning adapter / adding listener
        final Spinner quest5 = findViewById(R.id.spinner_quest_5);
        quest5.setEnabled(false);
        quest5.setAdapter(categoryAdapter);
        quest5.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                switch (item) {
                    case "Animals":
                        categoryLayout.setVisibility(view.GONE);
                        animalListLayout.setVisibility(view.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "no item selected", Toast.LENGTH_SHORT).show();
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        Button done = findViewById(R.id.option_done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont++;
                rg.clearCheck();
                if (cont == 1) {
                    questTile1.setText(questNames.get(0));
                    quest1.setEnabled(false);
                    quest2.setEnabled(true);
                }

                if (cont == 2) {
                    questTile2.setText(questNames.get(1));
                    quest2.setEnabled(false);
                    quest3.setEnabled(true);

                }
                if (cont == 3) {
                    questTile3.setText(questNames.get(2));
                    quest3.setEnabled(false);
                    quest4.setEnabled(true);

                }
                if (cont == 4) {
                    questTile4.setText(questNames.get(3));
                    quest4.setEnabled(false);
                    quest5.setEnabled(true);
                }
                if (cont == 5) {
                    questTile5.setText(questNames.get(4));

                }
                categoryLayout.setVisibility(v.VISIBLE);
                animalListLayout.setVisibility(v.GONE);
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.quest_1:
                int selectedId = rg.getCheckedRadioButtonId();
                selected = findViewById(selectedId);
                String passing = selected.getText().toString();
                questNames.set(0, passing);
                gettingQuest(passing);
            break;
            case R.id.quest_2:
                int selectedId2 = rg.getCheckedRadioButtonId();
                selected = findViewById(selectedId2);
                String passing2 = selected.getText().toString();
                questNames.set(1, passing2);
                gettingQuest("Can i eat it");
                break;
            case R.id.quest_3:
                int selectedId3 = rg.getCheckedRadioButtonId();
                selected = findViewById(selectedId3);
                String passing3 = selected.getText().toString();
                questNames.set(2,passing3);
                gettingQuest("Does it swim");
                break;
            case R.id.quest_4:
                int selectedId4= rg.getCheckedRadioButtonId();
                selected = findViewById(selectedId4);
                String passing4 = selected.getText().toString();
                questNames.set(3, passing4);
                gettingQuest("Miaw");
                break;
            case R.id.quest_5:
                int selectedId5 = rg.getCheckedRadioButtonId();
                selected = findViewById(selectedId5);
                String passing5 = selected.getText().toString();
                questNames.set(4, passing5);
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
                        // String name = quest.getQuestName();
                        Toast.makeText(QuestCategory.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


}



