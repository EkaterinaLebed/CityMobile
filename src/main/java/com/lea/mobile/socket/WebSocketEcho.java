package com.lea.mobile.socket;

import org.apache.log4j.Logger;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/echo")
public class WebSocketEcho {
    private static final Logger logger = Logger.getLogger(WebSocketEcho.class);

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig){
        try {
            logger.debug("Open connection");
            session.getBasicRemote().sendText("server>> Connected!");
        } catch (IOException e) {
            logger.error(e.toString());
        }

        //HttpSession s = (HttpSession)endpointConfig.getUserProperties().get(HttpSession.class.getName());
    }

    @OnClose
    public void onClose(Session session) {
        logger.debug("Close connection");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            logger.debug("Invoked");
            session.getBasicRemote().sendText("server>> "+message);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    @OnError
    public void onError(Throwable e) {
        logger.error(e.toString());
    }
}
