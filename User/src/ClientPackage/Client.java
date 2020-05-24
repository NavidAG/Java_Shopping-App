package ClientPackage;

import javafx.animation.*;
import javafx.event.EventHandler;

import java.io.File;
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
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
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


public class Client extends Application {
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

    public static TextField usernameTextField;
    public static PasswordField passwordTextField;

    TranslateTransition signInTitleBarTranslateTransition;
    TranslateTransition transformerToNextSceneButtonTranslateTransition;
    RotateTransition transformerToNextSceneButtonRotateTransition1;
    ParallelTransition transformerToNextSceneButtonParallelTransitionTransition;


    private double xOffset = 0;
    private double yOffset = 0;

    private double xOffset1 = 0;
    private double yOffset1 = 0;


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
    Label userMoney = new Label();
    Label userItems = new Label();
    Label consumerName = new Label();

    TextField nameTextBox;
    TextField categoryTextBox;
    TextField measurementTextBox;
    TextField priceTextBox;
    TextField minimumQuantityTextBox;
    TextField quantityTextBox;
    TextField money;

    DropShadow dropShadow1 = new DropShadow(20, 7.0, 7.0, Color.BLACK);
    DropShadow dropShadow2 = new DropShadow(20, 7.0, 7.0, Color.WHITE);


//    Product iphone = new Product("iPhone", "Electronics","device",899,10,60);
//    Product ipad = new Product("iPad", "Electronics","device",999,5,20);
//    Product ipod = new Product("iPod", "Electronics","device",499,6,30);
//    Product macbook = new Product("MacBook", "Electronics","device",1299,10,40);
//    Product MacMini = new Product("Mac Mini", "Electronics","device",1599,3,50);


    public static ArrayList<String> productArrayListString = new ArrayList<String>();

//    public static ArrayList<Product> productArrayListProduct = new ArrayList<Product>();

//    public static ListView <String> productList = new ListView<String>();

    public static ArrayList<String> productArrayListOfConsumerString = new ArrayList<>();

    public static ArrayList<Product> productArrayListOfConsumerProduct = new ArrayList<>();

    ListView<String> productListOfConsumer = new ListView<>();

    ClientThread clientThread = null;

    private void removeProduct(String productName) {

        Product product = stringProductToProduct(productName);
        clientThread.product.remove(product);
        productArrayListString.remove(productName);
        clientThread.productList.getItems().remove(productName);
//        clientThread.productList.refresh();
    }

    public void addToBag(String productName) {

//        Product products = stringProductToProduct(productName);
        productArrayListOfConsumerString.add(productName);
        productListOfConsumer.getItems().add(productName);
//        productListOfConsumer.refresh();

    }

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
        circles.setEffect(new BoxBlur(10, 10, 3));
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

        //sign up scene
//        BorderPane signUpPane = new BorderPane();

        VBox signUpPane = new VBox();
        BackgroundImage signUpBackground = new BackgroundImage(new Image(new File("Fbackground.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        BorderPane signInTitle = signInTitleBar();


        signInTitleBarTranslateTransition = new TranslateTransition(Duration.millis(4000), signInTitle);
        signInTitleBarTranslateTransition.setFromY(-130f);
        signInTitleBarTranslateTransition.setToY(0);
        signInTitleBarTranslateTransition.setAutoReverse(true);
        signInTitleBarTranslateTransition.play();


        signUpPane.getChildren().add(signInTitle);
        signUpPane.getChildren().add(signInAuthentication());
        HBox otherServicesHBox = otherServices();

        otherServicesHBoxTranslateTransition = new TranslateTransition(Duration.millis(4000), otherServicesHBox);
        otherServicesHBoxTranslateTransition.setFromY(80f);
        otherServicesHBoxTranslateTransition.setToY(0);
        otherServicesHBoxTranslateTransition.setAutoReverse(true);
        otherServicesHBoxTranslateTransition.play();
        signUpPane.getChildren().add(otherServicesHBox);
        signUpPane.setSpacing(182.5);

//        BackgroundImage signUpBackground = new BackgroundImage(new Image(new File("Fbackground.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
//        HBox signInTitle = signInTitleBar();
//        signUpPane.setTop(signInTitle);
//        signUpPane.setCenter(signInAuthentication());
//        signUpPane.setBottom(otherServices());
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
        Label navid = new Label("Navid All Gharaee");
        Label javid = new Label("Javid Chaji");
        VBox about = new VBox();
        navid.setFont(Font.font("Arial Bold", 30));
        javid.setFont(Font.font("Arial Bold", 30));
        about.getChildren().addAll(navid, javid);
        aboutPane.setCenter(about);
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

//        signInScene = new Scene(signUpPane, 800, 700);
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
        textAnimation(signInTitle, "Welcom To Sign In Page", 5000);
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

        transformerToNextSceneButtonParallelTransitionTransition = new ParallelTransition(transformerToNextSceneButton, transformerToNextSceneButtonTranslateTransition, transformerToNextSceneButtonRotateTransition1);
        transformerToNextSceneButtonParallelTransitionTransition.play();


        setTransitionPassword(passwordTextField);


        sequentialTransitionInfinitPassword = new SequentialTransition(passwordTextField, fadeTransitionPassword, fadeTransitionPassword1);
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


        sequentialTransitionInfinitUserName = new SequentialTransition(usernameTextField, fadeTransitionUserName, fadeTransitionUserName1);
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

                if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
                    emptyUsernameOrPasswordError.setVisible(true);
                    unauthorizedUsernameOrPasswordError.setVisible(false);
                } else {

//                    for (int i = 0; i < adminAccounts.length; i++) {
//                        if (usernameTextField.getText().equals(adminAccounts[i].getAccount().getUserName()) && passwordTextField.getText().equals(adminAccounts[i].getAccount().getPassword())) {

//                    try {
//                        wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    clientThread = new ClientThread(usernameTextField.getText(), passwordTextField.getText());
                    clientThread.start();
                    updatePersonStatus();
                    if (clientThread.ok) {

//                        clientThread.product = clientThread.product;
                        productArrayListString = ProductToStringProduct();
//                        clientThread.productList.getItems().addAll(clientThread);
                        window.setScene(shopManagmentScene);
                        window.getIcons().add(new Image(new File("StorageIcon.png").toURI().toString()));
                        unauthorizedUsernameOrPasswordError.setVisible(false);
                        emptyUsernameOrPasswordError.setVisible(false);
                        usernameTextField.setText("");
                        passwordTextField.setText("");
                        usernameTextField.requestFocus();

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
                    usernameTextField.setText("");
                    passwordTextField.setText("");
                    usernameTextField.requestFocus();
                }
            }


        };
        transformerToNextSceneButton.setOnAction(transformerToNextScene);
        passwordTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                transformerToNextSceneButton.fire();
            }
        });
        usernameTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                transformerToNextSceneButton.fire();
            }
        });

        return authenticationPane;
    }

    public HBox otherServices() {
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
        consumerInfo.getChildren().addAll(userMoney, userItems, consumerName);
        userMoney.setFont(Font.font("Arial Bold", 30));
        userItems.setFont(Font.font("Arial Bold", 30));
        consumerName.setFont(Font.font("Arial Bold", 30));
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

    public BorderPane productsShelve() {
        BorderPane productsListPane = new BorderPane();
        productsListPane.setStyle("-fx-border-width: 2;-fx-border-color: rgba(85,85,85,.4);-fx-border-radius: 4;");
        clientThread.productList.setPrefSize(400, 600);
        clientThread.productList.setFixedCellSize(50);
        clientThread.productList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productsListPane.setCenter(clientThread.productList);
        addItems(clientThread.productList);

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

    public BorderPane productDescription() {
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
        productNameInformation.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));
        productCategoryInformation.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));
        measurmentText.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));
        productPriceInformation.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));
        minimumQuantityText.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));
        productQuantityInformation.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));

        informationVBox.setSpacing(36);

        informationVBox.getChildren().addAll(productNameInformation,
                productCategoryInformation,
                measurmentText,
                productPriceInformation,
                minimumQuantityText,
                productQuantityInformation
        );

        VBox productValueVbox = new VBox();

        names.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));
        categories.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));
        measurment.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));
        prices.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));
        minimumQuantity.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));
        quantities.setFont(Font.font("Helvetica", FontWeight.LIGHT, 24));
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
        ImageView addIcon = new ImageView(new Image(new File("src/add.png").toURI().toString()));
        addIcon.setFitWidth(30);
        addIcon.setFitHeight(30);
        Button addButton = new Button();
        addButton.setGraphic(addIcon);
        addButton.setPrefSize(20, 20);
        addButton.setCursor(Cursor.HAND);
        addButton.setStyle(defualtButtonStyle);
        addButton.setOnMouseEntered(e -> addButton.setStyle(hoveredButtonStyle));
        addButton.setOnMouseExited(e -> addButton.setStyle(defualtButtonStyle));
        EventHandler<ActionEvent> addFunction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String newValue = clientThread.productList.getSelectionModel().getSelectedItem();
                addToBag(newValue);
//                productListOfConsumer.refresh();
                Product product = stringProductToProduct(newValue);
                int k = productSearch(product);
//                if(clientThread.product.get(k).getQuantity() >= clientThread.product.get(k).getMinimumQuantity()){
                clientThread.product.get(k).quantity--;
//                }else {
//                    clientThread.product.get(k).quantity += 36;
//                    clientThread.product.get(k).quantity --;
//                }
                clientThread.userAccount.getAccount().payment(product.price);
                clientThread.writer.println(clientThread.productArrayList.toString());
                updatePersonStatus();
                updateProductCenter(newValue);
            }
        };
        ImageView walletIcon = new ImageView(new Image(new File("src/wallet.png").toURI().toString()));
        walletIcon.setFitHeight(30);
        walletIcon.setFitWidth(30);
        Button walletButton = new Button();
        walletButton.setGraphic(walletIcon);
        walletButton.setPrefSize(20, 20);
        walletButton.setCursor(Cursor.HAND);
        walletButton.setStyle(defualtButtonStyle);
        walletButton.setOnMouseEntered(e -> walletButton.setStyle(hoveredButtonStyle));
        walletButton.setOnMouseExited(e -> walletButton.setStyle(defualtButtonStyle));
        EventHandler<ActionEvent> walletFunction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                String newValue = clientThread.productList.getSelectionModel().getSelectedItem();
                BorderPane walletPane = new BorderPane();
                walletPane.setPrefSize(500, 300);
                walletPane.setPadding(new Insets(20));
                BackgroundImage editBackground = new BackgroundImage(new Image(new File("background1.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

                walletPane.setBackground(new Background(editBackground));
                HBox titleBar = new HBox();
                titleBar.setPadding(new Insets(0, 20, 0, 20));
                titleBar.setSpacing(2);
                Text UItitle = new Text("Wallet");
                UItitle.setFont(Font.font("Arial Bold", 25));
                UItitle.setFill(Color.BLACK);
                titleBar.getChildren().add(UItitle);
                walletPane.setTop(titleBar);

                money = new TextField();
                money.setStyle("-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black;");
                money.setPrefSize(300, 40);
                money.setPromptText("Enter the Money you have");
                walletPane.setCenter(money);
                Group root1 = new Group();
                Stage walletStage = new Stage();
                Scene walletScene = new Scene(root1, 500, 300);
                walletStage.setScene(walletScene);
                HBox buttonPane = new HBox();

                Button confirmButton = new Button("Confirm");
                confirmButton.setStyle(defualtButtonStyle);
                confirmButton.setOnMouseEntered(e -> confirmButton.setStyle(hoveredButtonStyle));
                confirmButton.setOnMouseExited(e -> confirmButton.setStyle(defualtButtonStyle));
                confirmButton.setCursor(Cursor.HAND);
                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle(defualtButtonStyle);
                cancelButton.setOnMouseEntered(e -> cancelButton.setStyle(hoveredButtonStyle));
                cancelButton.setOnMouseExited(e -> cancelButton.setStyle(defualtButtonStyle));
                cancelButton.setCursor(Cursor.HAND);
                buttonPane.setSpacing(5);
                buttonPane.setAlignment(Pos.CENTER_RIGHT);
                EventHandler<ActionEvent> confirmFunction;
                confirmFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        clientThread.userAccount.getAccount().charge(Double.valueOf(money.getText()));
                        updatePersonStatus();
                        walletStage.close();


                    }
                };
                confirmButton.setOnAction(confirmFunction);
                EventHandler<ActionEvent> cancelFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        walletStage.close();
                    }
                };
                cancelButton.setOnAction(cancelFunction);
                buttonPane.getChildren().addAll(cancelButton, confirmButton);
                walletPane.setBottom(buttonPane);
                root1.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset1 = event.getSceneX();
                        yOffset1 = event.getSceneY();
                    }
                });
                root1.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        walletStage.setX(event.getScreenX() - xOffset1);
                        walletStage.setY(event.getScreenY() - yOffset1);
                    }
                });
                root1.getChildren().add(walletPane);
                walletStage.initStyle(StageStyle.UNDECORATED);
                walletStage.setResizable(false);
                walletStage.show();

            }
        };
        walletButton.setOnAction(walletFunction);
        ImageView AddToShopingListIcon = new ImageView(new Image(new File("src/bag.png").toURI().toString()));
        AddToShopingListIcon.setFitHeight(30);
        AddToShopingListIcon.setFitWidth(30);
        Button AddToShopingListButton = new Button();
        AddToShopingListButton.setGraphic(AddToShopingListIcon);
        AddToShopingListButton.setCursor(Cursor.HAND);
        AddToShopingListButton.setPrefSize(20, 20);
        AddToShopingListButton.setStyle(defualtButtonStyle);
        AddToShopingListButton.setOnMouseEntered(e -> AddToShopingListButton.setStyle(hoveredButtonStyle));
        AddToShopingListButton.setOnMouseExited(e -> AddToShopingListButton.setStyle(defualtButtonStyle));


        managmentHbox.getChildren().addAll(addButton, AddToShopingListButton, walletButton);
        managmentPane.setCenter(managmentHbox);
        managmentHbox.setPadding(new Insets(5));

        //-----------------------------------------
        EventHandler<ActionEvent> addToShopingListFunction;
        addToShopingListFunction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorderPane addPane = new BorderPane();
                BackgroundImage editBackground = new BackgroundImage(new Image(new File("Fbackground.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

                addPane.setBackground(new Background(editBackground));
                HBox titleBar = new HBox();
                titleBar.setPadding(new Insets(20, 20, 20, 20));
                titleBar.setSpacing(5);
                Text UItitle = new Text("Bag");
                UItitle.setFont(Font.font("Arial Bold", 30));
                UItitle.setFill(Color.BLACK);
                titleBar.getChildren().add(UItitle);
                addPane.setTop(titleBar);


                //..............

                BorderPane productTextVbox = new BorderPane();
                productTextVbox.setPadding(new Insets(10, 10, 10, 10));


                productListOfConsumer.setPrefSize(400, 600);
                productListOfConsumer.setFixedCellSize(50);
                productListOfConsumer.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                productTextVbox.setCenter(productListOfConsumer);
                HBox commandHbox = new HBox();
                commandHbox.setPadding(new Insets(10));
                commandHbox.setSpacing(6);
                Button deleteButton = new Button();
                ImageView trashIcon1 = new ImageView(new Image(new File("src/trash.png").toURI().toString()));
                trashIcon1.setFitHeight(30);
                trashIcon1.setFitWidth(30);
                Button trashButton = new Button();
                deleteButton.setGraphic(trashIcon1);
                deleteButton.setStyle(defualtButtonStyle);
                deleteButton.setOnMouseEntered(e -> deleteButton.setStyle(hoveredButtonStyle));
                deleteButton.setOnMouseExited(e -> deleteButton.setStyle(defualtButtonStyle));
                deleteButton.setCursor(Cursor.HAND);

                Label emptyLabel = new Label("The Textfields cannot be empthy!");
                emptyLabel.setStyle("-fx-text-fill: rgb(245,196,82);");
                emptyLabel.setVisible(false);

                Button cancelButton = new Button("Close");
                cancelButton.setPrefSize(110, 36);
                cancelButton.setStyle(defualtButtonStyle);
                cancelButton.setOnMouseEntered(e -> cancelButton.setStyle(hoveredButtonStyle));
                cancelButton.setOnMouseExited(e -> cancelButton.setStyle(defualtButtonStyle));
                cancelButton.setCursor(Cursor.HAND);
                commandHbox.getChildren().addAll(emptyLabel, cancelButton, deleteButton);
                commandHbox.setAlignment(Pos.CENTER_RIGHT);
                addPane.setBottom(commandHbox);

                addPane.setCenter(productTextVbox);
                Stage addStage = new Stage();
                EventHandler<ActionEvent> deleteFunction;
                deleteFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String newValue = productListOfConsumer.getSelectionModel().getSelectedItem();
                        productListOfConsumer.getItems().remove(newValue);
//                        productListOfConsumer.refresh();
                        updatePersonStatus();
                    }
                };
                EventHandler<ActionEvent> cancelFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        addStage.close();
                        updatePersonStatus();
                    }

                };
                cancelButton.setOnAction(cancelFunction);
                deleteButton.setOnAction(deleteFunction);
                addStage.setResizable(false);
                addStage.setTitle("Edit");
                Scene addScene = new Scene(addPane, 500, 500);
                addStage.setScene(addScene);
                addStage.show();
                updatePersonStatus();
            }

        };

        addButton.setOnAction(addFunction);
        AddToShopingListButton.setOnAction(addToShopingListFunction);


        productsDescriptionPane.setLeft(informationVBox);
        productsDescriptionPane.setBottom(managmentPane);

        return productsDescriptionPane;
    }

    public BorderPane productPicture() {
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

    public BorderPane controlerButtons() {
        BorderPane shopManagerBottomPane = new BorderPane();
        Button logOutButton = new Button("Log out");
        logOutButton.setCursor(Cursor.HAND);
        logOutButton.setStyle(defualtButtonStyle);
        logOutButton.setOnMouseEntered(e -> logOutButton.setStyle(hoveredButtonStyle));
        logOutButton.setOnMouseExited(e -> logOutButton.setStyle(defualtButtonStyle));
        shopManagerBottomPane.setLeft(logOutButton);

        EventHandler<ActionEvent> transformerToPreviousScene;
        transformerToPreviousScene = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                redreshSignInPage();
            }
        };
        logOutButton.setOnAction(transformerToPreviousScene);

        return shopManagerBottomPane;
    }


    public BorderPane controlerAboutPaneButtons() {
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


    public void updateProductCenter(String productName) {


        Product products = null;

        for (int i = 0; i < clientThread.product.size(); i++) {

            Product temp = clientThread.product.get(i);

            if (productName.equals(temp.getName())) {

                products = temp;

            }
        }

        names.setText(products.getName());
        textAnimation(names, products.getName(), 1000);

        categories.setText(products.getCategory());
        textAnimation(categories, products.getCategory(), 1000);

        measurment.setText(products.getMeasurement());
        textAnimation(measurment, products.getMeasurement(), 1000);

        prices.setText(String.format("%.2f", products.getPrice()));
        textAnimation(prices, String.valueOf(products.getPrice()), 1000);

        minimumQuantity.setText(String.format("%.2f", products.getMinimumQuantity()));
        textAnimation(minimumQuantity, String.valueOf(products.getMinimumQuantity()), 1000);

        quantities.setText(String.format("%.2f", products.getQuantity()));
        textAnimation(quantities, String.valueOf(products.getQuantity()), 1000);

        updatePersonStatus();

        nameTextBox = new TextField(products.getName());
        categoryTextBox = new TextField(products.getCategory());
        measurementTextBox = new TextField(products.getMeasurement());
        priceTextBox = new TextField(String.format("%.2f", products.getPrice()));
        minimumQuantityTextBox = new TextField(String.format("%.2f", products.getMinimumQuantity()));
        quantityTextBox = new TextField(String.format("%.2f", products.getQuantity()));
    }

    public void addItems(ListView<String> productList1) {
        productList1.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ObservableValue<? extends String> ov,
                         final String oldvalue,
                         final String newValue) -> {

                            updateProductCenter(newValue);

                        });

    }

    public void setTransitionPassword(TextField passwordTextField) {

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

    public void setTransitionUserName(TextField usernameTextField) {

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

    public void textAnimation(Label text, String content, int time) {
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

    public Product stringProductToProduct(String productName) {

        Product products = null;
        for (int i = 0; i < clientThread.product.size(); i++) {

            Product temp = clientThread.product.get(i);

            if (productName.equals(temp.getName())) {

                return products = temp;

            }
        }
        return products;
    }

    public void updatePersonStatus() {

        userMoney.setText("Cash: " + " " + String.valueOf(clientThread.userAccount.getAccount().getBalance()));

        userItems.setText("Cart Items: " + String.valueOf(productListOfConsumer.getItems().size()));
        consumerName.setText(clientThread.userAccount.getPerson().getFirstName() + " " + clientThread.userAccount.getPerson().getLastName());

    }


    public ArrayList<String> ProductToStringProduct() {

        ArrayList<String> stringProducts = new ArrayList<>();

        for (int i = 0; i < clientThread.product.size(); i++) {

            stringProducts.add(clientThread.product.get(i).getName());

        }
        return stringProducts;
    }

    public int productSearch(Product product) {

        int k = 0;
        for (int i = 0; i < clientThread.product.size(); i++) {

            if (clientThread.product.get(i).equals(product)) {

                k = i;

            }

        }
        return k;

    }

    public void redreshSignInPage() {

        window.setScene(signInScene);
        sequentialTransitionPassword.play();
        sequentialTransitionUserName.play();
        unauthorizedUsernameOrPasswordError.setVisible(false);
        emptyUsernameOrPasswordError.setVisible(false);
        textAnimation(signInTitle, "WelcomeBack To Sign In Page", 4000);
        otherServicesHBoxTranslateTransition.play();
        signInTitleBarTranslateTransition.play();
        transformerToNextSceneButtonParallelTransitionTransition.play();
        timeline.play();
        usernameTextField.setText("");
        passwordTextField.setText("");
        usernameTextField.requestFocus();
        updatePersonStatus();

//        clientThread.start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}

