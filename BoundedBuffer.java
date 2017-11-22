import java.util.concurrent.Semaphore;

class BoundedBuffer
{
    private int in;
    private int out;
    private int count;
    private int[] buffer;
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;
    private static final int BUFFER_SIZE = 5;

    public BoundedBuffer()
    {
        //init
        this.in = 0;
        this.out = 0;
        this.count = 0;
        this.buffer = new int[BUFFER_SIZE];

        this.mutex = new Semaphore(1);
        this.empty = new Semaphore(BUFFER_SIZE);
        this.full = new Semaphore(0);
    }

    public void insert(int value)
    {
        try
        {
            this.empty.acquire(1);
            this.mutex.acquire(1);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Producer produced " + value + " @position " + this.in);

        this.buffer[in] = value;
        this.in = (in + 1) % BUFFER_SIZE;

        this.mutex.release(1);
        this.full.release(1);
    }

    public int remove()
    {
        try
        {
            this.full.acquire(1);
            this.mutex.acquire(1);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        int value = this.buffer[out];

        System.out.println("Consumer consumed " + value + " @position " + this.out);

        this.buffer[out] = -1;
        this.out = (out + 1) % BUFFER_SIZE;

        this.mutex.release(1);
        this.empty.release(1);

        return value;
    }
}
