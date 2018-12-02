import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;
import model.TranslateGame;

public class Main extends Application {
    private int HSize = 500;
    private int WSize = 800;
    private TranslateGame game;
    private Stage window;
    private Player p1;
    private Player p2;
    private Scene mainmenu, chooseLanguage, startLogin;
    private String word;
    private Text topeg;
    private Text qWord;
    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane g1 = new GridPane();
        g1.setPadding(new Insets(10,10,10,10));
        g1.setVgap(8);
        g1.setHgap(10);


        Text header = new Text("Choose Language");
        header.setFont(Font.font("Century Gothic", FontWeight.MEDIUM, 15));

        ChoiceBox<String> cb = new ChoiceBox<String>();
        cb.getItems().add("Spanish");
        cb.getItems().add("Chinese");
        cb.getItems().add("Japanese");
        cb.getItems().add("French");
        cb.getItems().add("Korean");
        cb.getItems().add("Russian");
        cb.setValue("Spanish");

        Button confirm = new Button();
        confirm.setOnAction( e -> {
            try {
                languageChoice(cb);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        confirm.setText("Confirm");
        g1.getChildren().addAll(header, cb, confirm);
        g1.setConstraints(header, 30, 10);
        g1.setConstraints(cb, 30, 13);
        g1.setConstraints(confirm, 30, 16);

        window = primaryStage;

        chooseLanguage = new Scene(g1, WSize, HSize);
        chooseLanguage.getStylesheets().add("ui/design");
        window.setScene(chooseLanguage);
        window.show();
    }

    public void handleAnswer(Player player, TextField field) {

        player.addAnswer(field.getText());
        player.setAnswerConfirmed();
        field.clear();
        System.out.println(field.getText());
        if (p1.isAnswerConfirmed() && p2.isAnswerConfirmed()) {
            game.handleWin();
            updateGame();
        }
        topeg.setText((p1.getScore()+" - " +p2.getScore()));

    }

    public void updateGame() {
        word = game.getNewWord();
        topeg.setText((p1.getScore()+" - " +p2.getScore()));
        qWord.setText(word);

    }

    public void gameStart(String language){

        BorderPane bp = new BorderPane();
        GridPane player1left = new GridPane();
        GridPane player2right = new GridPane();
        GridPane top = new GridPane();
        GridPane bot = new GridPane();
        GridPane centre = new GridPane();


        topeg = new Text(p1.getScore()+" - " +p2.getScore());
        topeg.setFont(Font.font("Century Gothic", FontWeight.MEDIUM, 15));

        TextField player1answer = new TextField();
        player1answer.setPromptText("Your Translation");

        TextField player2answer = new TextField();
        player2answer.setPromptText("Your Translation");

        Text player1name = new Text("Player 1");
        player1name.setFont(Font.font("Century Gothic", FontWeight.MEDIUM, 15));

        Text player2name = new Text("Player 2");
        player2name.setFont(Font.font("Century Gothic", FontWeight.MEDIUM, 15));


        qWord = new Text("Translate: "+game.getNewWord());

        Button confirmButton1 = new Button("Confirm");
        confirmButton1.setOnAction(e -> handleAnswer(p1, player1answer));

        Button confirmButton2 = new Button("Confirm");
        confirmButton1.setOnAction(e -> handleAnswer(p2, player2answer));

        Button cl = new Button();
        cl.setText("go back");
        cl.setOnAction(e -> goBack());
        Text CurrentLanguages = new Text("English to "+language);
        CurrentLanguages.setFont(Font.font("Century Gothic", FontWeight.MEDIUM, 15));

        top.getChildren().addAll(topeg);
        bot.getChildren().addAll(CurrentLanguages, cl);
        centre.getChildren().addAll(qWord);
        player1left.getChildren().addAll(player1name, player1answer, confirmButton1);
        player2right.getChildren().addAll(player2name, player2answer, confirmButton2);


        //Top
        top.setConstraints(topeg, 30, 5);
        top.setPadding(new Insets(10,10,10,10));
        top.setVgap(8);
        top.setHgap(10);

        //Bot
        bot.setConstraints(CurrentLanguages, 30, 3);
        bot.setConstraints(cl, 30, 5);
        bot.setPadding(new Insets(10,10,10,10));
        bot.setVgap(8);
        bot.setHgap(10);

        //Centre
        centre.setConstraints(qWord, 5 , 5);
        centre.setPadding(new Insets(10,10,10,10));
        centre.setVgap(8);
        centre.setHgap(10);

        //Left

        player1left.setConstraints(player1name, 1, 4);
        player1left.setConstraints(player1answer, 1, 6);
        player1left.setConstraints(confirmButton1, 1, 8);
        player1left.setPadding(new Insets(10,10,10,10));
        player1left.setVgap(8);
        player1left.setHgap(10);

        //Right

        player2right.setConstraints(player2name, 1, 4);
        player2right.setConstraints(player2answer, 1, 6);
        player2right.setConstraints(confirmButton2, 1, 8);
        player2right.setPadding(new Insets(10,10,10,10));
        player2right.setVgap(8);
        player2right.setHgap(10);


        bp.setLeft(player1left);
        bp.setRight(player2right);
        bp.setTop(top);
        bp.setBottom(bot);
        bp.setCenter(centre);

        window.setTitle("Translation Game");

        mainmenu = new Scene(bp, WSize, HSize);
        mainmenu.getStylesheets().add("ui/design");
        window.setScene(mainmenu);
        window.show();

    }

    public void goBack(){
        try {
            start(window);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void languageChoice(ChoiceBox<String> cb) {
        String language = cb.getValue();
        p1 = new Player();
        p2 = new Player();
        game = new TranslateGame(language, p1, p2);
        gameStart(language);
    }
}