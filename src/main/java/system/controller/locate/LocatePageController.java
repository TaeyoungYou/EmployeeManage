package system.controller.locate;


import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;
import system.management.location.Location;
import system.management.location.LocationList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

public class LocatePageController implements Initializable {

    @FXML
    private BorderPane mapPane;

    @FXML
    private ScrollPane dataField;
    private VBox dataGrid = new VBox();

    @FXML private TextField nameField;
    @FXML private TextField addrField;

    public static ObservableList<Location> locations = FXCollections.observableArrayList();

    private MapView mapView = new MapView();

    private Marker marker;
    private Coordinate coordinate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocateControllerManager.setLocatePageController(this);
        setGeoCodeMap("Wellington St, Ottawa, ON K1A 0A9");

        initMap();
        addrField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String input = addrField.getText();
                setGeoCodeMap(input);
            }
        });
        addrField.setOnAction(event->{
            String input = addrField.getText();
            setGeoCodeMap(input);
        });

        locations.addListener((ListChangeListener<Location>) change -> {
            while(change.next()) {
                if(change.wasAdded()||change.wasRemoved()){
                    refreshData();
                }
            }
        });
        locations.clear();
        locations.addAll(LocationList.getLocations());
    }

    private void refreshData(){
        dataGrid.getChildren().clear();

        for(Location location : locations){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/locate/locate-data-field.fxml"));
                HBox box = loader.load();
                LocateDataController controller = loader.getController();
                controller.putData(location);
                dataGrid.getChildren().add(box);
            }catch (IOException e){}
        }
        dataField.setContent(dataGrid);
    }

    private void initMap(){
        mapView.setMapType(MapType.OSM);
        mapView.initialize(Configuration.builder().build());

        mapView.initializedProperty().addListener(((observableValue, aBoolean, t1) -> {
            if(t1){
                mapView.addMarker(marker);
                mapView.setCenter(coordinate);
                mapView.setZoom(15);

                mapPane.setCenter(mapView);
            }
        }));
    }

    public void showLocation(Location location){
        setGeoCodeMap(location.getAddress());
    }

    private void updateMap(){
        if(marker == null){
            marker = Marker.createProvided(Marker.Provided.RED).setPosition(coordinate).setVisible(true);
            mapView.addMarker(marker);
        }else{
            marker.setPosition(coordinate);
        }

        mapView.setCenter(coordinate);
        mapView.setZoom(15);
    }

    private void setGeoCodeMap(String address){
        try{
            String apiKey = "AIzaSyDRrXTyK5u4yHFUI9BRyn65YCaD4Dv1TEQ";
            String urlString = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s", URLEncoder.encode(address,"UTF-8"),apiKey);

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String readLine;
            StringBuilder respon = new StringBuilder();
            while((readLine=in.readLine())!=null){
                respon.append(readLine);
            }
            in.close();

            double[] resGeo = getGeo(respon.toString());
            coordinate = new Coordinate(resGeo[0],resGeo[1]);

            updateMap();
        }catch(IOException e){}

    }

    private double[] getGeo(String json){
        JSONObject jsonObject = new JSONObject(json);
        JSONObject location = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
        double lat = location.getDouble("lat");
        double lng = location.getDouble("lng");

        return new double[]{lat,lng};
    }

    @FXML private ImageView addBtn;

    @FXML
    private void handleMouseEnterAdd(MouseEvent event){
        addBtn.setImage(new Image(getClass().getResourceAsStream("/icon/add_blue.png")));
    }

    @FXML
    private void handleMouseExitAdd(MouseEvent event){
        addBtn.setImage(new Image(getClass().getResourceAsStream("/icon/add.png")));
    }

    @FXML
    private void handleClickAdd(MouseEvent event){
        if(nameField.getText().isEmpty()||addrField.getText().isEmpty()){
            return;
        }
        Location location = new Location(nameField.getText(), addrField.getText());
        LocationList.add(location);
        locations.clear();
        locations.addAll(LocationList.getLocations());

        nameField.setText("");
        addrField.setText("");
    }

    public void delLocation(Location location){
        LocationList.delLocation(location);
        locations.clear();
        locations.addAll(LocationList.getLocations());
    }
}
