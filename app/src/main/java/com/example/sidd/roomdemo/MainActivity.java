package com.example.sidd.roomdemo;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.sidd.roomdemo.data.Person;
import com.example.sidd.roomdemo.data.PersonDatabase;
import com.example.sidd.roomdemo.ui.home.HomeContract;
import com.example.sidd.roomdemo.ui.home.HomePresenter;
import com.example.sidd.roomdemo.ui.home.OnItemClickListener;
import com.example.sidd.roomdemo.ui.home.PeopleAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeContract.View, OnItemClickListener {

    private HomeContract.Presenter presenter;

    final PeopleAdapter peopleAdapter = new PeopleAdapter(this);

    EditText name;
    EditText age;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonDatabase personDatabase = Room.databaseBuilder(getApplicationContext(),
                PersonDatabase.class, "PersonDatabase").build();

        presenter = new HomePresenter(personDatabase, this);

        presenter.getPeople();

        Button save = findViewById(R.id.btnSave);
        name = findViewById(R.id.etName);
        age = findViewById(R.id.etAge);
        email = findViewById(R.id.etEmail);

        RecyclerView recyclerView = findViewById(R.id.rvPeople);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                linearLayoutManager.getOrientation()));
        recyclerView.setAdapter(peopleAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addPerson(name.getText().toString(),
                        age.getText().toString(),
                        email.getText().toString());
            }
        });

    }

    @Override
    public void showPeople(List<Person> people) {
        peopleAdapter.setData(people);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showPersonDetails(String name, String age, String email) {
        this.name.setText(name);
        this.age.setText(age);
        this.email.setText(email);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void onItemClicked(Person person) {
        presenter.onPersonSelected(person);
    }
}
