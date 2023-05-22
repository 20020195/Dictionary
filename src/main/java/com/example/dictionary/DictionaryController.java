package com.example.dictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
    @FXML
    private TextArea output;
    @FXML
    private TextField id_search;
    @FXML
    private TableView<Word> table;
    @FXML
    private TableColumn<Word, String> targetColumn;

    private ObservableList<Word> wordList;
    private ObservableList<Word> hisList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordList = FXCollections.observableArrayList();
        hisList = FXCollections.observableArrayList();

        targetColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        table.setItems(wordList);
    }

    public void search (Event event) {
        wordList.clear();
        String inp = id_search.getText();
        output.setText(DictionaryManagement.dictionaryLookup(inp));

        for (int i = 0; i < DictionaryManagement.dictionarySearcher(inp).size(); i++) {
            wordList.add(DictionaryManagement.dictionarySearcher(inp).get(i));
        }

        if (!output.getText().equals("...")) hisList.add(table.getItems().get(0));
    }

    public void speech (ActionEvent event) {
        String inp = id_search.getText();
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        voice.speak(inp);
    }

    public void delete (ActionEvent event) {
        String inp = id_search.getText();
        if (inp.equals("")) {
            output.setText("Hãy nhập từ!!");
        } else {
            if (DictionaryManagement.deleteWord(inp) == true) {
                output.setText("Đã xóa từ " + inp);
            } else output.setText("Từ này không có trong từ điển");
        }
    }

    public void add (ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddFixView.fxml"));
        Parent addFix = loader.load();
        Scene addFixScene = new Scene(addFix);
        stage.setScene(addFixScene);
    }

    public void history (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HistoryView.fxml"));
        Parent his = loader.load();
        HistoryController controller = loader.getController();
        controller.setHisTable(hisList);
        Stage stage = new Stage();
        stage.setScene(new Scene(his));
        stage.show();
        controller.scrollToEnd(hisList);
    }

    @FXML
    public void click() {
        id_search.setText(table.getSelectionModel().getSelectedItem().getWord_target());
        output.setText(DictionaryManagement.dictionaryLookup(id_search.getText()));
        hisList.add(table.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void showAll (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAllView.fxml"));
        Parent showAll = loader.load();
        ShowAllController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Danh sách toàn bộ các từ");
        stage.setScene(new Scene(showAll));
        stage.show();
    }
}
