package com.game;


import com.game.luban.ByteBuf;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
//        cfg.Tables tables = new cfg.Tables(Main::createByteBufFromFile);
        System.out.println("== load succ ==");
    }

    private static ByteBuf createByteBufFromFile(String file) throws IOException {
        return new ByteBuf(java.nio.file.Files.readAllBytes(Paths.get("../GenerateDatas/bytes", file + ".bytes")));
    }
}
