package com.example.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAllController implements Initializable {
    @FXML
    private TableView<Word> showAllTable;
    @FXML
    private TableColumn<Word, String> engColumn;
    @FXML
    private TableColumn<Word, String> spellColoumn;
    @FXML
    private TableColumn<Word, String> classColumn;
    @FXML
    private TableColumn<Word, String> vnColoumn;

    private ObservableList<Word> wordList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordList = FXCollections.observableArrayList();
        engColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        spellColoumn.setCellValueFactory(new PropertyValueFactory<Word, String>("wordSpelling"));
        classColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("wordClass"));
        vnColoumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));

        for (int i = 0; i < Dictionary.arrayWord.size(); i++) {
            wordList.add(Dictionary.arrayWord.get(i));
        }

        showAllTable.setItems(wordList);
    }
}
