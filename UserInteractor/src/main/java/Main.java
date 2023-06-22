import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        interactWithUser();
    }

    public static void interactWithUser(){
        Scanner input = new Scanner(System.in);

        FileSerializer serializer = new FileSerializer();
        PortScanner scanner = new PortScanner();
        int startPort = 0;
        int endPort = 0;
        boolean goodInput;
        boolean isShortOutputFormat = false;
        boolean isExit = false;

        while(!isExit){
            do {
                goodInput = true;
                System.out.println("Enter port from which start scanning:");
                if (input.hasNextInt()) {
                    startPort = input.nextInt();
                } else {
                    System.out.println("Wrong port number format. Try again.");
                    input.next();
                    goodInput = false;
                    continue;
                }

                System.out.println("Enter port on which stop scanning:");
                if (input.hasNextInt()) {
                    endPort = input.nextInt();
                } else {
                    System.out.println("Wrong port number format. Try again.");
                    input.next();
                    goodInput = false;
                    continue;
                }

                if (endPort <= startPort) {
                    System.out.println("End port must be greater than start port. Try again.");
                    goodInput = false;
                    continue;
                }

                if(startPort < 0 || endPort > 65535){
                    System.out.println("Wrong port values");
                    goodInput = false;
                    continue;
                }

                System.out.println("Press 1 if you want to use short result format instead of default");
                isShortOutputFormat = Objects.equals(input.next(), "1");

            } while (!goodInput);
            System.out.println("Input result file name:");
            String filename = input.next();
            serializer.serializeResult(scanner.scanPorts(startPort,endPort,"localhost"),(filename+".txt"),isShortOutputFormat);
            System.out.println("press 0 to exit or press another button to scan other ports");
            if(Objects.equals(input.next(), "0")){
                isExit = true;
            }
        }

        input.close();
    }
}
