package javatf;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class Gestao extends Application implements Observer {

    private TextField tfId, tfDataPrev, tfDestino, tfQtdCaixas;
    private TextField tfDlgId, tfDlgDataPrev, tfDlgDestino, tfDlgQtdCaixas;
    private TextField tfDlgQtNormal, tfDlgQtRefrigerada, tfDlgQtPerecivel;
    private Stage dlgStage;
    private ListView<Veiculo> listViewGaragem = null;
    private ListView<Veiculo> listViewTransito = null;
    private ListView<Pedido> listViewPedidos = null;
    private Label label;
    private Veiculo itemSelecionado;
    private Pedido itemSelecionadoPedido;
    private List<Veiculo> v = new ArrayList<>();
    private List<Pedido> pedidosEntregues = new ArrayList<>();
    private ObservableList<Veiculo> itemsGaragem = null;
    private ObservableList<Veiculo> itemsTransito = null;
    private ObservableList<Pedido> itemsPedidos = null;
    private int destinoSelecionado;
    private int pedidosAtrasados;

    public Gestao() {
    }

    private void exibeDados() {
        Pedidos ped = Pedidos.getInstance();
        tfId.setText(ped.getCorrente().getId());
        tfDestino.setText(ped.getCorrente().getLocal().getCidade());
        tfQtdCaixas.setText("" + ped.getCorrente().qtdadeCaixas());
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
        Text tfTitulo = new Text("Só vai transportadora:");
        tfTitulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tfTitulo, 0, 0, 2, 1);

        Integer rowNum = 1;
        // Agrupa os botoes em um HBox e posiciona
        HBox gestData = new HBox(30);
        Label labelDia = new Label();
        labelDia.setText(Calendario.getInstance().getDate().toString());
        grid.add(labelDia, 0, rowNum++);

        Button dataNext = new Button();
        dataNext.setText(">>");
        gestData.getChildren().add(dataNext);
        grid.add(gestData, 0, rowNum++);

        dataNext.setOnAction((ActionEvent event) -> {
            Calendario.getInstance().nextDay();
            labelDia.setText(Calendario.getInstance().getDate().toString());
            pedidosAtrasados = Pedidos.getInstance().getPedidosAtrasados(Calendario.getInstance().getDate());           
            listViewPedidos.refresh();
            atualizaDiasRestantesVeiculos();            
            PedidosAtrasados.getInstance().criaGrafico(Calendario.getInstance().getDate(), itemsPedidos);
            PedidosAtendidos.getInstance().criaGrafico(Calendario.getInstance().getDate(), pedidosEntregues);
            pedidosEntregues.clear();
        });

        // Destinos
        HBox destino = new HBox(30);
        Text titleDestinos = new Text("Destino:");
        destino.getChildren().add(titleDestinos);
        //grid.add(titleDestinos, 0, rowNum++);

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
        Destinos.getInstance().getDestinos()));
        destino.getChildren().add(cb);
        grid.add(destino, 0, rowNum++);

        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                String a = Destinos.getInstance().getLocal(newValue.intValue()).toString();
                itemsGaragem = FXCollections.observableArrayList(Garagem.getInstance().getVeiculosByDestino(a));
                listViewGaragem.setItems(itemsGaragem);
                destinoSelecionado = newValue.intValue();
            }

        }
        );

        //HBox titulos = new HBox(30);
        // Define o título tabela Garagem
        Text titlePedidos = new Text("Pedidos disponíveis:");
        //titulos.getChildren().add(titlePedidos);
        grid.add(titlePedidos, 0, rowNum);
        
        
        Text titleGaragem = new Text("Veiculos Disponiveis:");
        //titulos.getChildren().add(titleGaragem);
        grid.add(titleGaragem, 1, rowNum);
        
        Text titleEmTransito = new Text("Veiculos em transito:");
        //titulos.getChildren().add(titleEmTransito);
        grid.add(titleEmTransito, 2, rowNum++);
        //grid.add(titulos, 0, rowNum++);

        //HBox listas = new HBox(30);
        //Define os itens da tabela Pedidos
        Pedidos.getInstance().addObserver(this);
        itemsPedidos = FXCollections.observableArrayList(Pedidos.getInstance().getPedidos());
        listViewPedidos = new ListView<>(itemsPedidos);
        listViewPedidos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pedido>() {
            @Override
            public void changed(ObservableValue<? extends Pedido> ov, Pedido old_val, Pedido new_val) {
                if (new_val != null) {
                    //label.setText(new_val);
                    itemSelecionadoPedido = new_val;
                    cb.setValue(itemSelecionadoPedido.getLocal());
                } else if (Pedidos.getInstance().getPedidos().size() > 0) {
                    itemSelecionadoPedido = Pedidos.getInstance().getPedidos().get(0);
                }
                itemsGaragem = FXCollections.observableArrayList(Garagem.getInstance().getVeiculosByDestino(itemSelecionadoPedido.getLocal().toString()));
                listViewGaragem.setItems(itemsGaragem);
            }
        });

        grid.add(listViewPedidos, 0, rowNum);
        //listas.getChildren().add(listViewPedidos);

        //Define os itens da tabela Garagem
        Garagem.getInstance().addObserver(this);
        listViewGaragem = new ListView<>(itemsGaragem);
        listViewGaragem.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Veiculo>() {
            public void changed(ObservableValue<? extends Veiculo> ov, Veiculo old_val, Veiculo new_val) {
                //label.setText(new_val);     
                itemSelecionado = new_val;
            }
        });
        
        grid.add(listViewGaragem, 1, rowNum);
        //listas.getChildren().add(listViewGaragem);


        //Define os itens da tabela EmTransito
        EmTransito.getInstance().addObserver(this);
        itemsTransito = FXCollections.observableArrayList(EmTransito.getInstance().getVeiculos());
        listViewTransito = new ListView<>(itemsTransito);
        listViewTransito.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Veiculo>() {
            public void changed(ObservableValue<? extends Veiculo> ov, Veiculo old_val, Veiculo new_val) {

                
            }
        });

        grid.add(listViewTransito, 2, rowNum++);
       // listas.getChildren().add(listViewTransito);
        //sgrid.add(listas, 0, rowNum++);

        Button btnSalvarPedido = new Button();
        btnSalvarPedido.setText("Salvar Pedido");

        btnSalvarPedido.setOnAction((ActionEvent event) -> {
            
            if (itemSelecionadoPedido == null || itemSelecionado == null) {
                if(itemSelecionadoPedido == null){
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Pedido não selecionado");
                    alert.setHeaderText("Pedido não selecionado");
                    alert.setContentText("Não há nenhum pedido selecionado");
                    alert.showAndWait();
                }else if(itemSelecionado == null){
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Veiculo não selecionado");
                    alert.setHeaderText("Veiculo não selecionado");
                    alert.setContentText("Não há nenhum veiculo selecionado");
                    alert.showAndWait();
                }                
            } else {          
                if(adicionaPedidoAoVeiculo(itemSelecionadoPedido, itemSelecionado) == 1){                    
                    v.add(itemSelecionado);
                    adicionaVeiculoAoPedido(itemSelecionadoPedido, itemSelecionado);
                    removePedidoDaLista(itemSelecionadoPedido);
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Pedido adicionado ao Veiculo");
                    alert.setHeaderText("Pedido adicionado com sucesso");
                    alert.setContentText("Não esqueça de colocar o Veiculo em transito!");
                    alert.showAndWait();
                    itemSelecionadoPedido = null;
                    itemSelecionado = null;                    
                    itemsGaragem = FXCollections.observableArrayList(Garagem.getInstance().getVeiculosByDestino(Destinos.getInstance().getLocal(destinoSelecionado).toString()));
                    listViewGaragem.setItems(itemsGaragem);
                }else if(adicionaPedidoAoVeiculo(itemSelecionadoPedido, itemSelecionado) == 2){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Erro ao adicionar pedido");
                    alert.setHeaderText("Carga excedida");
                    alert.setContentText("O veiculo selecionado está cheio");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Erro ao adicionar pedido");
                    alert.setHeaderText("Numero de tomadas insuficientes");
                    alert.setContentText("O veiculo selecionado não tem tomadas suficientes para a carga");
                    alert.showAndWait();
                }             
            }
        });
        
        HBox groupBtnPed = new HBox(30);
        groupBtnPed.getChildren().add(btnSalvarPedido);
        
       /* Button btView = new Button();
        btView.setText("Visualizar");
        btView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                criaDialogoPedido();
            }
        });        
        groupBtnPed.getChildren().add(btView);*/
        
        grid.add(groupBtnPed, 0, rowNum);

        Button btnGaragem = new Button();
        btnGaragem.setText("Colocar em Transito");

        btnGaragem.setOnAction((ActionEvent event) -> {
                
                if(v.isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Erro ao retirar veiculo da garagem");
                    alert.setHeaderText("Sem pedidos");
                    alert.setContentText("Não há nenhum veiculo com pedido");
                    alert.showAndWait();
                }else{
                    for(int x = 0; x < v.size(); x++){
                        if(v.get(x).getPedidos() != null){
                            EmTransito.getInstance().viajem(v.get(x));
                            Garagem.getInstance().removeVeiculo(v.get(x));
                            
                            int tempoViagem = v.get(x).tempoViagem(Destinos.getInstance().getDistancia(v.get(x).getDestino()));
                            v.get(x).setTempoRestante(tempoViagem);
                            //v.clear();
                        }
                    }                   
                    
                    itemsGaragem = FXCollections.observableArrayList(Garagem.getInstance().getVeiculosByDestino(Destinos.getInstance().getLocal(destinoSelecionado).toString()));
                    listViewGaragem.setItems(itemsGaragem);

                    itemsTransito = FXCollections.observableArrayList(EmTransito.getInstance().getVeiculos());
                    listViewTransito.setItems(itemsTransito);
                    
                    v.clear();
                }
                
            
        });

        grid.add(btnGaragem, 1, rowNum++);

        
/*
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
        grid.add(hbBtn, 0, rowNum++);
        HBox hbView = new HBox(30);
        hbView.setAlignment(Pos.CENTER);
        hbView.getChildren().add(btView);
        grid.add(hbView, 0, rowNum);
        HBox hbClose = new HBox(30);
        hbClose.setAlignment(Pos.BOTTOM_RIGHT);
        hbClose.getChildren().add(btClose);
        grid.add(hbClose, 1, rowNum++);
*/
        
        HBox groupGraficos = new HBox(30);
        
        Button pedAtendDia = new Button();
        pedAtendDia.setText("Ped. Atend. p/ dia");
        groupGraficos.getChildren().add(pedAtendDia);
        pedAtendDia.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                criaDialogoPedAtend();
            }
        });

        Button pedAtrDia = new Button();
        pedAtrDia.setText("Ped. Atrasados. p/ dia");
        groupGraficos.getChildren().add(pedAtrDia);
        pedAtrDia.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                criaDialogoPedAtr();
            }
        });


        Button lucro = new Button();
        lucro.setText("Lucratividade");
        groupGraficos.getChildren().add(lucro);
        lucro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                criaDialogoLucratividade();
            }
        });
        
        grid.add(groupGraficos, 0, rowNum++);

        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        primaryStage.setTitle("So vai - Transportadora");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void criaDialogoPedido() {
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
        grid.add(hbClose, 1, 7);

        // Exibe os dados
        Pedidos ped = Pedidos.getInstance();
        tfDlgId.setText(ped.getCorrente().getId());
        tfDlgDestino.setText(ped.getCorrente().getLocal().getCidade());
        tfDlgQtdCaixas.setText("" + ped.getCorrente().qtdadeCaixas());
        tfDlgQtNormal.setText("" + ped.getCorrente().qtdadeCaixasTipo(TipoCaixa.NORMAL));
        tfDlgQtRefrigerada.setText("" + ped.getCorrente().qtdadeCaixasTipo(TipoCaixa.REFRIGERADA));
        tfDlgQtPerecivel.setText("" + ped.getCorrente().qtdadeCaixasTipo(TipoCaixa.PERECIVEL));

        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Detalhamento do pedido");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
    }

    private void criaDialogoPedAtr() {
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));          
        
        LineChart graficoLinha = new LineChart<>(
        new CategoryAxis(), new NumberAxis());

        XYChart.Series prod1 = new XYChart.Series();
        prod1.setName("Pedidos Atrasados");
        
        for (LocalDate key: PedidosAtrasados.getInstance().getAtrasados().keySet()) {
            if(PedidosAtrasados.getInstance().getAtrasados().get(key).intValue() > 0) {
                prod1.getData().add(new XYChart.Data(key.toString(), PedidosAtrasados.getInstance().getAtrasados().get(key).intValue()));   
            }
        }
        
        graficoLinha.getData().addAll(prod1);
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
        grid.add(hbClose, 1, 0);

        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Pedidos Atrasados Por Dia");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
    }

    private void criaDialogoPedAtend() {
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));          
        
        LineChart graficoLinha = new LineChart<>(
        new CategoryAxis(), new NumberAxis());

        XYChart.Series prod1 = new XYChart.Series();
        prod1.setName("Pedidos atendidos");
        
        for (LocalDate key: PedidosAtendidos.getInstance().getAtendidos().keySet()) {
            if(PedidosAtendidos.getInstance().getAtendidos().get(key).intValue() > 0) {
                prod1.getData().add(new XYChart.Data(key.toString(), PedidosAtendidos.getInstance().getAtendidos().get(key).intValue()));   
            }
        }
        
        graficoLinha.getData().addAll(prod1);
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
        grid.add(hbClose, 1, 0);

        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Pedidos atendidos Por Dia");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
        
    }

    private void criaDialogoLucratividade() {
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(new Label("Lucratividade: " + EmTransito.getInstance().getLucroString()), 0, 0);

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
        grid.add(hbClose, 1, 0);

        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Taxa de lucro");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
    }

    private void criaDialogoTxOcup() {
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
        grid.add(hbClose, 1, 0);

        // Adiciona o painel a cena e exibe        
        Scene scene = new Scene(grid);
        dlgStage = new Stage();
        dlgStage.setTitle("Taxa de Ocupação");
        dlgStage.initModality(Modality.APPLICATION_MODAL);
        dlgStage.setScene(scene);
        dlgStage.showAndWait();
    }

    private void criaDialogoLucro() {
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        PieChart graficoPizza = new PieChart();
        Veiculo vg = new VeiculoGrande("aaa", "BBB");
        Veiculo vm = new VeiculoMedio("aaa", "BBB");
        Veiculo vp = new VeiculoPequeno("aaa", "BBB");
        graficoPizza.getData().addAll(new PieChart.Data("Veiculos Grandes", vg.getCapacidadeMax()),
                new PieChart.Data("Veiculos Médios", vm.getCapacidadeMax()),
                new PieChart.Data("Veiculos Pequenos", vp.getCapacidadeMax()));
        graficoPizza.setTitle("Lucros das entregas");
        graficoPizza.setPrefSize(400, 400);

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
        grid.add(hbClose, 0, 1);

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

    @Override
    public void update(Observable o, Object arg) {
        itemsGaragem = FXCollections.observableArrayList(Garagem.getInstance().getVeiculosByDestino(Destinos.getInstance().getLocal(destinoSelecionado).toString()));
        listViewGaragem.setItems(itemsGaragem);
    }

    private void atualizaDiasRestantesVeiculos() {
        ObservableList<Veiculo> items = listViewTransito.getItems();

        for (Veiculo veiculo : items) {
            veiculo.decrementaDia();
            if (veiculo.getTempoRestante() == 0) {
                atualizaListasVeiculos(veiculo);
                veiculo.setEntregue();
                if(veiculo.getPedidos() != null) {
                    for(Pedido p : veiculo.getPedidos()) {
                       pedidosEntregues.add(p);
                    }   
            }
                veiculo.limpaPedidos();
                v.clear();
            }
        }
    }

    private void atualizaListasVeiculos(Veiculo veiculo) {
        EmTransito.getInstance().removeVeiculo(veiculo);
        Garagem.getInstance().viajem(veiculo);

        itemsTransito = FXCollections.observableArrayList(EmTransito.getInstance().getVeiculos());
        listViewTransito.setItems(itemsTransito);
    }

    private int adicionaPedidoAoVeiculo(Pedido itemSelecionadoPedido, Veiculo veiculo) {
        int num = 0;
        if(veiculo.setPesoCarga(itemSelecionadoPedido.pesoTotal(), itemSelecionadoPedido.qtdadeCaixasTipo(TipoCaixa.REFRIGERADA)) == 1){
            if (veiculo != null) {                
                veiculo.addPedido(itemSelecionadoPedido);                
                num = 1;
                listViewGaragem.refresh();
            }
        }else if(veiculo.setPesoCarga(itemSelecionadoPedido.pesoTotal(), itemSelecionadoPedido.qtdadeCaixasTipo(TipoCaixa.REFRIGERADA)) == 2){
            num = 2;
        }else{
            num = 3;            
        }
        return num;
    }

    private void adicionaVeiculoAoPedido(Pedido itemSelecionadoPedido, Veiculo veiculo) {
        if (veiculo != null) {
            itemSelecionadoPedido.addVeiculoEntrega(veiculo);            
        }
    }

    private void removePedidoDaLista(Pedido itemSelecionadoPedido) {
        Pedidos.getInstance().getPedidos().remove(itemSelecionadoPedido);

        itemsPedidos = FXCollections.observableArrayList(Pedidos.getInstance().getPedidos());
        listViewPedidos.setItems(itemsPedidos);
    }

}
