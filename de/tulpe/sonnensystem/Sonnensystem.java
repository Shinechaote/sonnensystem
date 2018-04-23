/*
* Bilder für Planeten finden und zuordnen
* In Echtzeit umwandeln
* Gravitationsgleichungen benutzen
* Monde hinzufügen(brauch sehr viel Performance)
System in eine ArrayList() bringen  (FERTIG)
* Timeline funktionstüchtig machen
* In 3D umsetzen
    * Exzentrität beachten
    * Formeln ohne Offset definieren
    * 3D System implementieren
* (Hier Ideeen einfügen)
 */
package de.tulpe.sonnensystem;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author praktikant
 */
public class Sonnensystem extends Application {

    public Circle createSun() {
        Circle circle = new Circle(12.5);
        circle.setFill(Color.ORANGE);

        circle.setCenterX(138.8615536385206);
        circle.setCenterY((2.33e-11));

        return circle;
    }

    public planet createMars() {
        planet mars = new planet(24920000, 20660000, 799.94, 0, 1, 0, 0.0010, 3896.2, 10, -138.8615536385206 / 2, 0, 1.025949074);
        return mars;
    }

    public planet createVenus() {
        planet venus = new planet(10816000, 10816000, 583.92, 0, 1, 0, 0.01, 6551.8, 10, 138.8615536385206, 0, 247.01875);
        return venus;
    }

    public planet createJupiter() {
        planet jupiter = new planet(77841200, 77841200, 11.862615 * 365, 0, 1, 0, 0.01, 35796, 10, 138.8615536385206 / 2, 0, 0.413541667);
        return jupiter;
    }

    public planet createUranus() {
        planet uranus = new planet(300500000, 273400000, 84.011 * 365, 0, 1, 0, 0.01, 25559 * 10, 10, 0, 0, 0.718333);
//        Radius 10-fach vergrößert wegen Größe und Sichtbarkeit
        return uranus;
    }

    public planet createNeptun() {
        planet neptun = new planet(453500000, 445800000, 60189, 0, 1, 0, 0.001, 24766 * 10, 10, 0, 0, 0.665266204);
//        Radius 10-fach vergrößert zu Sichtzwecken
        return neptun;
    }

    public planet createSaturn() {
        planet saturn = new planet(150640000, 134760000, 10746, 0, 1, 0, 0.001, 54364, 10, 0, 0, 0.439583333);
        return saturn;
    }

    public planet createMerkur() {
        planet merkur = new planet(5790917.6, 5667000, 115.88, 0, 1, 0, 0.001, 2439.7, 10, 138.8615536385206/1.25, 0, 58.65);
        return merkur;
    }

    public planet createEarth() {
        planet earth = new planet(15210000, 14710000, 365.25, 0, 1, 0, 0.0010, 6378.16, 10, 138.8615536385206 / 2, 0, 0.997268519);
        return earth;
    }

    public GridPane createContent() {
        GridPane grid = new GridPane();
        GridPane controlGrid = new GridPane();
        GridPane subControlGrid = new GridPane();
        Pane animationGrid = new Pane();

        final DragClass dragger = new DragClass(animationGrid.getTranslateX(), animationGrid.getTranslateY(), 0);

        //animationGrid.setPadding(new Insets(10,10,10,20));
        RowConstraints rowinfo3 = new RowConstraints();
        rowinfo3.setPercentHeight(96);
        ColumnConstraints colinfo3 = new ColumnConstraints();
        colinfo3.setPercentWidth(7);
        ColumnConstraints colinfo4 = new ColumnConstraints();
        colinfo3.setPercentWidth(10);
        ColumnConstraints colinfo5 = new ColumnConstraints();
        colinfo3.setPercentWidth(6);
        ColumnConstraints colinfo6 = new ColumnConstraints();
        colinfo6.setPercentWidth(85);
        grid.getRowConstraints().add(rowinfo3);

        subControlGrid.getColumnConstraints().add(colinfo3);
        subControlGrid.getColumnConstraints().add(colinfo3);
        subControlGrid.getColumnConstraints().add(colinfo3);

        subControlGrid.getColumnConstraints().add(colinfo5);
        subControlGrid.getColumnConstraints().add(colinfo5);
        subControlGrid.getColumnConstraints().add(colinfo4);
        subControlGrid.getColumnConstraints().add(colinfo4);
        subControlGrid.getColumnConstraints().add(colinfo4);
        subControlGrid.getColumnConstraints().add(colinfo4);

        controlGrid.getColumnConstraints().add(colinfo6);

        Timeline timeline = new Timeline();

        timeline.setCycleCount(Timeline.INDEFINITE);

        Button start = new Button("Start");
        Button stop = new Button("Stop");
        Button reset = new Button("Reset");
        Button faster = new Button("+");
        Button slower = new Button("-");
        CheckBox showDirection = new CheckBox("Direction");
        CheckBox showPaths = new CheckBox("Path");
        CheckBox makeVisible = new CheckBox("Make Planets bigger(4x)");
        Label copyright = new Label("©2018 The Scherer Clan");

        Image arrowImage = new Image("/de/tulpe/images/testImage.png");

        Image earthImage = new Image("/de/tulpe/images/highlights-ribbon.png");
        Image marsImage = new Image("/de/tulpe/images/testImage.png");
        Image venusImage = new Image("/de/tulpe/images/testImage.png");
        Image jupiterImage = new Image("/de/tulpe/images/testImage.png");
        Image uranusImage = new Image("/de/tulpe/images/testImage.png");
        Image neptunImage = new Image("/de/tulpe/images/testImage.png");
        Image saturnImage = new Image("/de/tulpe/images/testImage.png");
        Image merkurImage = new Image("/de/tulpe/images/testImage.png");

        List<Image> planetImages = new ArrayList<>();
        planetImages.add(earthImage);
        planetImages.add(marsImage);
        planetImages.add(venusImage);
        planetImages.add(jupiterImage);
        planetImages.add(uranusImage);
        planetImages.add(neptunImage);
        planetImages.add(saturnImage);
        planetImages.add(merkurImage);

        List<Image> arrowImages = new ArrayList<>();
        arrowImages.add(arrowImage);
        arrowImages.add(arrowImage);
        arrowImages.add(arrowImage);
        arrowImages.add(arrowImage);
        arrowImages.add(arrowImage);
        arrowImages.add(arrowImage);
        arrowImages.add(arrowImage);
        arrowImages.add(arrowImage);

        List<Color> planetPathColors = new ArrayList<>();
        planetPathColors.add(Color.DODGERBLUE);
        planetPathColors.add(Color.LIGHTSALMON);
        planetPathColors.add(Color.VIOLET);
        planetPathColors.add(Color.DARKGREEN);
        planetPathColors.add(Color.BROWN);
        planetPathColors.add(Color.ORANGE);
        planetPathColors.add(Color.LIME);
        planetPathColors.add(Color.DARKRED);

        List<Color> planetPathTransparent = new ArrayList<>();
        planetPathTransparent.add(Color.TRANSPARENT);
        planetPathTransparent.add(Color.TRANSPARENT);
        planetPathTransparent.add(Color.TRANSPARENT);
        planetPathTransparent.add(Color.TRANSPARENT);
        planetPathTransparent.add(Color.TRANSPARENT);
        planetPathTransparent.add(Color.TRANSPARENT);
        planetPathTransparent.add(Color.TRANSPARENT);
        planetPathTransparent.add(Color.TRANSPARENT);

        TextField timelapseText = new TextField();

        GridPane.setConstraints(start, 0, 1);
        GridPane.setConstraints(stop, 1, 1);
        GridPane.setConstraints(reset, 2, 1);
        GridPane.setConstraints(faster, 3, 1);
        GridPane.setConstraints(slower, 4, 1);
        GridPane.setConstraints(timelapseText, 5, 1);
        GridPane.setConstraints(showDirection, 6, 1);
        GridPane.setConstraints(showPaths, 7, 1);
        GridPane.setConstraints(makeVisible, 8, 1);
        GridPane.setConstraints(copyright, 9, 0);
        GridPane.setConstraints(controlGrid, 0, 1);

        GridPane.setHalignment(start, HPos.LEFT);
        GridPane.setHalignment(stop, HPos.LEFT);
        GridPane.setHalignment(reset, HPos.LEFT);

        subControlGrid.getChildren().addAll(start, stop, reset, faster, slower, timelapseText, showDirection, showPaths, makeVisible);
        controlGrid.getChildren().addAll(subControlGrid, copyright);
        Circle sun = createSun();

//        planet earth = createEarth();
//        planet mars = createMars();
//        planet merkur = createMerkur();
//        planet saturn = createSaturn();
//        planet neptun = createNeptun();
//        planet uranus = createUranus();
//        planet jupiter = createJupiter();
//        planet venus = createVenus();
        PlanetenSystem planets = new PlanetenSystem(createEarth(), createMars(), createVenus(), createJupiter(), createUranus(), createNeptun(), createSaturn(), createMerkur());

        planets.newImage(planetImages);

//        Moon earthMoon = new Moon(383397.7916, 382805.89, 36  , 0, 20, 0, 0.0001*(365.25/27.73) , earth);
        timelapseText.setText(String.valueOf((int) planets.planets.get(0).timelapseSpeed) + " Tage/Sekunde");
        timelapseText.setEditable(false);
        

//        earth.planetRepresentation.setFill(new ImagePattern(earthImage));
//        double oldStrokeWidth = earth.planetRepresentation.getStrokeWidth();
//        earth.planetRepresentation.setStrokeWidth(0);
//        earth.planetRepresentation.setRadius(earth.planetRepresentation.getRadius() + oldStrokeWidth);
//
//        mars.planetRepresentation.setFill(new ImagePattern(marsImage));
//        oldStrokeWidth = mars.planetRepresentation.getStrokeWidth();
//        mars.planetRepresentation.setStrokeWidth(0);
//        mars.planetRepresentation.setRadius(oldStrokeWidth + mars.planetRepresentation.getRadius());
//
//        venus.planetRepresentation.setFill(new ImagePattern(venusImage));
//        oldStrokeWidth = venus.planetRepresentation.getStrokeWidth();
//        venus.planetRepresentation.setStrokeWidth(0);
//        venus.planetRepresentation.setRadius(oldStrokeWidth + venus.planetRepresentation.getRadius());
//
//        merkur.planetRepresentation.setFill(new ImagePattern(merkurImage));
//        oldStrokeWidth = merkur.planetRepresentation.getStrokeWidth();
//        merkur.planetRepresentation.setStrokeWidth(0);
//        merkur.planetRepresentation.setRadius(oldStrokeWidth + merkur.planetRepresentation.getRadius());
//
//        uranus.planetPath.setStrokeWidth(15);
//        uranus.planetRepresentation.setFill(new ImagePattern(uranusImage));
//        oldStrokeWidth = uranus.planetRepresentation.getStrokeWidth();
//        uranus.planetRepresentation.setStrokeWidth(0);
//        uranus.planetRepresentation.setRadius(oldStrokeWidth + uranus.planetRepresentation.getRadius());
//
//        saturn.planetPath.setStrokeWidth(5);
//        saturn.planetRepresentation.setFill(new ImagePattern(saturnImage));
//        oldStrokeWidth = saturn.planetRepresentation.getStrokeWidth();
//        saturn.planetRepresentation.setStrokeWidth(0);
//        saturn.planetRepresentation.setRadius(oldStrokeWidth + saturn.planetRepresentation.getRadius());
//
//        neptun.planetPath.setStrokeWidth(10);
//        neptun.planetRepresentation.setFill(new ImagePattern(neptunImage));
//        oldStrokeWidth = neptun.planetRepresentation.getStrokeWidth();
//        neptun.planetRepresentation.setStrokeWidth(0);
//        neptun.planetRepresentation.setRadius(oldStrokeWidth + neptun.planetRepresentation.getRadius());
//
//        jupiter.planetRepresentation.setFill(new ImagePattern(jupiterImage));
//        oldStrokeWidth = jupiter.planetRepresentation.getStrokeWidth();
//        jupiter.planetRepresentation.setStrokeWidth(0);
//        jupiter.planetRepresentation.setRadius(oldStrokeWidth + jupiter.planetRepresentation.getRadius());
//
//        earth.planetPath.setStroke(Color.TRANSPARENT);
//
//        mars.planetPath.setStroke(Color.TRANSPARENT);
//
//        venus.planetPath.setStroke(Color.TRANSPARENT);
//
//        merkur.planetPath.setStroke(Color.TRANSPARENT);
//
//        uranus.planetPath.setStroke(Color.TRANSPARENT);
//
//        saturn.planetPath.setStroke(Color.TRANSPARENT);
//
//        neptun.planetPath.setStroke(Color.TRANSPARENT);
//
//        jupiter.planetPath.setStroke(Color.TRANSPARENT);
//        animationGrid.getChildren().addAll(earth.planetRepresentation, sun);
//        animationGrid.getChildren().addAll(earth.planetPath);
//
//        animationGrid.getChildren().addAll(merkur.planetRepresentation);
//        animationGrid.getChildren().addAll(merkur.planetPath);
//
//        animationGrid.getChildren().addAll(saturn.planetRepresentation);
//        animationGrid.getChildren().addAll(saturn.planetPath);
//
//        animationGrid.getChildren().addAll(mars.planetRepresentation);
//        animationGrid.getChildren().addAll(mars.planetPath);
//
//        animationGrid.getChildren().addAll(neptun.planetRepresentation);
//        animationGrid.getChildren().addAll(neptun.planetPath);
//
//        animationGrid.getChildren().addAll(uranus.planetRepresentation);
//        animationGrid.getChildren().addAll(uranus.planetPath);
//
//        animationGrid.getChildren().addAll(jupiter.planetRepresentation);
//        animationGrid.getChildren().addAll(jupiter.planetPath);
//
//        animationGrid.getChildren().addAll(venus.planetRepresentation);
//        animationGrid.getChildren().addAll(venus.planetPath);
        planets.newPathColor(planetPathTransparent);
        animationGrid.getChildren().add(sun);
        for (int i = 0; i < planets.planets.size(); i += 1) {
            animationGrid.getChildren().addAll(planets.planets.get(i).planetRepresentation);
            animationGrid.getChildren().addAll(planets.planets.get(i).planetPath);
        }
//        animationGrid.getChildren().addAll(earthMoon.moonRepresentation);
//        animationGrid.getChildren().addAll(earthMoon.moonPath);
        start.setOnAction((ActionEvent event) -> {
            timeline.play();
//            earth.planetTransition.play();
//            mars.planetTransition.play();
//            merkur.planetTransition.play();
//            neptun.planetTransition.play();
//            uranus.planetTransition.play();
//            jupiter.planetTransition.play();
//            venus.planetTransition.play();
//            saturn.planetTransition.play();
            planets.play();
//            earth.planetPathTransition.play();
//            mars.planetPathTransition.play();
//            merkur.planetPathTransition.play();
//            neptun.planetPathTransition.play();
//            uranus.planetPathTransition.play();
//            jupiter.planetPathTransition.play();
//            venus.planetPathTransition.play();
//            earthMoon.moonPathTransition.play();
        });
        stop.setOnAction((ActionEvent event) -> {
            timeline.pause();
//            earth.planetTransition.pause();
//            mars.planetTransition.pause();
//            merkur.planetTransition.pause();
//            neptun.planetTransition.pause();
//            uranus.planetTransition.pause();
//            jupiter.planetTransition.pause();
//            venus.planetTransition.pause();
//            saturn.planetTransition.pause();
            planets.pause();
//            earth.planetPathTransition.pause();
//            mars.planetPathTransition.pause();
//            merkur.planetPathTransition.pause();
//            neptun.planetPathTransition.pause();
//            uranus.planetPathTransition.pause();
//            jupiter.planetPathTransition.pause();
//            venus.planetPathTransition.pause();
        });
        reset.setOnAction((ActionEvent event) -> {
            animationGrid.setScaleX(0.50);
            animationGrid.setScaleY(0.50);
            animationGrid.setTranslateX(dragger.originX);
            animationGrid.setTranslateY(dragger.originY);
            timeline.playFromStart();
//            earth.timelapseSpeed = 10;
//            mars.timelapseSpeed = 10;
//            merkur.timelapseSpeed = 10;
//            neptun.timelapseSpeed = 10;
//            uranus.timelapseSpeed = 10;
//            jupiter.timelapseSpeed = 10;
//            venus.timelapseSpeed = 10;
//            saturn.timelapseSpeed = 10;

//            earth.changeSpeed(earth.timelapseSpeed);
//            mars.changeSpeed(earth.timelapseSpeed);
//            merkur.changeSpeed(earth.timelapseSpeed);
//            neptun.changeSpeed(earth.timelapseSpeed);
//            uranus.changeSpeed(earth.timelapseSpeed);
//            jupiter.changeSpeed(earth.timelapseSpeed);
//            venus.changeSpeed(earth.timelapseSpeed);
//            saturn.changeSpeed(earth.timelapseSpeed);
            timelapseText.setText("10 Tage/Sekunde");

//            earth.planetTransition.jumpTo(Duration.ZERO);
//            mars.planetTransition.jumpTo(Duration.ZERO);
//            merkur.planetTransition.jumpTo(Duration.ZERO);
//            neptun.planetTransition.jumpTo(Duration.ZERO);
//            uranus.planetTransition.jumpTo(Duration.ZERO);
//            jupiter.planetTransition.jumpTo(Duration.ZERO);
//            venus.planetTransition.jumpTo(Duration.ZERO);
//            saturn.planetTransition.jumpTo(Duration.ZERO);
//            earth.planetRotateTransition.jumpTo(Duration.ZERO);
//            mars.planetRotateTransition.jumpTo(Duration.ZERO);
//            merkur.planetRotateTransition.jumpTo(Duration.ZERO);
//            neptun.planetRotateTransition.jumpTo(Duration.ZERO);
//            uranus.planetRotateTransition.jumpTo(Duration.ZERO);
//            jupiter.planetRotateTransition.jumpTo(Duration.ZERO);
//            venus.planetRotateTransition.jumpTo(Duration.ZERO);
//            earthMoon.moonPathTransition.jumpTo(Duration.ZERO);
//            earth.planetTransition.stop();
//            mars.planetTransition.stop();
//            merkur.planetTransition.stop();
//            neptun.planetTransition.stop();
//            uranus.planetTransition.stop();
//            jupiter.planetTransition.stop();
//            venus.planetTransition.stop();
//            saturn.planetTransition.stop();
            planets.stop();
//            earth.planetRotateTransition.stop();
//            mars.planetRotateTransition.stop();
//            merkur.planetRotateTransition.stop();
//            neptun.planetRotateTransition.stop();
//            uranus.planetRotateTransition.stop();
//            jupiter.planetRotateTransition.stop();
//            venus.planetRotateTransition.stop();
//            earthMoon.moonPathTransition.stop();
        });
        faster.setOnAction((ActionEvent event) -> {
            if (planets.planets.get(0).timelapseSpeed >= 1) {
//                earth.timelapseSpeed += 1;
//                mars.timelapseSpeed += 1;
//                merkur.timelapseSpeed += 1;
//                neptun.timelapseSpeed += 1;
//                uranus.timelapseSpeed += 1;
//                jupiter.timelapseSpeed += 1;
//                venus.timelapseSpeed += 1;
//                saturn.timelapseSpeed += 1;
                planets.changeTimeLapse(planets.planets.get(0).timelapseSpeed + 1);
                timelapseText.setText(String.valueOf((int) planets.planets.get(0).timelapseSpeed) + " Tage/Sekunde");
            } else {
//                earth.timelapseSpeed *= 2;
//                mars.timelapseSpeed *= 2;
//                merkur.timelapseSpeed *= 2;
//                neptun.timelapseSpeed *= 2;
//                jupiter.timelapseSpeed *= 2;
//                venus.timelapseSpeed *= 2;
//                saturn.timelapseSpeed *= 2;
//                uranus.timelapseSpeed *= 2;
                planets.changeTimeLapse(planets.planets.get(0).timelapseSpeed * 2);
                if ((int) Math.pow(2, (-1) * Math.log(planets.planets.get(0).timelapseSpeed) / Math.log(2)) == 1) {
                    timelapseText.setText("1 Tage/Sekunde");
                } else if (timelapseText.getText().equals("Echtzeit")) {
//                    earth.timelapseSpeed = 1.0 / Math.pow(2, 16);
//                    mars.timelapseSpeed = 1.0 / Math.pow(2, 16);
//                    merkur.timelapseSpeed = 1.0 / Math.pow(2, 16);
//                    neptun.timelapseSpeed = 1.0 / Math.pow(2, 16);
//                    jupiter.timelapseSpeed = 1.0 / Math.pow(2, 16);
//                    venus.timelapseSpeed = 1.0 / Math.pow(2, 16);
//                    saturn.timelapseSpeed = 1.0 / Math.pow(2, 16);
//                    uranus.timelapseSpeed = 1.0 / Math.pow(2, 16);
                    planets.changeTimeLapse(1.0 / Math.pow(2, 16));

                    timelapseText.setText("1/" + String.valueOf(((int) Math.pow(2, (-1) * Math.log(planets.planets.get(0).timelapseSpeed) / Math.log(2)))) + " Tage/Sekunde");
                } else {
                    timelapseText.setText("1/" + String.valueOf(((int) Math.pow(2, (-1) * Math.log(planets.planets.get(0).timelapseSpeed) / Math.log(2)))) + " Tage/Sekunde");
                }
            }
//            System.out.println(earth.timelapseSpeed);
//            earth.changeSpeed(earth.timelapseSpeed);
//            mars.changeSpeed(earth.timelapseSpeed);
//            merkur.changeSpeed(earth.timelapseSpeed);
//            neptun.changeSpeed(earth.timelapseSpeed);
//            uranus.changeSpeed(earth.timelapseSpeed);
//            jupiter.changeSpeed(earth.timelapseSpeed);
//            venus.changeSpeed(earth.timelapseSpeed);
//            saturn.changeSpeed(earth.timelapseSpeed);

//          earthMoon.changeSpeed(earth.timelapseSpeed);
        });

        slower.setOnAction((ActionEvent event) -> {
            if (planets.planets.get(0).timelapseSpeed > 1) {

//                earth.timelapseSpeed -= 1;
//                mars.timelapseSpeed -= 1;
//                merkur.timelapseSpeed -= 1;
//                neptun.timelapseSpeed -= 1;
//                uranus.timelapseSpeed -= 1;
//                jupiter.timelapseSpeed -= 1;
//                venus.timelapseSpeed -= 1;
//                saturn.timelapseSpeed -= 1;
                planets.changeTimeLapse(planets.planets.get(0).timelapseSpeed - 1);
                timelapseText.setText(String.valueOf((int) planets.planets.get(0).timelapseSpeed) + " Tage/Sekunde");
            } else if (Math.pow(2, (-1) * Math.log(planets.planets.get(0).timelapseSpeed / 2) / Math.log(2)) >= 86400) {

//                earth.timelapseSpeed = 1.0 / 86400;
//                mars.timelapseSpeed = 1.0 / 86400;
//                merkur.timelapseSpeed = 1.0 / 86400;
//                neptun.timelapseSpeed = 1.0 / 86400;
//                uranus.timelapseSpeed = 1.0 / 86400;
//                jupiter.timelapseSpeed = 1.0 / 86400;
//                venus.timelapseSpeed = 1.0 / 86400;
//                saturn.timelapseSpeed = 1.0 / 86400;
                planets.changeTimeLapse(1.0 / 86400);
                timelapseText.setText("Echtzeit");
            } else {

//                earth.timelapseSpeed /= 2;
//                mars.timelapseSpeed /= 2;
//                merkur.timelapseSpeed /= 2;
//                neptun.timelapseSpeed /= 2;
//                uranus.timelapseSpeed /= 2;
//                jupiter.timelapseSpeed /= 2;
//                venus.timelapseSpeed /= 2;
//                saturn.timelapseSpeed /= 2;
                planets.changeTimeLapse(planets.planets.get(0).timelapseSpeed / 2);
                timelapseText.setText("1/" + String.valueOf(((int) Math.pow(2, (-1) * Math.log(planets.planets.get(0).timelapseSpeed) / Math.log(2)))) + " Tage/Sekunde");
            }

//            earth.changeSpeed(earth.timelapseSpeed);
//            mars.changeSpeed(earth.timelapseSpeed);
//            merkur.changeSpeed(earth.timelapseSpeed);
//            neptun.changeSpeed(earth.timelapseSpeed);
//            uranus.changeSpeed(earth.timelapseSpeed);
//            jupiter.changeSpeed(earth.timelapseSpeed);
//            venus.changeSpeed(earth.timelapseSpeed);
//            saturn.changeSpeed(earth.timelapseSpeed);
        });

//          earthMoon.changeSpeed(earth.timelapseSpeed);
        showDirection.setOnAction((ActionEvent event) -> {
            if (showDirection.isSelected()) {
//                earth.planetRepresentation.setFill(new ImagePattern(arrowImage));
//                mars.planetRepresentation.setFill(new ImagePattern(arrowImage));
//                merkur.planetRepresentation.setFill(new ImagePattern(arrowImage));
//                venus.planetRepresentation.setFill(new ImagePattern(arrowImage));
//                saturn.planetRepresentation.setFill(new ImagePattern(arrowImage));
//                neptun.planetRepresentation.setFill(new ImagePattern(arrowImage));
//                uranus.planetRepresentation.setFill(new ImagePattern(arrowImage));
//                jupiter.planetRepresentation.setFill(new ImagePattern(arrowImage));
                planets.newImage(arrowImages);

            } else {
//                earth.planetRepresentation.setFill(new ImagePattern(earthImage));
//                mars.planetRepresentation.setFill(new ImagePattern(marsImage));
//                merkur.planetRepresentation.setFill(new ImagePattern(merkurImage));
//                venus.planetRepresentation.setFill(new ImagePattern(venusImage));
//                saturn.planetRepresentation.setFill(new ImagePattern(saturnImage));
//                neptun.planetRepresentation.setFill(new ImagePattern(neptunImage));
//                uranus.planetRepresentation.setFill(new ImagePattern(uranusImage));
//                jupiter.planetRepresentation.setFill(new ImagePattern(jupiterImage));
                planets.newImage(planetImages);
            }

        });
        showPaths.setOnAction((ActionEvent event) -> {
            if (showPaths.isSelected()) {
//                earth.planetPath.setStroke(Color.DODGERBLUE);
//
//                mars.planetPath.setStroke(Color.LIGHTSALMON);
//
//                venus.planetPath.setStroke(Color.VIOLET);
//
//                merkur.planetPath.setStroke(Color.DARKRED);
//
//                uranus.planetPath.setStroke(Color.BROWN);
//
//                saturn.planetPath.setStroke(Color.LIME);
//
//                neptun.planetPath.setStroke(Color.ORANGE);
//
//                jupiter.planetPath.setStroke(Color.DARKGREEN);

                planets.newPathColor(planetPathColors);
            } else {
//                earth.planetPath.setStroke(Color.TRANSPARENT);
//
//                mars.planetPath.setStroke(Color.TRANSPARENT);
//
//                venus.planetPath.setStroke(Color.TRANSPARENT);
//
//                merkur.planetPath.setStroke(Color.TRANSPARENT);
//
//                uranus.planetPath.setStroke(Color.TRANSPARENT);
//
//                saturn.planetPath.setStroke(Color.TRANSPARENT);
//
//                neptun.planetPath.setStroke(Color.TRANSPARENT);
//
//                jupiter.planetPath.setStroke(Color.TRANSPARENT);
                planets.newPathColor(planetPathTransparent);

            }
//            animationGrid.getChildren().set(2, earth.planetPath);
//            animationGrid.getChildren().set(4, merkur.planetPath);
//            animationGrid.getChildren().set(6, saturn.planetPath);
//            animationGrid.getChildren().set(8, mars.planetPath);
//            animationGrid.getChildren().set(10, neptun.planetPath);
//            animationGrid.getChildren().set(12, uranus.planetPath);
//            animationGrid.getChildren().set(14, jupiter.planetPath);
//            animationGrid.getChildren().set(16, venus.planetPath);

            animationGrid.getChildren().set(2, planets.planets.get(0).planetPath);
            animationGrid.getChildren().set(4, planets.planets.get(1).planetPath);
            animationGrid.getChildren().set(6, planets.planets.get(2).planetPath);
            animationGrid.getChildren().set(8, planets.planets.get(3).planetPath);
            animationGrid.getChildren().set(10, planets.planets.get(4).planetPath);
            animationGrid.getChildren().set(12, planets.planets.get(5).planetPath);
            animationGrid.getChildren().set(14, planets.planets.get(6).planetPath);
            animationGrid.getChildren().set(16, planets.planets.get(7).planetPath);
        });

        makeVisible.setOnAction((ActionEvent event) -> {
            if (makeVisible.isSelected()) {
//                earth.planetRepresentation.setRadius(earth.planetRepresentation.getRadius() * 4);
//                jupiter.planetRepresentation.setRadius(jupiter.planetRepresentation.getRadius() * 4);
//                uranus.planetRepresentation.setRadius(uranus.planetRepresentation.getRadius() * 4);
//                neptun.planetRepresentation.setRadius(neptun.planetRepresentation.getRadius() * 4);
//                saturn.planetRepresentation.setRadius(saturn.planetRepresentation.getRadius() * 4);
//                merkur.planetRepresentation.setRadius(merkur.planetRepresentation.getRadius() * 4);
//                mars.planetRepresentation.setRadius(mars.planetRepresentation.getRadius() * 4);
//                venus.planetRepresentation.setRadius(venus.planetRepresentation.getRadius() * 4);

                planets.makeVisible(4);
            } else {
//                earth.planetRepresentation.setRadius(earth.planetRepresentation.getRadius() / 4);
//                jupiter.planetRepresentation.setRadius(jupiter.planetRepresentation.getRadius() / 4);
//                uranus.planetRepresentation.setRadius(uranus.planetRepresentation.getRadius() / 4);
//                neptun.planetRepresentation.setRadius(neptun.planetRepresentation.getRadius() / 4);
//                saturn.planetRepresentation.setRadius(saturn.planetRepresentation.getRadius() / 4);
//                merkur.planetRepresentation.setRadius(merkur.planetRepresentation.getRadius() / 4);
//                mars.planetRepresentation.setRadius(mars.planetRepresentation.getRadius() / 4);
//                venus.planetRepresentation.setRadius(venus.planetRepresentation.getRadius() / 4);

                planets.makeVisible(0.25);
            }

        });

        grid.setOnScroll((ScrollEvent event) -> {

            double y = event.getDeltaY();

            if (y < 0) {
                for (int i = 0; i < (-1 * y); i += 1) {

                    animationGrid.setScaleX(animationGrid.getScaleX() - animationGrid.getScaleX() / 400);
                    animationGrid.setScaleY(animationGrid.getScaleY() - animationGrid.getScaleY() / 400);
                };
            } else if (y > 0) {
                for (int i = 0; i < y; i += 1) {
                    animationGrid.setScaleX(animationGrid.getScaleX() + animationGrid.getScaleX() / 400);
                    animationGrid.setScaleY(animationGrid.getScaleY() + animationGrid.getScaleY() / 400);

                };
            }
            controlGrid.toFront();
        });
        grid.setOnMousePressed((MouseEvent event) -> {
            dragger.startX = event.getX();
            dragger.startY = event.getY();
            dragger.pressed = true;
        });
        grid.setOnMouseReleased((MouseEvent event) -> {
            dragger.pressed = false;

        });
        grid.setOnMouseDragged((MouseEvent event) -> {
            if (dragger.pressed) {

                animationGrid.setTranslateX(animationGrid.getTranslateX() + event.getX() - dragger.startX);
                animationGrid.setTranslateY(animationGrid.getTranslateY() + event.getY() - dragger.startY);
                dragger.startX = event.getX();
                dragger.startY = event.getY();

            }

        });

        grid.getChildren().addAll(controlGrid, animationGrid);
        animationGrid.setScaleX(0.5);
        animationGrid.setScaleY(0.5);
        animationGrid.toBack();

        controlGrid.toFront();
        planets.play();
        return grid;

    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createContent(), 1280, 980);

        primaryStage.setTitle("Sonnensystem");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

}
