package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.manager.MessageManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Message;
import es.uc3m.ecommerce.model.Purchas;


/*
* Handler para leer mensajes 1 a 1
*/
public class ReadMessageHandler implements IHandler {
	
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;	

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13103");
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");
		Appuser sender = (Appuser)session.getAttribute("sender");
		String path = "message"+"/"+user.getUserId()+"/"+sender.getUserId();
		webTargetPath = webTarget.path(path);
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.get();
		
		if(resp.getStatus()==200) {
			Message[] miMensajes= resp.readEntity(Message[].class);
			List<String> listaMensaje=new ArrayList<String>();
			List<Message> MessagesToUpdate=new ArrayList<Message>();
			
			for(int i=0;i<miMensajes.length;i++) {
				Message msg=(Message)miMensajes[i];
				listaMensaje.add(msg.getMessage());
				MessagesToUpdate.add(msg);
			}			
			
			request.setAttribute("listaMensaje", listaMensaje);
			session.setAttribute("sender", sender);
			
			path = "message";
			webTargetPath = webTarget.path(path);
			invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
			resp= invocationBuilder.put(Entity.entity(MessagesToUpdate,MediaType.APPLICATION_JSON));
		}
		
		
		/*
		MessageManager messageManager = new MessageManager();
		ConnectionFactory tiwconnectionfactory = messageManager.connectionfactory;
		Queue queue = messageManager.queue;
		
		try {
			// First create a connection using the connectionFactory
			Connection con = tiwconnectionfactory.createConnection();
		      
		    // Next create the session. Indicate that transaction will not be supported
			Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			con.start();		
			
			
			HttpSession session = request.getSession();
			Appuser user = (Appuser) session.getAttribute("user");			
			
			Appuser sender = (Appuser)session.getAttribute("sender");
			
			// selector
			String selector = "(sendTo=" + user.getUserId()+")"
					+ "AND "
					+ "(sendFrom=" + sender.getUserId() + ")";
			
			
			//Create a consumer
			MessageConsumer mc= ses.createConsumer(queue, selector);
			
			//Se crea un lista de mensajes
			List<String> listaMensaje=new ArrayList<String>();
			
			Message message = null;
			while (true) {
				message = mc.receive(1000);
				
				if (message != null) { (message instanceof MapMessage) {
						MapMessage m = (MapMessage) message;
						listaMensaje.add(m.getString("msg"));
					} else {
						// JHC ************ Not the right type
						break;
					}
				} else // there are no messages
					{
					break;
				}

			}
			request.setAttribute("listaMensaje", listaMensaje);
			session.setAttribute("sender", sender);

			//Close the session
			ses.close();

			//Close the connection
			con.close();
		} catch (javax.jms.JMSException e) {
				System.out.println("Error when sending the message</BR>");
		} catch (Exception e) {
				System.out.println("Error when sending the message</BR>");
		}
		*/
		return "messages_1to1.jsp";
	}

}
