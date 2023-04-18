import java.io.*;
import java.util.*;
import java.net.*;

class client
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

    public static void main(String[] args) throws Exception 
    {
        Scanner sc = new Scanner(System.in);    
        Socket socket = new Socket("localhost", 8886);
        System.out.println("\n");
        DataInputStream inStream=new DataInputStream(socket.getInputStream());
        DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
        DataOutputStream outStream1=new DataOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter Data:");
        String data = br.readLine();
        System.out.println("Enter Generator:");
        String gen = br.readLine();
        String code = data;
        while(code.length() < (data.length() + gen.length() - 1))
        code = code + "0";
        code = data + div(code,gen);
        System.out.println("The transmitted Code Word is: " + code);

    
        System.out.println("\nPlease enter the Code Word you want to send : ");
        String rec = br.readLine();

        outStream.writeUTF(rec);
        outStream.flush();

        if(Integer.parseInt(div(rec,gen)) == 0)
        {
      
        String without_error = "The received code word contains no errors.";
        }
        else
        {
        String with_error = "The received code word contains no errors.";
        }

      
        String resp = inStream.readUTF();
        System.out.println(resp);
        inStream.close();


    }

}