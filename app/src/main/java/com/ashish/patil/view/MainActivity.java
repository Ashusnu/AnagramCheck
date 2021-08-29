package com.ashish.patil.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ashish.patil.R;
import com.ashish.patil.enums.INPUT_TYPE;
import com.ashish.patil.model.AnagramValidationResponse;
import com.ashish.patil.util.Util;
import com.ashish.patil.viewModels.AnagramValidateViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    AnagramValidateViewModel anagramValidateViewModel;
    Button      validateButton;
    EditText    inputFirstWord,inputSecondWord;
    TextView    output_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validateButton       =       findViewById(R.id.btn_validate);
        inputFirstWord       =       findViewById(R.id.etFirstWordInput);
        inputSecondWord      =       findViewById(R.id.etSecondWordInput);
        output_txt           =       findViewById(R.id.output);

        validateButton.setOnClickListener(validateListener);

        anagramValidateViewModel =
                ViewModelProviders.of(this).get(AnagramValidateViewModel.class);

        observeViewModel();
    }

    View.OnClickListener validateListener = view -> {
        anagramValidateViewModel.validateIfAnagrams(inputFirstWord.getText().toString(),
                inputSecondWord.getText().toString());
        Util.hideSoftKeyBoard(this);
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
                    break;

                case SECOND:
                    inputSecondWord.setError(getString(R.string.input_required));
                    break;
            }
        }

        if (anagramValidationResponse.isAnagram()) {
            output_txt.setText(R.string.anagram_true);
        } else {
            output_txt.setText(R.string.anagram_false);
        }


    }
}