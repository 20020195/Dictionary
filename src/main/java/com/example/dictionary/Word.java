package com.example.dictionary;

public class Word {
    private String word_target;
    private String word_explain;
    private String wordSpelling;
    private String wordClass;

    public Word(String wordTarget, String wordSpelling, String wordClass, String wordExplain) {
        this.word_target = wordTarget;
        this.word_explain = wordExplain;
        this.wordSpelling = wordSpelling;
        this.wordClass = wordClass;
    }
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word() {

    }

    public String getWord_explain() {
        return word_explain;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWordSpelling() { return wordSpelling; }

    public void setWordSpelling(String wordSpelling) { this.wordSpelling = wordSpelling; }

    public String getWordClass() { return wordClass; }

    public void setWordClass(String wordClass) { this.wordClass = wordClass; }
}