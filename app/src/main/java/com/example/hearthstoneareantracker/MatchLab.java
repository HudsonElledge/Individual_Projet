package com.example.hearthstoneareantracker;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MatchLab {
    private static MatchLab sMatchLab;
    private List<Game> mGames;

    public static MatchLab get(Context context){
        if (sMatchLab == null){
            sMatchLab = new MatchLab(context);
        }
        return sMatchLab;
    }
    private MatchLab(Context context){
        mGames = new ArrayList<>();
        for (int i=0; i<100;i++){
            Game game = new Game();
            game.setTitle("Game #"+i);
            game.setEnemyClass("Druid");
            game.setWin(i % 2 == 0);
            mGames.add(game);
        }

    }

    public List<Game> getGames(){
        return mGames;
    }

    public Game getGame(UUID id){
        for (Game game:mGames){
            if (game.getID().equals(id)){
                return game;
            }
        }
        return null;
    }

}
