package com.wso2.connection;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class WriteEventHandler implements EventHandler
{

    @Override
    public void handleEvent(SelectionKey handle) throws IOException
    {
        System.out.println("Writing Event Handler Started........");

        SocketChannel socketChannel = (SocketChannel) handle.channel();

        ByteBuffer inputBuffer = (ByteBuffer) handle.attachment();
        socketChannel.write(inputBuffer);

        socketChannel.close();
    }
}
