/*
 * Name: Liora Levi
 * ID: 326037488
 * Date: 13.01.2026
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TrafficLightSim extends Application {

	// Default durations in milliseconds
	private static long greenDuration = 3000;
	private static long redDuration = 2000; 

	private final List<TrafficLight> trafficLights = new ArrayList<>();
	private volatile boolean running = true;

	public static void main(String[] args) {
		if (args.length >= 2) {
			try {
				greenDuration = Long.parseLong(args[0]);
				redDuration = Long.parseLong(args[1]);
			} catch (NumberFormatException e) {
				System.out.println("Invalid arguments. Using defaults.");
			}
		}
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		root.setStyle("-fx-background-color: WHITE;"); 

		// 4 Traffic Lights - Positioned to leave room for the taller structures
		TrafficLight tlNorth = new TrafficLight(300, 50); 
		TrafficLight tlSouth = new TrafficLight(300, 400);
		TrafficLight tlWest = new TrafficLight(100, 225);
		TrafficLight tlEast = new TrafficLight(500, 225);

		trafficLights.add(tlNorth);
		trafficLights.add(tlSouth);
		trafficLights.add(tlWest);
		trafficLights.add(tlEast);

		for (TrafficLight tl : trafficLights) {
			root.getChildren().addAll(tl.getShapes());
		}

		Thread simulationThread = new Thread(this::runSimulation);
		simulationThread.setDaemon(true); 
		simulationThread.start();

		Scene scene = new Scene(root, 600, 600);
		primaryStage.setTitle("Traffic Light Simulation - Liora Levi");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() {
		running = false;
	}

	private void runSimulation() {
		boolean northSouthGreen = true;

		while (running) {
			final boolean nsGreenState = northSouthGreen;

			Platform.runLater(() -> {
				for (int i = 0; i < trafficLights.size(); i++) {
					TrafficLight tl = trafficLights.get(i);
					// North(0) & South(1) vs West(2) & East(3)
					boolean isNorthSouth = (i == 0 || i == 1);

					if (isNorthSouth == nsGreenState) {
						tl.setVehicleGreen(); 
					} else {
						tl.setVehicleRed();   
					}
				}
			});

			long duration = nsGreenState ? greenDuration : redDuration; 
			long endTime = System.currentTimeMillis() + duration;

			while (System.currentTimeMillis() < endTime && running) {
				try {
					Thread.sleep(200); 
					Platform.runLater(() -> {
						for (TrafficLight tl : trafficLights) {
							if (tl.isPedestrianGreenActive()) {
								tl.togglePedestrianGreen();
							}
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			northSouthGreen = !northSouthGreen;
		}
	}
}
