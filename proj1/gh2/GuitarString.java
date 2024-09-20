package gh2;

 import deque.ArrayDeque;
 import deque.Deque;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
     private Deque<Double> buffer;

     /** Capacity of the buffer. */
     private int capacity;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque<>();

        // Fills the buffer with 0 to capacity size
        while (buffer.size() != capacity) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        // Dequeues everything in buffer
        while (!buffer.isEmpty()) {
            buffer.removeFirst();
        }

        // Replaces it with random numbers between -0.5 and 0.5
        while (buffer.size() != capacity) {
            double r = Math.random() - 0.5;
            buffer.addLast(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // Removes the front double
        double front = buffer.removeFirst();
        // Gets the next double
        double nextFront = buffer.get(0);

        // Calculates the new sample which would be the average of
        // the first two samples multiplied by the DECAY factor
        double newSample = DECAY * 0.5 * (front + nextFront);

        // Enqueues the new sample to the last of the buffer
        buffer.addLast(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // The front of the buffer would be index 0
        return buffer.get(0);
    }
}
