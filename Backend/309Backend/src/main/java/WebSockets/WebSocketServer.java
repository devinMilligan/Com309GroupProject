package WebSockets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@ServerEndpoint("/websocket/{id}")
@Component
public class WebSocketServer {
	
	// Store all socket session and their corresponding username.
    private static Map<Session, Integer> sessionUsernameMap = new HashMap<>();
    private static Map<Integer, Session> usernameSessionMap = new HashMap<>();
    
    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    
    @OnOpen
    public void onOpen(
    	      Session session, 
    	      @PathParam("id") int id) throws IOException 
    {
        logger.info("Entered into Open");
        
        sessionUsernameMap.put(session, id);
        usernameSessionMap.put(id, session);
    }
 
    @OnMessage
    public void onMessage(int username, String message) throws IOException 
    {
        // Handle new messages
    	logger.info("Entered into Message: Got Message:"+message);
    	
    	sendMessageToPArticularUser(username, message);
    }
 
    @OnClose
    public void onClose(Session session) throws IOException
    {
    	logger.info("Entered into Close");
    	
    	int username = sessionUsernameMap.get(session);
    	sessionUsernameMap.remove(session);
    	usernameSessionMap.remove(username);
    }
 
    @OnError
    public void onError(Session session, Throwable throwable) 
    {
    	logger.info("Entered into Error");
    }
    
	private void sendMessageToPArticularUser(int id, String message) 
    {	
    	try {
    		usernameSessionMap.get(id).getBasicRemote().sendText(message);
        } catch (IOException e) {
        	logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }
}

