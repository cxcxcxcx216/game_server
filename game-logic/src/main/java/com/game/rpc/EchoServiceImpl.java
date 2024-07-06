package com.game.rpc;

import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;

public class EchoServiceImpl {

    @ProtobufRPCService(serviceName = "echoService", methodName = "echo")
    public EchoInfo doEcho(EchoInfo info) {
        EchoInfo ret = new EchoInfo();
        ret.setMessage("hello:" + info.message);

        return ret;
    }
}
