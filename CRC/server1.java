import java.io.*;
import java.net.*;
import java.util.*;

class server1
{

    static String div(String num1,String num2)
    {
        int pointer = num2.length();
        String result = num1.substring(0, pointer);
        String remainder = "";
        for(int i = 0; i < num2.length(); i++)
        {
        if(result.charAt(i) == num2.charAt(i))
            remainder += "0";
        else
            remainder += "1";
        }
        while(pointer < num1.length())
        {
        if(remainder.charAt(0) == '0')
        {
            remainder = remainder.substring(1, remainder.length());
            remainder = remainder + String.valueOf(num1.charAt(pointer));
            pointer++;
        }
        result = remainder;
        remainder = "";
        for(int i = 0; i < num2.length(); i++)
        {
            if(result.charAt(i) == num2.charAt(i))
            remainder += "0";
            else
            remainder += "1";
        }
        }
                String x = remainder.substring(1,remainder.length());
                return x;
    }

    public static void server_client() throws Exception
    {
            try
            { 
            Scanner sc = new Scanner(System.in);
            System.out.println("Server is waiting for client connection ...");
            ServerSocket ss = new ServerSocket(8886);
            Socket s = ss.accept();
            System.out.println("Client connected...");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
           
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("\n");
            String recv_code = dis.readUTF();
            System.out.println("======================================");
            System.out.println("The received code word is:" + recv_code);
            System.out.println("======================================");
            System.out.println("\n");

            System.out.println("Enter Data:");
            String data = br.readLine();
            System.out.println("Enter Generator:");
            String gen = br.readLine();
            String code = data;
            while(code.length() < (data.length() + gen.length() - 1))
            code = code + "0";
            code = data + div(code,gen);
            System.out.println("\nThe generated Code Word is: " + code);

            
            if(Integer.parseInt(div(recv_code,gen)) == 0)
            {

                String without_error = "\nThe received code word contains no errors.";
                System.out.println(without_error);
                Thread.sleep(1000);
                dout.writeUTF(without_error);
            

            }
            else
            {
                String with_error = "\nThe received code word contains errors.";
                System.out.println(with_error);
                Thread.sleep(1000);
                dout.writeUTF(with_error);

            }

            // dout.close();


            ss.close();
      
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

    }

    public static void main(String[] args) throws Exception 
    {

        server1 obj = new server1();
        obj.server_client();
        
    }
}