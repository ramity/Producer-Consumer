public class ProducerConsumer extends Thread
{
    public static void main(String args[])
    {
        //get command line arguments
        int sleepTime = Integer.parseInt(args[0]);
        int producerThreads = Integer.parseInt(args[1]);
        int consumerThreads = Integer.parseInt(args[2]);

        //init int array (serves as our buffer)
        BoundedBuffer buffer = new BoundedBuffer();

        Producer[] producers = new Producer[producerThreads + 1];
        Consumer[] consumers = new Consumer[consumerThreads + 1];

        //create producer threads
        for(int q = 0;q < producerThreads;q++)
        {
            producers[q] = new Producer(100, buffer);
            producers[q].start();
        }

        //create consumer threads
        for(int q = 0;q < consumerThreads;q++)
        {
            consumers[q] = new Consumer(100, buffer);
            consumers[q].start();
        }

        //sleep
        try
        {
            Thread.sleep(sleepTime * 1000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
