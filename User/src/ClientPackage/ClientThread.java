
package ClientPackage;

import static com.sun.xml.internal.ws.model.RuntimeModeler.PORT;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import javafx.scene.control.ListView;

public class ClientThread extends Thread {

    public ArrayList<String> productArrayList = new ArrayList<>();

    public static ArrayList<Product> product = new ArrayList<Product>();

    public static ListView<String> productList = new ListView<>();

    public ArrayList<String> product_ArraylistOfConsumer ;

    public ListView<String> product_listOfConsumer ;

    public static boolean ok;

    public static UserAccount userAccount = new UserAccount();

    Socket clientSocket;

    public static String userName;

    public static String password;

    private final static int PORT = 3000;

    private final static String SERVERADDRESS = "127.0.0.1";

    public static InputStream clientInputStream;

    public static InputStreamReader clientInputStreamReader;

    public static OutputStream clientOutputStream;

    public static OutputStreamWriter clientOutputStreamWriter;

    public static BufferedReader  reader;

    public static BufferedWriter clientBufferedWriter;

    public static PrintWriter writer;

    public ClientThread(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public ArrayList<String> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<String> productArrayList) {
        this.productArrayList = productArrayList;
    }

//    public ArrayList<ClientPackage.Product> getProduct() {
//        return product;
//    }

    public ListView<String> getProductList() {
        return productList;
    }

    public void setProductList(ListView<String> productList) {
        this.productList = productList;
    }

    @Override
    public void run() {

        try {

            clientSocket = new Socket(SERVERADDRESS, PORT);

            System.out.println("Connect To Server");

            clientInputStream = clientSocket.getInputStream();

            clientInputStreamReader = new InputStreamReader(clientInputStream);

            clientOutputStream = clientSocket.getOutputStream();

            clientOutputStreamWriter = new OutputStreamWriter(clientOutputStream);

            clientBufferedWriter = new BufferedWriter(clientOutputStreamWriter);

            reader = new BufferedReader(clientInputStreamReader);

            writer = new PrintWriter(clientBufferedWriter, true);

            String serverSaid = reader.readLine();

            if (serverSaid.equals("What Is Your UserName ?")) {

                writer.println(userName);

            }

            serverSaid = reader.readLine();

            if (serverSaid.equals("Please Enter Your Password ?")) {

                writer.println(password);

            }


            serverSaid = reader.readLine();
            if(serverSaid.equals("true")){
                ok = true;
            }
//            notifyAll();


            System.out.println(ok);
            while (ok) {

                String arrayListUpdater = reader.readLine();

                String[] orderType = arrayListUpdater.split("%");

                if(orderType[0].equals("Product")) {

                    productArrayList = new ArrayList<>();
                    product = new ArrayList<>();
                    productList.getItems().clear();
                    productInterpret(orderType[1]);
//                    productArrayList = ProductToStringProduct(product);
//                    productList.getItems().addAll(productArrayList);

//                    notifyAll();

                }

                if(orderType[0].equals("AddProduct")){

                    addProductInterpret(orderType[1]);
//                    Client.productList.refresh();

                }

                if(orderType[0].equals("UpdateProduct")){

                    productList.getItems().removeAll(productArrayList);
                    productArrayList = new ArrayList<>();
                    product = new ArrayList<>();
                    productInterpret(orderType[1]);
//                    Client.productList.refresh();

                }

                if(orderType[0].equals("UserAccount")){


                    userAccountInterpret(orderType[1]);

                }

            }

            System.out.println("Username or Password is inaccurate!");

        } catch (IOException | NumberFormatException ex) {

            ex.printStackTrace();

        }


    }

    public ArrayList<Product> getProduct() {
        return product;
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

    public void addProductInterpret(String arrayListUpdater){

        String[] DetailProducts = arrayListUpdater.split(":");

        Product productNow = new Product();

        productNow.setName(DetailProducts[0]);

        productArrayList.add(DetailProducts[0]);

        productNow.setCategory(DetailProducts[1]);

        productNow.setMeasurement(DetailProducts[2]);

        productNow.setPrice(Double.valueOf(DetailProducts[3]));

        productNow.setMinimumQuantity(Double.valueOf(DetailProducts[4]));

        productNow.setQuantity(Double.valueOf(DetailProducts[5]));

        productNow.setQuantity(Double.valueOf(DetailProducts[5]));

        product.add(productNow);

        productList.getItems().add(DetailProducts[0]);

    }

    public static void userAccountInterpret(String userAccountUpdater){

        String[] detailUser = userAccountUpdater.split("#");
        Person person = new Person(detailUser[0],detailUser[1],Integer.valueOf(detailUser[2]));

        Account account = new Account(detailUser[3],detailUser[4],Integer.valueOf(detailUser[5]),Double.valueOf(detailUser[6]));
        System.out.println(Double.valueOf(detailUser[6]));
        userAccount.setAccount(account);
        userAccount.setPerson(person);

    }


    public ArrayList<String> ProductToStringProduct( ArrayList<Product> product){

        ArrayList <String> stringProducts = new ArrayList<>();

        for (int i = 0 ;  i < product.size(); i++ ){

            stringProducts.add(product.get(i).getName());

        }
        return stringProducts;
    }

}
