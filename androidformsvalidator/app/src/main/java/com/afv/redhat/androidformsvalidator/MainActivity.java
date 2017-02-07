package com.afv.redhat.androidformsvalidator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.afv.redhat.forms_validator.LibraryClass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFunction(); //Lib test
    }

    public void myFunction(){
        LibraryClass libraryClass = new LibraryClass();
        libraryClass.printLog("Hello World");
    }
}
