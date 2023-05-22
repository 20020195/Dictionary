package com.example.dictionary;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistoryController {
    @FXML
    private TableView<Word> histable;
    @FXML
    private TableColumn<Word, String> engColumn;
    @FXML
    private TableColumn<Word, String> vnColoumn;

    public void setHisTable( ObservableList<Word> hisList){
        engColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        vnColoumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        histable.setItems(hisList);
    }

    public void scrollToEnd(ObservableList<Word> hisList) {
        histable.scrollTo(hisList.size());
    }
}
