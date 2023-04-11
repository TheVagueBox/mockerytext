package com.thevaguebox.mockerytextconverter;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText inputText;
    EditText outputText;
    String rawText;
    CheckBox upperCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);
        upperCheck = findViewById(R.id.uppercaseCheck);

    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.resetButton)
        {
            inputText.setText("");
            outputText.setText("");
        }

        if (v.getId()==R.id.convertButton)
        {
            rawText = inputText.getText().toString();
            String[] arrayRaw = rawText.split("");

            if(upperCheck.isChecked())
            {
                for (int i=0; i<arrayRaw.length; i++)
                {
                    if(i%2==1)
                    {
                        arrayRaw[i] = arrayRaw[i].toUpperCase();
                    }
                    else
                        arrayRaw[i] = arrayRaw[i].toLowerCase();
                }
            }

            else
            {
                for (int i=0; i<arrayRaw.length; i++)
                {
                    if(i%2==0)
                    {
                        arrayRaw[i] = arrayRaw[i].toUpperCase();
                    }
                    else
                        arrayRaw[i] = arrayRaw[i].toLowerCase();
                }
            }


            StringBuilder sb = new StringBuilder();
            for (String s : arrayRaw) {
                sb.append(s);
            }

            rawText = sb.toString();
            outputText.setText(rawText);

            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Mock Text",rawText);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "MoCkErY tExT copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }
}