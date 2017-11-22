//Lewis Brown
//010744629

import java.util.Random;

class Consumer extends Thread
{
    public static int iterations;
    public static int iterationsAllowed;
    public static BoundedBuffer buffer;

    public Consumer(int iterationsAllowed, BoundedBuffer buffer)
    {
        if(this.buffer == null)
            this.buffer = buffer;
        if(this.iterations == 0)
            this.iterations = 0;
        if(this.iterationsAllowed == 0)
            this.iterationsAllowed = iterationsAllowed;
    }

    public void run()
    {
        System.out.println("Consumer " + this.getId() + " started");

        //init random number generator
        Random dice = new Random();

        while(this.iterations < this.iterationsAllowed)
        {
            int roll = dice.nextInt(500);

            try
            {
                Thread.sleep(roll);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            if(this.iterations < this.iterationsAllowed)
            {
                int result = buffer.remove();

                this.iterations++;
            }
        }

        System.out.println("Consumer " + this.getId() + " finished @" + this.iterations);
    }
}
