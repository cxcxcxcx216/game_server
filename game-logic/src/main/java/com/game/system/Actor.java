package com.game.system;

import com.game.system.bag.BagStore;
import lombok.Data;

@Data
public class Actor {
    private BagStore bagStore = new BagStore();

}
