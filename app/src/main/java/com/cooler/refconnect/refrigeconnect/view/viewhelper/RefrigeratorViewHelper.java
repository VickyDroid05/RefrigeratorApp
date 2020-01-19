package com.cooler.refconnect.refrigeconnect.view.viewhelper;

import com.cooler.refconnect.refrigeconnect.model.ParameterModel;

import java.util.List;

/**
 * Created by Vigneshwaran G on 19/01/20.
 */
public interface RefrigeratorViewHelper {

    void updateRefrigeratorInfo(String refrigeratorName, String refrigeratorImage);

    void updateRefrigeratorControls(List<ParameterModel> refrigeratorControls);

    void onItemClick(int position);

    void onBottomSheetOptionClicked(ParameterModel originalParamModel, int position);

    void onInputTextValueChanged(ParameterModel parameterModel, String newValue);

}
