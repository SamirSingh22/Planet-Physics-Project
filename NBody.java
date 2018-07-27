import java.awt.*;

public class NBody {
	
	//This reads the radius of the universe and returns it
	
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
	
	//This reads a file and returns an array of planets
	
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[N];
		for (int i = 0; i < N; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return planets;
	}
	
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		
		for (int i = 0; i < planets.length; i++) {
			planets[i].draw();
		}
		StdDraw.enableDoubleBuffering();
		for (int time = 0; time != T; time += dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "./images/starfield.jpg");
			for (int i = 0; i < planets.length; i++) {
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}











































