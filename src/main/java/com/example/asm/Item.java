package com.example.asm;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import java.time.LocalDate;

public class Item implements Comparable<Item>{
    private String itemName;
    private String itemLocation;
    private String itemDate;
    private Integer itemPrice;
    private Integer indexItem;

    public Item(String itemName, String itemLocation, String itemDate, Integer itemPrice, Integer indexItem) {
        this.itemName = itemName;
        this.itemLocation = itemLocation;
        if(isDateValid(itemDate)) {
            this.itemDate = itemDate;
        }else{
            this.itemDate = "";
        }
        this.itemPrice = itemPrice;
        this.indexItem = indexItem;
    }

    public Node RenderItem(){
        try{
            Node node = FXMLLoader.load(getClass().getResource("Item.fxml"));
            ((Label) node.lookup("#itemName")).setText(this.itemName);
            ((Label) node.lookup("#itemLocation")).setText(this.itemLocation);
            ((Label) node.lookup("#itemDate")).setText(this.itemDate);
            ((Label) node.lookup("#itemPrice")).setText("$"+Integer.toString(this.itemPrice));
            ((Button) node.lookup("#btnItemDelete")).setOnAction(e->{
                OverviewController ov = new OverviewController();
                ov.deleteItem(this.indexItem);
            });
            node.setOnMouseEntered(event -> {
                node.setStyle("-fx-background-color : #0A0E3F");
            });
            node.setOnMouseExited(event -> {
                node.setStyle("-fx-background-color : #02030A");
            });
            node.setOnMouseClicked(e -> {
                HelloController hc = new HelloController();
                hc.viewItemDetail(this.itemName, this.itemLocation, this.itemDate, this.itemPrice, this.indexItem);
            });
            return node;
        }catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public Integer getIndexItem() {
        return indexItem;
    }

    private boolean isDateValid(String date) {
        return date.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    private boolean isNumberValid(String number) {
        return number.matches("\\d+");
    }

    public void updateItem(String itemName, String itemLocation, String itemDate, String itemPrice) {
        this.itemName = itemName;
        this.itemLocation = itemLocation;
        if(isDateValid(itemDate)) {
            this.itemDate = itemDate;
        }else{
            this.itemDate = "";
        }
        itemPrice = itemPrice.replace("$", "");
        if(isNumberValid(itemPrice)) {
            this.itemPrice = Integer.parseInt(itemPrice);
        }else{
            this.itemPrice = 0;
        }
    }

    @Override
    public int compareTo(Item item) {
        return item.getItemPrice()-this.itemPrice;
    }

    @Override
    public String toString() {
        return itemName+", "+itemLocation+", "+itemDate+", "+itemPrice;
    }
}
