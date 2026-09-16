/*
 * Name: Liora Levi
 * ID: 326037488
 * Date: 13.01.2026
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Inner class representing a single Traffic Light unit.
 * Design: TIGHT PACKING (No gaps), Taller Rectangles for pedestrians.
 */
public class TrafficLight {
	private final Circle vehicleRed;
	private final Circle vehicleGreen;
	private final Rectangle pedestrianRed;
	private final Rectangle pedestrianGreen;

	private boolean pedestrianGreenActive = false; 

	public TrafficLight(double x, double y) {
		double radius = 15;
		double diameter = radius * 2; // Width is 30
		double rectHeight = 40; // גובה חדש למלבנים - יותר גבוהים!

		// 1. Vehicle Red (Top)
		vehicleRed = new Circle(radius, Color.WHITE);
		vehicleRed.setStroke(Color.BLACK);
		vehicleRed.setCenterX(x); 
		vehicleRed.setCenterY(y);

		// 2. Vehicle Green (Touching Red from below)
		vehicleGreen = new Circle(radius, Color.WHITE);
		vehicleGreen.setStroke(Color.BLACK);
		vehicleGreen.setCenterX(x); 
		vehicleGreen.setCenterY(y + diameter); 

		// 3. Pedestrian Red (Touching Vehicle Green) - Rectangle
		// Start Y = Center of Green (y+30) + Radius (15) = y+45
		pedestrianRed = new Rectangle(diameter, rectHeight, Color.WHITE); // 30x40 Rectangle
		pedestrianRed.setStroke(Color.BLACK);
		pedestrianRed.setX(x - radius); // Center horizontally
		pedestrianRed.setY(y + diameter + radius); // y + 45

		// 4. Pedestrian Green (Touching Pedestrian Red) - Rectangle
		// Start Y = Start of Ped Red (y+45) + Height (40) = y+85
		pedestrianGreen = new Rectangle(diameter, rectHeight, Color.WHITE); // 30x40 Rectangle
		pedestrianGreen.setStroke(Color.BLACK);
		pedestrianGreen.setX(x - radius);
		pedestrianGreen.setY(y + diameter + radius + rectHeight); // y + 85
	}

	public Shape[] getShapes() {
		return new Shape[]{vehicleRed, vehicleGreen, pedestrianRed, pedestrianGreen};
	}

	public boolean isPedestrianGreenActive() {
		return pedestrianGreenActive;
	}

	public void setVehicleGreen() {
		pedestrianGreenActive = false;

		vehicleRed.setFill(Color.WHITE);
		vehicleGreen.setFill(Color.LIME);

		pedestrianRed.setFill(Color.RED);
		pedestrianGreen.setFill(Color.WHITE);
	}

	public void setVehicleRed() {
		pedestrianGreenActive = true;

		vehicleRed.setFill(Color.RED);
		vehicleGreen.setFill(Color.WHITE);

		pedestrianRed.setFill(Color.WHITE);
	}

	public void togglePedestrianGreen() {
		if (pedestrianGreen.getFill() == Color.LIME) {
			pedestrianGreen.setFill(Color.WHITE);
		} else {
			pedestrianGreen.setFill(Color.LIME);
		}
	}
}
