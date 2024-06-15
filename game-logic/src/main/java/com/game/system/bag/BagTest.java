package com.game.system.bag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BagTest {

    public static void main(String[] args) {
        BagStore bagStore = new BagStore();
        bagStore.addItem(1000,10000);
//        bagStore.getItemMap().forEach((id,item)->{
//            System.out.println(id +"=="  +item.toString());
//        });

        log.error(bagStore.getItemMap().size()+ "");


        bagStore.removeItem(100,100);

        bagStore.getItemMap().forEach((id,item)->{
            System.out.println(id +"=="  +item.toString());
        });

        log.error(bagStore.getItemMap().size()+ "");

    }
}
