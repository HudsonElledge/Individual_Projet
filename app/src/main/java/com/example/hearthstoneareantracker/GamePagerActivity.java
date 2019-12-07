package com.example.hearthstoneareantracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class GamePagerActivity extends AppCompatActivity {

    private static final String EXTRA_GAME_ID = "com.example.hearthstoneareantracker.game_id";



    private ViewPager mViewPager;
    private List<Game> mGames;

    public static Intent newIntent(Context packageContext, UUID gameID){
        Intent intent = new Intent(packageContext, GamePagerActivity.class);
        intent.putExtra(EXTRA_GAME_ID,gameID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_pager);

        UUID gameID = (UUID) getIntent().getSerializableExtra(EXTRA_GAME_ID);

        mViewPager = (ViewPager) findViewById(R.id.game_view_pager);

        mGames = MatchLab.get(this).getGames();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Game game = mGames.get(position);
                return MatchFragment.newInstance(game.getID());
            }

            @Override
            public int getCount() {
                return mGames.size();
            }
        });

        for (int i = 0; i<mGames.size(); i++){
            if (mGames.get(i).getID().equals(gameID)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
