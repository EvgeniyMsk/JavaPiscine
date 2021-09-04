import java.util.Arrays;
public class Program {
    public static void main(String[] args) {
		if (args.length != 2)
		{
			System.out.println("Пример запуска: java Program --arraySize=13 --threadsCount=3");
			System.exit(-1);
		}
		else
		{
			int arraySize = Integer.parseInt(args[0].split("=")[1]);
        	int threadsCount = Integer.parseInt(args[1].split("=")[1]);
        	int[] array = new int[arraySize];
        	int sum = 0;
        	for (int i = 0; i < arraySize; i++)
        	{
            	array[i] = (int)(Math.random() * 5) + 1;
            	sum += array[i];
            	System.out.print(array[i] + " ");
        	}
        	System.out.printf("\nСумма элементов: %d\n", sum);
        	for (int i = 0; i < threadsCount; i++)
        	{
           		int from = i * (arraySize / threadsCount);
            	int to = from + (arraySize / threadsCount) - 1;
            	if (i == threadsCount - 1)
                	new Thread(new Counter(i + 1, Arrays.copyOfRange(array, from, array.length), from, array.length - 1)).start();
            	else
                	new Thread(new Counter(i + 1, Arrays.copyOfRange(array, from, to + 1), from, to)).start();
        	}
		}
    }
}
