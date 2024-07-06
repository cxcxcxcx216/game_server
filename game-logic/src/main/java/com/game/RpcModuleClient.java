package com.game;

import com.baidu.jprotobuf.pbrpc.client.ProtobufRpcProxy;
import com.baidu.jprotobuf.pbrpc.proto.EchoInfoClass;

import com.baidu.jprotobuf.pbrpc.transport.RpcClient;
import com.game.rpc.EchoInfo;
import com.game.rpc.EchoService;
import lombok.Data;

@Data
public class RpcModuleClient {


    public RpcModuleClient() {
        RpcClient rpcClient = new RpcClient();
        ProtobufRpcProxy<EchoService> pbrpcProxy = new ProtobufRpcProxy<EchoService>(rpcClient, EchoService.class);
        pbrpcProxy.setPort(1031);
        EchoService echoService = pbrpcProxy.proxy();

        EchoInfo req = new EchoInfo();
        req.setMessage("hello");
        EchoInfo response = echoService.echo(req);
        rpcClient.stop();
    }

    public static void main(String[] args) {
        RpcModuleClient client = new RpcModuleClient();
    }
}
