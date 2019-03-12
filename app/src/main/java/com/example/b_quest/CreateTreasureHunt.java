package com.example.b_quest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class CreateTreasureHunt extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText mTreasureHuntName;
    private EditText mHeroname;
    private EditText mHeroEmail;

    private Map<String, Object> questList = new HashMap<>();

    public Map<String, Object> getQuestList() {
        return questList;
    }

    public void setQuestList(Map<String, Object> questList) {
        this.questList = questList;
    }

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView mNavigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creating_th);

        mTreasureHuntName = findViewById(R.id.th_name);
        mHeroname = findViewById(R.id.choose_hero);
        mHeroEmail = findViewById(R.id.hero_email);

        final Button questCategory = findViewById(R.id.quest_categories);
        questCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateTreasureHunt.this, QuestCategory.class);
                intent.putExtra("nothing", "asd");
                startActivity(intent);

            }
        });

        //  contribution spinner + listenerSpinner contribution = findViewById(R.id.minimum_contribution);
        Spinner contribution = findViewById(R.id.minimum_contribution);
        ArrayAdapter<CharSequence> contributionAdapter = ArrayAdapter.createFromResource(this, R.array.contribution, R.layout.spinner_item);
        contributionAdapter.setDropDownViewResource(R.layout.spinner_item_dropdown);
        contribution.setAdapter(contributionAdapter);
        contribution.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getApplicationContext(), "" + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "no item selected", Toast.LENGTH_SHORT).show();
            }
        });



        Toolbar toolbar = findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mNavigationView = findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        switch (id){

            case R.id.home:
                Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_profile:
                Toast.makeText(getApplicationContext(),"profile",Toast.LENGTH_LONG).show();
                break;


            case R.id.settings:
                Toast.makeText(getApplicationContext(),"settings",Toast.LENGTH_LONG).show();
                break;

            case R.id.logout:
                Intent intent = new Intent(CreateTreasureHunt.this, MainActivity.class);
                finish();
                startActivity(intent);

                Toast.makeText(getApplicationContext(),"logout",Toast.LENGTH_LONG).show();
                break;

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }


}
