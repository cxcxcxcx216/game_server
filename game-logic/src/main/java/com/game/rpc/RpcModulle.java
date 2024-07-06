package com.game.rpc;

import com.baidu.jprotobuf.pbrpc.transport.RpcServer;
import lombok.Data;


@Data
public class RpcModulle {
    private RpcServer rpcServer = new RpcServer();

    public RpcModulle(){
        EchoServiceImpl echoServiceImpl = new EchoServiceImpl();
        rpcServer.registerService(echoServiceImpl);
        rpcServer.start(1031);
    }

}
