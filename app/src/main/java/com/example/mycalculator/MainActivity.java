package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ans, numerical;
    MaterialButton bClr, bBracketOpen, bBracketClose;
    MaterialButton buttonDivide, buttonMultiply, buttonadd, buttonsubtract, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonpoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ans = findViewById(R.id.ans);
        numerical = findViewById(R.id.numerical);

        assignId(bClr, R.id.b_clr);
        assignId(bBracketOpen, R.id.bopen_bracket);
        assignId(bBracketClose, R.id.bclose_bracket);
        assignId(buttonDivide, R.id.b_divide);
        assignId(buttonMultiply, R.id.b_multiply);
        assignId(buttonadd, R.id.b_add);
        assignId(buttonsubtract, R.id.b_sub);
        assignId(buttonEquals, R.id.b_equals);
        assignId(button0, R.id.b_0);
        assignId(button1, R.id.b_1);
        assignId(button2, R.id.b_2);
        assignId(button3, R.id.b_3);
        assignId(button4, R.id.b_4);
        assignId(button5, R.id.b_5);
        assignId(button6, R.id.b_6);
        assignId(button7, R.id.b_7);
        assignId(button8, R.id.b_8);
        assignId(button9, R.id.b_9);
        assignId(buttonAC, R.id.b_ac);
        assignId(buttonpoint, R.id.b_point);


    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = numerical.getText().toString();

        if (buttonText.equals("AC")) {
            numerical.setText("");
            ans.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            numerical.setText(ans.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        numerical.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")) {
            ans.setText(finalResult);
        }

    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }

}