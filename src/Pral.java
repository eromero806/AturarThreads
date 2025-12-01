import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pral {

	public static void main(String[] args) throws IOException {


		int nt = 4; 

        Thread[] threads = new Thread[nt];
        boolean[] running = new boolean[nt];
		
        for(int i = 0; i < nt; i++) {

        	final int num = i + 1; 
            running[i] = true; 
            threads[i] = new Thread(() -> {
                while (running[num - 1]) { 
                    System.out.println(num);
                    try {
                        Thread.sleep(100); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); 
                    }
                }
            });
            threads[i].start();
        	
        }
        
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Introdueix un número del 1 al 5 per aturar el thread corresponent, o 'q' per sortir:");
            String input = read.readLine();

            if (input.equals("q")) {

                for (int i = 0; i < nt; i++) {
                    running[i] = false; 
                }
                break; 
            } else {
                try {
                    int num = Integer.parseInt(input); 
                    if (num >= 1 && num <= 5) {
                        running[num - 1] = false; 
                    } else {
                        System.out.println("Número no vàlid. Si us plau, introdueix un número del 1 al " + nt);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no vàlida. Si us plau, introdueix un número del 1 al" + nt +" o 'q'.");
                }
            }
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Programa finalitzat.");

	}

}
