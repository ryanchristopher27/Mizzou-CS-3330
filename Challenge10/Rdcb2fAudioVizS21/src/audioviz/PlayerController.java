/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audioviz;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Professor Wergeles 
 * @author Ryan Christopher
 * 
 * Music: 
 * http://www.bensound.com/royalty-free-music
 * http://www.audiocheck.net/testtones_sinesweep20-20k.php
 * 
 * 
 * References: 
 * http://stackoverflow.com/questions/11994366/how-to-reference-primarystage
 */
public class PlayerController implements Initializable {

    @FXML
    private AnchorPane vizPane;

    @FXML
    private MediaView mediaView;

    @FXML
    private Text filePathText;

    @FXML
    private Text lengthText;

    @FXML
    private Text currentText;

    @FXML
    private Text bandsText;

    @FXML
    private Text visualizerNameText;

    @FXML
    private Text errorText;

    @FXML
    private Menu visualizersMenu;

    @FXML
    private Menu bandsMenu;

    @FXML
    private Slider timeSlider;

    @FXML
    private Button playPause;
    
    @FXML
    private Slider volumeSlider;

    private Media media;
    private MediaPlayer mediaPlayer;

    private Integer numOfBands;
    private final Double updateInterval = 0.05;

    private ArrayList<Visualizer> visualizers;
    private Visualizer currentVisualizer;
    private final Integer[] bandsList = {1, 2, 4, 8, 16, 20, 40, 60, 80, 100, 120, 140, 180, 200};

    private int currentStatus = 0;
    
    @FXML
    private Text volumeText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numOfBands = 40;
        
        bandsText.setText(Integer.toString(numOfBands));

        visualizers = new ArrayList<>();
        visualizers.add(new EllipseVisualizer1());
        visualizers.add(new EllipseVisualizer2());
        visualizers.add(new EllipseVisualizer3());
        
        // Add my visualizer here
        visualizers.add(new Rdcb2fPrimaryVisualizerS21());
        visualizers.add(new Rdcb2fSecondaryVisualizerS21());
        
        for (Visualizer visualizer : visualizers) {
            MenuItem menuItem = new MenuItem(visualizer.getVizName());
            menuItem.setUserData(visualizer);
            menuItem.setOnAction((ActionEvent event) -> {
                selectVisualizer(event);
            });
            visualizersMenu.getItems().add(menuItem);
        }
        
        currentVisualizer = visualizers.get(0);
        visualizerNameText.setText(currentVisualizer.getVizName());

        for (Integer bands : bandsList) {
            MenuItem menuItem = new MenuItem(Integer.toString(bands));
            menuItem.setUserData(bands);
            menuItem.setOnAction((ActionEvent event) -> {
                selectBands(event);
            });
            bandsMenu.getItems().add(menuItem);
        }
    }

    private void selectVisualizer(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        Visualizer visualizer = (Visualizer) menuItem.getUserData();
        changeVisualizer(visualizer);
    }

    private void selectBands(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        numOfBands = (Integer) menuItem.getUserData();
        if (currentVisualizer != null) {
            currentVisualizer.setup(numOfBands, vizPane);
        }
        if (mediaPlayer != null) {
            mediaPlayer.setAudioSpectrumNumBands(numOfBands);
        }
        bandsText.setText(Integer.toString(numOfBands));
    }

    private void changeVisualizer(Visualizer visualizer) {
        if (currentVisualizer != null) {
            currentVisualizer.destroy();
        }
        currentVisualizer = visualizer;
        currentVisualizer.setup(numOfBands, vizPane);
        visualizerNameText.setText(currentVisualizer.getVizName());
    }

    private void openMedia(File file) {
        filePathText.setText("");
        errorText.setText("");

        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }

        try {
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setOnReady(() -> {
                handleReady();
            });
            mediaPlayer.setOnEndOfMedia(() -> {
                handleEndOfMedia();
            });
            mediaPlayer.setAudioSpectrumNumBands(numOfBands);
            mediaPlayer.setAudioSpectrumInterval(updateInterval);
            mediaPlayer.setAudioSpectrumListener((double timestamp, double duration, float[] magnitudes, float[] phases) -> {
                handleVisualize(timestamp, duration, magnitudes, phases);
            });
            mediaPlayer.setAutoPlay(true);
            filePathText.setText(file.getPath());
            currentStatus = 1;
            mediaPlayer.play();
            playPause.setText("Pause");
        } catch (Exception ex) {
            errorText.setText(ex.toString());
        }
    }

    private void handleReady() {
        Duration duration = mediaPlayer.getTotalDuration();
//        lengthText.setText(duration.toString());
        setLengthText();
//        Duration ct = mediaPlayer.getCurrentTime();
//        currentText.setText(ct.toString());
        setCurrentTime();
        currentVisualizer.setup(numOfBands, vizPane);
        timeSlider.setMin(0);
        timeSlider.setMax(duration.toMillis());
        
        volumeSlider.setMin(0);
        volumeSlider.setMax(1);
        volumeSlider.setValue(1);
    }

    private void handleEndOfMedia() {
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        timeSlider.setValue(0);
        
        volumeSlider.setValue(0);
    }

    private void handleVisualize(double timestamp, double duration, float[] magnitudes, float[] phases) {
        Duration ct = mediaPlayer.getCurrentTime();
        double ms = ct.toMillis();
//        currentText.setText(String.format("%.1f ms", ms));
        setCurrentTime();
        timeSlider.setValue(ms);

        currentVisualizer.visualize(timestamp, duration, magnitudes, phases);
    }

    @FXML
    private void handleOpen(Event event) {
        Stage primaryStage = (Stage) vizPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            openMedia(file);
        }
    }

    @FXML
    private void handlePlayPause(ActionEvent event) {
        if (mediaPlayer != null) {
            if (currentStatus == 0) {
                currentStatus = 1;
                mediaPlayer.play();
                volumeSlider.setValue(mediaPlayer.getVolume());
                playPause.setText("Pause");
            } else {
                currentStatus = 0;
                mediaPlayer.pause();
                playPause.setText("Play");
            }
        }
    }
    
    @FXML
    private void handleSliderMousePressed(Event event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    private void handleSliderMouseReleased(Event event) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(new Duration(timeSlider.getValue()));
            System.out.println(timeSlider.getValue());
            currentVisualizer.setup(numOfBands, vizPane);
            mediaPlayer.play();
        }
    }

    @FXML
    private void handleVolumeSliderMouseReleased(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volumeSlider.getValue());
            
            String temp = String.format("%d", Math.round(volumeSlider.getValue() * 100));
            volumeText.setText(temp + "%");
        }
    }
    
    private void setLengthText() {
        Duration duration = mediaPlayer.getTotalDuration();
        
        int minutes = (int) Math.floor(duration.toMinutes());
        int seconds = (int) Math.floor(duration.toSeconds()) - (minutes * 60);
                        
        String temp = String.format("%02d:%02d", minutes, seconds);
                
        lengthText.setText(temp + " s");
    }
    
    private void setCurrentTime() {
        Duration ct = mediaPlayer.getCurrentTime();
        
        int minutes = (int) Math.floor(ct.toMinutes());
        int seconds = (int) Math.floor(ct.toSeconds()) - (minutes * 60);
        
        String temp = String.format("%02d:%02d", minutes, seconds);
        
        currentText.setText(temp + " s");
    }
}