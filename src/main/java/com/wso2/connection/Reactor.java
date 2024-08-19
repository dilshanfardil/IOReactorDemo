package com.wso2.connection;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.nio.channels.Selector;

public class Reactor
{
    private final Map<Integer, EventHandler> registeredHandlerMap = new ConcurrentHashMap<>();
    private Selector deMultiplexer;

    public Reactor() throws IOException {
        deMultiplexer = Selector.open();
    }

    public Selector getDeMultiplexer() {
        return deMultiplexer;
    }

    public void registerEventHandler(
            int eventType, EventHandler eventHandler) {
        registeredHandlerMap.put(eventType, eventHandler);
    }

    public void registerChannel(
            int eventType, SelectableChannel channel) throws ClosedChannelException {
        channel.register(deMultiplexer, eventType);
    }

    public void run() {
        try {
            while (true) {
                deMultiplexer.select();

                Set<SelectionKey> readyHandles = deMultiplexer.selectedKeys();
                Iterator<SelectionKey> handleIterator = readyHandles.iterator();

                while (handleIterator.hasNext()) {
                    controlAndDelegate(handleIterator);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The Reactor is all about control and delegation.
     * It ensures that the system remains responsive, quickly directing traffic to the appropriate handler
     * without getting bogged down in the details.
     * This is the responsibility of the Reactor.
     *
     * @param handleIterator
     * @throws IOException
     */
    private void controlAndDelegate(Iterator<SelectionKey> handleIterator) throws IOException {
        SelectionKey handle = handleIterator.next();

        if (handle.isAcceptable()) {
            EventHandler handler = registeredHandlerMap.get(SelectionKey.OP_ACCEPT);
            handler.handleEvent(handle);
        }

        if (handle.isReadable()) {
            EventHandler handler = registeredHandlerMap.get(SelectionKey.OP_READ);
            handler.handleEvent(handle);
            handleIterator.remove();
        }

        if (handle.isWritable()) {
            EventHandler handler = registeredHandlerMap.get(SelectionKey.OP_WRITE);
            handler.handleEvent(handle);
            handleIterator.remove();
        }
    }
}
