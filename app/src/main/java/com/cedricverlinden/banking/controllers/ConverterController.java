package com.cedricverlinden.banking.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.cedricverlinden.banking.constants.Currency;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConverterController extends BaseController implements Initializable {

    @FXML
    private ComboBox<String> fromCurrency;
    @FXML
    private TextField fromInput;

    @FXML
    private ComboBox<String> toCurrency;
    @FXML
    private TextField toInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromCurrency.getItems().addAll(Arrays.stream(Currency.values()).map(Enum::name).toArray(String[]::new));
        toCurrency.getItems().addAll(Arrays.stream(Currency.values()).map(Enum::name).toArray(String[]::new));

        fromInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\\\d*(\\\\.\\\\d*)?")) {
                    fromInput.setText(newValue.replaceAll("[^\\d.]", ""));
                }
            }
        });

        toInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    toInput.setText(newValue.replaceAll("[^\\d.]", ""));
                }
            }
        });
    }

    @FXML
    private void goToHome() {
        getScreenManager().setScreen("Dashboard");
    }

    @FXML
    private void goToAccounts() {
        getScreenManager().setScreen("Accounts");
    }

    @FXML
    private void switchCurrencies() {
        String from = fromCurrency.getValue();
        fromCurrency.setValue(toCurrency.getValue());
        toCurrency.setValue(from);
    }

    @FXML
    private void convert() throws IOException {
        Currency from = Currency.valueOf(fromCurrency.getValue());
        Currency to = Currency.valueOf(toCurrency.getValue());
        
        String url_str = "https://v6.exchangerate-api.com/v6/2669a1383b874f5498fb851c/pair/" + from + "/" + to + "";
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("GET");
        request.connect();
        int responseCode = request.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();
            String req_result = jsonobj.get("conversion_rate").getAsString();
            double rate = Double.parseDouble(req_result);
            double result = Double.parseDouble(fromInput.getText()) * rate;
            toInput.setText(result + "");
        }
    }
}
