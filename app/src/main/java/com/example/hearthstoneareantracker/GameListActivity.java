package com.example.hearthstoneareantracker;

import androidx.fragment.app.Fragment;

public class GameListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){

        return new GameListFragment();
    }

}
