package demo.georgiosafo.com.androiddemo.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demo.georgiosafo.com.androiddemo.AndroidDemoApp;
import demo.georgiosafo.com.androiddemo.di.component.AndroidDemoAppComponent;

/**
 * Created by gevorksafaryan on 16.05.17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(AndroidDemoApp.getAndroidDemoApp().getAppComponent());
    }


    protected abstract void setupComponent(AndroidDemoAppComponent appComponent);
}
