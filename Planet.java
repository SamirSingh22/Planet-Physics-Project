import static java.lang.Math.sqrt;
public class Planet {
	public static final double G = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	/* This creates a new Planet with its position in the plane, its velocity, its mass, and its picture */
	
	public Planet (double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	// This creates a clone of the planet inputted
	
	public Planet (Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	
	// This calculates the distance between two planets
	
	public double calcDistance (Planet p) {
		double xxDis = this.xxPos - p.xxPos;
		double yyDis = this.yyPos - p.yyPos;
		double totDis = Math.sqrt((xxDis * xxDis) + (yyDis * yyDis));
		return totDis;
	}
	
	// This calculates the force exerted by a planet on another planet
	
	public double calcForceExertedBy (Planet p) {
		double dis = this.calcDistance(p);
		double force = G * this.mass * p.mass / (dis*dis);
		return force;
	}
	
	// This calculates the force exerted in the x direction
	
	public double calcForceExertedByX (Planet p) {
		double dis = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		double xForce = force * (p.xxPos - this.yyPos) / dis;
		return xForce;
	}
	
	// This calculates the force exerted in the y direction
	
	public double calcForceExertedByY (Planet p) {
		double dis = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		double yForce = force * (p.yyPos - this.yyPos) / dis;
		return yForce;
	}
	
	// This calculates the net force exerted in the x direction
	
	public double calcNetForceExertedByX (Planet[] p) {
		double xNet = 0;
		for (int i = 0; i < p.length; i++) {
			if (p[i] == this){
				continue;
			} else {
				xNet += this.calcForceExertedByX(p[i]);
			}
		}
		return xNet;
	}
	
	//This calculates the net force exerted in the y direction
	
	public double calcNetForceExertedByY (Planet[] p) {
		double yNet = 0;
		for (int i = 0; i < p.length; i++) {
			if (p[i] == this){
				continue;
			} else {
				yNet += this.calcForceExertedByY(p[i]);
			}
		}
		return yNet;
	}
	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}
	
	public void draw() {
		String filename = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, filename);
	}
}






































































