package com.example.sidd.roomdemo.ui.home;

import com.example.sidd.roomdemo.data.Person;
import com.example.sidd.roomdemo.data.PersonDatabase;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    private final PersonDatabase personDatabase;
    private final HomeContract.View view;

    private CompositeDisposable compositeDisposable;

    private Person person = null;

    private boolean isNewPerson = false;


    public HomePresenter(PersonDatabase personDatabase, HomeContract.View view) {
        this.personDatabase = personDatabase;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getPeople() {
        compositeDisposable.add(personDatabase.personDao().getAllPeople()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Person>>() {
                    @Override
                    public void accept(List<Person> people) throws Exception {
                        view.showPeople(people);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(throwable.getMessage());
                    }
                }));
    }

    @Override
    public void addPerson(String name, String age, String email) {
        if (person == null) {
            person = new Person();
            isNewPerson = true;
        }
        person.age = Integer.parseInt(age);
        person.email = email;
        person.name = name;
        compositeDisposable.add(Completable.fromCallable(new Callable<Person>() {
            @Override
            public Person call() throws Exception {
                if (isNewPerson) {
                    personDatabase.personDao().insert(person);
                } else {
                    personDatabase.personDao().update(person);
                }
                return null;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe());
    }

    @Override
    public void stop() {
        compositeDisposable.clear();
    }

    @Override
    public void onPersonSelected(Person person) {
        this.person = person;
        view.showPersonDetails(person.name, String.valueOf(person.age), person.email);
    }
}
