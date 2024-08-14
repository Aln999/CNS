import java.math.BigInteger;
import java.util.Scanner;

class rsa {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the message to be encrypted:");
        int msg = scanner.nextInt();

        System.out.println("Enter the first prime number (p):");
        int p = scanner.nextInt();

        System.out.println("Enter the second prime number (q):");
        int q = scanner.nextInt();

        scanner.close();

        int n = p * q;
        int phi = (p - 1) * (q - 1);
        System.out.println("The value of z = " + phi);

        int e;
        for (e = 2; e < phi; e++) {
            if (gcd(e, phi) == 1) {
                break;
            }
        }
        System.out.println("The value of e = " + e);

        // int d = 0;
        // for (int i = 0; i <= 9; i++) {
        //     int x = 1 + (i * z);
        //     if (x % e == 0) {
        //         d = x / e;
        //         break;
        //     }
        // }

        int d = 1;
        while ((d * e) % phi != 1) {
            d++;
        }
        System.out.println("The value of d = " + d);

        BigInteger N = BigInteger.valueOf(n);
        BigInteger E = BigInteger.valueOf(e);
        BigInteger D = BigInteger.valueOf(d);
        BigInteger MSG = BigInteger.valueOf(msg);

        // Encryption: c = (msg ^ e) % n
        BigInteger C = MSG.modPow(E, N);
        System.out.println("Encrypted message is: " + C);

        // Decryption: msgback = (C ^ d) % n
        BigInteger msgback = C.modPow(D, N);
        System.out.println("Decrypted message is: " + msgback);
    }

    static int gcd(int a, int b) {
        if (a == 0)
            return b;
        else
            return gcd(b % a, a);
    }
}
