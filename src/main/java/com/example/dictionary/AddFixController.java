package com.example.dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;


public class AddFixController {
    @FXML
    private TextField id_add;
    @FXML
    private TextField id_addTV;

    private boolean ktadd = false;
    private boolean ktfix = false;

    public void goBack (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DictionaryView.fxml"));
        Parent scene1 = loader.load();
        Scene scene = new Scene(scene1);
        stage.setScene(scene);
    }

    public void addWord(ActionEvent event) {
        String target = id_add.getText();
        String explain = id_addTV.getText();
        if (target.isEmpty() || explain.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thêm từ không thành công!");
            alert.setContentText("Hãy nhập lại!");
            alert.show();
        } else {
            ktadd = DictionaryManagement.addNewWord(target,explain);
            if (ktadd) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Thêm từ thành công!");
                alert.setContentText(target + ": " + explain);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Thêm từ không thành công!");
                alert.setContentText("Hãy nhập lại!");
                alert.show();
            }
        }
    }

    public void fix(ActionEvent event) {
        String target = id_add.getText();
        String explain = id_addTV.getText();
        if (target.isEmpty() || explain.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Sửa từ không thành công!");
            alert.setContentText("Hãy nhập lại!");
            alert.show();
        } else {
            ktfix = DictionaryManagement.fixWord(target, explain);
            if (ktfix) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Sửa từ thành công!");
                alert.setContentText(target + ": " + explain);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Sửa từ không thành công!");
                alert.setContentText("Hãy nhập lại!");
                alert.show();
            }
        }
    }
}
