package com.cooler.refconnect.refrigeconnect;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.cooler.refconnect.refrigeconnect.bottomsheet.OptionsBottomSheetFragment;
import com.cooler.refconnect.refrigeconnect.databinding.ActivityMainBinding;
import com.cooler.refconnect.refrigeconnect.datastore.RefrigeratorDataStore;
import com.cooler.refconnect.refrigeconnect.model.ParameterModel;
import com.cooler.refconnect.refrigeconnect.presenter.RefrigeratorPresenter;
import com.cooler.refconnect.refrigeconnect.utils.Constants;
import com.cooler.refconnect.refrigeconnect.view.CustomInputDialog;
import com.cooler.refconnect.refrigeconnect.view.viewadapter.RefrigeratorControlViewAdapter;
import com.cooler.refconnect.refrigeconnect.view.viewhelper.RefrigeratorViewHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RefrigeratorViewHelper {

    private static final String TAG = MainActivity.class.getName();

    private RefrigeratorDataStore mRefrigeratorDataStore;
    private RefrigeratorPresenter mRefrigeratorPresenter;

    private RefrigeratorControlViewAdapter mAdapter;
    private ActivityMainBinding mViewBinding;

    OptionsBottomSheetFragment mBottomSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initDataStore();
        initPresenter();
        initView();
    }

    private void initView() {
        setSupportActionBar(mViewBinding.toolbarNew);
        mAdapter = new RefrigeratorControlViewAdapter(new ArrayList<>(), this);
        mViewBinding.rvControls.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mViewBinding.rvControls.getContext(),
                DividerItemDecoration.VERTICAL);
        mViewBinding.rvControls.addItemDecoration(dividerItemDecoration);
        mViewBinding.rvControls.setAdapter(mAdapter);

        BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        mRefrigeratorPresenter.getRefrigeratorControls(getApplicationContext(), R.raw.sample);
    }

    private void initDataStore() {
        mRefrigeratorDataStore = new RefrigeratorDataStore();
    }

    private void initPresenter() {
        mRefrigeratorPresenter = new RefrigeratorPresenter(mRefrigeratorDataStore, this);
    }

    @Override
    public void updateRefrigeratorInfo(String refrigeratorName, String refrigeratorImage) {
        setTitle(refrigeratorName);
        //can set image here when it is dynamic
    }

    @Override
    public void updateRefrigeratorControls(List<ParameterModel> refrigeratorControls) {
        mAdapter.updateList(refrigeratorControls);
    }

    @Override
    public void onItemClick(int position) {
        ParameterModel parameterModel = mAdapter.getOptions().get(position);
        if (parameterModel.getType().equals(Constants.TYPE_OPTIONS)) {
            mBottomSheetFragment = OptionsBottomSheetFragment.getInstance(
                    mAdapter.getOptions().get(position));
            mBottomSheetFragment.setViewHelper(this);
            mBottomSheetFragment.show(getSupportFragmentManager(), mBottomSheetFragment.getTag());
        } else if (parameterModel.getType().equals(Constants.TYPE_INPUT)) {
            CustomInputDialog.showInputTextDialog(MainActivity.this, parameterModel, this);
        }
    }

    @Override
    public void onBottomSheetOptionClicked(ParameterModel originalParameterModel, int position) {
        mBottomSheetFragment.dismiss();
        mAdapter.updateList(updateModification(originalParameterModel, position));
    }

    @Override
    public void onInputTextValueChanged(ParameterModel parameterModel, String newValue) {
        mAdapter.updateList(updateModification(parameterModel, newValue));
    }

    /**
     * Method to update the modification from option selection
     *
     * @param originalParameterModel Un modified Parameter model
     * @param newSelectionPos        Current selection position of the options
     * @return updated list
     */
    private List<ParameterModel> updateModification(ParameterModel originalParameterModel, int newSelectionPos) {
        List<ParameterModel> options = mAdapter.getOptions();
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(originalParameterModel)) {
                options.get(i).setPnu("0" + newSelectionPos);
                break;
            }
        }
        return options;
    }

    /**
     * Method to update the modification from option selection
     *
     * @param originalParameterModel Un modified Parameter model
     * @param newValue               Current selection value of the options
     * @return updated list
     */
    private List<ParameterModel> updateModification(ParameterModel originalParameterModel, String newValue) {
        List<ParameterModel> options = mAdapter.getOptions();
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(originalParameterModel)) {
                options.get(i).setPnu(newValue);
                break;
            }
        }
        return options;
    }
}
