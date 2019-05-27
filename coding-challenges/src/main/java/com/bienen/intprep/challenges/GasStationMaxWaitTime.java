package com.bienen.intprep.challenges;

import java.util.ArrayList;
import java.util.List;

/*
 * 
Problem Statement from Codility:
a queue of N cars waiting at a filling Gas station (A[]) with respect needed amount of gas, 
there are 3 fuel dispensers (X,Y,Z) with a limit amount of fuel. it takes 1 sec to fill 1 liter. Cars move instantly.
suppose that the fuel demand is D for a car, the car should go to a dispenser that provides at least D liters or waits.
if all dispensers are unoccupied the car goes to the first one alphabetically. 
return -1 when any car won't be able to refuel anymore.
for example X=7, Y=11, Z=13 and A=[2,8,4,3,2]
at time0 car0 drives to X and car1 drives to Y there is not enough fuel in Z for car2, 
so it waits at time2 car0 finishes refueling and car 2 drives to dispenser X at time2 car3 drives to dispenser Z 
all dispenser are occupied so car4 waits there would not be enough gas in X and Y for car4 when car2 and 
car3 leave so car4 waits until car1 finishes refueling. At time8 car 4 drives to dispenser Y.

maxwait=8
 * 
 * 
 */
public class GasStationMaxWaitTime {

	public static void main(String[] args) {
		System.out.println(solutionCarRefueling(new int[] { 2, 8, 4, 3, 2 }, 7, 11, 3));// 8
		System.out.println(solutionCarRefueling(new int[] { 2, 3, 4 }, 0, 0, 0));// -1
		System.out.println(solutionCarRefueling(new int[] { 2 }, 1, 2, 0));// 0
		System.out.println(solutionCarRefueling(new int[] { 2, 3 }, 1, 5, 0));// 0
	}

	public static int solutionCarRefueling(int[] A, int X, int Y, int Z) {

		if (A.length <= 0)
			return -1;

		List<Car> cars = new ArrayList<Car>();
		for (int i = 0; i < A.length; i++) {
			cars.add(new Car(A[i]));
		}

		List<FuelStation> stations = new ArrayList<>();
		stations.add(new FuelStation(false, X));
		stations.add(new FuelStation(false, Y));
		stations.add(new FuelStation(false, Z));

		for (Car car : cars) {

			for (FuelStation fuelStation : stations) {
				if (car.tankCapacity <= fuelStation.capacity && !fuelStation.isOccupied) {
					fuelStation.isOccupied = true;
					fuelStation.capacity = fuelStation.capacity - car.tankCapacity;
					fuelStation.nextCarWaitTime = car.tankCapacity;
					car.waitTime = 0;
					break;
				}
				if (car.tankCapacity <= fuelStation.capacity && fuelStation.isOccupied) {
					fuelStation.isOccupied = false;
					fuelStation.capacity = fuelStation.capacity - car.tankCapacity;
					car.waitTime = fuelStation.nextCarWaitTime;
					fuelStation.nextCarWaitTime = fuelStation.nextCarWaitTime + car.tankCapacity;
					fuelStation.isOccupied = true;
					break;
				}
				if (car.tankCapacity > fuelStation.capacity) {
					car.waitTime = -1;
				}
			}
		}

		return cars.stream().mapToInt(x -> x.waitTime).max().getAsInt();

	}

}

class FuelStation {
	boolean isOccupied = false;
	int capacity = 0;
	int nextCarWaitTime = 0;

	public FuelStation(boolean isOccupied, int capacity) {
		this.isOccupied = isOccupied;
		this.capacity = capacity;
	}
}

class Car {
	int tankCapacity;
	int waitTime;

	public Car(int tankCapacity) {
		super();
		this.tankCapacity = tankCapacity;
	}

}
