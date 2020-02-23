public class Planet{
	// instance variables
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double gvalue = 6.67e-11;

	// constructors
	public Planet(double xP, double yP, double xV,
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/* copy constructor */
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double distance_x = Math.pow((xxPos - p.xxPos), 2);
		double distance_y = Math.pow((yyPos - p.yyPos), 2);
		double distance = Math.sqrt(distance_x + distance_y);
		return distance;
	}

	public double calcForceExertedBy(Planet p){
		double distance = this.calcDistance(p);
		double force = gvalue * mass * p.mass / Math.pow(distance, 2);
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double distance = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		double force_x = force * (p.xxPos - xxPos) / distance;
		return force_x;
	}

	public double calcForceExertedByY(Planet p){
		double distance = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		double force_y = force * (p.yyPos - yyPos) / distance;
		return force_y;
	}

	public double calcNetForceExertedByX(Planet[] allplanets){
		int arrlen = allplanets.length;
		double result = 0;
		for(int i = 0; i < arrlen; i += 1){
			if(this.equals(allplanets[i]) == false)
				result += this.calcForceExertedByX(allplanets[i]);
		}
		return result;
	}

	public double calcNetForceExertedByY(Planet[] allplanets){
		int arrlen = allplanets.length;
		double result = 0;
		for(int i = 0; i < arrlen; i += 1){
			if(this.equals(allplanets[i]) == false)
				result += this.calcForceExertedByY(allplanets[i]);
		}
		return result;
	}

	public void update(double dt, double fX, double fY){
		double ax = fX/mass;
		double ay = fY/mass;
		xxVel = xxVel + dt * ax;
		yyVel = yyVel + dt * ay;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;		
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}