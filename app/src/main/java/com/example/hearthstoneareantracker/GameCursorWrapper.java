package com.example.hearthstoneareantracker;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

public class GameCursorWrapper extends CursorWrapper {
    public GameCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Game getGame(){
        String uuidString = getString(getColumnIndex(GameDbSchema.GameTable.Cols.UUID));
        String title = getString(getColumnIndex(GameDbSchema.GameTable.Cols.TITLE));
        long date = getLong(getColumnIndex(GameDbSchema.GameTable.Cols.DATE));
        int isWin = getInt(getColumnIndex(GameDbSchema.GameTable.Cols.WINS));

        Game game = new Game(UUID.fromString(uuidString));
        game.setTitle(title);
        game.setDate(new Date(date));
        game.setWin(isWin != 0);

        return game;
    }
}
