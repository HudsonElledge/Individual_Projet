package com.example.hearthstoneareantracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class RunActivity extends SingleFragmentActivity {

    public  static final String EXTRA_GAME_ID =
            "com.example..hearthstoneareantracker.match_id";

    public static Intent newIntent(Context packageContext, UUID crimeID){
        Intent intent = new Intent(packageContext, RunActivity.class);
        intent.putExtra(EXTRA_GAME_ID, crimeID);
        return intent;
    }

    @Override
    protected Fragment createFragment(){

        UUID gameID = (UUID) getIntent()
                .getSerializableExtra(EXTRA_GAME_ID);
        return MatchFragment.newInstance(gameID);
    }
}
