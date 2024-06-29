package com.game.entity;

import com.game.system.buffer.Buff;
import com.game.system.buffer.BuffManager;
import lombok.Data;

import java.util.List;
@Data
public abstract class BaseActor {
    private long id;

    // 应用一个 buff 到角色
    public void applyBuff(Buff buff) {
        BuffManager.getInstance().applyBuff(id, buff);
    }

    // 更新buff 状态
    public void updateBuffs() {
        BuffManager.getInstance().updateBuffs();
    }

    // 获取所有的 buff
    public List<Buff> getActiveBuffs() {
        return BuffManager.getInstance().getActiveBuffs(id);
    }


}
