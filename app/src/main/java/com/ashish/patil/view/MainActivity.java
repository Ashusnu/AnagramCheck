package com.ashish.patil.view;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashish.patil.R;
import com.ashish.patil.model.AnagramValidationResponse;
import com.ashish.patil.util.Util;
import com.ashish.patil.viewModels.AnagramValidateViewModel;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    AnagramValidateViewModel anagramValidateViewModel;
    Button      validateButton;
    TextInputLayout inputFirstWord, inputSecondWord;
    TextView    tv_result;
    ConstraintLayout animationView;
    ImageView img_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validateButton       =       findViewById(R.id.btn_validate);
        inputFirstWord       =       findViewById(R.id.etFirstWordInput);
        inputSecondWord      =       findViewById(R.id.etSecondWordInput);
        tv_result            =       findViewById(R.id.tv_result);
        animationView        =       findViewById(R.id.animationView);
        img_result           =       findViewById(R.id.img_result);

        validateButton.setOnClickListener(validateListener);
        inputFirstWord.setEndIconOnClickListener(clearFirstInput);
        inputFirstWord.getEditText().addTextChangedListener(textOneWatcher);

        inputSecondWord.setEndIconOnClickListener(clearSecondInput);
        inputSecondWord.getEditText().addTextChangedListener(textTwoWatcher);


        anagramValidateViewModel =
                ViewModelProviders.of(this).get(AnagramValidateViewModel.class);

        observeViewModel();
    }

    View.OnClickListener validateListener = view -> {
        anagramValidateViewModel.validateIfAnagrams(inputFirstWord.getEditText().getText().toString(),
                inputSecondWord.getEditText().getText().toString());
        Util.hideSoftKeyBoard(this);
        validateButton.setVisibility(View.GONE);
    };

    View.OnClickListener clearFirstInput = view -> resetView(inputFirstWord);
    View.OnClickListener clearSecondInput = view -> {
        resetView(inputSecondWord);
    };

    private void resetView(TextInputLayout inputView) {
        if (inputView != null)
            inputView.getEditText().setText("");
        tv_result.setVisibility(View.GONE);
        animationView.setVisibility(View.GONE);
        validateButton.setVisibility(View.VISIBLE);
        img_result.setVisibility(View.GONE);
    }

    TextWatcher textOneWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(inputFirstWord.isErrorEnabled()){
                inputFirstWord.setErrorEnabled(false);
            }

            if (charSequence.length() > 0)
                inputFirstWord.setHintTextColor(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.white)));
            else
                inputFirstWord.setHintTextColor(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.gray_800)));
            resetView(null);
        }

            @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher textTwoWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(inputSecondWord.isErrorEnabled()){
                inputSecondWord.setErrorEnabled(false);
            }
            if (charSequence.length() > 0)
                inputSecondWord.setHintTextColor(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.white)));
            else
                inputSecondWord.setHintTextColor(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.gray_800)));
            resetView(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    private void observeViewModel() {
        anagramValidateViewModel.checkAnagramResponse().observe(this,
                this::handleResult);
    }

    private void handleResult(AnagramValidationResponse anagramValidationResponse) {
        if (anagramValidationResponse.getError() != null) {
            switch (anagramValidationResponse.getError().getInput_type()) {
                case FIRST:
                    inputFirstWord.setError(getString(R.string.input_required));
                    validateButton.setVisibility(View.VISIBLE);
                    break;

                case SECOND:
                    inputSecondWord.setError(getString(R.string.input_required));
                    validateButton.setVisibility(View.VISIBLE);
                    break;
            }
            return;
        }

        if (anagramValidationResponse.isAnagram()) {
            tv_result.setVisibility(View.VISIBLE);
            tv_result.setBackground(ContextCompat.getDrawable(this,R.drawable.result_success_bg));
            tv_result.setText(R.string.its_an_anagram);
            img_result.setBackground(ContextCompat.getDrawable(this,R.drawable.ic_baseline_check_circle_24));
            img_result.setVisibility(View.VISIBLE);
            Util.runAnimation(animationView,
                    true,
                    this);
        } else {
            tv_result.setVisibility(View.VISIBLE);
            tv_result.setBackground(ContextCompat.getDrawable(this,R.drawable.result_fail_bg));
            tv_result.setText(R.string.anagram_false);
            img_result.setVisibility(View.VISIBLE);
            img_result.setBackground(ContextCompat.getDrawable(this,R.drawable.ic_baseline_red_cross_24));
            Util.runAnimation(animationView,
                    false,
                    this);
        }
    }



}