package com.example.asm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {

    @FXML
    public VBox pnlItems;

    @FXML
    private ScrollPane splItems;

    public static VBox pnlItemsStatic;

    private Integer indexDelete = -1;

    public static Store myStoreData = new Store();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(pnlItems != null){
            for (int i = 0; i < 10; i++) {
                Item item = new Item("Item " + i, "Vietnam", LocalDate.now(), Math.toIntExact(Math.round(Math.random() * 100 + 100)), i);
                myStoreData.add(item);
            }
            myStoreData.sortData();
            pnlItemsStatic = myStoreData.RenderOverviewData(pnlItems);
        }
    }

    public void addNewItem(){
        Item item = new Item("Item " + (myStoreData.getStoreLengtḥ()+1), "Vietnam", LocalDate.now(), Math.toIntExact(Math.round(Math.random() * 100 + 100)), myStoreData.getStoreLengtḥ()+1);
        myStoreData.addNew(item, pnlItems);
        splItems.vvalueProperty().bind(pnlItems.heightProperty());
    }

    public void deleteItem(Integer index){
        System.out.println(pnlItemsStatic.toString());
        System.out.println(myStoreData.toString());
        indexDelete = index;
        myStoreData.delete(indexDelete, pnlItemsStatic);
        indexDelete = -1;
        pnlItemsStatic = myStoreData.RenderOverviewData(pnlItemsStatic);
    }

    public void updateItem(Integer index, String name, String location, String date, String price){
        myStoreData.getItem(index).updateItem(name, location, date, price);
        pnlItemsStatic.getChildren().clear();
        pnlItemsStatic = myStoreData.RenderOverviewData(pnlItemsStatic);        
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        String action = actionEvent.getSource().toString();
        if (action.equals("Button[id=btnImportItem, styleClass=button]'+'")) {
            addNewItem();
        }
        if (action.equals("Button[id=btnImportData, styleClass=button]'Import data'")) {

        }
    }
}