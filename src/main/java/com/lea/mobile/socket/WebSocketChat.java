package com.lea.mobile.socket;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/chat")
@SuppressWarnings("unused")
public class WebSocketChat {
    private static final Logger logger = Logger.getLogger(WebSocketEcho.class);
    private static Map<String, Session> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig){
        try {
            logger.debug("Open connection");
            session.getBasicRemote().sendText("server>> Connected!");
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    @OnClose
    public void onClose(Session session) {
        logger.debug("Close connection");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        if (message != null && message.contains(":")) {
            String[] messageArray = message.split(":");

            if (messageArray[0].equals("reg")) {
                users.put(messageArray[1], session);
                if(logger.isDebugEnabled()){
                    logger.debug("New user: "+messageArray[1] + ": session.id = " + session.getId());
                }
                return;
            }

            if (messageArray[0].equals("list")) {
                String output = "list:";
                for (String name : users.keySet()) {
                    output += name + "\n";
                }
                session.getBasicRemote().sendText(output);
                if(logger.isDebugEnabled()){
                    logger.debug("Update user list for user sessionId="+session.getId());
                }
                return;
            }

            String nameFrom = "";
            for (Map.Entry<String, Session> entry : users.entrySet()) {
                if (entry.getValue() == session) {
                    nameFrom = entry.getKey();
                    break;
                }
            }

            String nameTo = messageArray[0];
            if (!nameFrom.isEmpty() && !nameTo.isEmpty()) {
                String outputMessage = nameFrom + ":" + messageArray[1];
                Session receiverSession = users.get(nameTo);

                if(receiverSession!=null){
                    receiverSession.getBasicRemote().sendText(outputMessage);
                    if(logger.isDebugEnabled()){
                        logger.debug("Sent message: receiver="+nameTo+"; sessionId="+receiverSession.getId()+";");
                    }
                }
                else {
                    logger.error("FAILED: Message does mot delivered: empty session, receiver="+nameTo);
                }

            }
        }
    }

    @OnError
    public void onError(Throwable e) {
        logger.error(e.toString());
    }
}
