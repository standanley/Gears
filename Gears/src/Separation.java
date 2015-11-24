public class Separation {

    static final int TRIES = 100;

    static final int increments = 1000;
    static final double Drivingturns = 1;
    static final double DrivenTurns = 1;
    static final int steps = (int) (Drivingturns * increments);
    static final double thetaStep = 2 * Math.PI / (increments);

    static final double GEAR1_X = 1;
    static final double GEAR1_Y = 5;

    public static void main(String[] args) {

        double[] r1 = new double[steps];
        for (int i = 0; i < steps; i++) {

            // change the line below
            r1[i] = 3 + Math.sin(i * thetaStep / 2) + .3
                    * Math.cos(i * thetaStep * 5.5);
            // r1[i] =
            // 1/(1.0/3.0*Math.pow(Math.cos(i*thetaStep),2)+1.0/2.0*Math.pow(Math.sin(i*thetaStep),2));
            // //7.35
            
            //r1[i] = 2 + Math.sin(i * thetaStep / 2);

            //r1[i] = 2+Math.sin(i*thetaStep)+.3*Math.cos(5*thetaStep * i);
            //r1[i] = 2 + Math.sin(i * thetaStep / 2);
            r1[i] = 1/(1.0/3.0*Math.pow(Math.cos(i*thetaStep),2)+1.0/2.0*Math.pow(Math.sin(i*thetaStep),2));
            
            /**
            if (i < steps / 2) {
                r1[i] = 1.5 + .5 * Math.cos(i * thetaStep);
            } else {
                r1[i] = 2.5 - .5 * Math.cos(i * thetaStep);
            }
            **/
            
          //now add teeth
            int teeth = 16;
            double height = .2;
            double freq = i * thetaStep * teeth;
            r1[i] += height * Math.sin(freq) + height / 9
                    * Math.sin(3 * freq);
            
        }

        double target = 2 * Math.PI * DrivenTurns;
        double tolerance = .00001;

        double upper = 20;
        double lower = 0;
        double guess = 0;

        boolean found = false;
        for (int i = 0; i < TRIES; i++) {
            guess = (upper + lower) / 2.0;
            double theta2 = getTheta2(r1, guess);
            if (Math.abs(theta2 - target) < tolerance) {
                found = true;
                break;
            }
            
            if(theta2 < target && theta2 > 0){
                upper = guess;
            }else{
                lower = guess;
            }

            System.out.println(i + "\t" + guess + "\t" + theta2);
        }

        if (found) {
            System.out.println("Got it: " + guess);
        } else {

            System.out.println("My best guess is: " + guess);
        }

    }

    static double getTheta2(double[] r1, double c) {
        double theta2 = 0;
        // double[] toReturn = new double[steps];
        for (int i = 0; i < steps; i++) {
            // toReturn[i] = theta2;
            theta2 += thetaStep * r1[i] / (c - r1[i]);

        }

        return theta2;
    }
}
