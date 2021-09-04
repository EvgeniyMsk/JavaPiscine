public class Counter implements Runnable {
    private int id;
    private int[] array;
    private int from;
    private int to;

    public Counter(int id, int[] array, int from, int to) {
        this.id = id;
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        int result = 0;
        for (int i : array)
            result += i;
        System.out.printf("Thread %d: from %d to %d sum is %d\n", id, from, to, result);
    }
}
