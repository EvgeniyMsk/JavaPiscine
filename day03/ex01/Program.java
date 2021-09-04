public class Program {
    public static void main(String[] args) {
		if (args.length != 1)
		{
			System.out.println("Пример запуска: java Program --count=10");
			System.exit(-1);
		} else
		{
			int count = Integer.parseInt(args[0].split("=")[1]);
        	ProducerConsumer producerConsumer = new ProducerConsumer();
        	Thread thread1 = new Thread(new Egg(count, producerConsumer));
        	Thread thread2 = new Thread(new Hen(count, producerConsumer));
        	thread1.start();
			thread2.start();
		}
    }
}
