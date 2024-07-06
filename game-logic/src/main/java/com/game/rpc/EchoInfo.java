package com.game.rpc;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import lombok.Data;

@Data
public class EchoInfo {
    @Protobuf
    public String message;
}
