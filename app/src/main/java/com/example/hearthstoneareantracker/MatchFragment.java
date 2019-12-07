package com.example.hearthstoneareantracker;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;
import java.util.UUID;


public class MatchFragment extends Fragment {

    private static final String ARG_MATCHID = "match_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private Game mGame;
    private EditText mTitleField;
    private Button mDateButton;
    private Button getmDateButton;
    private CheckBox mSolvedCheckBox;

    public static MatchFragment newInstance(UUID gameID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_MATCHID,gameID);

        MatchFragment fragment = new MatchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID gameID = (UUID) getArguments().getSerializable(ARG_MATCHID);
        mGame = MatchLab.get(getActivity()).getGame(gameID);
    }

    @Override
    public void onPause(){
        super.onPause();

        MatchLab.get(getActivity()).updateGame(mGame);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_game,container,false);


        mTitleField = (EditText) v.findViewById(R.id.opponents_class);
        mTitleField.setText(mGame.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mGame.setTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton = (Button) v.findViewById(R.id.rgame_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mGame.getDate());
                dialog.setTargetFragment(MatchFragment.this,REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
            }
        });

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.match_win);
        mSolvedCheckBox.setChecked(mGame.isWin());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                mGame.setWin(isChecked);
            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mGame.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mGame.getDate().toString());
    }

}
