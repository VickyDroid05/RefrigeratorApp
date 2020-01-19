package com.cooler.refconnect.refrigeconnect.datastore;

import android.content.Context;

import com.cooler.refconnect.refrigeconnect.model.ControllerModel;
import com.cooler.refconnect.refrigeconnect.utils.FileUtils;
import com.google.gson.Gson;

/**
 * Created by Vigneshwaran G on 19/01/20.
 */
public class RefrigeratorDataStore {

    public ControllerModel getRefrigeratorControls(Context context, int resFile) {
        String jsonString = FileUtils.readJsonfromAssets(context, resFile);
        return new Gson().fromJson(jsonString, ControllerModel.class);
    }
}
