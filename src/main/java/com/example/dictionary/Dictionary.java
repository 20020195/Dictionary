package com.example.dictionary;

import java.util.ArrayList;

public class Dictionary {
    public static ArrayList<Word> arrayWord = new ArrayList<Word>();
    public static void  addWord(Word w){
        arrayWord.add(w);
    }
    public ArrayList<Word> getArrayWord(){
        return arrayWord;
    }
}

