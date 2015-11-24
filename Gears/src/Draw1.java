public class Draw1 {
    static final int increments = 1000;
    static final double turns = 2;
    static final int steps = (int) (turns * increments);
    static final double thetaStep = 2 * Math.PI / (increments);

    static final double GEAR1_X = 1;
    static final double GEAR1_Y = 4;

    public static void main(String[] args) {

        StdDraw.setXscale(0, 8);
        StdDraw.setYscale(0, 8);
        StdDraw.show(0);

        double[] r1 = new double[steps];
        for (int i = 0; i < steps; i++) {
            // r1[i] = 2;

            // change the line below
            //r1[i] = 3 + Math.sin(i *
            // thetaStep/2)+.3*Math.cos(i*thetaStep*5.5);
            r1[i]
            =1/(1.0/3.0*Math.pow(Math.cos(i*thetaStep),2)+1.0/2.0*Math.pow(Math.sin(i*thetaStep),2));
            // //7.35
            // r1[i] = 2 + Math.sin(i * thetaStep / 2);
            // r1[i] = 2+Math.sin(i*thetaStep)+.3*Math.cos(5*thetaStep * i);

            
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

        double separation = 5.016446113586426;//4.344892501831055;// 4.3431;//6.269;

        // 4.43
        double[] theta2 = getTheta2(r1, separation);

        int offset = 0;

        // draw2(r1, theta2, separation, offset);

        offset++;
        offset %= steps;

    }

    static double[] getTheta2(double[] r1, double c) {
        double theta2 = 0;
        double[] toReturn = new double[steps];
        for (int i = 0; i < steps; i++) {
            // draw2(r1, toReturn, c, i);
            toReturn[i] = theta2;
            theta2 += thetaStep * r1[i] / (c - r1[i]);

            draw2(r1, toReturn, c, i);
        }

        return toReturn;
    }

    private static void draw(double[] r1, double[] theta2, double c,
            int offset) {
        StdDraw.clear();
        for (int i = 0; i < steps; i++) {
            drawRayA(i * thetaStep - offset * thetaStep, r1[i]);
            // System.out.println(i * thetaStep + "\t" + r1[i]);

            // I'm iffy on the second argument here
            // if (i <= offset)
            drawRayB(theta2[i] - theta2[offset], c - r1[i], c);
        }
        StdDraw.show(0);

    }

    private static void draw2(double[] r1, double[] theta2, double c,
            int offset) {
        StdDraw.clear();
        for (int i = 0; i < steps - 2; i++) {

            lineA(i * thetaStep - offset * thetaStep, r1[i], (i + 1)
                    * thetaStep - offset * thetaStep, r1[i + 1]);
            // System.out.println(i * thetaStep + "\t" + r1[i]);

            // I'm iffy on the second argument here
            // if (i <= offset)

        }

        for (int i = 0; i < offset - 1; i++) {
            lineB(theta2[i] - theta2[offset], c - r1[i], theta2[i + 1]
                    - theta2[offset], c - r1[i + 1], c);
        }
        StdDraw.show(0);

    }

    private static void lineA(double theta1, double l1, double theta2,
            double l2) {
        // System.out.println(l1+"\t"+l2);
        StdDraw.line(GEAR1_X + l1 * Math.cos(theta1),
                GEAR1_Y + l1 * Math.sin(theta1),
                GEAR1_X + l2 * Math.cos(theta2),
                GEAR1_Y + l2 * Math.sin(theta2));

    }

    private static void lineB(double theta1, double l1, double theta2,
            double l2, double c) {
        System.out.println(l1 + "\t" + l2);
        StdDraw.line(GEAR1_X + c - l1 * Math.cos(theta1), GEAR1_Y + l1
                * Math.sin(theta1), GEAR1_X + c - l2 * Math.cos(theta2),
                GEAR1_Y + l2 * Math.sin(theta2));

    }

    private static void drawRayA(double theta, double length) {
        StdDraw.line(GEAR1_X, GEAR1_Y, GEAR1_X + length * Math.cos(theta),
                GEAR1_Y + length * Math.sin(theta));
    }

    private static void drawRayB(double theta, double length, double c) {
        StdDraw.line(GEAR1_X + c, GEAR1_Y,
                GEAR1_X + c - length * Math.cos(theta), GEAR1_Y + length
                        * Math.sin(theta));
    }
}
