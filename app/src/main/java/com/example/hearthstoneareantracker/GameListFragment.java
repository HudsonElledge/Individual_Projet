package com.example.hearthstoneareantracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;


public class GameListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private Game mGame;
    private RecyclerView mRunRecylcerView;
    private RunAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_run_list,container,false);

        mRunRecylcerView = (RecyclerView)view
                .findViewById(R.id.run_recycler_view);
        mRunRecylcerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_run_list,menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if(mSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        }else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.new_game:
                Game game = new Game();
                MatchLab.get(getActivity()).addGame(game);
                Intent intent = GamePagerActivity.newIntent(getActivity(),game.getID());
                startActivity(intent);
                return true;

            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle(){
        MatchLab matchLab = MatchLab.get(getActivity());
        int gameCount = matchLab.getGames().size();
        String subtitle = getString(R.string.subtitle_format, gameCount);

        if (!mSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }



    private void updateUI(){
        MatchLab matchLab = MatchLab.get(getActivity());
        List<Game> games = matchLab.getGames();

        if (mAdapter ==null) {
            mAdapter = new RunAdapter(games);
            mRunRecylcerView.setAdapter(mAdapter);
        }else{
            mAdapter.setGames(games);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();

    }



    private class RunHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTITLE;
        private TextView mDateTextView;

        public RunHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_game,parent,false));
            itemView.setOnClickListener(this);

            mTITLE = (TextView) itemView.findViewById(R.id.your_class);
            mDateTextView  = (TextView) itemView.findViewById(R.id.rgame_date);

        }

        public void bind(Game game){
            mGame = game;
            mTITLE.setText(mGame.getTitle());
            mDateTextView.setText(mGame.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Intent intent = GamePagerActivity.newIntent(getActivity(), mGame.getID());
            startActivity(intent);
        }


    }

    private class RunAdapter extends RecyclerView.Adapter<RunHolder>{

        private List<Game> mGames;

        public RunAdapter(List<Game> games){
            mGames = games;
        }



        @Override
        public RunHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new RunHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(RunHolder holder, int position){
            Game game = mGames.get(position);
            holder.bind(game);

        }

        @Override
        public int getItemCount(){
            return mGames.size();
        }

        public void setGames(List<Game> games){
            mGames = games;
        }



    }



}
