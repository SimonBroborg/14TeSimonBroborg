import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	public static int PORT = 5555;
	public int snakeNr; 
	public static int clients = 0; 
	public static Game game; 
	
	//Name of the players
	private ArrayList<String> names = new ArrayList<String>();
	//The clients
	private static ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();
	
	private static ArrayList<Float> angles = new ArrayList<Float>();
	
	public static void log(String message){
		System.out.println(message);
	}
	
	public static void main(String[] args) throws IOException{
		ServerSocket listener = new ServerSocket(PORT);
		log("Server is now running..."); 
		
		try{
			while(true){
				
				new Handler(listener.accept()).start();  
				clients++; 
				
			}
		}
		finally{
			listener.close(); 
		}
		
	}
	
	private static class Handler extends Thread{
		private int snakeNr; 
		private Socket socket; 
		private BufferedReader in; 
		private PrintWriter out; 
		
		public Handler(Socket socket){
			this.socket = socket; 
		}
		
		public void run(){
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true); 
				
				writers.add(out);
				snakeNr = writers.size() -1 ;
				out.println(snakeNr);
				
				
				while(true){
					
					String input = in.readLine();
					
						for(PrintWriter writer : writers ){
							writer.println(input);
						}
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try{
					socket.close();
					writers.remove(out); 
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}

	}
	
	
}