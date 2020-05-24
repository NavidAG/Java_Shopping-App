
package ServerPackag;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.ListView;

public class ClientManager extends Thread{

    boolean ok = false;

    Socket clientSocket;
    
    public static InputStream serverInputStream;

    public static InputStreamReader serverInputStreamReader;

    public static OutputStream serverOutputStream;

    public static OutputStreamWriter serverOutputStreamWriter;

    public static BufferedReader reader;

    public static BufferedWriter serverBufferedWriter;

    public static PrintWriter writer;
    
    private String userName ;
    private String password;
    
    HashMap<String , ClientManager> clientsMap = new HashMap<String , ClientManager>();
    
    ArrayList<String> productArrayList;

    ServerSocket serverSocket ;

    public static ArrayList<Product> product ;

    public static ListView <String> productList = new ListView<>();

    static Person javid = new Person("Javid","Chaji", 640663443);
    static Account javidAccount = new Account("1","1",5566875,103005);
    static UserAccount javidUser = new UserAccount(javid, javidAccount);

    static Person mohamad = new Person("mohamad","rezaee", 64063443);
    static Account mohamadAccount = new Account("3","3",5544875,10664);
    static UserAccount mohamadUser = new UserAccount(mohamad, mohamadAccount);

    static Person jakson = new Person("jakson","tatar", 640763443);
    static Account jaksonAccount = new Account("2","2",55676875,11300);
    static UserAccount jaksonUser = new UserAccount(jakson, jaksonAccount);

    static Person vahid = new Person("vahid","aghamiri", 640863443);
    static Account vahidAccount = new Account("54","54",55686875,350000);
    static UserAccount vahidUser = new UserAccount(vahid, vahidAccount);

    static Person pooya = new Person("pooya","tehrani", 640263443);
    static Account pooyaAccount = new Account("65","65",556776875,19000);
    static UserAccount pooyaUser = new UserAccount(pooya, pooyaAccount);

    static Person omid = new Person("omid","imani", 640963443);
    static Account omidAccount = new Account("28","28",556683575,18000);
    static UserAccount omidUser = new UserAccount(omid, omidAccount);

    int reg;

    String clientSaid;

    static ArrayList<UserAccount> userAccounts = new ArrayList<UserAccount>();

    public ClientManager(ServerSocket serverSocket, ArrayList<Product> product) {

        this.serverSocket = serverSocket ;
        this.product = product;

    }

    @Override
    public void run() {

        userAccounts.add(javidUser);
        userAccounts.add(mohamadUser);
        userAccounts.add(jaksonUser);
        userAccounts.add(vahidUser);
        userAccounts.add(pooyaUser);
        userAccounts.add(omidUser);



        try {

            while (true) {

                Socket clientSocket = serverSocket.accept();

                System.out.println("A New Client Is Connected !");

                serverInputStream = clientSocket.getInputStream();

                serverInputStreamReader = new InputStreamReader(serverInputStream);

                serverOutputStream = clientSocket.getOutputStream();

                serverOutputStreamWriter = new OutputStreamWriter(serverOutputStream);

                serverBufferedWriter = new BufferedWriter(serverOutputStreamWriter);

                reader = new BufferedReader(serverInputStreamReader);

                writer = new PrintWriter(serverBufferedWriter, true);

                writer.println("What Is Your UserName ?");

                userName = reader.readLine();

                writer.println("Please Enter Your Password ?");

                password = reader.readLine();

                for (int i = 0; i < userAccounts.size(); i++) {
                    if (userName.equals(userAccounts.get(i).getAccount().getUserName()) && password.equals(userAccounts.get(i).getAccount().getPassword())) {

                        ok = true;
                        reg = i;
                        writer.println("true");

                    }
                }

                if (ok) {

                    writer.println("UserAccount%" + this.userAccounts.get(reg).toString());
                    writer.println("Product%" + this.product.toString());

//                    while (ok){
//
//                        clientSaid = reader.readLine();
//
//                        String[] orderType = clientSaid.split("%");
//
//                        if(orderType[0].equals("Product")) {
//
//                            productArrayList = new ArrayList<>();
//                            product = new ArrayList<>();
//                            productList.getItems().clear();
//                            productInterpret(orderType[1]);
//                            productArrayList = ProductToStringProduct(product);
//                            productList.getItems().addAll(productArrayList);
//
////                    notifyAll();
//
//                        }
//
//                        if(orderType[0].equals("RemoveProduct")){
//
//
//
//                        }
//
//                        if(orderType[0].equals("AddProduct")){
//
//
//
//                        }
//
//                        if(orderType[0].equals("UpdateProduct")){
//
//
//
//                        }
//
//                        if(orderType[0].equals("UserAccount")){
//
//
//
//                        }
//
//
//                    }

                }

                 clientSocket.close();

            }


        }

        catch(IOException ex){

            ex.printStackTrace();

        }
        
    }

    public void productInterpret(String arrayListUpdater){

        String[] arrayOfProducts = arrayListUpdater.split(", ");

        for (int i = 0; i < arrayOfProducts.length; i++) {

            String[] arrayOfDetailProducts = arrayOfProducts[i].split(":");

            Product productNow = new Product();

            arrayOfDetailProducts[0] = arrayOfDetailProducts[0].replace("[", "");

            productNow.setName(arrayOfDetailProducts[0]);

            productArrayList.add(arrayOfDetailProducts[0]);

            productNow.setCategory(arrayOfDetailProducts[1]);

            productNow.setMeasurement(arrayOfDetailProducts[2]);

            productNow.setPrice(Double.valueOf(arrayOfDetailProducts[3]));

            productNow.setMinimumQuantity(Double.valueOf(arrayOfDetailProducts[4]));

            arrayOfDetailProducts[5] = arrayOfDetailProducts[5].replace("]", "");

            productNow.setQuantity(Double.valueOf(arrayOfDetailProducts[5]));

            product.add(productNow);

            productList.getItems().add(arrayOfDetailProducts[0]);

        }

        System.out.println(product.toString());
    }


    public ArrayList<String> ProductToStringProduct( ArrayList<Product> product){

        ArrayList <String> stringProducts = new ArrayList<>();

        for (int i = 0 ;  i < product.size(); i++ ){

            stringProducts.add(product.get(i).getName());

        }
        return stringProducts;
    }
    

    
}
