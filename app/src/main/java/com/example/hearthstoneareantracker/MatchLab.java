package com.example.hearthstoneareantracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MatchLab {
    private static MatchLab sMatchLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MatchLab get(Context context){
        if (sMatchLab == null){
            sMatchLab = new MatchLab(context);
        }
        return sMatchLab;
    }
    private MatchLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new GameBaseHelper(mContext).getWritableDatabase();


    }

    public void addGame(Game g){
        ContentValues values = getContentValues(g);

        mDatabase.insert(GameDbSchema.GameTable.NAME,null,values);
    }

    public List<Game> getGames(){
        List<Game> games = new ArrayList<>();

        GameCursorWrapper cursor = queryGames(null,null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                games.add(cursor.getGame());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return games;
    }

    public Game getGame(UUID id){

        GameCursorWrapper cursor = queryGames(
                GameDbSchema.GameTable.Cols.UUID + " = ?", new String[] {id.toString()}
        );

        try {
            if(cursor.getCount()==0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getGame();
        }finally {
            cursor.close();
        }
    }

    public void updateGame(Game game ){
        String uuidString = game.getID().toString();
        ContentValues values = getContentValues(game);

        mDatabase.update(GameDbSchema.GameTable.NAME, values, GameDbSchema.GameTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private GameCursorWrapper queryGames(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                GameDbSchema.GameTable.NAME, null, whereClause, whereArgs, null,null,null
        );
        return new GameCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Game game){
        ContentValues values = new ContentValues();
        values.put(GameDbSchema.GameTable.Cols.UUID, game.getID().toString());
        values.put(GameDbSchema.GameTable.Cols.TITLE,game.getTitle());
        values.put(GameDbSchema.GameTable.Cols.DATE,game.getDate().getTime());
        values.put(GameDbSchema.GameTable.Cols.WINS,game.isWin() ? 1 :0);

        return values;
    }

}
