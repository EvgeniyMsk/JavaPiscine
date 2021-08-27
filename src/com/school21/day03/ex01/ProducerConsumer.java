public class ProducerConsumer {
    private static boolean flag = false;
    synchronized void sayHen() throws InterruptedException {
        if (flag)
        {
            System.out.println("Hen");
            wait();
        }
        flag = !flag;
        notify();
    }

    synchronized void sayEgg() throws InterruptedException {
        if (!flag) {
            System.out.println("Egg");
            wait();
        }
        flag = !flag;
        notify();
    }
}
