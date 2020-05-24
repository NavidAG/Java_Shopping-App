package ServerPackag;

import javafx.animation.*;
import javafx.event.EventHandler;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import static java.lang.Math.random;


public class Server extends Application{

    String defualtButtonStyle = "-fx-background-color: \n" +
        "        rgba(0,0,0,0.08),\n" +
        "        linear-gradient(#5a61af, #51536d),\n" +
        "        linear-gradient(#e4fbff 0%,#cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);\n" +
        "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
        "    -fx-background-radius: 5,5,4;\n" +
        "    -fx-padding: 3 30 3 30;\n" +
        "    -fx-text-fill: #242d35;\n" +
        "    -fx-font-size: 14px;";
    String hoveredButtonStyle = "-fx-background-color: \n" +
        "        rgba(0,0,0,0.08),\n" +
        "        linear-gradient(#5a61af, #51536d),\n" +
        "        linear-gradient(#e4fbff 0%,#cee6fb 5%, #a5d3fb 40%, #88c6fb 40%, #d5faff 80%);\n" +
        "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
        "    -fx-background-radius: 5,5,4;\n" +
        "    -fx-padding: 3 30 3 30;\n" +
        "    -fx-text-fill: #242d35;\n" +
        "    -fx-font-size: 14px;";

    Timeline timeline;


    TranslateTransition signInTitleBarTranslateTransition;
    TranslateTransition transformerToNextSceneButtonTranslateTransition;
    RotateTransition transformerToNextSceneButtonRotateTransition1;
    ParallelTransition transformerToNextSceneButtonParallelTransitionTransition;


    private double xOffset = 0;
    private double yOffset = 0;


    Label signInTitle = new Label("Welcom To Sign In Page");

    Label unauthorizedUsernameOrPasswordError = new Label("Username or Password is inaccurate!");
    Label emptyUsernameOrPasswordError = new Label("Username or Password field cannot be empty!");

    PauseTransition pauseTransitionPassword;
    FadeTransition fadeTransitionPassword;
    TranslateTransition translateTransitionPassword;
//    RotateTransition rotateTransitionPassword;
    ScaleTransition scaleTransitionPassword;
    FadeTransition fadeTransitionPassword1;

    SequentialTransition sequentialTransitionPassword;

    SequentialTransition sequentialTransitionInfinitPassword;

    PauseTransition pauseTransitionUserName;
    FadeTransition fadeTransitionUserName;
    TranslateTransition translateTransitionUserName;
//    RotateTransition rotateTransitionUserName;
    ScaleTransition scaleTransitionUserName;
    FadeTransition fadeTransitionUserName1;


    TranslateTransition otherServicesHBoxTranslateTransition;

    SequentialTransition sequentialTransitionUserName;

    SequentialTransition sequentialTransitionInfinitUserName;

    Label names = new Label();
    Label categories = new Label();
    Label measurment = new Label();
    Label prices = new Label();
    Label minimumQuantity = new Label();
    Label quantities = new Label();
    Label adminName = new Label();

    TextField nameTextBox;
    TextField categoryTextBox;
    TextField measurementTextBox;
    TextField priceTextBox;
    TextField minimumQuantityTextBox;
    TextField quantityTextBox;


    DropShadow dropShadow1 = new DropShadow(20,7.0,7.0,Color.BLACK);
    DropShadow dropShadow2 = new DropShadow(20,7.0,7.0,Color.WHITE);
    
    Product iphone = new Product("iPhone", "Electronics","device",899,10,60);
    Product ipad = new Product("iPad", "Electronics","device",999,5,20);
    Product ipod = new Product("iPod", "Electronics","device",499,6,30);
    Product macbook = new Product("MacBook", "Electronics","device",1299,10,40);
    Product MacMini = new Product("Mac Mini", "Electronics","device",1599,3,50);

    TextField usernameTextField;
    PasswordField passwordTextField;

    ServerSocket serverSocket ;

    ArrayList<Thread> clientThreads = new ArrayList<>();

    private final int SERVERPORT = 3000;

    private final int CLIENTLIMIT = 1000000;

    public ArrayList<String> productArrayListString = new ArrayList<>();

    public ArrayList<Product> productArrayListProduct = new ArrayList<>();

    ClientManager clientManager = new ClientManager(serverSocket,productArrayListProduct);

//    public ListView <String> productList = new ListView<> ();

    public ArrayList<String> createStringOfProductArray()
    {

        productArrayListString.add(iphone.getName());
        productArrayListString.add(ipad.getName());
        productArrayListString.add(ipod.getName());
        productArrayListString.add(macbook.getName());
        productArrayListString.add(MacMini.getName());

        return productArrayListString;
    }
    
    private ArrayList<Product> createProductArray()
    {
        productArrayListProduct.add(iphone);
        productArrayListProduct.add(ipad);
        productArrayListProduct.add(ipod);
        productArrayListProduct.add(macbook);
        productArrayListProduct.add(MacMini);
        productArrayListProduct.toString();

        try {
            ClientManager threadClientManager = new ClientManager(serverSocket , productArrayListProduct);
            clientThreads.add(threadClientManager);
            if(clientThreads.size() < CLIENTLIMIT) {

                threadClientManager.start();

            }

        }
        catch(Exception ex){

        }

        return productArrayListProduct;
    }
    
    private Product createProducts(String name ,
                                          String category ,
                                          String measurement ,
                                          double price ,
                                          double quantitiy ,
                                          double minimumQuantity){
        
        Product products = new Product(name, category, measurement , price, minimumQuantity , quantitiy);
//        clientManager.productArrayList.refresh();
        productArrayListProduct.add(products);
        ClientManager.writer.println("AddProduct%" + products.toString());

        return products;
        
    }
    
     private void editProducts(String productLastName,
                                   String productNewName ,
                                   String category,
                                   String measurement ,
                                   double price ,
                                   double minimumQuantity ,
                                   double quantitiy){
        removeProduct(productLastName);
        Product products = createProducts(productNewName, category , measurement , price, minimumQuantity, quantitiy);
        productArrayListProduct.add(products);
        productArrayListString.add(productNewName);
         clientManager.productList.getItems().add(productNewName);
//         clientManager.productList.refresh();

        ClientManager.writer.println("UpdateProduct%" + productArrayListProduct.toString());
         
     }
    
     private void removeProduct(String productName){

        Product product = stringProductToProduct(productName);

         productArrayListProduct.remove(product);
         productArrayListString.remove(productName);
         clientManager.productList.getItems().remove(productName);
//         clientManager.productList.refresh();
     }

    Person ali = new Person("ali","mohamadi",653446810);
    Account aliAccount = new Account("1","1",78794611,1000000000);
    AdminAccount aliAdmin = new AdminAccount(ali,aliAccount);

    Person reza = new Person("reza","mohamadi",656514680);
    Account rezaAccount = new Account("5","5",764594611,1000000000);
    AdminAccount rezaAdmin = new AdminAccount(reza,rezaAccount);

    Person navid = new Person("Navid","admin",689721810);
    Account navidAccount = new Account("12","12",2846731,1000000000);
    AdminAccount navidAdmin = new AdminAccount(navid,navidAccount);

    AdminAccount[] adminAccounts = {aliAdmin,rezaAdmin,navidAdmin};

    Scene signInScene;

    public static Scene aboutScene;
    
    public static Scene shopManagmentScene;
    public static Stage window;

    
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        window = primaryStage;

        Group root = new Group();
        signInScene = new Scene(root, 800, 700, Color.BLACK);

        window.setScene(signInScene);
        Group circles = new Group();
        for (int i = 0; i < 30; i++) {
            Circle circle = new Circle(150, Color.web("white", 0.05));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16));
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }
        Rectangle colors = new Rectangle(signInScene.getWidth(), signInScene.getHeight(),
                new LinearGradient(0f, 0f, 1f, 0f, true, CycleMethod.NO_CYCLE, new Stop[]{
                        new Stop(0, Color.web("#f8bd55")),
                        new Stop(0.14, Color.web("#c0fe56")),
                        new Stop(0.28, Color.web("#5dfbc1")),
                        new Stop(0.43, Color.web("#64c2f8")),
                        new Stop(0.57, Color.web("#be4af7")),
                        new Stop(0.71, Color.web("#ed5fc2")),
                        new Stop(0.85, Color.web("#ef504c")),
                        new Stop(1, Color.web("#f2660f")),}));
        Group blendModeGroup =
                new Group(new Group(new Rectangle(signInScene.getWidth(), signInScene.getHeight(), Color.BLACK), circles), colors);
        colors.setBlendMode(BlendMode.OVERLAY);
        root.getChildren().add(blendModeGroup);
        circles.setEffect(new BoxBlur(10, 100, 3));
        timeline = new Timeline();
        for (Node circle : circles.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                            new KeyValue(circle.translateXProperty(), random() * 800),
                            new KeyValue(circle.translateYProperty(), random() * 700)),
                    new KeyFrame(new Duration(30000), // set end position at 40s
                            new KeyValue(circle.translateXProperty(), random() * 800),
                            new KeyValue(circle.translateYProperty(), random() * 700)));
        }
        // play 40s of animation
        timeline.play();

        try {
            serverSocket = new ServerSocket(SERVERPORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server Created successfully");


        //sign up scene

        //BorderPane signUpPane = new BorderPane();
        VBox signUpPane = new VBox();
        BackgroundImage signUpBackground = new BackgroundImage(new Image(new File("Fbackground.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        BorderPane signInTitle = signInTitleBar();

        signInTitleBarTranslateTransition = new TranslateTransition(Duration.millis(4000),signInTitle);
        signInTitleBarTranslateTransition.setFromY(-130f);
        signInTitleBarTranslateTransition.setToY(0);
        signInTitleBarTranslateTransition.setAutoReverse(true);
        signInTitleBarTranslateTransition.play();

        signUpPane.getChildren().add(signInTitle);
        signUpPane.getChildren().add(signInAuthentication());
        HBox otherServicesHBox = otherServices();
        otherServicesHBoxTranslateTransition = new TranslateTransition(Duration.millis(4000),otherServicesHBox);
        otherServicesHBoxTranslateTransition.setFromY(80f);
        otherServicesHBoxTranslateTransition.setToY(0);
        otherServicesHBoxTranslateTransition.setAutoReverse(true);
        otherServicesHBoxTranslateTransition.play();
        signUpPane.getChildren().add(otherServicesHBox);
        signUpPane.setSpacing(182.5);
//        signUpPane.setBackground(new Background(signUpBackground));
        
        //loged scene

        BorderPane shopManagmentPane = new BorderPane(); 
        shopManagmentPane.setStyle("-fx-border-width: 5;-fx-border-color: transparent;-fx-border-radius: 4;");
        shopManagmentPane.setTop(shopTitleBar());
        shopManagmentPane.setLeft(productsShelve());
        shopManagmentPane.setCenter(productDescription());
        shopManagmentPane.setRight(productPicture());
        //BackgroundImage myba = new BackgroundImage(new Image(new File("background3.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        shopManagmentPane.setBackground(new Background(signUpBackground));
        shopManagmentScene = new Scene(shopManagmentPane, 1200, 700);
        shopManagmentPane.setBottom(controlerButtons());


        BorderPane aboutPane = new BorderPane();
        aboutPane.setStyle("-fx-border-width: 5;-fx-border-color: transparent;-fx-border-radius: 4;");
        BackgroundImage aboutBackground = new BackgroundImage(new Image(new File("background1.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        aboutPane.setBackground(new Background(aboutBackground));
        aboutScene = new Scene(aboutPane, 800, 700);
        aboutPane.setBottom(controlerAboutPaneButtons());


        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        window.setTitle("Storage");
        window.setResizable(false);
        window.getIcons().add(new Image(new File("StorageIcon.png").toURI().toString()));
//        window.setScene(signInScene);
        root.getChildren().add(signUpPane);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();

    }
    
    //sign up page:------------------------------------------------------------
    public BorderPane signInTitleBar() {
        BorderPane signInTitlePane = new BorderPane();
        signInTitlePane.setStyle("-fx-background-color: rgba(90,90,90,.3);");
        BorderPane minimizeAndExitPane = new BorderPane();
        signInTitlePane.setPadding(new Insets(20, 20, 20, 20));

        signInTitle.setFont(Font.font("Arial Bold", 30));
        signInTitle.setTextFill(Color.WHITE);
        textAnimation(signInTitle,"Welcom To Sign In Page",5000);
        signInTitle.setEffect(dropShadow2);

        Button exitButton = new Button("EXIT");
        exitButton.setCursor(Cursor.HAND);
        exitButton.setPrefSize(100, 30);
        exitButton.setStyle("-fx-background-color: transparent;-fx-border-width: 1;-fx-border-color: white;-fx-border-radius: 2;-fx-text-fill: white");
        Button minimizeButton = new Button("MINIMIZE");
        minimizeButton.setCursor(Cursor.HAND);
        minimizeButton.setPrefSize(100, 25);
        minimizeButton.setStyle("-fx-background-color: transparent;-fx-border-width: 1;-fx-border-color: white;-fx-border-radius: 2;-fx-text-fill: white");
        EventHandler<ActionEvent> exitFunction;
        exitFunction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        };

        EventHandler<ActionEvent> minimizeFunction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setIconified(true);
            }
        };
        exitButton.setOnAction(exitFunction);
        minimizeButton.setOnAction(minimizeFunction);
        minimizeAndExitPane.setLeft(minimizeButton);
        minimizeAndExitPane.setRight(exitButton);
        signInTitlePane.setLeft(signInTitle);
        signInTitlePane.usesMirroring();
        signInTitlePane.setRight(minimizeAndExitPane);
        return signInTitlePane;
    }
    
    public GridPane signInAuthentication() {
        GridPane authenticationPane = new GridPane();
        authenticationPane.setHgap(10);
        authenticationPane.setVgap(10);
        authenticationPane.setPadding(new Insets(15, 0, 0, 250));
        authenticationPane.setHalignment(authenticationPane, HPos.CENTER);
        authenticationPane.setValignment(authenticationPane, VPos.CENTER);            

        usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        usernameTextField.setPrefSize(300, 40);        
        usernameTextField.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
        authenticationPane.add(usernameTextField, 1, 1);
        
        passwordTextField = new PasswordField();
        authenticationPane.add(passwordTextField, 1, 3);
        passwordTextField.setPrefSize(300, 40);
        passwordTextField.setPromptText("Password");
        passwordTextField.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
        Button transformerToNextSceneButton = new Button("Next");
        transformerToNextSceneButton.setCursor(Cursor.HAND);
        authenticationPane.setHalignment(transformerToNextSceneButton, HPos.RIGHT);



        transformerToNextSceneButtonTranslateTransition = new TranslateTransition(Duration.millis(4000));
        transformerToNextSceneButtonTranslateTransition.setFromX(-800f);
        transformerToNextSceneButtonTranslateTransition.setToX(0);
        transformerToNextSceneButtonTranslateTransition.setFromY(-800f);
        transformerToNextSceneButtonTranslateTransition.setToY(0);
        transformerToNextSceneButtonTranslateTransition.setAutoReverse(true);
//        transformerToNextSceneButtonTranslateTransition.play();

        transformerToNextSceneButtonRotateTransition1 = new RotateTransition(Duration.millis(4000));
        transformerToNextSceneButtonRotateTransition1.setByAngle(2880f);
        transformerToNextSceneButtonRotateTransition1.setAutoReverse(false);

        transformerToNextSceneButtonParallelTransitionTransition = new ParallelTransition(transformerToNextSceneButton,transformerToNextSceneButtonTranslateTransition, transformerToNextSceneButtonRotateTransition1);
        transformerToNextSceneButtonParallelTransitionTransition.play();


        setTransitionPassword(passwordTextField);

//        pauseTransitionPassword = new PauseTransition(Duration.millis(500));
//        fadeTransitionPassword = new FadeTransition(Duration.millis(1000));
//        fadeTransitionPassword.setFromValue(1.0f);
//        fadeTransitionPassword.setToValue(0.3f);
//        fadeTransitionPassword.setAutoReverse(true);
//        translateTransitionPassword = new TranslateTransition(Duration.millis(1000));
//        translateTransitionPassword.setFromX(1000f);
//        translateTransitionPassword.setToX(0);
//        translateTransitionPassword.setAutoReverse(true);
//        rotateTransitionPassword = new RotateTransition(Duration.millis(1000));
//        rotateTransitionPassword.setByAngle(180f);
//        rotateTransitionPassword.setCycleCount(2);
//        rotateTransitionPassword.setAutoReverse(true);
//        scaleTransitionPassword = new ScaleTransition(Duration.millis(1000));
//        scaleTransitionPassword.setByX(1.5f);
//        scaleTransitionPassword.setByY(1.5f);
//        scaleTransitionPassword.setCycleCount(2);
//        scaleTransitionPassword.setAutoReverse(true);
//        fadeTransitionPassword1 = new FadeTransition(Duration.millis(1000),passwordTextField);
//        fadeTransitionPassword1.setFromValue(0.3f);
//        fadeTransitionPassword1.setToValue(1.0f);
//        fadeTransitionPassword1.setAutoReverse(true);
//
//
//        sequentialTransitionPassword = new SequentialTransition(passwordTextField,
//                                                                 pauseTransitionPassword,
//                                                                  fadeTransitionPassword,
//                                                                   translateTransitionPassword,
//                                                                    rotateTransitionPassword,
//                                                                     scaleTransitionPassword);
//        sequentialTransitionPassword.play();


        sequentialTransitionInfinitPassword = new SequentialTransition(passwordTextField,fadeTransitionPassword,fadeTransitionPassword1);
        sequentialTransitionInfinitPassword.setCycleCount(Timeline.INDEFINITE);
        sequentialTransitionInfinitPassword.play();

//      UserName
        setTransitionUserName(usernameTextField);

//        pauseTransitionUserName = new PauseTransition(Duration.millis(500));
//        fadeTransitionUserName = new FadeTransition(Duration.millis(1000));
//        fadeTransitionUserName.setFromValue(1.0f);
//        fadeTransitionUserName.setToValue(0.3f);
//        fadeTransitionUserName.setAutoReverse(true);
//        translateTransitionUserName = new TranslateTransition(Duration.millis(1000));
//        translateTransitionUserName.setFromX(-1000f);
//        translateTransitionUserName.setToX(0);
//        translateTransitionUserName.setAutoReverse(true);
//        rotateTransitionUserName = new RotateTransition(Duration.millis(1000));
//        rotateTransitionUserName.setByAngle(180f);
//        rotateTransitionUserName.setCycleCount(2);
//        rotateTransitionUserName.setAutoReverse(true);
//        scaleTransitionUserName = new ScaleTransition(Duration.millis(1000));
//        scaleTransitionUserName.setByX(1.5f);
//        scaleTransitionUserName.setByY(1.5f);
//        scaleTransitionUserName.setCycleCount(2);
//        scaleTransitionUserName.setAutoReverse(true);
//        fadeTransitionUserName1 = new FadeTransition(Duration.millis(1000));
//        fadeTransitionUserName1.setFromValue(0.3f);
//        fadeTransitionUserName1.setToValue(1.0f);
//        fadeTransitionUserName1.setAutoReverse(true);
//
//
//        sequentialTransitionUserName = new SequentialTransition(usernameTextField,
//                                                                 pauseTransitionUserName,
//                                                                  fadeTransitionUserName,
//                                                                   translateTransitionUserName,
//                                                                    rotateTransitionUserName,
//                                                                     scaleTransitionUserName);
//        sequentialTransitionUserName.play();


        sequentialTransitionInfinitUserName = new SequentialTransition(usernameTextField,fadeTransitionUserName,fadeTransitionUserName1);
        sequentialTransitionInfinitUserName.setCycleCount(Timeline.INDEFINITE);
        sequentialTransitionInfinitUserName.play();

        
        transformerToNextSceneButton.setPrefSize(100, 30);
        authenticationPane.add(transformerToNextSceneButton, 1, 6);
        transformerToNextSceneButton.setStyle(defualtButtonStyle);
        transformerToNextSceneButton.setOnMouseEntered(e -> transformerToNextSceneButton.setStyle(hoveredButtonStyle));
        transformerToNextSceneButton.setOnMouseExited(e -> transformerToNextSceneButton.setStyle(defualtButtonStyle));




        emptyUsernameOrPasswordError.setStyle("-fx-text-fill: rgb(245,196,82);");
        emptyUsernameOrPasswordError.setVisible(false);
        authenticationPane.add(emptyUsernameOrPasswordError, 1, 5);

        authenticationPane.add(unauthorizedUsernameOrPasswordError, 1, 5);
        unauthorizedUsernameOrPasswordError.setVisible(false);
        unauthorizedUsernameOrPasswordError.setStyle("-fx-text-fill: rgb(245,196,82);");
        EventHandler<ActionEvent> transformerToNextScene;
        transformerToNextScene = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty())
                {
                    emptyUsernameOrPasswordError.setVisible(true);
                    unauthorizedUsernameOrPasswordError.setVisible(false);
                }
                else {

                    for (int i = 0; i < adminAccounts.length; i++) {
                        if (usernameTextField.getText().equals(adminAccounts[i].getAccount().getUserName()) && passwordTextField.getText().equals(adminAccounts[i].getAccount().getPassword())) {

                            updatePersonStatus();
                            window.setScene(shopManagmentScene);
                            window.getIcons().add(new Image(new File("StorageIcon.png").toURI().toString()));
                            unauthorizedUsernameOrPasswordError.setVisible(false);
                            emptyUsernameOrPasswordError.setVisible(false);

                        }

                    }
                    TranslateTransition translateTransitionPasswordFailed = new TranslateTransition(Duration.millis(80));
                    translateTransitionPasswordFailed.setFromX(-10f);
                    translateTransitionPasswordFailed.setToX(10f);
                    translateTransitionPasswordFailed.setFromY(-5f);
                    translateTransitionPasswordFailed.setToY(5f);
                    translateTransitionPasswordFailed.setCycleCount(5);
                    translateTransitionPasswordFailed.setAutoReverse(true);
                    TranslateTransition translateTransitionPasswordFailed1 = new TranslateTransition(Duration.millis(80));
                    translateTransitionPasswordFailed1.setFromX(10f);
                    translateTransitionPasswordFailed1.setToX(0);
                    translateTransitionPasswordFailed1.setFromY(5f);
                    translateTransitionPasswordFailed1.setToY(0);
                    translateTransitionPasswordFailed1.setAutoReverse(true);
                    SequentialTransition sequentialTransitionPasswordFailed = new SequentialTransition(passwordTextField,
                            translateTransitionPasswordFailed,
                            translateTransitionPasswordFailed1
                            );
                    sequentialTransitionPasswordFailed.play();

                    TranslateTransition translateTransitionUserNameFailed = new TranslateTransition(Duration.millis(80));
                    translateTransitionUserNameFailed.setFromX(-10f);
                    translateTransitionUserNameFailed.setToX(10f);
                    translateTransitionUserNameFailed.setFromY(-5f);
                    translateTransitionUserNameFailed.setToY(5f);
                    translateTransitionUserNameFailed.setCycleCount(5);
                    translateTransitionUserNameFailed.setAutoReverse(true);
                    TranslateTransition translateTransitionUserNameFailed1 = new TranslateTransition(Duration.millis(80));
                    translateTransitionUserNameFailed1.setFromX(10f);
                    translateTransitionUserNameFailed1.setToX(0);
                    translateTransitionUserNameFailed1.setFromY(5f);
                    translateTransitionUserNameFailed1.setToY(0);
                    translateTransitionUserNameFailed1.setAutoReverse(true);
                    SequentialTransition sequentialTransitionUserNameFailed = new SequentialTransition(usernameTextField,
                            translateTransitionUserNameFailed,
                            translateTransitionUserNameFailed1
                    );
                    sequentialTransitionUserNameFailed.play();




                    unauthorizedUsernameOrPasswordError.setVisible(true);
                    emptyUsernameOrPasswordError.setVisible(false);

                }
            }

            
        };
        transformerToNextSceneButton.setOnAction(transformerToNextScene);
        passwordTextField.setOnKeyPressed(e -> {
            if(e.getCode()== KeyCode.ENTER)
            {
              transformerToNextSceneButton.fire();
            }
        });
        usernameTextField.setOnKeyPressed(e->{
           if(e.getCode()== KeyCode.ENTER) 
           {
              transformerToNextSceneButton.fire();
           }
        });

        return authenticationPane;
    }
    public HBox otherServices(){
        HBox otherServicesHbox = new HBox();
        otherServicesHbox.setStyle("-fx-background-color: rgba(90,90,90,.3);");
        otherServicesHbox.setPadding(new Insets(5));
        otherServicesHbox.setSpacing(5);
        Button aboutButton = new Button("About");
        aboutButton.setCursor(Cursor.HAND);
        aboutButton.setPrefSize(75, 30);
        aboutButton.setStyle("-fx-background-color: transparent;-fx-border-width: 1;-fx-border-color: white;-fx-border-radius: 2;-fx-text-fill: white");
        Button TermsOfUseButton = new Button("Terms of Use");
        TermsOfUseButton.setCursor(Cursor.HAND);
        TermsOfUseButton.setPrefSize(120, 30);
        TermsOfUseButton.setStyle("-fx-background-color: transparent;-fx-border-width: 1;-fx-border-color: white;-fx-border-radius: 2;-fx-text-fill: white");

        EventHandler<ActionEvent> aboutFunction;
        aboutFunction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                window.setScene(aboutScene);
                window.getIcons().add(new Image(new File("StorageIcon.png").toURI().toString()));


                window.setTitle("Storage");
                window.setResizable(false);
                window.getIcons().add(new Image(new File("StorageIcon.png").toURI().toString()));
                window.show();
            }
        };

        aboutButton.setOnAction(aboutFunction);
        otherServicesHbox.getChildren().add(aboutButton);
        otherServicesHbox.getChildren().add(TermsOfUseButton);
        otherServicesHbox.setSpacing(595);
        otherServicesHbox.setCenterShape(true);
        otherServicesHbox.setAlignment(Pos.CENTER);
        return otherServicesHbox;
    }
    
    //store page:---------------------------------------------------------------
    public BorderPane shopTitleBar() {

        BorderPane userInfo = new BorderPane();
        HBox consumerInfo = new HBox();
        consumerInfo.setPadding(new Insets(20));
        consumerInfo.setSpacing(30);
        consumerInfo.getChildren().addAll(adminName);
        adminName.setFont(Font.font("Arial Bold", 30));

        HBox productTitleHBox = new HBox();
        productTitleHBox.setPadding(new Insets(20, 20, 20, 20));
        productTitleHBox.setSpacing(5);
        Text productTitle = new Text("Products");
        productTitle.setFont(Font.font("Arial Bold", 30));
        productTitle.setFill(Color.BLACK);

        productTitleHBox.getChildren().add(productTitle);
        userInfo.setLeft(productTitleHBox);
        userInfo.setRight(consumerInfo);        
        return userInfo;
    }
    
    public BorderPane productsShelve(){
        BorderPane productsListPane = new BorderPane();
        productsListPane.setStyle("-fx-border-width: 2;-fx-border-color: rgba(85,85,85,.4);-fx-border-radius: 4;");
        clientManager.productList.setPrefSize(400, 600);
        clientManager.productList.setFixedCellSize(50);
        clientManager.productList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productArrayListProduct = createProductArray();
        clientManager.productList.getItems().addAll(createStringOfProductArray());
        productsListPane.setCenter(clientManager.productList);
        addItems(clientManager.productList);

        HBox columnTitleHBoxForItems = new HBox();
        columnTitleHBoxForItems.setPadding(new Insets(10, 10, 10, 10));
        columnTitleHBoxForItems.setSpacing(5);
        Text itemstitle = new Text("Items");
        columnTitleHBoxForItems.getChildren().add(itemstitle);
        productsListPane.setTop(columnTitleHBoxForItems);
        columnTitleHBoxForItems.setStyle("-fx-background-color: rgba(85,85,85,.4);");
        itemstitle.setFont(Font.font("Arial Bold", 30));
        itemstitle.setFill(Color.WHITE);
        return productsListPane;
    }
    
    public BorderPane productDescription(){
        BorderPane productsDescriptionPane = new BorderPane();
        productsDescriptionPane.setPrefWidth(1000);
        productsDescriptionPane.setStyle("-fx-border-width: 2;-fx-border-color: rgba(0,0,0,.4);-fx-border-radius: 4;");
        HBox columnTitleForInformationHbox = new HBox();
        columnTitleForInformationHbox.setPadding(new Insets(10, 10, 10, 10));
        columnTitleForInformationHbox.setSpacing(5);
        Text informationTitle = new Text("Information");
        columnTitleForInformationHbox.getChildren().add(informationTitle);
        productsDescriptionPane.setTop(columnTitleForInformationHbox);
        columnTitleForInformationHbox.setStyle("-fx-background-color: rgba(0,0,0,.4);");
        informationTitle.setFont(Font.font("Arial Bold", 30));
        informationTitle.setFill(Color.WHITE);
        VBox informationVBox = new VBox();
        informationVBox.setPadding(new Insets(10, 10, 10, 10));
//        informationVBox.setSpacing(30);
        
        
        Text productNameInformation = new Text("Name:");
        Text productCategoryInformation = new Text("Category:");
        Text measurmentText = new Text("Measurment:");
        Text productPriceInformation = new Text("Price:");
        Text minimumQuantityText = new Text("Minimum Quantity:");
        Text productQuantityInformation = new Text("Quantity:");
        productNameInformation.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        productCategoryInformation.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        measurmentText.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        productPriceInformation.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        minimumQuantityText.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        productQuantityInformation.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));

        informationVBox.setSpacing(36);

        informationVBox.getChildren().addAll(productNameInformation,
                                              productCategoryInformation,
                                               measurmentText,
                                                productPriceInformation,
                                                 minimumQuantityText,
                                                  productQuantityInformation
                                                         );
        
        VBox productValueVbox = new VBox();

//        Light.Distant light = new Light.Distant();
//        light.setElevation(50);
//        light.setAzimuth(50);
//        Lighting lighting = new Lighting();
//        lighting.setLight(light);
//        lighting.setSurfaceScale(5);
//        Reflection reflection = new Reflection();
//        reflection.setInput(lighting);



//        DropShadow shdw = new DropShadow();
//        shdw.setBlurType(BlurType.GAUSSIAN);
//        shdw.setColor(Color.GAINSBORO);
//        shdw.setRadius(10);
//        shdw.setSpread(0.12);
//        shdw.setHeight(10);
//        shdw.setWidth(10);


        names.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        categories.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        measurment.setFont(Font.font("Helvetica",FontWeight.NORMAL, 24));
        prices.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        minimumQuantity.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        quantities.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        names.setEffect(dropShadow1);
        categories.setEffect(dropShadow1);
        measurment.setEffect(dropShadow1);
        prices.setEffect(dropShadow1);
        minimumQuantity.setEffect(dropShadow1);
        quantities.setEffect(dropShadow1);
        productValueVbox.getChildren().addAll(names, categories, measurment, prices, minimumQuantity, quantities);
        productsDescriptionPane.setRight(productValueVbox);
        productValueVbox.setPadding(new Insets(10, 10, 10, 10));
        productValueVbox.setSpacing(33);
        BorderPane managmentPane = new BorderPane();
        HBox managmentHbox = new HBox();
        managmentHbox.setAlignment(Pos.CENTER);
        managmentHbox.setSpacing(4);
        ImageView editIcon = new ImageView(new Image(new File("src/edit.png").toURI().toString()));
        editIcon.setFitWidth(30); editIcon.setFitHeight(30);
        Button editButton = new Button();
        editButton.setGraphic(editIcon);
        editButton.setPrefSize(20, 20);
        editButton.setCursor(Cursor.HAND);
        editButton.setStyle(defualtButtonStyle);
        editButton.setOnMouseEntered(e -> editButton.setStyle(hoveredButtonStyle));
        editButton.setOnMouseExited(e -> editButton.setStyle(defualtButtonStyle));
        
        ImageView trashIcon = new ImageView(new Image(new File("src/trash.png").toURI().toString()));
        trashIcon.setFitHeight(30); trashIcon.setFitWidth(30);
        Button trashButton = new Button();
        trashButton.setGraphic(trashIcon);
        trashButton.setPrefSize(20, 20);
        trashButton.setCursor(Cursor.HAND);
        trashButton.setStyle(defualtButtonStyle);
        trashButton.setOnMouseEntered(e -> trashButton.setStyle(hoveredButtonStyle));
        trashButton.setOnMouseExited(e -> trashButton.setStyle(defualtButtonStyle));
        EventHandler<ActionEvent> trashFunction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            
            String newValue = clientManager.productList.getSelectionModel().getSelectedItem();

            removeProduct(newValue);

//            clientManager.productList.refresh();
        }
        };
        trashButton.setOnAction(trashFunction);
        ImageView addIcon = new ImageView(new Image(new File("src/add.png").toURI().toString()));
        addIcon.setFitHeight(30); addIcon.setFitWidth(30);
        Button addButton = new Button();
        addButton.setGraphic(addIcon);
        addButton.setCursor(Cursor.HAND);
        addButton.setPrefSize(20, 20);
        addButton.setStyle(defualtButtonStyle);
        addButton.setOnMouseEntered(e -> addButton.setStyle(hoveredButtonStyle));
        addButton.setOnMouseExited(e -> addButton.setStyle(defualtButtonStyle));
        
        
        
        managmentHbox.getChildren().addAll(editButton, addButton, trashButton);
        managmentPane.setCenter(managmentHbox);
        managmentHbox.setPadding(new Insets(5));
        EventHandler<ActionEvent> editFunction = (ActionEvent event) -> {
            BorderPane editPane = new BorderPane();
            BackgroundImage editBackground = new BackgroundImage(new Image(new File("Fbackground.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

            editPane.setBackground(new Background(editBackground));
            HBox titleBar = new HBox();
            titleBar.setPadding(new Insets(20, 20, 20, 20));
            titleBar.setSpacing(5);
            Text UItitle = new Text("Edit");
            UItitle.setFont(Font.font("Arial Bold", 30));
            UItitle.setFill(Color.BLACK);
            titleBar.getChildren().add(UItitle);
            editPane.setTop(titleBar);

            VBox productLabel = new VBox();
            productLabel.setPadding(new Insets(10, 10, 10, 10));
            productLabel.setSpacing(40);
            Text name = new Text("Name:");
            Text category = new Text("Category:");
            Text measurment = new Text("Measurment:");
            Text price = new Text("Price:");
            Text minimumQuantity = new Text("Minimum Quantity:");
            Text quantity = new Text("Quantity:");
            name.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
            category.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
            measurment.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
            price.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
            minimumQuantity.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
            quantity.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
            productLabel.getChildren().addAll(name, category, measurment, price, minimumQuantity, quantity);
            editPane.setLeft(productLabel);
            //..............

            VBox productTextVbox = new VBox();
            productTextVbox.setPadding(new Insets(10, 10, 10, 10));
            productTextVbox.setSpacing(30);
//            addItems(productList);
            String newValue = clientManager.productList.getSelectionModel().getSelectedItem();

            nameTextBox.setPrefSize(300, 40);
            nameTextBox.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
            categoryTextBox.setPrefSize(300, 40);
            categoryTextBox.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
            measurementTextBox.setPrefSize(300, 40);
            measurementTextBox.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
            priceTextBox.setPrefSize(300, 40);
            priceTextBox.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
            minimumQuantityTextBox.setPrefSize(300, 40);
            minimumQuantityTextBox.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
            quantityTextBox.setPrefSize(300, 40);
            quantityTextBox.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
            HBox commandHbox = new HBox();
            commandHbox.setPadding(new Insets(10));
            commandHbox.setSpacing(6);
            Button confirmButtonForEdit = new Button("Confirm");
            confirmButtonForEdit.setStyle(defualtButtonStyle);
            confirmButtonForEdit.setOnMouseEntered(e->confirmButtonForEdit.setStyle(hoveredButtonStyle));
            confirmButtonForEdit.setOnMouseExited(e-> confirmButtonForEdit.setStyle(defualtButtonStyle));
            confirmButtonForEdit.setCursor(Cursor.HAND);
            Button cancelButton = new Button("Cancel");
            cancelButton.setStyle(defualtButtonStyle);
            Label emptyLabel = new Label("The Textfields cannot be empthy!");
            emptyLabel.setStyle("-fx-text-fill: rgb(245,196,82);");
            emptyLabel.setVisible(false);
            cancelButton.setOnMouseEntered(e->cancelButton.setStyle(hoveredButtonStyle));
            cancelButton.setOnMouseExited(e-> cancelButton.setStyle(defualtButtonStyle));
            cancelButton.setCursor(Cursor.HAND);
            commandHbox.getChildren().addAll(emptyLabel,cancelButton,confirmButtonForEdit);

            commandHbox.setAlignment(Pos.CENTER_RIGHT);
            editPane.setBottom(commandHbox);
            productTextVbox.getChildren().addAll(nameTextBox, categoryTextBox, measurementTextBox, priceTextBox, minimumQuantityTextBox, quantityTextBox);
            editPane.setRight(productTextVbox);

            Stage editStage = new Stage();
            EventHandler<ActionEvent> cancelFunction = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    editStage.close();
                }
            };
            cancelButton.setOnAction(cancelFunction);
            EventHandler<ActionEvent> confirmFunctionForEdit;
            confirmFunctionForEdit = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(nameTextBox.getText().equals("") || quantityTextBox.getText().equals("") || priceTextBox.getText().equals("") || categoryTextBox.getText().equals("")){
                        emptyLabel.setVisible(true);
                    }else{
                        emptyLabel.setVisible(false);
                        editProducts(newValue,nameTextBox.getText(), categoryTextBox.getText(), measurementTextBox.getText() , Double.valueOf(priceTextBox.getText()), Double.valueOf(minimumQuantityTextBox.getText()) ,Double.valueOf(quantityTextBox.getText()));
//                        clientManager.productList.refresh();
                        editStage.close();

                    }
                }
            };
            confirmButtonForEdit.setOnAction(confirmFunctionForEdit);
            editStage.setResizable(false);
            editStage.setTitle("Edit");
            Scene editScene= new Scene(editPane, 750, 650);
            editStage.setScene(editScene);
            editStage.getIcons().add(new Image(new File("Editicon.png").toURI().toString()));
            editStage.show();
        };
        //-----------------------------------------
        EventHandler<ActionEvent> addFunction;
        addFunction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorderPane addPane = new BorderPane();
                BackgroundImage editBackground = new BackgroundImage(new Image(new File("Fbackground.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                
                addPane.setBackground(new Background(editBackground));
                HBox titleBar = new HBox();
                titleBar.setPadding(new Insets(20, 20, 20, 20));
                titleBar.setSpacing(5);
                Text UItitle = new Text("Add");
                UItitle.setFont(Font.font("Arial Bold", 30));
                UItitle.setFill(Color.BLACK);
                titleBar.getChildren().add(UItitle);
                addPane.setTop(titleBar);
                
                VBox productLabel = new VBox();
                productLabel.setPadding(new Insets(10, 10, 10, 10));
                productLabel.setSpacing(40);
                Text name = new Text("Name:");
                Text category = new Text("Category:");
                Text measurment = new Text("Measurment:");
                Text price = new Text("Price:");
                Text minimumQuantity = new Text("Minimum Quantity:");
                Text quantity = new Text("Quantity:");
                name.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                category.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                measurment.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                price.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                minimumQuantity.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                quantity.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                productLabel.getChildren().addAll(name, category, measurment, price, minimumQuantity, quantity);
                addPane.setLeft(productLabel);
                //..............
                
                VBox productTextVbox = new VBox();
                productTextVbox.setPadding(new Insets(10, 10, 10, 10));
                productTextVbox.setSpacing(30);
                ListView <String> product_list = new ListView<> ();
                
                TextField nameTextBox1 = new TextField();
                TextField categoryTextBox1 = new TextField();
                TextField measurementTextBox1 = new TextField();
                TextField priceTextBox1 = new TextField();
                TextField minimumQuantityTextBox1 = new TextField();
                TextField quantityTextBox1 = new TextField();
                nameTextBox1.setPrefSize(300, 40);
                nameTextBox1.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
                categoryTextBox1.setPrefSize(300, 40);
                categoryTextBox1.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
                measurementTextBox1.setPrefSize(300, 40);
                measurementTextBox1.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
                priceTextBox1.setPrefSize(300, 40);
                priceTextBox1.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
                minimumQuantityTextBox1.setPrefSize(300, 40);
                minimumQuantityTextBox1.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
                quantityTextBox1.setPrefSize(300, 40);
                quantityTextBox1.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");

                HBox commandHbox = new HBox();
                commandHbox.setPadding(new Insets(10));
                commandHbox.setSpacing(6);
                Button confirmButtonForAdd = new Button("Confirm");
                confirmButtonForAdd.setStyle(defualtButtonStyle);
                confirmButtonForAdd.setOnMouseEntered(e->confirmButtonForAdd.setStyle(hoveredButtonStyle));
                confirmButtonForAdd.setOnMouseExited(e-> confirmButtonForAdd.setStyle(defualtButtonStyle));
                confirmButtonForAdd.setCursor(Cursor.HAND);
                
                Label emptyLabel = new Label("The Textfields cannot be empthy!");
                emptyLabel.setStyle("-fx-text-fill: rgb(245,196,82);");
                emptyLabel.setVisible(false);
                
                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle(defualtButtonStyle);
                cancelButton.setOnMouseEntered(e->cancelButton.setStyle(hoveredButtonStyle));
                cancelButton.setOnMouseExited(e-> cancelButton.setStyle(defualtButtonStyle));
                cancelButton.setCursor(Cursor.HAND);
                commandHbox.getChildren().addAll(emptyLabel,cancelButton,confirmButtonForAdd);
                commandHbox.setAlignment(Pos.CENTER_RIGHT);
                addPane.setBottom(commandHbox);
                productTextVbox.getChildren().addAll(nameTextBox1, categoryTextBox1, measurementTextBox1, priceTextBox1 , minimumQuantityTextBox1, quantityTextBox1);
                addPane.setRight(productTextVbox);
                Stage addStage = new Stage();
                EventHandler<ActionEvent> confirmFunctionForAdd;
                confirmFunctionForAdd = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        if(nameTextBox1.getText().equals("") ||
                            categoryTextBox1.getText().equals("") ||
                             measurementTextBox1.getText().equals("") ||
                              priceTextBox1.getText().equals("") ||
                               minimumQuantityTextBox1.getText().equals("") ||
                                quantityTextBox1.getText().equals("") ){

                            emptyLabel.setVisible(true);

                        }
                        else{
                            emptyLabel.setVisible(false);
                            Product products = createProducts(nameTextBox1.getText(), categoryTextBox1.getText(), measurementTextBox1.getText() , Double.valueOf(priceTextBox1.getText()), Double.valueOf(minimumQuantityTextBox1.getText()) ,Double.valueOf(quantityTextBox1.getText()));
                            productArrayListProduct.add(products);
                            addStage.close();
                            clientManager.productList.getItems().add(products.getName());
//                            clientManager.productList.refresh();
                        }
                    }
                };
                 EventHandler<ActionEvent> cancelFunction = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    addStage.close();
                }
            };
            cancelButton.setOnAction(cancelFunction);
                confirmButtonForAdd.setOnAction(confirmFunctionForAdd);
                addStage.setResizable(false);
                addStage.setTitle("Add");
                Scene addScene= new Scene(addPane, 650, 650);
                addStage.setScene(addScene);
                addStage.getIcons().add(new Image(new File("src\\Add.png").toURI().toString()));
                addStage.show();
            }
        };
        
        editButton.setOnAction(editFunction);
        addButton.setOnAction(addFunction);
        
        
        productsDescriptionPane.setLeft(informationVBox);
        productsDescriptionPane.setBottom(managmentPane);
        
        return productsDescriptionPane;
    }
    
    public BorderPane productPicture(){
        BorderPane productsImage = new BorderPane();
        productsImage.setStyle("-fx-border-width: 2;-fx-border-color: rgba(100,100,100,.6);-fx-border-radius: 4;");
        HBox columnTitleForImagHbox = new HBox();
        columnTitleForImagHbox.setPrefWidth(398);
        columnTitleForImagHbox.setPadding(new Insets(10, 10, 10, 10));
        columnTitleForImagHbox.setSpacing(5);
        Text pictureTitle = new Text("Picture");
        columnTitleForImagHbox.getChildren().add(pictureTitle);
        productsImage.setTop(columnTitleForImagHbox);
        columnTitleForImagHbox.setStyle("-fx-background-color: rgba(100,100,100,.6);");
        pictureTitle.setFont(Font.font("Arial Bold", 30));
        pictureTitle.setFill(Color.WHITE);
        return productsImage;
    }
    public BorderPane controlerButtons(){
        BorderPane shopManagerBottomPane = new BorderPane();
        Button logOutButton = new Button("Log out");
        logOutButton.setCursor(Cursor.HAND);
        logOutButton.setStyle(defualtButtonStyle);
        logOutButton.setOnMouseEntered(e -> logOutButton.setStyle(hoveredButtonStyle));
        logOutButton.setOnMouseExited(e -> logOutButton.setStyle(defualtButtonStyle));
        shopManagerBottomPane.setLeft(logOutButton);
        
        EventHandler<ActionEvent> transformerToPreviousScene = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                redreshSignInPage();
            }
        };
        logOutButton.setOnAction(transformerToPreviousScene);
        
       return shopManagerBottomPane;
    }
    public BorderPane controlerAboutPaneButtons(){
        BorderPane AboutBottomPane = new BorderPane();
        Button backAboutButton = new Button("Back");
        backAboutButton.setCursor(Cursor.HAND);
        backAboutButton.setStyle(defualtButtonStyle);
        backAboutButton.setOnMouseEntered(e -> backAboutButton.setStyle(hoveredButtonStyle));
        backAboutButton.setOnMouseExited(e -> backAboutButton.setStyle(defualtButtonStyle));
        AboutBottomPane.setLeft(backAboutButton);

        EventHandler<ActionEvent> transformerToPreviousScene = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                redreshSignInPage();
            }
        };
        backAboutButton.setOnAction(transformerToPreviousScene);

        return AboutBottomPane;
    }



    public void updateProductCenter(String productName){


        
        Product products = null ;
        
        for (int i = 0 ;  i < productArrayListProduct.size(); i++ ){
          
            Product temp = productArrayListProduct.get(i);
            
            if (productName.equals(temp.getName())){
            
              products = temp;
                
            }
        }
        
        names.setText(products.getName());
        textAnimation(names,products.getName(),1000);

        categories.setText(products.getCategory());
        textAnimation(categories,products.getCategory(),1000);

        measurment.setText(products.getMeasurement());
        textAnimation(measurment,products.getMeasurement(),1000);

        prices.setText(String.format("%.2f", products.getPrice()));
        textAnimation(prices,String.valueOf(products.getPrice()),1000);

        minimumQuantity.setText(String.format("%.2f", products.getMinimumQuantity()));
        textAnimation(minimumQuantity,String.valueOf(products.getMinimumQuantity()),1000);

        quantities.setText(String.format("%.2f", products.getQuantity()));
        textAnimation(quantities,String.valueOf(products.getQuantity()),1000);


        updatePersonStatus();


        nameTextBox = new TextField(products.getName());
        categoryTextBox = new TextField(products.getCategory());
        measurementTextBox = new TextField(products.getMeasurement());
        priceTextBox = new TextField(String.format("%.2f", products.getPrice()));
        minimumQuantityTextBox = new TextField(String.format("%.2f",products.getMinimumQuantity()));
        quantityTextBox = new TextField(String.format("%.2f", products.getQuantity()));
    }
    
    public void addItems(ListView<String> productList1) {

        productList1.getSelectionModel().selectFirst();

        productList1.getSelectionModel()
              .selectedItemProperty()
               .addListener(
                (ObservableValue<? extends String> ov,
                 final String oldvalue,
                  final String newValue) -> {

        updateProductCenter(newValue);

        });
                
    }
    public void setTransitionPassword(TextField passwordTextField){

        pauseTransitionPassword = new PauseTransition(Duration.millis(500));
        fadeTransitionPassword = new FadeTransition(Duration.millis(1000));
        fadeTransitionPassword.setFromValue(1.0f);
        fadeTransitionPassword.setToValue(0.3f);
        fadeTransitionPassword.setAutoReverse(true);
        translateTransitionPassword = new TranslateTransition(Duration.millis(1000));
        translateTransitionPassword.setFromX(1000f);
        translateTransitionPassword.setToX(0);
        translateTransitionPassword.setAutoReverse(true);
//        rotateTransitionPassword = new RotateTransition(Duration.millis(1000));
//        rotateTransitionPassword.setByAngle(360f);
//        rotateTransitionPassword.setCycleCount(1);
//        rotateTransitionPassword.setAutoReverse(true);
        scaleTransitionPassword = new ScaleTransition(Duration.millis(1000));
        scaleTransitionPassword.setByX(1.5f);
        scaleTransitionPassword.setByY(1.5f);
        scaleTransitionPassword.setCycleCount(2);
        scaleTransitionPassword.setAutoReverse(true);
        fadeTransitionPassword1 = new FadeTransition(Duration.millis(1000));
        fadeTransitionPassword1.setFromValue(0.3f);
        fadeTransitionPassword1.setToValue(1.0f);
        fadeTransitionPassword1.setAutoReverse(true);


        sequentialTransitionPassword = new SequentialTransition(passwordTextField,
                pauseTransitionPassword,
                fadeTransitionPassword,
                translateTransitionPassword,
                scaleTransitionPassword);
        sequentialTransitionPassword.play();




    }

    public void setTransitionUserName (TextField usernameTextField){

        pauseTransitionUserName = new PauseTransition(Duration.millis(500));
        fadeTransitionUserName = new FadeTransition(Duration.millis(1000));
        fadeTransitionUserName.setFromValue(1.0f);
        fadeTransitionUserName.setToValue(0.3f);
        fadeTransitionUserName.setAutoReverse(true);
        translateTransitionUserName = new TranslateTransition(Duration.millis(1000));
        translateTransitionUserName.setFromX(-1000f);
        translateTransitionUserName.setToX(0);
        translateTransitionUserName.setAutoReverse(true);
//        rotateTransitionUserName = new RotateTransition(Duration.millis(1000));
//        rotateTransitionUserName.setByAngle(360f);
//        rotateTransitionUserName.setCycleCount(1);
//        rotateTransitionUserName.setAutoReverse(true);
        scaleTransitionUserName = new ScaleTransition(Duration.millis(1000));
        scaleTransitionUserName.setByX(1.5f);
        scaleTransitionUserName.setByY(1.5f);
        scaleTransitionUserName.setCycleCount(2);
        scaleTransitionUserName.setAutoReverse(true);
        fadeTransitionUserName1 = new FadeTransition(Duration.millis(1000));
        fadeTransitionUserName1.setFromValue(0.3f);
        fadeTransitionUserName1.setToValue(1.0f);
        fadeTransitionUserName1.setAutoReverse(true);


        sequentialTransitionUserName = new SequentialTransition(usernameTextField,
                pauseTransitionUserName,
                fadeTransitionUserName,
                translateTransitionUserName,
                scaleTransitionUserName);
        sequentialTransitionUserName.play();

    }



    public void updatePersonStatus(){

        int k = productSearch();
        adminName.setText(adminAccounts[k].getPerson().getFirstName() + " " + adminAccounts[k].getPerson().getLastName());

    }
    public int productSearch(){

        int  k = 0;
        for(int i = 0 ; i < adminAccounts.length ; i++){

            if(adminAccounts[i].getAccount().getUserName().equals(usernameTextField.getText()) && adminAccounts[i].getAccount().getPassword().equals(passwordTextField.getText())){

                k = i;

            }

        }
        return k;

    }


    public void redreshSignInPage(){

        window.setScene(signInScene);
        sequentialTransitionPassword.play();
        sequentialTransitionUserName.play();
        unauthorizedUsernameOrPasswordError.setVisible(false);
        emptyUsernameOrPasswordError.setVisible(false);
        textAnimation(signInTitle,"WelcomeBack To Sign In Page",4000);
        otherServicesHBoxTranslateTransition.play();
        signInTitleBarTranslateTransition.play();
        transformerToNextSceneButtonParallelTransitionTransition.play();
        timeline.play();
        usernameTextField.setText("");
        passwordTextField.setText("");
        usernameTextField.requestFocus();
    }



    public void textAnimation(Label text , String content, int time){
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(time));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                text.setText(content.substring(0, n));
            }

        };
        animation.play();
    }

    public Product stringProductToProduct(String productName){

        Product products = null ;
        for (int i = 0 ;  i < productArrayListProduct.size(); i++ ){

            Product temp = productArrayListProduct.get(i);

            if (productName.equals(temp.getName())){

                return products = temp;

            }
        }
        return products;
    }




    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
        
    }



}


