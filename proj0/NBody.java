public class NBody{

	public static double readRadius(String args){
		In in = new In(args);
		int n_num = in.readInt();
		double radius = in.readDouble();
		in.close();
		return radius;
	}

	public static Planet[] readPlanets(String args){
		In in = new In(args);
		int n_num = in.readInt();
		double radius = in.readDouble();

		Planet[] planets = new Planet[n_num];
		for(int i = 0; i < n_num; i += 1){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			Planet planet = new Planet(xP, yP, xV, yV, m, img);
			planets[i] = planet;
		}
		in.close();
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		int planetlen = planets.length;
		for(int i = 0; i < planetlen; i += 1){
			planets[i].draw();
		}

		StdDraw.enableDoubleBuffering();

		double time = 0;
		while(time < T){
			double[] xForces = new double[planetlen];
			double[] yForces = new double[planetlen];
			for(int i = 0; i < planetlen; i += 1){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int i = 0; i < planetlen; i += 1){
				planets[i].draw();
			}			
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
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