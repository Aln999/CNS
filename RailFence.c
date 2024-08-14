#include <stdio.h>
#include <string.h>

void railFenceEncrypt(char *input, char *output) {
    int i, j, k, l;
    l = strlen(input);

    /* Ciphering */
    for (i = 0, j = 0; i < l; i++) {
        if (i % 2 == 0)
            output[j++] = input[i];
    }
    for (i = 0; i < l; i++) {
        if (i % 2 == 1)
            output[j++] = input[i];
    }
    output[j] = '\0';
}

void railFenceDecrypt(char *input, char *output) {
    int i, j, k, l;
    l = strlen(input);

    /* Deciphering */
    if (l % 2 == 0)
        k = l / 2;
    else
        k = (l / 2) + 1;

    for (i = 0, j = 0; i < k; i++) {
        output[j] = input[i];
        j = j + 2;
    }
    for (i = k, j = 1; i < l; i++) {
        output[j] = input[i];
        j = j + 2;
    }
    output[l] = '\0';
}

int main() {
    char input[20], encrypted[20], decrypted[20];

    printf("\n\t\t RAIL FENCE TECHNIQUE");
    printf("\n\nEnter the input string: ");
    fgets(input, sizeof(input), stdin); // Use fgets to read input safely

    // Remove newline character from input
    if (input[strlen(input) - 1] == '\n') {
        input[strlen(input) - 1] = '\0';
    }

    railFenceEncrypt(input, encrypted);
    printf("\nCipher text after applying rail fence: %s", encrypted);

    railFenceDecrypt(encrypted, decrypted);
    printf("\nText after decryption: %s\n", decrypted);

    return 0;
}

