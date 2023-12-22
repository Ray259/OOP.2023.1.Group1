package oop.frontend.controller;


import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import oop.frontend.jfxutils.*;


import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {


    @FXML
    private ToggleGroup menu;


    @FXML
    private TabPane tabPane;


    @FXML
    private ScrollPane homeView;


    @FXML
    private VBox blogView;


    @FXML
    private ComboBox<String> trendingComboBox;


    @FXML
    private ComboBox<String> topComboBox;


    @FXML
    private ToggleGroup menuTrending;


    @FXML
    private TabPane trendingTabPane;


    @FXML
    private ToggleGroup menuTop;


    @FXML
    private TabPane topTabPane;


    @FXML
    private VBox postView;


    @FXML
    private VBox hotTagsView;


    @FXML
    private VBox openseaTrendingView;


    @FXML
    private VBox binanceTrendingView;


    @FXML
    private VBox niftyTrendingView;


    @FXML
    private VBox raribleTrendingView;


    @FXML
    private VBox openseaTopView;


    @FXML
    private VBox binanceTopView;


    @FXML
    private VBox niftyTopView;


    @FXML
    private VBox raribleTopView;


    @FXML
    private JFXTextField search;



    @FXML
    private VBox openseaAnalysisView;


    @FXML
    private VBox binanceAnalysisView;


    @FXML
    private VBox niftyAnalysisView;


    @FXML
    private VBox correlationView;


    @FXML
    private ToggleGroup menuAnalysis;


    @FXML
    private ToggleGroup menuCorrelation;


    @FXML
    void returnHome(MouseEvent event) {
        tabPane.getSelectionModel().select(0);
        menu.getToggles().get(6).setSelected(true);
    }


    @FXML
    void setSelection(ActionEvent event) {
        switch (menuTrending.getToggles().indexOf(menuTrending.getSelectedToggle())) {
            case 0 -> ComboBoxUtil.setViewByComboBox(trendingComboBox, openseaTrendingView,"/trending/opensea");
            case 1 -> ComboBoxUtil.setViewByComboBox(trendingComboBox, binanceTrendingView,"/trending/binance");
            case 2 -> ComboBoxUtil.setViewByComboBox(trendingComboBox, niftyTrendingView,"/trending/niftygateway");
            case 3 -> ComboBoxUtil.setViewByComboBox(trendingComboBox, raribleTrendingView,"/trending/rarible");
            default -> {}
        }
        switch (menuTop.getToggles().indexOf(menuTop.getSelectedToggle())) {
            case 0 -> ComboBoxUtil.setViewByComboBox(topComboBox, openseaTopView,"/top/opensea");
            case 1 -> ComboBoxUtil.setViewByComboBox(topComboBox, binanceTopView,"/top/binance");
            case 2 -> ComboBoxUtil.setViewByComboBox(topComboBox, niftyTopView, "/top/nifty_gateway");
            case 3 -> ComboBoxUtil.setViewByComboBox(topComboBox, raribleTopView, "/top/rarible");
            default -> {}
        }
        switch (menuAnalysis.getToggles().indexOf(menuAnalysis.getSelectedToggle())) {
            case 0 -> ComboBoxUtil.setViewByComboBox(topComboBox, openseaAnalysisView,"/analysis/opensea");
            case 1 -> ComboBoxUtil.setViewByComboBox(topComboBox, binanceAnalysisView,"/analysis/binance");
            case 2 -> ComboBoxUtil.setViewByComboBox(topComboBox, niftyAnalysisView, "/analysis/niftygateway");
            case 3 -> ComboBoxUtil.setViewByComboBox(topComboBox, raribleTrendingView, "/analysis/rarible");
            default -> {}
        }
    }


    @FXML
    void onSearch(ActionEvent event) {
        TextFieldSearchUtil.onSearchAction(search, postView);
    }


    @FXML
    void searchByHasTag(MouseEvent event) {
        TextFieldSearchUtil.onSearchAction(search, postView);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TabPaneUtil.setSelection(menu, tabPane, 1);
        TabPaneUtil.setSelection(menuTrending, trendingTabPane);
        TabPaneUtil.setSelection(menuTop, topTabPane);
        TabPaneUtil.setSelection(menuAnalysis, topTabPane, 1);


        TabPaneUtil.resetIndexTabPane(menu, menuTop, menuTrending, menuAnalysis);
        TabPaneUtil.setViewChange(menu, postView, hotTagsView, openseaTrendingView, openseaTopView, blogView, openseaAnalysisView, correlationView,0,"");
        TabPaneUtil.setViewChange(menuTrending, openseaTrendingView, binanceTrendingView, niftyTrendingView, raribleTrendingView, null,null,null,1,"trending");
        TabPaneUtil.setViewChange(menuTop, openseaTopView, binanceTopView, niftyTopView, raribleTopView, null, null, null,1,"top");
        TabPaneUtil.setViewChange(menuAnalysis, openseaAnalysisView, binanceAnalysisView, niftyAnalysisView, raribleTrendingView, null, null, null,1,"analysis/top");
        TabPaneUtil.setViewChange(menuCorrelation, correlationView, null, null, null, null, null, null,1,"analysis/correlation");


        ComboBoxUtil.setItem(topComboBox);
        ComboBoxUtil.setItem(trendingComboBox);
        ComboBoxUtil.resetComboBoxItem(topComboBox, menu);
        ComboBoxUtil.resetComboBoxItem(topComboBox, menuTop);
        ComboBoxUtil.resetComboBoxItem(trendingComboBox, menu);
        ComboBoxUtil.resetComboBoxItem(trendingComboBox, menuTrending);

        try {
            homeView.setContent(WebViewUtil.setView("/html/GioiThieu.txt"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}



