import java.util.Scanner;
import java.util.Arrays;

public class PlayfairCipher {
    //private static final int SIZE = 30;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key: ");
        String key = scanner.nextLine().toLowerCase().replaceAll("[^a-z]", "");
       
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().toLowerCase().replaceAll("[^a-z]", "");
      
        String ciphertext = encryptByPlayfairCipher(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        scanner.close();
    }

    private static String encryptByPlayfairCipher(String plaintext, String key) {
        char[] str = plaintext.toCharArray();
        char[] keyArray = key.toCharArray();
        char[][] keyTable = new char[5][5];

        generateKeyTable(keyArray, keyTable);

        str = prepare(str);

        encrypt(str, keyTable, str.length);

        return new String(str);
    }

    private static void generateKeyTable(char[] key, char[][] keyTable) {
        int ks = key.length;
        boolean[] visited = new boolean[26];
        int row = 0, col = 0;

        for (int i = 0; i < ks; i++) {
            if (!visited[key[i] - 'a']) {
                keyTable[row][col] = key[i];
                visited[key[i] - 'a'] = true;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (c == 'j') continue;
            if (!visited[c - 'a']) {
                keyTable[row][col] = c;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    private static char[] prepare(char[] str) {
        int ptrs = str.length;
        if (ptrs % 2 != 0) {
            str = Arrays.copyOf(str, ptrs + 1); 
            str[ptrs] = 'z';
        }
        return str;
    }

    private static void encrypt(char[] str, char[][] keyTable, int ps) {
        for (int i = 0; i < ps; i += 2) {
            int[] a = new int[4];
            search(keyTable, str[i], str[i + 1], a);

            if (a[0] == a[2]) {
                str[i] = keyTable[a[0]][mod5(a[1] + 1)];
                str[i + 1] = keyTable[a[0]][mod5(a[3] + 1)];
            } else if (a[1] == a[3]) {
                str[i] = keyTable[mod5(a[0] + 1)][a[1]];
                str[i + 1] = keyTable[mod5(a[2] + 1)][a[1]];
            } else {
                str[i] = keyTable[a[0]][a[3]];
                str[i + 1] = keyTable[a[2]][a[1]];
            }
        }
    }

    private static void search(char[][] keyTable, char a, char b, int[] arr) {
        if (a == 'j') a = 'i';
        else if (b == 'j') b = 'i';

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyTable[i][j] == a) {
                    arr[0] = i;
                    arr[1] = j;
                } else if (keyTable[i][j] == b) {
                    arr[2] = i;
                    arr[3] = j;
                }
            }
        }
    }

    private static int mod5(int a) {
        return (a % 5 + 5) % 5;
    }
}

