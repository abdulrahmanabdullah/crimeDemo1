package com.bignerdranch.abdulrahman.criminalintent.dataBase;

public class CrimeDbScheme {

    // table name
    public static final class CrimeTable{
        public static final String NAME ="crimes";
        // columns name
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }

}
