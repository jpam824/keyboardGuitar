package gh2;
import javax.swing.*;
import java.util.*;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero extends JApplet{

    static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final double CONCERT_A = 440.0;
    public static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.5, .8, "Play Some Guitar, Dawg! (use the keyboard.)");
        StdDraw.picture(0.5, .4, "guitar.png", 0.9, 0.6);


        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        ArrayList<GuitarString> strings = new ArrayList<>();
        for(int i = 0; i < keyboard.length(); i++){
            double poop = CONCERT_A * Math.pow(2, (((double)i-24)/12));
            strings.add(new GuitarString(poop));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
                if (StdDraw.hasNextKeyTyped()) {
                    char key = StdDraw.nextKeyTyped();
                    if(keyboard.contains(String.valueOf(key))) {
                        int ind = keyboard.indexOf(key);
                        strings.get(ind).pluck();
                        System.out.println(strings.get(ind).freq);
                        StdDraw.text(0, 0, "Play some guitar, dawg.");

                    }
                }


            /* compute the superposition of samples */
            double sample = 0;

            for(GuitarString f: strings) {
                sample += f.sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(GuitarString f: strings){
                f.tic();
            }
        }
    }

}
