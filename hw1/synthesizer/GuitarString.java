package synthesizer;
//package <package name>;

//Make sure this class is public
public class GuitarString {
    /* Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<Double> (new Double(Math.round(SR/frequency)).intValue());
        for(int i = 0; i < buffer.capacity(); i += 1) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int i = 0;
        while(i < buffer.capacity()) {
            buffer.dequeue();
            buffer.enqueue(Math.random() - 0.5);
            i += 1;
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        double frontValue = buffer.dequeue();
        buffer.enqueue((frontValue + buffer.peek()) * 0.5 * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
