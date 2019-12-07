package com.example.hearthstoneareantracker;

public class GameDbSchema {
    public static final class GameTable{
        public static final String NAME = "games";

        public static final class Cols{

            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String WINS = "win";

        }
    }
}
