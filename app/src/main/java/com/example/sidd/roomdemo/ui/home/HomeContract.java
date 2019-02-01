package com.example.sidd.roomdemo.ui.home;

import com.example.sidd.roomdemo.data.Person;

import java.util.List;

public interface HomeContract {

    interface View {
        void showPeople(List<Person> people);
        void showError(String message);
        void showProgress();
        void hideProgress();
        void showPersonDetails(String name, String age, String email);
    }

    interface Presenter {
        void getPeople();
        void addPerson(String name, String age, String email);
        void stop();
        void onPersonSelected(Person person);
    }
}
