import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

class proxy 
{

    public static void client_Proxy() throws Exception
    {
        try
            {
            
            Scanner sc = new Scanner(System.in);
            System.out.println("\nServer is waiting for client ...");
            ServerSocket ss = new ServerSocket(8000);
            Socket s = ss.accept();
            System.out.println("Client connected...");
            System.out.println("\n");
            InetAddress localhost = InetAddress.getLocalHost();
            String address = " Client connected with " + localhost + " on port no." + ss.getLocalPort();
            System.out.println(address);

            DataInputStream inStream=new DataInputStream(s.getInputStream());
            DataOutputStream outStream=new DataOutputStream(s.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

            String code = inStream.readUTF();
            System.out.println(code);
            System.out.println("\n");
            inStream.close();


            ss.close();
      
            }
            catch(Exception e)
            {
                System.out.println(e);
            }          
    }


    public static void Proxy_server() throws Exception 
    {
        try 
        {
        Socket socket = new Socket("127.0.0.1", 8886);
        System.out.println("\n");
        Scanner sc = new Scanner(System.in);
        DataInputStream inStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        InetAddress Proxy_localhost = InetAddress.getLocalHost();
        String Proxy_address = "Proxy Server connected on " + Proxy_localhost + " with port no." + socket.getLocalPort();

        System.out.println("\nEnter the received codeword");
        String code = br.readLine();
        
        outStream.writeUTF(code);
        // outStream.flush();
        // outStream.close();

        String output = inStream.readUTF();
        // inStream.close();
        socket.close();
        
        } 
        catch (Exception e) 
        {
        System.out.println(e);
    
        }
  }

    public static void main(String[] args) throws Exception 
    {

      while (true) 
      {
      Scanner sc = new Scanner(System.in);
      System.out.println("\n");
      System.out.println("****************************************");
      System.out.println("1. client - Proxy server interaction ...");
      System.out.println("2. Proxy server - server interaction ...");
      System.out.println("3. Exit !");
      System.out.println("****************************************");

      System.out.println("Enter your choice");
      int choice = sc.nextInt();

      switch (choice) 
      {
        case 1:
        System.out.println("1. client - Proxy ...");
        proxy obj = new proxy();
        obj.client_Proxy(); 
        break;

        case 2:
        System.out.println("2. Proxy - server  ...");
        proxy obj1 = new proxy() ;
        obj1.Proxy_server();
        break;

        case 3:
        System.out.println("3. Exit!...");
        break;

        default:
        System.out.println("incorrect output...");
      }
    }
       
        

    }
                
}

        