package javatf;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.util.Locale;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.media.AudioClip;


public class Gestao extends Application{
    private TextField tfId,tfDataPrev,tfDestino,tfQtdCaixas;
    private TextField tfDlgId,tfDlgDataPrev,tfDlgDestino,tfDlgQtdCaixas;
    private TextField tfDlgQtNormal,tfDlgQtRefrigerada,tfDlgQtPerecivel;
    private Stage dlgStage;
  
    public Gestao(){
    }
    
    private void exibeDados(){
        Pedidos ped = Pedidos.getInstance();
        tfId.setText(ped.getCorrente().getId());
        tfDestino.setText(ped.getCorrente().getLocal().getCidade());
        tfQtdCaixas.setText(""+ped.getCorrente().qtdadeCaixas());
    }
        
    @Override
    public void start(Stage primaryStage) {
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Define o título do form
        Text tfTitulo = new Text("Pedidos:");
        tfTitulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tfTitulo, 0, 0, 2, 1);    
       
        // Cria e posiciona 
        grid.add(new Label("Pedido:"), 0, 1);
        tfId = new TextField();
        grid.add(tfId, 1, 1);        
        grid.add(new Label("Destino:"), 0, 2);
        tfDestino = new TextField();
        grid.add(tfDestino, 1, 2);       
        grid.add(new Label("Qtdade caixas:"), 0, 3);
        tfQtdCaixas = new TextField();
        grid.add(tfQtdCaixas, 1, 3);        
     
        exibeDados();
        
        // Define os botoes
        Button btPrev = new Button();
        btPrev.setText("<<");
        btPrev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (Pedidos.getInstance().getAnterior() == null){
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Atenção !!");
                    alert.setHeaderText(null);
                    alert.setContentText("Este é o primeiro pedido!");
                    alert.showAndWait();
                }else{
                    exibeDados();
                }
            }
        });

        Button btNext = new Button();
        btNext.setText(">>");
        btNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (Pedidos.getInstance().getProximo() == null){
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Atenção !!");
                    alert.setHeaderText(null);
                    alert.setContentText("Este é o ultimo pedido!");
                    alert.showAndWait();
                }else{
                    exibeDados();
                }
            }
        });

        Button btView = new Button();
        btView.setText("Visualizar");
        btView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                criaDialogoPedido();
            }
        });

        Button btClose = new Button();
        btClose.setText("Fechar");
        btClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.close();
            }
        });
        
        // Agrupa os botoes em um HBox e posiciona
        HBox hbBtn = new HBox(30);
        hbBtn.getChildren().add(btPrev);
        hbBtn.getChildren().add(btNext);
        grid.add(hbBtn, 0, 4);
        HBox hbView = new HBox(30);
        hbView.setAlignment(Pos.CENTER);
        hbView.getChildren().add(btView);
        grid.add(hbView, 0, 5);
        HBox hbClose = new HBox(30);
        hbClose.setAlignment(Pos.BOTTOM_RIGHT);
        hbClose.getChildren().add(btClose);
        grid.add(hbClose,1,5);

        Button pedAtendDia = new Button();
        pedAtendDia.setText("Ped. Atend. p/ dia");         
        grid.add(pedAtendDia,0,6);
        pedAtendDia.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                criaDialogoPedAtend();
            }
        });
        
        Button pedAtrDia = new Button();
        pedAtrDia.setText("Ped. Atrasados. p/ dia");         
        grid.add(pedAtrDia,1,6);
        pedAtrDia.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                criaDialogoPedAtr();
            }
        });
        
        Button TxSucces = new Button();
        TxSucces.setText("Taxa de sucesso");         
        grid.add(TxSucces,0,7);
        TxSucces.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                criaDialogoTxSucces();
            }
        });
        
        Button TxOcup = new Button();
        TxOcup.setText("Taxa de ocupação");         
        grid.add(TxOcup,1,7);
        TxOcup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                criaDialogoTxOcup();
            }
        });
        
        Button lucro = new Button();
        lucro.setText("Lucratividade");         
        grid.add(lucro,0,8);
        lucro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                criaDialogoLucro();
            }
        });
       
        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        primaryStage.setTitle("So vai - Transportadora");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void criaDialogoPedido(){
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
       
        // Cria e posiciona 
        grid.add(new Label("Pedido:"), 0, 1);
        tfDlgId = new TextField();
        tfDlgId.setEditable(false);
        grid.add(tfDlgId, 1, 1);        
        grid.add(new Label("Destino:"), 0, 2);
        tfDlgDestino = new TextField();
        tfDlgDestino.setEditable(false);
        grid.add(tfDlgDestino, 1, 2);       
        grid.add(new Label("Qtdade caixas:"), 0, 3);
        tfDlgQtdCaixas = new TextField();
        tfDlgQtdCaixas.setEditable(false);
        grid.add(tfDlgQtdCaixas, 1, 3);        
        grid.add(new Label("Qtdade caixas normais:"), 0, 4);
        tfDlgQtNormal = new TextField();
        tfDlgQtNormal.setEditable(false);
        grid.add(tfDlgQtNormal, 1, 4);        
        grid.add(new Label("Qtdade caixas refrigeradas:"), 0, 5);
        tfDlgQtRefrigerada = new TextField();
        tfDlgQtRefrigerada.setEditable(false);
        grid.add(tfDlgQtRefrigerada, 1, 5);        
        grid.add(new Label("Qtdade caixas pereciveis:"), 0, 6);
        tfDlgQtPerecivel = new TextField();
        tfDlgQtPerecivel.setEditable(false);
        grid.add(tfDlgQtPerecivel, 1, 6);        


        Button btClose = new Button();
        btClose.setText("Fechar");
        btClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dlgStage.close();
            }
        });
        HBox hbClose = new HBox(30);
        hbClose.setAlignment(Pos.BOTTOM_RIGHT);
        hbClose.getChildren().add(btClose);
        grid.add(hbClose,1,7);
        
        // Exibe os dados
        Pedidos ped = Pedidos.getInstance();
        tfDlgId.setText(ped.getCorrente().getId());
        tfDlgDestino.setText(ped.getCorrente().getLocal().getCidade());
        tfDlgQtdCaixas.setText(""+ped.getCorrente().qtdadeCaixas());
        tfDlgQtNormal.setText(""+ped.getCorrente().qtdadeCaixasTipo(TipoCaixa.NORMAL));
        tfDlgQtRefrigerada.setText(""+ped.getCorrente().qtdadeCaixasTipo(TipoCaixa.REFRIGERADA));
        tfDlgQtPerecivel.setText(""+ped.getCorrente().qtdadeCaixasTipo(TipoCaixa.PERECIVEL));

        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Detalhamento do pedido");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
    }
    
    private void criaDialogoPedAtend(){
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        LineChart graficoLinha = new LineChart<>(
            new CategoryAxis(), new NumberAxis());
          final String T1 = "T1";
          final String T2 = "T2";
          final String T3 = "T3";
          final String T4 = "T4";

          XYChart.Series prod1 = new XYChart.Series();
          prod1.setName("Produto 1");

          prod1.getData().add(new XYChart.Data(T1, 5));
          prod1.getData().add(new XYChart.Data(T2, -2));
          prod1.getData().add(new XYChart.Data(T3, 3));
          prod1.getData().add(new XYChart.Data(T4, 15));

          XYChart.Series prod2 = new XYChart.Series();
          prod2.setName("Produto 2");

          prod2.getData().add(new XYChart.Data(T1, -5));
          prod2.getData().add(new XYChart.Data(T2, -1));
          prod2.getData().add(new XYChart.Data(T3, 12));
          prod2.getData().add(new XYChart.Data(T4, 8));

          XYChart.Series prod3 = new XYChart.Series();
          prod3.setName("Produto 3");

          prod3.getData().add(new XYChart.Data(T1, 12));
          prod3.getData().add(new XYChart.Data(T2, 15));
          prod3.getData().add(new XYChart.Data(T3, 12));
          prod3.getData().add(new XYChart.Data(T4, 20));
          graficoLinha.getData().addAll(prod1, prod2, prod3);
          graficoLinha.setPrefSize(400, 400);
       
        grid.add(graficoLinha, 0, 0);

        Button btClose = new Button();
        btClose.setText("Fechar");
        btClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dlgStage.close();
            }
        });
        HBox hbClose = new HBox(30);
        hbClose.setAlignment(Pos.BOTTOM_RIGHT);
        hbClose.getChildren().add(btClose);
        grid.add(hbClose,1,0);
    
        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Pedidos Atendidos Por Dia");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
    }
    
    private void criaDialogoPedAtr(){
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        BarChart graficoBarra = new BarChart<>(
            new CategoryAxis(), new NumberAxis());
        
        final String T1 = "T1";
        final String T2 = "T2";
        final String T3 = "T3";
        final String T4 = "T4";
          XYChart.Series prod1 = new XYChart.Series();
            prod1.setName("Produto 1");

            prod1.getData().add(new XYChart.Data(T1, 5));
            prod1.getData().add(new XYChart.Data(T2, -2));
            prod1.getData().add(new XYChart.Data(T3, 3));
            prod1.getData().add(new XYChart.Data(T4, 15));

            XYChart.Series prod2 = new XYChart.Series();
            prod2.setName("Produto 2");

            prod2.getData().add(new XYChart.Data(T1, -5));
            prod2.getData().add(new XYChart.Data(T2, -1));
            prod2.getData().add(new XYChart.Data(T3, 12));
            prod2.getData().add(new XYChart.Data(T4, 8));

            XYChart.Series prod3 = new XYChart.Series();
            prod3.setName("Produto 3");

            prod3.getData().add(new XYChart.Data(T1, 12));
            prod3.getData().add(new XYChart.Data(T2, 15));
            prod3.getData().add(new XYChart.Data(T3, 12));
            prod3.getData().add(new XYChart.Data(T4, 20));
          graficoBarra.getData().addAll(prod1, prod2, prod3);
          graficoBarra.setPrefSize(400, 400);
       
        grid.add(graficoBarra, 0, 0);

        Button btClose = new Button();
        btClose.setText("Fechar");
        btClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dlgStage.close();
            }
        });
        HBox hbClose = new HBox(30);
        hbClose.setAlignment(Pos.BOTTOM_RIGHT);
        hbClose.getChildren().add(btClose);
        grid.add(hbClose,1,0);
    
        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Pedidos Atrasados Por Dia");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
    }
    
    private void criaDialogoTxSucces(){
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
       
        grid.add(new Label("Teste caixa dialogo:"), 0, 0);

        Button btClose = new Button();
        btClose.setText("Fechar");
        btClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dlgStage.close();
            }
        });
        HBox hbClose = new HBox(30);
        hbClose.setAlignment(Pos.BOTTOM_RIGHT);
        hbClose.getChildren().add(btClose);
        grid.add(hbClose,1,0);
    
        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Taxa de sucesso");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
    }
    
    private void criaDialogoTxOcup(){
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
       
        grid.add(new Label("Teste caixa dialogo:"), 0, 0);

        Button btClose = new Button();
        btClose.setText("Fechar");
        btClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dlgStage.close();
            }
        });
        HBox hbClose = new HBox(30);
        hbClose.setAlignment(Pos.BOTTOM_RIGHT);
        hbClose.getChildren().add(btClose);
        grid.add(hbClose,1,0);
    
        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Taxa de Ocupação");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
    }
    
    private void criaDialogoLucro(){
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        PieChart graficoPizza = new PieChart();
        graficoPizza.getData().addAll(new PieChart.Data("Trimestre 1", 11),
          new PieChart.Data("Trimestre 2", 1),
          new PieChart.Data("Trimestre 3", 34),
          new PieChart.Data("Trimestre 5", 12));
        graficoPizza.setTitle("Lucros por Trimestre");
        graficoPizza.setPrefSize(400,400);
        
        grid.add(graficoPizza, 0, 0);

        Button btClose = new Button();
        btClose.setText("Fechar");
        btClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dlgStage.close();
            }
        });
        HBox hbClose = new HBox(30);
        hbClose.setAlignment(Pos.BOTTOM_RIGHT);
        hbClose.getChildren().add(btClose);
        grid.add(hbClose,0,1);
    
        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Lucratividade Média");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
    }
        
    public static void main(String[] args) {
        launch(args);           
    }
}

