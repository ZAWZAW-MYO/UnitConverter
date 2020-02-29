package com.zawzawmyo.unitconverter;

import android.content.DialogInterface;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.text.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity
{

    EditText etval;
    TextView tv;
    Spinner spn1,spn2,spn3;
    UnitConverter converter;

    String[] modes={
            "အလျား",
            "ဧရိယာ",
            "ထုထည်",
            "အလေးချိန်"
    };

    String[] temp;

    String[] lengthUnits={
            "နာနိုမီတာ",
            "မိုက္ခရိုမီတာ",
            "မီလီမီတာ",
            "စင်တီမီတာ",
            "ဒက်စီမီတာ",
            "မီတာ",
            "ဟက်တိုမီတာ",
            "ကီလိုမီတာ",
            "လက်မ",
            "ပေ",
            "ကိုက်",
            "မိုင်",
            "ဆံခြည်",
            "နှမ်း",
            "မုယော",
            "လက်သစ်",
            "မိုက်",
            "ထွာ",
            "တောင်",
            "လံ",
            "တာ",
            "ဥဿဘ",
            "ကောသ",
            "ဂါဝုတ်",
            "ယူဇနာ"
    };

    String[] areaUnits={
            "square_nanometer",
            "square_micrometer",
            "square_millimeter",
            "square_centimeter",
            "square_decimeter",
            "square_meter",
            "square_kilometer",
            "ဟက်တာ",
            "ဧက",
            "square_inch",
            "square_foot",
            "square_mile"
    };

    String[] volumeUnits={
            "milliliter",
            "liter",
            "cubic_millimeter",
            "cubic_centimeter",
            "cubic_decimeter",
            "gallon",
            "cubic_kilometer",
            "cubic_inch",
            "cubic_foot",

            "လမြူ",
            "လမြက်",
            "လမယ်",
            "စလယ်",
            "ခွက်",
            "ပြည်",
            "စိတ်",
            "ခွဲ",
            "တင်း"
    };

    String[] massUnits={
            "တန်",
            "ကီလိုဂရမ်",
            "ဂရမ်",
            "မီလီဂရမ်",
            "မိုက်ခရိုဂရမ်",
            "အောင်စ",
            "ပေါင်",

            "ရွေးလေး",
            "ရွေးကြီး",
            "ပဲသား",
            "မူးသား",
            "မတ်သား",
            "ငါးမူးသား",
            "ကျပ်သား",
            "အ၀က်သား",
            "အစိတ်သား",
            "ငါးဆယ်သား",
            "ပိဿာ",
            "အချိန်တစ်ရာ"
    };
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getApplicationContext(),getResources().getString(R.string.app_id));
        setContentView(R.layout.activity_main);
        setTitle("Unit Converter");
        etval=(EditText)findViewById(R.id.etval);

        spn1=(Spinner)findViewById(R.id.spn1);
        spn1.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,modes));

        spn2=(Spinner)findViewById(R.id.spn2);
        spn3=(Spinner)findViewById(R.id.spn3);

        tv=(TextView)findViewById(R.id.tv);
        etval.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
            {
                // TODO: Implement this method
            }

            @Override
            public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
            {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable p1)
            {
                // TODO: Implement this method
            }


        });
        converter=new UnitConverter();

        spn1.setOnItemSelectedListener(new OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
            {
                switch(p3){
                    case 0:
                        temp=lengthUnits;
                        break;
                    case 1:
                        temp=areaUnits;
                        break;
                    case 2:
                        temp=volumeUnits;
                        break;
                    case 3:
                        temp=massUnits;
                        break;
                }
                spn2.setAdapter(new ArrayAdapter<String>(
                        getBaseContext(),
                        android.R.layout.simple_spinner_dropdown_item,temp));
                spn3.setAdapter(new ArrayAdapter<String>(
                        getBaseContext(),
                        android.R.layout.simple_spinner_dropdown_item,temp));
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> p1)
            {
// TODO: Implement this method
            }

        });
        spn2.setOnItemSelectedListener(new OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
            {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> p1)
            {
                // TODO: Implement this method
            }


        });
        spn3.setOnItemSelectedListener(new OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
            {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> p1)
            {
                // TODO: Implement this method
            }


        });

    }

    public void calculate(){
        if(etval.getText().toString().length()==0){
            tv.setText("");
        }else{
            double res=converter.Convert(
                    spn1.getSelectedItem().toString(),
                    Double.parseDouble(etval.getText().toString()),
                    spn2.getSelectedItem().toString(),
                    spn3.getSelectedItem().toString());

            tv.setText(res+"");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add("About");
        menu.add("Exit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        String title=item.getTitle().toString();
        switch(title){
            case "About":
                showAboutDialog();
                break;
            case "Exit":
                showExitDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showExitDialog(){
        AlertDialog ad=new AlertDialog.Builder(this).create();

        ad.setTitle("Exit");
        ad.setIcon(R.mipmap.ic_launcher);
        ad.setMessage("Do you really want to exit?");

        ad.setButton(
                AlertDialog.BUTTON_NEGATIVE,
                "No",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface p1, int p2)
                    {
                        p1.cancel();
                    }
                });

        ad.setButton(
                AlertDialog.BUTTON_POSITIVE,
                "Yes",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface p1, int p2)
                    {
                        finish();
                    }
                });
        ad.show();
    }

    public void showAboutDialog(){
        AlertDialog ad=new AlertDialog.Builder(this).create();

        ad.setTitle("About");
        ad.setIcon(R.mipmap.ic_launcher);
        ad.setMessage("This application is created by AungZinMoe.Refrence From Sir Nyi Nyi Lwin");

        ad.setButton(
                AlertDialog.BUTTON_POSITIVE,
                "Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface p1, int p2)
                    {
                        p1.cancel();
                    }
                });
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }}
