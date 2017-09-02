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
            registerUser(session,"Support");
            sendUserList(session);
            sendMessage(session,"Support","Hello! Can I help you?");
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
                registerUser(session,messageArray[1].trim());
                return;
            }else if (messageArray[0].equals("list")) {
                sendUserList(session);
                return;
            }

            String nameFrom = messageArray.length>2?messageArray[2]:"";

            if(!nameFrom.isEmpty()) {
                if(!users.containsKey(nameFrom)){
                    registerUser(session,nameFrom);
                    sendUserList(session);
                }

                String nameTo = messageArray[0];
                if(nameTo.isEmpty()) nameTo = "Support";

                sendMessage(nameFrom, nameTo, messageArray[1]);
                if(nameTo=="Support"){
                    sendMessage(nameTo,nameFrom, "answer>>"+messageArray[1]);
                }
            }
            else {
                logger.error("Value 'nameFrom' is empty");
            }
        }
    }

    @OnError
    public void onError(Throwable e) {
        logger.error(e.toString());
    }


    private void registerUser(Session session,String userName){
        users.put(userName, session);
        if(logger.isDebugEnabled()){
            logger.debug("New user: " + userName + ": session.id = " + session.getId());
        }
    }

    private void sendUserList(Session session) throws IOException {
        String output = "list:";
        for (String name : users.keySet()) {
            output += name + "\n";
        }
        session.getBasicRemote().sendText(output);
        if(logger.isDebugEnabled()){
            logger.debug("Update user list for user sessionId="+session.getId());
        }
    }

    private void sendMessage(Session session, String nameFrom,String message) throws IOException {
        session.getBasicRemote().sendText(nameFrom+": "+message);
    }

    private void sendMessage(String nameFrom, String nameTo, String message) throws IOException {
        Session receiverSession = users.get(nameTo);
        String outputMessage = nameFrom + ": " + message;

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
