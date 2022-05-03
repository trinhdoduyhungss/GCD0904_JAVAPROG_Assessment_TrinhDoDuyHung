package com.example.asm;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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

    public void viewItemDetail(String name, String location, String date, Integer price, Integer indexEdit) {
        try{
            Pane pnlItemDetailXML = FXMLLoader.load(getClass().getResource("ItemDetail.fxml"));
            ((TextField) pnlItemDetailXML.lookup("#boxName")).setText(name);
            ((TextField) pnlItemDetailXML.lookup("#boxLocation")).setText(location);
            ((TextField) pnlItemDetailXML.lookup("#boxPrice")).setText("$"+price.toString());
            ((TextField) pnlItemDetailXML.lookup("#boxDate")).setText(date);
            ((Button) pnlItemDetailXML.lookup("#btnItemEdit")).setOnAction(e->{
                OverviewController ov = new OverviewController();
                ov.updateItem(indexEdit, ((TextField) pnlItemDetailXML.lookup("#boxName")).getText(), ((TextField) pnlItemDetailXML.lookup("#boxLocation")).getText(), ((TextField) pnlItemDetailXML.lookup("#boxDate")).getText(), ((TextField) pnlItemDetailXML.lookup("#boxPrice")).getText());
                pnlOverviewStatic.toFront();
            });
            pnlItemDetailStatic.getChildren().add(pnlItemDetailXML);
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
                break;
            case "Button[id=btnQuit, styleClass=button]'Quit'" :
                Platform.exit();
                break;
        }
    }
}