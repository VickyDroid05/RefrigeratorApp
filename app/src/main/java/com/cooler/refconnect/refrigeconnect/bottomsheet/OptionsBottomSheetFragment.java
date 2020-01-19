package com.cooler.refconnect.refrigeconnect.bottomsheet;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooler.refconnect.refrigeconnect.R;
import com.cooler.refconnect.refrigeconnect.bottomsheet.viewadapter.RefrigeratorOptionsAdapter;
import com.cooler.refconnect.refrigeconnect.databinding.ActivityMainBinding;
import com.cooler.refconnect.refrigeconnect.databinding.FragmentOptionsBottomSheetBinding;
import com.cooler.refconnect.refrigeconnect.model.ParameterModel;
import com.cooler.refconnect.refrigeconnect.view.viewhelper.RefrigeratorViewHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsBottomSheetFragment extends BottomSheetDialogFragment {

    private static final String BUNDLE_PARAM = "Params";

    private RefrigeratorViewHelper mViewHelper;
    private RefrigeratorOptionsAdapter mAdapter;
    private FragmentOptionsBottomSheetBinding mViewBinding;
    private ParameterModel mSelectedParamModel;

    public OptionsBottomSheetFragment() {
        // Required empty public constructor
    }

    public static OptionsBottomSheetFragment getInstance(ParameterModel parameterModel) {
        OptionsBottomSheetFragment optionsBottomSheetFragment = new OptionsBottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_PARAM, parameterModel);
        optionsBottomSheetFragment.setArguments(bundle);
        return optionsBottomSheetFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSelectedParamModel = (ParameterModel) getArguments().get(BUNDLE_PARAM);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_options_bottom_sheet, container, false);
        return mViewBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        mAdapter = new RefrigeratorOptionsAdapter(mSelectedParamModel, mViewHelper);
        mViewBinding.rvOptions.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mViewBinding.rvOptions.getContext(),
                DividerItemDecoration.VERTICAL);
        mViewBinding.rvOptions.addItemDecoration(dividerItemDecoration);
        mViewBinding.rvOptions.setAdapter(mAdapter);

        mViewBinding.tvOptionTitle.setText(mSelectedParamModel.getParameterName());
    }

    public void setViewHelper(RefrigeratorViewHelper viewHelper) {
        this.mViewHelper = viewHelper;
    }
}
