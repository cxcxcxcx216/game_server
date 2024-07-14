package com.game.manager;


import cn.hutool.core.util.IdUtil;
import com.game.constant.BagActionType;
import com.game.constant.GameConstant;
import com.game.data.redis.RedisDataCenter;
import com.game.entity.Bag;
import com.game.entity.Player;
import com.game.listener.EventUtil;
import com.game.net.Session;
import com.game.proto.MsgCode;
import com.game.proto.ProtoMessage;
import com.game.utils.MsgUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Slf4j
public class PlayerManager {

    private static final Map<Long, Player> PLAYER_MAP = new ConcurrentHashMap<>();

    private static final PlayerManager INSTANCE = new PlayerManager();

    public static PlayerManager getInstance(){
        return INSTANCE;
    }


    public boolean createPlayer(Player player){
        player.setCreateTime(System.currentTimeMillis());
        PLAYER_MAP.put(player.getPid(), player);
        log.error("玩家数量 = {}",PLAYER_MAP.size());
        if(player != null){
            log.info("player create is successful ! player= {}",player.getName());
            return true;
        }
        return false;
    }

    public boolean logout(long pid){
        Player result = PLAYER_MAP.remove(pid);
        if(result != null){
            log.info("player logout is successful ! player= {}",result);
            return true;
        }
        log.info("player logout is fail ! player= {}",result.toString());
        return false;
    }

    public Player getPlayerById(long pid){
        Player player = PLAYER_MAP.getOrDefault(pid, null);
        return player;
    }

    public Map<Long, Player> getPlayerMap(){
        return PLAYER_MAP;
    }

    /**
     * 登录
     * @param name
     * @param session
     * @return
     */
    public boolean login(String name, Session session){
        Player loginPlayer = RedisDataCenter.getInstance().getPlayer(name);
        if(loginPlayer != null){
            loginPlayer.setSession(session);
            session.setPlayer(loginPlayer);
            loginPlayer.getBag().setPlayer(loginPlayer);
            this.PLAYER_MAP.put(loginPlayer.getPid(),loginPlayer);
            ProtoMessage.LoginMessageRes.Builder builder = ProtoMessage.LoginMessageRes.newBuilder();
            builder.setName(loginPlayer.getName());
            builder.setErrorCode(0);
            MsgUtil.sendMsg(loginPlayer.getPid(), MsgCode.Code.LoginMessageRes_VALUE,builder.build().toByteArray());
            return true;
        }


        Player player = Player.create();
        player.setName(name);
        player.setPid(IdUtil.getSnowflakeNextId());
        player.setSession(session);
        Bag bag = Bag.create(player);

        bag.addItem(1001,271, BagActionType.GM);

        player.setBag(bag);
        bag.setPid(player.getPid());
        session.setPlayer(player);
        EventUtil.fire(GameConstant.Listener.DATA_BASE,player);
        ProtoMessage.LoginMessageRes.Builder builder = ProtoMessage.LoginMessageRes.newBuilder();
        if (PlayerManager.getInstance().createPlayer(player)) {
            log.info("账号登录成功");
            builder.setName(player.getName());
            builder.setErrorCode(0);
            //返回客户端消息
            MsgUtil.sendMsg(player.getPid(), MsgCode.Code.LoginMessageRes_VALUE,builder.build().toByteArray());
            return true;
        }else {
            log.error("账号登录失败");
            builder.setName(player.getName());
            builder.setErrorCode(1001);
            //返回客户端消息
            MsgUtil.sendMsg(player.getPid(), MsgCode.Code.LoginMessageRes_VALUE,builder.build().toByteArray());
            return false;
        }
    }

}


