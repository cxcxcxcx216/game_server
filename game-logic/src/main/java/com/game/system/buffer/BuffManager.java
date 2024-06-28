package com.game.system.buffer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BuffManager {
    private Map<Integer, Buff> buffs  = new ConcurrentHashMap<>(); // 存储所有 buff 的 Map
    private Map<Long, List<Buff>> activeBuffs = new ConcurrentHashMap<>(); // 存储角色当前激活的 buff

    public BuffManager() {
        buffs = new HashMap<>();
        activeBuffs = new HashMap<>();
    }

    // 添加一个 buff 到管理器中
    public void addBuff(Buff buff) {
        buffs.put(buff.getId(), buff);
    }

    // 移除一个 buff
    public void removeBuff(int buffId) {
        buffs.remove(buffId);
        activeBuffs.values().forEach(list -> list.removeIf(buff -> buff.getId() == buffId));
    }

    // 检查和更新所有激活中的 buff
    public void updateBuffs() {
        for (List<Buff> buffs : activeBuffs.values()) {
            Iterator<Buff> iterator = buffs.iterator();
            while (iterator.hasNext()) {
                Buff buff = iterator.next();
                buff.updateDuration(); // 更新 buff 的持续时间
                if (buff.getDuration() <= 0) {
                    iterator.remove(); // buff 到期，移除
                }
            }
        }
    }

    // 给角色添加一个 buff
    public void applyBuff(long characterId, Buff buff) {
        List<Buff> characterBuffs = activeBuffs.computeIfAbsent(characterId, k -> new ArrayList<>());
        characterBuffs.add(buff);
    }

    // 获取角色当前所有的 buff
    public List<Buff> getActiveBuffs(long characterId) {
        return activeBuffs.getOrDefault(characterId, Collections.emptyList());
    }
}
