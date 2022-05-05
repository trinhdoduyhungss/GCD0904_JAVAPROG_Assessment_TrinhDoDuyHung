package com.example.asm;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Store {
    private List<Item> myItems = new ArrayList<Item>();

    public Store(){
    }

    public int getStoreLengtḥ(){
        return this.myItems.size();
    }

    public void addNew(Item item, VBox pnItems){
        myItems.add(item);
        pnItems.getChildren().add(item.RenderItem());
    }

    public void add(Item item){
        myItems.add(item);
    }

    public void delete(Integer indexItem, VBox pnItems){
        pnItems.getChildren().clear();
        Integer itemDelete = -1;
        for (int i = 0; i < myItems.size(); i++) {
            if(myItems.get(i).getIndexItem() == indexItem){
                itemDelete = i;
                break;
            }
        }
        if(itemDelete >= 0){
            myItems.remove((int) itemDelete);
            sortData();
        }
    }

    public void clear(){
        myItems.clear();
    }

    public void sortData(){
        Collections.sort(this.myItems);
    }

    public Item getItem(Integer indexItem){
        for (int i = 0; i < myItems.size(); i++) {
            if(myItems.get(i).getIndexItem() == indexItem){
                return myItems.get(i);
            }
        }
        return null;
    }

    public Integer getTotalPrice(){
        Integer totalPrice = 0;
        for (int i = 0; i < myItems.size(); i++) {
            totalPrice += myItems.get(i).getItemPrice();
        }
        return totalPrice;
    }

    public Integer getTotalOrders(){
        return myItems.size();
    }

    @Override
    public String toString() {
        String multiLineData = "";
        for (int i = 0; i < myItems.size(); i++) {
            multiLineData += myItems.get(i).toString() + "\n";
        }
        return multiLineData;
    }

    public VBox RenderOverviewData(VBox pnItems){
        for(Item item: myItems){
            pnItems.getChildren().add(item.RenderItem());
        }
        return pnItems;
    }
}
