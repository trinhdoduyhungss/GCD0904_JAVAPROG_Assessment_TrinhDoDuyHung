package com.example.asm;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlItemDetail;

    public static Pane pnlOverviewStatic;
    
    public static Pane pnlItemDetailStatic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pnlItemDetailStatic = pnlItemDetail;
        pnlOverviewStatic = pnlOverview;
    }

    public void showAlert(String title, String header, String body){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(body);
        alert.showAndWait();
    }

    public void viewItemDetail(String name, String location, String date, Integer price, Integer indexEdit) {
        try{
            Pane pnlItemDetailXML = FXMLLoader.load(getClass().getResource("ItemDetail.fxml"));
            ((TextField) pnlItemDetailXML.lookup("#boxName")).setText(name);
            ((TextField) pnlItemDetailXML.lookup("#boxLocation")).setText(location);
            ((TextField) pnlItemDetailXML.lookup("#boxPrice")).setText("$"+price.toString());
            ((TextField) pnlItemDetailXML.lookup("#boxDate")).setText(date);
            ((Button) pnlItemDetailXML.lookup("#btnItemEdit")).setOnAction(e->{
                String textName = ((TextField) pnlItemDetailXML.lookup("#boxName")).getText();
                if(((TextField) pnlItemDetailXML.lookup("#boxName")).getText().isEmpty()){
                    ((TextField) pnlItemDetailXML.lookup("#boxName")).setStyle("-fx-border-color: red");
                    showAlert("Check your inputs", "Name is empty", "Please enter a name");
                }
                else if(((TextField) pnlItemDetailXML.lookup("#boxLocation")).getText().isEmpty()){
                    ((TextField) pnlItemDetailXML.lookup("#boxLocation")).setStyle("-fx-border-color: red");
                    showAlert("Check your inputs", "Location is empty", "Please enter a location");
                }
                else if(((TextField) pnlItemDetailXML.lookup("#boxPrice")).getText().isEmpty() || !((TextField) pnlItemDetailXML.lookup("#boxPrice")).getText().replace("$", "").matches("\\d+")){
                    ((TextField) pnlItemDetailXML.lookup("#boxPrice")).setStyle("-fx-border-color: red");
                    showAlert("Check your inputs", "Price is empty or not a number", "Please enter a price");
                }
                else if(((TextField) pnlItemDetailXML.lookup("#boxDate")).getText().isEmpty() || !((TextField) pnlItemDetailXML.lookup("#boxDate")).getText().matches("\\d{2}/\\d{2}/\\d{4}")){
                    ((TextField) pnlItemDetailXML.lookup("#boxDate")).setStyle("-fx-border-color: red");
                    showAlert("Check your inputs", "Date is empty or not has format dd/mm/yyyy", "Please enter a date");
                }
                else{
                    OverviewController ov = new OverviewController();
                    ov.updateItem(indexEdit, ((TextField) pnlItemDetailXML.lookup("#boxName")).getText(), ((TextField) pnlItemDetailXML.lookup("#boxLocation")).getText(), ((TextField) pnlItemDetailXML.lookup("#boxDate")).getText(), ((TextField) pnlItemDetailXML.lookup("#boxPrice")).getText());
                    pnlOverviewStatic.setStyle("-fx-background-color : #02030A");
                    pnlOverviewStatic.toFront();
                }
            });
            pnlItemDetailStatic.getChildren().add(pnlItemDetailXML);
            pnlOverviewStatic.setStyle("-fx-background-color : #02030A");
            pnlItemDetailStatic.toFront();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void handleClicks(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource().toString());
        switch(actionEvent.getSource().toString()) {
            case "Button[id=btnOverview, styleClass=button]'Overview data'" :
                pnlOverview.setStyle("-fx-background-color : #02030A");
                pnlOverview.toFront();
                break;
            case "Button[id=btnItemDetail, styleClass=button]'Item detail'" :
                pnlItemDetail.setStyle("-fx-background-color : #53639F");
                pnlItemDetail.toFront();
                break;
            case "Button[id=btnSaveData, styleClass=button]'Save data'" :
                OverviewController ov = new OverviewController();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                File file = fileChooser.showSaveDialog(new Stage());
                if(file != null){
                    ov.ExportData(file);
                }
                break;
            case "Button[id=btnQuit, styleClass=button]'Quit'" :
                Platform.exit();
                break;
        }
    }
}