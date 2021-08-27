public class Program {

    public static void main(String[] args) throws InterruptedException {
		if (args.length != 1)
		{
			System.out.println("Пример запуска: java Program --count=1000");
			System.exit(-1);
		} else
		{
			int count = Integer.parseInt(args[0].split("=")[1]);
        	Thread m1 = new Thread(new Egg(count));
        	Thread m2 = new Thread(new Hen(count));
        	m1.start();
       	 	m2.start();
        	m1.join();
        	m2.join();
        	for (int i = 0; i < count; i++)
				System.out.println("Human");
        }
    }

}
