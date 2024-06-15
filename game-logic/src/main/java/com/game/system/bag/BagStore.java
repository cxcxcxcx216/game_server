package com.game.system.bag;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Data
@Slf4j
public class BagStore {

    private Map<Long,Item> itemMap = new ConcurrentHashMap<>();


    public void addItem(int itemId, int count){
        List<Item> items = new ArrayList<>();
        itemMap.forEach((id,item)->{
            if (item.getItemId()==itemId) {
                items.add(item);
            }
        });

        if(items.size()>0){
            for (Item item : items) {
                if (item.getCount() + count < 100) {
                    item.setCount(99);
                }else {
                    int i = 99 - item.getCount();//还能放的数量
                    item.setCount(99);
                    count -= i;
                }
                if(count<=0)
                    break;
            }
        }

        while (count>0){
            if(count > 99){
                Item item = Item.createItem(itemId, 99);
                itemMap.put(item.getId(),item);
                count -= 99;
            }else {
                Item item = Item.createItem(itemId, count);
                itemMap.put(item.getId(),item);
                count = 0;
            }

        }
    }


    public void removeItem(int itemId, int count){

        if(!enough(itemId,count)){
            log.error("道具数量不够");
            return;
        }

        List<Item> items = new ArrayList<>();
        itemMap.forEach((id,item)->{
            if (item.getItemId()==itemId) {
                items.add(item);
            }
        });

        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return (int) (o1.getTime() - o2.getTime());
            }
        });

        while (count > 0){
            Item item = items.get(0);
            if(item.getCount() > count){
                //可以直接删除
                item.setCount(item.getCount() - count);
                count  = 0;
            }else {
                //一个一个删除
                count -= item.getCount();
                item.setCount(0);
                items.remove(item);
                this.itemMap.remove(item.getId());
            }
        }

    }

    public boolean enough(int itemId,int count){
        List<Item> items = new ArrayList<>();
        itemMap.forEach((id,item)->{
            if (item.getItemId()==itemId) {
                items.add(item);
            }
        });

        AtomicInteger tem = new AtomicInteger();
        if(items.size()>0){
            items.forEach((item)->{
                tem.addAndGet(item.getCount());
            });
        }

        if(tem.get() > count)
            return true;


        return false;
    }
}
