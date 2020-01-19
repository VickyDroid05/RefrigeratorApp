package com.cooler.refconnect.refrigeconnect.presenter;

import android.content.Context;

import com.cooler.refconnect.refrigeconnect.datastore.RefrigeratorDataStore;
import com.cooler.refconnect.refrigeconnect.model.ControllerModel;
import com.cooler.refconnect.refrigeconnect.view.viewhelper.RefrigeratorViewHelper;

/**
 * Created by Vigneshwaran G on 19/01/20.
 */
public class RefrigeratorPresenter {

    private RefrigeratorDataStore mRefrigeratorDataStore;
    private RefrigeratorViewHelper mViewHelper;

    public RefrigeratorPresenter(RefrigeratorDataStore refrigeratorDataStore, RefrigeratorViewHelper viewHelper) {
        this.mRefrigeratorDataStore = refrigeratorDataStore;
        this.mViewHelper = viewHelper;
    }

    public void getRefrigeratorControls(Context context, int resFile) {
        ControllerModel refrigeratorControls = this.mRefrigeratorDataStore.getRefrigeratorControls(context, resFile);
        if (mViewHelper != null) {
            //update the controller name
            mViewHelper.updateRefrigeratorInfo(refrigeratorControls.getControllerName(), refrigeratorControls.getImage());
            //update the contoller controls information
            mViewHelper.updateRefrigeratorControls(refrigeratorControls.getParameters());
        }
    }


}
