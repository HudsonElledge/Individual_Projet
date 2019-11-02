package com.example.hearthstoneareantracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;


public class GameListFragment extends Fragment {

    private Game mGame;
    private RecyclerView mRunRecylcerView;
    private RunAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_run_list,container,false);

        mRunRecylcerView = (RecyclerView)view
                .findViewById(R.id.run_recycler_view);
        mRunRecylcerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        MatchLab matchLab = MatchLab.get(getActivity());
        List<Game> games = matchLab.getGames();

        if (mAdapter ==null) {
            mAdapter = new RunAdapter(games);
            mRunRecylcerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }

    }



    private class RunHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mOpponentsClass;
        private TextView mDateTextView;

        public RunHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_game,parent,false));
            itemView.setOnClickListener(this);

            mOpponentsClass = (TextView) itemView.findViewById(R.id.opponents_class);
            mDateTextView  = (TextView) itemView.findViewById(R.id.rgame_date);

        }

        public void bind(Game game){
            mGame = game;
            mOpponentsClass.setText(mGame.getEnemyClass());
            mDateTextView.setText(mGame.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Intent intent = RunActivity.newIntent(getActivity() , mGame.getID());
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



    }



}
