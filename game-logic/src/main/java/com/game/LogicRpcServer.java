package com.game;

import com.game.net.rpc.RpcBaseServer;
import lombok.Data;

@Data
public class LogicRpcServer extends RpcBaseServer {

    public static void main(String[] args) {
        LogicRpcServer logicRpcServer = new LogicRpcServer();
        logicRpcServer.startDefault();

    }
}
