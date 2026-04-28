package com.example.paisesdataset;


import com.example.paisesdataset.Data.Api;
import com.example.paisesdataset.Domain.Clima;
import com.example.paisesdataset.Domain.ClimaRespuesta;
import com.example.paisesdataset.Domain.Pais;
import com.example.paisesdataset.Domain.Temperatura;
import com.example.paisesdataset.Domain.ZonaTiempo;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.paisesdataset.Data.Api.consumirClima;

public class PaisController {

    private String pais;


    private LocalDateTime horaBase;
    private double latActual;
    private double lngActual;

    @FXML
    private Text txtInfoPais;

    @FXML
    private Text txtInfoHora;

    @FXML
    private Text txtInfoClima;

    @FXML
    private Button buttonRegreso;
    @FXML
    private Text txtHoraActual;

    @FXML
    private Text txtEstadoRecarga;

    @FXML
    private Text txtNombre;
    @FXML
    private ImageView imagenClima;

    @FXML
    private void regresarPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void recargarClima(ActionEvent event) {
        try {
            actualizarClima(latActual, lngActual);
            txtEstadoRecarga.setStyle("-fx-fill: #2E7D32;");
            txtEstadoRecarga.setText("Clima actualizado");
        } catch (Exception e) {
            txtEstadoRecarga.setStyle("-fx-fill: #C62828;");
            txtEstadoRecarga.setText("No se pudo actualizar");
            e.printStackTrace();
        }
    }

  /*  @FXML
    public void initialize() {
        iniciarRelojConZona();
    }
*/
    public void cargarDatos() {

        try {
            String json = Api.cosumirPais(pais);
            ObjectMapper mapper = new ObjectMapper();
            Pais[] paises = mapper.readValue(json, Pais[].class);

            double lat = paises[0].getLatlng().get(0);
            double lng = paises[0].getLatlng().get(1);
            latActual = lat;
            lngActual = lng;

            String jsonZona = Api.cosumirhora(lat, lng);
            ZonaTiempo tz = mapper.readValue(jsonZona, ZonaTiempo.class);

            txtNombre.setText(pais);

                Pais paisInfo = paises[0];

                txtInfoPais.setText(
                    "Nombre: " + valorTexto(paisInfo.getName() != null ? paisInfo.getName().getCommon() : null) + "\n" +
                    "Capital: " + primerValor(paisInfo.getCapital()) + "\n" +
                    "Región: " + valorTexto(paisInfo.getRegion()) + "\n" +
                    "Población: " + String.format("%,d", paisInfo.getPopulation()) + "\n" +
                    "Zona horaria: " + primerValor(paisInfo.getTimezones()) + "\n" +
                    "Latitud: " + coordenada(paisInfo.getLatlng(), 0) + "\n" +
                    "Longitud: " + coordenada(paisInfo.getLatlng(), 1)
                );

                txtInfoHora.setText(
                    "Región: " + valorTexto(tz.getRegionName()) + "\n" +
                    "Fecha: " + valorTexto(tz.getFormatted().substring(0,10))
                );
                iniciarRelojConZona(valorTexto(tz.getFormatted()));
                actualizarClima(lat, lng);
                txtEstadoRecarga.setStyle("-fx-fill: #2E7D32;");
                txtEstadoRecarga.setText("Clima cargado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarClima(double lat, double lng) throws Exception {
        ObjectMapper mapperWather = new ObjectMapper();
        String jsonWather = consumirClima(lat, lng);
        ClimaRespuesta data = mapperWather.readValue(jsonWather, ClimaRespuesta.class);

        Clima climaActual = (data.getClima() != null && !data.getClima().isEmpty()) ? data.getClima().get(0) : null;
        Temperatura tempActual = data.getTemperatura();

        txtInfoClima.setText(
                "Estado: " + valorTexto(climaActual != null ? climaActual.getPrincipal() : null) + "\n" +
                "Descripción: " + valorTexto(climaActual != null ? climaActual.getDescripcion() : null) + "\n" +
                "Temperatura: " + (tempActual != null ? String.format("%.1f °C", tempActual.getTemp()) : "N/A") + "\n" +
                "Sensación térmica: " + (tempActual != null ? String.format("%.1f °C", tempActual.getSensacionTermica()) : "N/A") + "\n" +
                "Humedad: " + (tempActual != null ? tempActual.getHumedad() + "%" : "N/A")
        );

        cargarImagen(climaActual != null ? climaActual.getIcono() : null);
    }

    public void cargarImagen(String icono   ){
        if (icono == null || icono.isBlank()) {
            imagenClima.setImage(null);
            return;
        }
        String url = "https://openweathermap.org/img/wn/" + icono + "@2x.png";
        Image image = new Image(url);
        imagenClima.setImage(image);

    }



    private String valorTexto(String valor) {
        return (valor == null || valor.isBlank()) ? "N/A" : valor;
    }

    private String primerValor(java.util.List<String> valores) {
        return (valores != null && !valores.isEmpty()) ? valorTexto(valores.get(0)) : "N/A";
    }

    private String coordenada(java.util.List<Double> latlng, int index) {
        return (latlng != null && latlng.size() > index) ? String.format("%.2f", latlng.get(index)) : "N/A";
    }

    public void iniciarRelojConZona(String horaApi) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        horaBase = LocalDateTime.parse(horaApi, formato);

        Timeline reloj = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            horaBase = horaBase.plusSeconds(1);
            txtHoraActual.setText(horaBase.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }));

        reloj.setCycleCount(Timeline.INDEFINITE);
        reloj.play();
    }

    public void setPais(String pais) {
        this.pais = pais;
        cargarDatos();
    }
}