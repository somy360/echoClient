package echoClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	//static variables of our server
	private static final String host = "3.9.181.31";
	private static final int port = 40138;
	
	//our socket to make a connection with the server
	Socket socket;
	//our writer for writing to the server
	PrintWriter writer;
	//our reader for reading from the server
	BufferedReader serverReader;
	
	BufferedReader stdIn;
	
	
	
	/*
	 * Our main constructor
	 */
	Client(){

		initialise();
		getEcho();
		
	}
	
	
	/*
	 * Initialise our global variables for use throughout the class
	 */
	private void initialise() {
		try {
			socket = new Socket(host, port);
			writer = new PrintWriter(socket.getOutputStream(), true);
			serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    stdIn = new BufferedReader(new InputStreamReader(System.in));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 
	 */
	private void getEcho() {
		String userInput;
		try {
			while ((userInput = stdIn.readLine()) != null) {
			    writer.println(userInput);
			    System.out.println("echo: " + serverReader.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
