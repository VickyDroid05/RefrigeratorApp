package com.cooler.refconnect.refrigeconnect.bottomsheet.viewadapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooler.refconnect.refrigeconnect.R;
import com.cooler.refconnect.refrigeconnect.databinding.ItemControlSingleOptionBinding;
import com.cooler.refconnect.refrigeconnect.databinding.ItemControlValuesBinding;
import com.cooler.refconnect.refrigeconnect.model.ParameterModel;
import com.cooler.refconnect.refrigeconnect.view.viewhelper.RefrigeratorViewHelper;

import java.util.List;

/**
 * Created by Vigneshwaran G on 19/01/20.
 */
public class RefrigeratorOptionsAdapter extends RecyclerView.Adapter<RefrigeratorOptionsAdapter.DetailViewHolder> {


    private List<String> mOptions;
    private ParameterModel mParameterModel;
    private RefrigeratorViewHelper mViewHelper;

    public RefrigeratorOptionsAdapter(ParameterModel parameterModel,
                                      RefrigeratorViewHelper viewHelper) {
        this.mParameterModel = parameterModel;
        this.mOptions = parameterModel.getValues();
        this.mViewHelper = viewHelper;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemControlSingleOptionBinding viewDataBinding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_control_single_option, parent, false);

        return new DetailViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        String ParameterModel = mOptions.get(position);
        holder.setValues(ParameterModel);
    }

    @Override
    public int getItemCount() {
        return mOptions.size();
    }

    /**
     * Method to update the List with new movie models
     *
     * @param movieModels The Movie model
     */
    public void updateList(List<String> movieModels) {
        this.mOptions = movieModels;
        notifyDataSetChanged();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {

        private ItemControlSingleOptionBinding mViewBinding;

        private DetailViewHolder(ItemControlSingleOptionBinding viewBinding) {
            super(viewBinding.getRoot());
            this.mViewBinding = viewBinding;

            this.mViewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mViewHelper != null) {
                        mViewHelper.onBottomSheetOptionClicked(mParameterModel, getAdapterPosition());
                    }
                }
            });
        }

        /**
         * Method to set the heading and title to view
         *
         * @param option The Key Value model
         */
        public void setValues(String option) {
            mViewBinding.tvValue.setText(option);
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

    public List<String> getControls() {
        return mOptions;
    }

}
