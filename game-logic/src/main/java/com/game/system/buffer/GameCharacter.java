package com.game.system.buffer;

import lombok.Data;

import java.util.List;


@Data
public class GameCharacter {

    private int id;
    private String name;
    private int health;
    private int mana;
    private BuffManager buffManager;

    public GameCharacter(int id, String name, int health, int mana) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.buffManager = new BuffManager();
    }

    // 其他属性的 getter 和 setter 方法

    // 应用一个 buff 到角色
    public void applyBuff(Buff buff) {
        buffManager.applyBuff(id, buff);
        // 根据 buff 的效果修改角色的状态
        // 例如增加生命值、增加法力值等
    }

    // 更新角色的 buff 状态
    public void updateBuffs() {
        buffManager.updateBuffs();
        // 更新角色的属性，根据当前激活的 buff
    }

    // 获取角色当前所有的 buff
    public List<Buff> getActiveBuffs() {
        return buffManager.getActiveBuffs(id);
    }
}
