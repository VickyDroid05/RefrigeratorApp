package com.cooler.refconnect.refrigeconnect.view.viewadapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooler.refconnect.refrigeconnect.R;
import com.cooler.refconnect.refrigeconnect.databinding.ItemControlValuesBinding;
import com.cooler.refconnect.refrigeconnect.model.ParameterModel;
import com.cooler.refconnect.refrigeconnect.utils.Constants;
import com.cooler.refconnect.refrigeconnect.view.viewhelper.RefrigeratorViewHelper;

import java.util.List;

/**
 * Created by Vigneshwaran G on 19/01/20.
 */
public class RefrigeratorControlViewAdapter extends RecyclerView.Adapter<RefrigeratorControlViewAdapter.DetailViewHolder> {


    private List<ParameterModel> mParameterModels;
    private RefrigeratorViewHelper mViewHelper;

    public RefrigeratorControlViewAdapter(List<ParameterModel> paramList,
                                          RefrigeratorViewHelper viewHelper) {
        this.mParameterModels = paramList;
        this.mViewHelper = viewHelper;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemControlValuesBinding viewDataBinding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_control_values, parent, false);

        return new DetailViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        ParameterModel ParameterModel = mParameterModels.get(position);
        holder.setValues(ParameterModel);
    }

    @Override
    public int getItemCount() {
        return mParameterModels.size();
    }

    /**
     * Method to update the List with new movie models
     *
     * @param movieModels The Movie model
     */
    public void updateList(List<ParameterModel> movieModels) {
        this.mParameterModels = movieModels;
        notifyDataSetChanged();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {

        private ItemControlValuesBinding mViewBinding;

        private DetailViewHolder(ItemControlValuesBinding viewBinding) {
            super(viewBinding.getRoot());
            this.mViewBinding = viewBinding;

            this.mViewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mViewHelper != null) {
                        mViewHelper.onItemClick(getAdapterPosition());
                    }
                }
            });
        }

        /**
         * Method to set the heading and title to view
         *
         * @param ParameterModel The Key Value model
         */
        public void setValues(ParameterModel ParameterModel) {
            mViewBinding.tvKey.setText(ParameterModel.getParameterName());
            mViewBinding.tvValue.setText(ParameterModel.getPnu());
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<ParameterModel> getOptions(){
        return mParameterModels;
    }

}
