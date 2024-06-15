package com.game.system.bag;


import cn.hutool.core.util.IdUtil;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Item {
    private long id;

    private int itemId;

    private int count;

    private long time;

    public static Item createItem(int itemId,int count){
        Item item = new Item();
        item.setItemId(itemId);
        item.setCount(count);
        item.setId(IdUtil.getSnowflakeNextId());
        item.setTime(System.currentTimeMillis()/1000);
        return item;
    }
}
