
package com.cooler.refconnect.refrigeconnect.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParameterModel implements Parcelable {

    @SerializedName("parameter_name")
    @Expose
    private String parameterName;
    @SerializedName("pnu")
    @Expose
    private String pnu;
    @SerializedName("scale")
    @Expose
    private Integer scale;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("values")
    @Expose
    private List<String> values = null;

    protected ParameterModel(Parcel in) {
        parameterName = in.readString();
        pnu = in.readString();
        if (in.readByte() == 0) {
            scale = null;
        } else {
            scale = in.readInt();
        }
        type = in.readString();
        values = in.createStringArrayList();
    }

    public static final Creator<ParameterModel> CREATOR = new Creator<ParameterModel>() {
        @Override
        public ParameterModel createFromParcel(Parcel in) {
            return new ParameterModel(in);
        }

        @Override
        public ParameterModel[] newArray(int size) {
            return new ParameterModel[size];
        }
    };

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getPnu() {
        return pnu;
    }

    public void setPnu(String pnu) {
        this.pnu = pnu;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(parameterName);
        parcel.writeString(pnu);
        if (scale == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(scale);
        }
        parcel.writeString(type);
        parcel.writeStringList(values);
    }
}
