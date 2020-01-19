package com.cooler.refconnect.refrigeconnect.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;

import com.cooler.refconnect.refrigeconnect.model.ParameterModel;
import com.cooler.refconnect.refrigeconnect.view.viewhelper.RefrigeratorViewHelper;

/**
 * Created by Vigneshwaran G on 19/01/20.
 */
public class CustomInputDialog {

    public static void showInputTextDialog(Context context, ParameterModel parameterModel, RefrigeratorViewHelper viewHelper) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(parameterModel.getParameterName());

        // Set up the input
        final EditText input = new EditText(context);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("Value should be in range of below " + parameterModel.getScale());
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewHelper.onInputTextValueChanged(parameterModel, input.getText().toString());
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
