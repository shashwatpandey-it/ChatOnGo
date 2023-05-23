package com.shashwat.services;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.gson.Gson;
import com.shashwat.home.LeftPanel;
import com.shashwat.models.RecieveMessageModel;
import com.shashwat.models.UserAccountModel;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class ClientService {

	private int maxClients = 100; 
	private static ClientService instance;
	private Socket client;
	private OkHttpClient okHttpClient;
	private Dispatcher dispatcher;
	private final int PORT_NUMBER = 9999;
	private final String IP = "chatongoserver.hopto.org";
	private List<UserAccountModel> users = new ArrayList<>();
	private int userId;
	
	public static ClientService getClientService() {
		if (instance == null) {
			instance = new ClientService();
		}
		return instance;
	}
	
	private ClientService() {
		
	}
	
	public void startClientService() {
		try {
			/*Each dispatcher uses an ExecutorService to run calls internally,
			 *by default, you won’t be able to create more than 5 Socket.IO clients
			 *(any additional client will be disconnected with “transport error” or “ping timeout” reason).
			 *That is due to the default OkHttp dispatcher, whose maxRequestsPerHost is set to 5 by default.
			*/
			
			dispatcher = new Dispatcher(); 					
			dispatcher.setMaxRequests(maxClients*2);
			dispatcher.setMaxRequestsPerHost(maxClients*2);
			
			/*we use MAX_CLIENTS * 2 because a client in HTTP long-polling mode will have one 
			 *long-running GET request for receiving data from the server,
			 *and will create a POST request for sending data to the server.
			*/
			
			okHttpClient = new OkHttpClient().newBuilder()
							.dispatcher(dispatcher)
							//.connectionSpecs(Arrays.asList(ConnectionSpec.RESTRICTED_TLS))
							.connectTimeout(0, TimeUnit.MILLISECONDS)
							.readTimeout(0, TimeUnit.MILLISECONDS)				//important for HTTP long polling
							.writeTimeout(0, TimeUnit.MILLISECONDS)
							.build();
			
			IO.Options options = new IO.Options();
			options.callFactory = okHttpClient;									//The OkHttpClient instance to use for HTTP long-polling requests.
			options.webSocketFactory = okHttpClient;
			
			client = IO.socket("http://"+ IP +":"+ PORT_NUMBER, options);		// ---creation of a socket instance, with custom options set for the client---
			
			client.on("listUsers", new Emitter.Listener() {
				
				@Override
				public void call(Object... args) { 
					if(args.length > 0) {
						//System.out.println(args[0]);
						for(Object object : args) {
							UserAccountModel userAccount = new Gson().fromJson(object.toString(), UserAccountModel.class);
							if(userAccount.getUserId() != userId) {
								users.add(userAccount);
							}
						}
						
						LeftPanel.getLeftPanelInstance().newUsers(users);
					}
					
				}
			});
			
			client.on("updateUsersList", new Emitter.Listener() {
				
				@Override
				public void call(Object... args) {
					// TODO Auto-generated method stub
					if(args.length > 0) {
						for(Object object : args) {
							UserAccountModel userAccount = new Gson().fromJson(object.toString(), UserAccountModel.class);
							users.removeIf(user -> user.getName().equals(userAccount.getName()));
						}
						LeftPanel.getLeftPanelInstance().newUsers(users);
					}
				}
			});
			
			client.on("recieveFromUser", new Emitter.Listener() {
				
				@Override
				public void call(Object... args) {
					if(args.length > 0) {
						for(Object object : args) {
							RecieveMessageModel message = new Gson().fromJson(object.toString(), RecieveMessageModel.class);
							message.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
							ChatService.getChatService().receiveMessage(message.getFromUserId(), message);
						}
					}
					
				}
			});
			
			client.open();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public Socket getClient() {
		return client;
	}

	public List<UserAccountModel> getUsers(){
		return users;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Dispatcher getDispatcher() {
		return dispatcher;
	}
}

