#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_LEN 100

void encrypt(char *message, int key[3][3])
{
    int len = strlen(message);
    int i, j, k;
    int cipher[MAX_LEN] = {0};

    for (i = 0; i < len; i += 3)
    {
        for (j = 0; j < 3; j++)
        {
            int sum = 0;
            for (k = 0; k < 3; k++)
            {
                sum += key[j][k] * (message[i + k] - 'A');
            }
            cipher[i + j] = sum % 26;
        }
    }

    for (i = 0; i < len; i++)
    {
        message[i] = cipher[i] + 'A';
    }
}

void decrypt(char *message, int key[3][3])
{
    int len = strlen(message);
    int i, j, k;
    int decipher[MAX_LEN] = {0};

    int det = key[0][0] * (key[1][1] * key[2][2] - key[1][2] * key[2][1]) -
              key[0][1] * (key[1][0] * key[2][2] - key[1][2] * key[2][0]) +
              key[0][2] * (key[1][0] * key[2][1] - key[1][1] * key[2][0]);

    int detInverse = 0;
    for (detInverse = 1; detInverse < 26; detInverse++)
    {
        if ((det * detInverse) % 26 == 1)
            break;
    }

    int adj[3][3];
    for (i = 0; i < 3; i++)
    {
        for (j = 0; j < 3; j++)
        {
            adj[j][i] = ((key[(i + 1) % 3][(j + 1) % 3] * key[(i + 2) % 3][(j + 2) % 3]) -
                         (key[(i + 1) % 3][(j + 2) % 3] * key[(i + 2) % 3][(j + 1) % 3]));

            adj[j][i] = (adj[j][i] + 26) % 26;
        }
    }

    int invKey[3][3];
    for (i = 0; i < 3; i++)
    {
        for (j = 0; j < 3; j++)
        {
            invKey[i][j] = (adj[i][j] * detInverse) % 26;

            if (invKey[i][j] < 0)
                invKey[i][j] += 26;
        }
    }

    for (i = 0; i < len; i += 3)
    {
        for (j = 0; j < 3; j++)
        {
            int sum = 0;
            for (k = 0; k < 3; k++)
            {
                sum += invKey[j][k] * (message[i + k] - 'A');
            }
            decipher[i + j] = sum % 26;

            if (decipher[i + j] < 0)
                decipher[i + j] += 26;
        }
    }

    for (i = 0; i < len; i++)
    {
        message[i] = decipher[i] + 'A';
    }
}

int main()
{
    char message[MAX_LEN];

    int key[3][3];
    printf("Enter the 3x3 key matrix:\n");
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            scanf("%d", &key[i][j]);
        }
    }

    printf("Enter the message for encryption:\n");
    scanf("%s", message);

    encrypt(message, key);

    printf("Encrypted message: %s\n", message);

    decrypt(message, key);
    printf("Decrypted message: %s\n", message);

    return 0;
}
