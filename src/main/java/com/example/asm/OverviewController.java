package com.example.asm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class OverviewController implements Initializable {

    @FXML
    public VBox pnlItems;

    @FXML
    private ScrollPane splItems;

    public static VBox pnlItemsStatic;

    private Integer indexDelete = -1;

    public static Store myStoreData = new Store();

    FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser.setInitialDirectory(new File("F:\\Academic\\Specialize\\JAVA\\ASM\\src\\main\\resources\\com\\example\\asm\\Data"));
        if(pnlItems != null){
            RenderData();
        }
    }

    public String formatDate(LocalDate date){
        String dateFormated = date.toString();
        dateFormated = dateFormated.substring(8, 10) + "/" + dateFormated.substring(5, 7) + "/" + dateFormated.substring(0, 4);
        return dateFormated;
    }

    public void addNewItem(){
        Item item = new Item("Item " + (myStoreData.getStoreLengtḥ()+1), "Vietnam", formatDate(LocalDate.now()), Math.toIntExact(Math.round(Math.random() * 100 + 100)), myStoreData.getStoreLengtḥ()+1);
        myStoreData.addNew(item, pnlItems);
        splItems.vvalueProperty().bind(pnlItems.heightProperty());
    }

    public void deleteItem(Integer index){
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

    public void RenderData(){
        File file = fileChooser.showOpenDialog(new Stage());
        try{
            Scanner scr = new Scanner(file);
            Integer indexItem = 0;
            pnlItems.getChildren().clear();
            myStoreData.clear();
            while (scr.hasNextLine()){
                String[] lineData = scr.nextLine().split(", ");
                Item item = new Item(lineData[0], lineData[1], lineData[2], Integer.valueOf(lineData[3]), indexItem);
                myStoreData.add(item);
                indexItem += 1;
            }
            myStoreData.sortData();
            pnlItemsStatic = myStoreData.RenderOverviewData(pnlItems);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void ExportData(File fileSave){
        try{
            PrintWriter printWriter = new PrintWriter(fileSave);
            printWriter.write(myStoreData.toString());
            printWriter.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        String action = actionEvent.getSource().toString();
        if (action.equals("Button[id=btnImportItem, styleClass=button]'+'")) {
            addNewItem();
        }
        if (action.equals("Button[id=btnImportData, styleClass=button]'Import data'")) {
            RenderData();
        }
    }
}