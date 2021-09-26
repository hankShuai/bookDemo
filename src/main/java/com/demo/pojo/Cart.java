package com.demo.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
   // private Integer totalCount;
    //private BigDecimal totalPrice;
    private Map<Integer,CartItem> items = new HashMap<>();

    public void  addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if(item==null){
            items.put(cartItem.getId(),cartItem);
        }else {
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    public void  delItem(Integer id){
        items.remove(id);
    }
    public void  clear(){
        items.clear();
    }
    public void  updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount =0;
        for(Map.Entry<Integer,CartItem> entry :items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
         BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry :items.entrySet()){
           totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }


}
