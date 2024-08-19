package com.wso2.connection;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface EventHandler
{
    void handleEvent(SelectionKey handle) throws IOException;

}
