package com.wso2.connection;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReadEventHandler implements EventHandler
{

    private final Selector demultiplexer;
    private final ByteBuffer inputBuffer = ByteBuffer.allocate(2048);

    public ReadEventHandler(Selector demultiplexer) {
        this.demultiplexer = demultiplexer;
    }

    public void handleEvent(SelectionKey handle) throws IOException {
        System.out.println("Read Event Handler Started........");

        SocketChannel socketChannel = (SocketChannel) handle.channel();
        socketChannel.read(inputBuffer);

        inputBuffer.flip();
        // Rewind the buffer to start reading from the beginning

        byte[] buffer = new byte[inputBuffer.limit()];
        inputBuffer.get(buffer);

        System.out.println("Received message from client : " + new String(buffer));
        inputBuffer.flip();

        socketChannel.register(demultiplexer, SelectionKey.OP_WRITE, inputBuffer);

    }
}
