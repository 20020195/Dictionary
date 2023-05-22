package com.example.dictionary;


import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class DictionaryManagement {

    /**
     * Đọc dữ liệu từ file.
     */
    public static void insertFromFile (String directory) {
        try {
            Scanner scanner = new Scanner(new File(directory));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] arr = input.split("/");
                String[] arr1 = arr[arr.length - 1].split("-");
                String explain = arr1[1];
                explain = explain.trim();

                arr[0] = arr[0].trim();
                arr1[0] = arr1[0].trim();

                Word tem = new Word();
                tem.setWord_target(arr[0]);
                tem.setWord_explain(explain);
                tem.setWordClass(arr1[0]);

                String spell = "/";
                for (int j = 1; j < arr.length; j++) {
                    if (arr[j].contains(tem.getWordClass())) {
                        break;
                    } else {
                        spell += arr[j];
                    }
                    spell += "/";
                }
                tem.setWordSpelling(spell);
                Dictionary.addWord(tem);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file database.");
        }
    }

    /**
     * Tra trừ (sử dụng binarySearch).
     */
    public static String dictionaryLookup(String targetWord) {
        targetWord = targetWord.toLowerCase(Locale.ROOT);
        String explainWord = "...";
        int l = 0, r = Dictionary.arrayWord.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0)) {
                int n = m - 1;
                while (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0)) {
                    if (Dictionary.arrayWord.get(m).getWord_target().equals(targetWord)) {
                        explainWord = Dictionary.arrayWord.get(m).getWordSpelling() + "\n\n"
                                + Dictionary.arrayWord.get(m).getWordClass() + "\n\n"
                                + Dictionary.arrayWord.get(m).getWord_explain();
                        return explainWord;
                    }
                    m++;
                    if (m == Dictionary.arrayWord.size()) {
                        m = Dictionary.arrayWord.size() - 1;
                        break;
                    }
                }
                while (Dictionary.arrayWord.get(n).getWord_target().charAt(0) == targetWord.charAt(0)) {
                    if (Dictionary.arrayWord.get(n).getWord_target().equals(targetWord)) {
                        explainWord = Dictionary.arrayWord.get(n).getWordSpelling() + "\n\n"
                                + Dictionary.arrayWord.get(n).getWordClass() + "\n\n"
                                + Dictionary.arrayWord.get(n).getWord_explain();
                        return explainWord;
                    }
                    n--;
                    if (n < 0) {
                        n = 0;
                        break;
                    }
                }
                return explainWord;
            }

            if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) < targetWord.charAt(0)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return explainWord;
    }

    /**
     * Trả về danh sách từ gợi ý.
     */
    public static ArrayList<Word> dictionarySearcher(String targetWord) {
        targetWord = targetWord.toLowerCase(Locale.ROOT);
        ArrayList<Word> resultWord = new ArrayList<Word>();
        int l = 0, r = Dictionary.arrayWord.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0)) {
                while (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0)) {
                    m--;
                    if (m < 0) {
                        m = -1;
                        break;
                    }
                }
                m++;
                while (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0)) {
                    if (Dictionary.arrayWord.get(m).getWord_target().startsWith(targetWord)) {
                        resultWord.add(Dictionary.arrayWord.get(m));
                    }
                    m++;
                    if (m == Dictionary.arrayWord.size()) {
                        m = Dictionary.arrayWord.size() - 1;
                        break;
                    }
                }
                return resultWord;
            }

            if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) < targetWord.charAt(0)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return resultWord;
    }

    /**
     * Xóa từ.
     */
    public static boolean deleteWord(String targetWord) {
        targetWord = targetWord.toLowerCase(Locale.ROOT);
        int l = 0 , r = Dictionary.arrayWord.size() - 1;
        int index = 0;
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0)) {
                while (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0)) {
                    m--;
                    if (m < 0) {
                        m = -1;
                        break;
                    }
                }
                m++;
                while (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0) ) {
                    if (Dictionary.arrayWord.get(m).getWord_target().equals(targetWord)) {
                        index = m;
                        break;
                    }
                    m++;
                    if (m == Dictionary.arrayWord.size()) {
                        m = Dictionary.arrayWord.size() - 1;
                        return false;
                    }
                    if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) != targetWord.charAt(0)) {
                        return false;
                    }
                }
                break;
            }
            if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) < targetWord.charAt(0)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        Dictionary.arrayWord.remove(index);
        dictionaryExportToFile("src/main/resources/data/Dictionary.txt");
        return true;
    }

    /**
     * Sửa từ.
     */
    public static boolean fixWord(String targetWord, String explainWord) {
        targetWord = targetWord.toLowerCase(Locale.ROOT);
        int l = 0, r = Dictionary.arrayWord.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0)) {
                int n = m - 1;
                while (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0)) {
                    if (Dictionary.arrayWord.get(m).getWord_target().equals(targetWord)) {
                        Dictionary.arrayWord.get(m).setWord_explain(explainWord);
                        dictionaryExportToFile("src/main/resources/data/Dictionary.txt");
                        return true;
                    }
                    m++;
                    if (m == Dictionary.arrayWord.size()) {
                        m = Dictionary.arrayWord.size() - 1;
                        break;
                    }
                }
                while (Dictionary.arrayWord.get(n).getWord_target().charAt(0) == targetWord.charAt(0)) {
                    if (Dictionary.arrayWord.get(n).getWord_target().equals(targetWord)) {
                        Dictionary.arrayWord.get(n).setWord_explain(explainWord);
                        dictionaryExportToFile("src/main/resources/data/Dictionary.txt");
                        return true;
                    }
                    n--;
                    if (n < 0) {
                        n = 0;
                        break;
                    }
                }
                return false;
            }

            if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) < targetWord.charAt(0)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }

    /**
     * Thêm từ mới.
     */
    public static boolean addNewWord(String targetWord, String explainWord) {
        targetWord = targetWord.toLowerCase(Locale.ROOT);
        boolean check = true;
        int index = 0;
        Word newWord = new Word(targetWord,"/null/", "# null" , explainWord);
        if (targetWord.compareTo(Dictionary.arrayWord.get(0).getWord_target()) < 0) {
            Dictionary.arrayWord.add(0, newWord);
            dictionaryExportToFile("src/main/resources/data/Dictionary.txt");
            return true;
        } else if (targetWord.compareTo(Dictionary.arrayWord.get(Dictionary.arrayWord.size() - 1).getWord_target()) > 0) {
            Dictionary.arrayWord.add(newWord);
            dictionaryExportToFile("src/main/resources/data/Dictionary.txt");
            return true;
        } else {
            int l = 0, r = Dictionary.arrayWord.size() - 1;
            int m = l + (r - l) / 2;
            while (l <= r) {
                m = l + (r - l) / 2;
                if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) == targetWord.charAt(0)) {
                    index = m;
                    break;
                }

                if (Dictionary.arrayWord.get(m).getWord_target().charAt(0) < targetWord.charAt(0)) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            while (true) {
                String s1 = Dictionary.arrayWord.get(index).getWord_target();
                String s2 = Dictionary.arrayWord.get(index + 1).getWord_target();
                if (s1.compareTo(targetWord) < 0 && targetWord.compareTo(s2) < 0) {
                    Dictionary.arrayWord.add(index+1, newWord);
                    dictionaryExportToFile("src/main/resources/data/Dictionary.txt");
                    return true;
                }
                if (targetWord.equals(s1) == true || targetWord.equals(s2) == true) {
                    return false;
                }
                if (targetWord.compareTo(s1) > 0) {
                    index ++;
                } else {
                    index--;
                }
            }
        }
    }


    /**
     * Xuất dữ liệu ra file.
     */
    public static void dictionaryExportToFile(String path) {
        try {
            File f = new File(path);
            FileWriter fw = new FileWriter(f);

            for (int i = 0; i < Dictionary.arrayWord.size(); i++) {
                fw.write(Dictionary.arrayWord.get(i).getWord_target());
                fw.write("\t");
                fw.write(Dictionary.arrayWord.get(i).getWordSpelling());
                fw.write("\t");
                fw.write(Dictionary.arrayWord.get(i).getWordClass());
                fw.write("\t-\t");
                fw.write(Dictionary.arrayWord.get(i).getWord_explain());
                fw.write("\n");
            }

            fw.close();
        } catch (IOException ex) {
            System.out.println("Lỗi ghi file: " + ex);
        }
    }
}
