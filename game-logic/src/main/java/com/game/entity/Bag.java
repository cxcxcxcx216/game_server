package com.game.entity;

import com.game.constant.BagActionType;
import com.game.proto.ProtoMessage;
import com.game.system.bag.Item;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Data
@Slf4j
public class Bag {

    private Player player;
    private Map<Long, Item> itemMap = new ConcurrentHashMap<>();


    /**
     * 添加道具
     * @param itemId
     * @param count
     */
    public void addItem(int itemId, int count, BagActionType bagActionType){
        log.info("playerId  =  {}, bagActionType = {}",player.getPid(),bagActionType);
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


    /**
     * 移除道具
     * @param itemId
     * @param count
     */
    public void removeItem(int itemId, int count, BagActionType bagActionType){
        log.info("playerId  =  {}, bagActionType = {}",player.getPid(),bagActionType);
        if(!enough(itemId,count,bagActionType)){
            log.error("道具数量不够");
            return;
        }

        List<Item> items = new ArrayList<>();
        itemMap.forEach((id,item)->{
            if (item.getItemId()==itemId) {
                items.add(item);
            }
        });

        //进行降序排序
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

    /**
     * 判断道具是否足够
     * @param itemId
     * @param count
     * @return
     */
    public boolean enough(int itemId,int count, BagActionType bagActionType){
        log.info("playerId  =  {}, bagActionType = {}",player.getPid(),bagActionType);

        long ret = count(itemId,bagActionType);
        if(ret > count)
            return true;
        return false;
    }

    /**
     * 计算道具总数
     * @param itemId
     * @return
     */
    public long count(int itemId, BagActionType bagActionType){
        log.info("playerId  =  {}, bagActionType = {}",player.getPid(),bagActionType);

        List<Item> items = new ArrayList<>();
        itemMap.forEach((id,item)->{
            if (item.getItemId()==itemId) {
                items.add(item);
            }
        });
        AtomicLong tem = new AtomicLong();
        if(items.size()>0){
            items.forEach((item)->{
                tem.addAndGet(item.getCount());
            });
        }

        return tem.get();
    }

    public static Bag create(Player player){
        Bag bag = new Bag();
        //todo 初始化背包数据
        bag.setPlayer(player);
        return bag;
    }

    public ProtoMessage.BagInfoRes.Builder build(){
        ProtoMessage.BagInfoRes.Builder builder = ProtoMessage.BagInfoRes.newBuilder();
        for (Map.Entry<Long, Item> entry : itemMap.entrySet()) {
            Item item = entry.getValue();
            ProtoMessage.Item.Builder itemBuilder = ProtoMessage.Item.newBuilder();
            itemBuilder.setItemId(item.getItemId())
                    .setId(item.getId())
                    .setCount(item.getCount())
                    .setTime(item.getTime());
        }

        return builder;
    }
}
