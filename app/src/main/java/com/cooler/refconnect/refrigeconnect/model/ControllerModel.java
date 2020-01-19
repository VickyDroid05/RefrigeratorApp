
package com.cooler.refconnect.refrigeconnect.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ControllerModel {

    @SerializedName("controller_name")
    @Expose
    private String controllerName;
    @SerializedName("_image")
    @Expose
    private String image;
    @SerializedName("parameters")
    @Expose
    private List<ParameterModel> parameters = null;

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ParameterModel> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterModel> parameters) {
        this.parameters = parameters;
    }

}
