package com.jayseeofficial.agecalculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.txt_youngest)
    TextView mTxtYoungest;

    @InjectView(R.id.txt_oldest)
    TextView mTxtOldest;

    @InjectView(R.id.txt_age)
    EditText mTxtAge;

    @OnClick(R.id.btn_calculate)
    public void onCalculateClick() {
        Log.d(getClass().getSimpleName(), mTxtAge.getText().toString().trim());
        Scanner scanner = new Scanner(mTxtAge.getText().toString().trim());
        // Check if they entered a valid number, and show an error and stop if they didn't
        if (!scanner.hasNextInt()) {
            mTxtAge.setError("Please enter a valid age.");
            return;
        }
        // Calculate the ages
        int age = scanner.nextInt();
        int youngest = XKCDAgeCalculator.getMinimumAge(age);
        int oldest = XKCDAgeCalculator.getMaximumAge(age);
        mTxtYoungest.setText(getString(R.string.youngest) + " " + youngest + "!");
        mTxtOldest.setText(getString(R.string.oldest) + " " + oldest + "!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_licences) {
            showOpenSourceLicences();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showOpenSourceLicences() {
        Notices notices = new Notices();

        String name = "ButterKnife";
        String copyright = "Copyright 2013 Jake Wharton";
        String url = "https://jakewharton.github.io/butterknife/";
        Notice butterknifeNotice = new Notice(name, url, copyright, new ApacheSoftwareLicense20());

        notices.addNotice(butterknifeNotice);
        new LicensesDialog.Builder(this).setIncludeOwnLicense(true).setNotices(notices).build().show();
    }
}
