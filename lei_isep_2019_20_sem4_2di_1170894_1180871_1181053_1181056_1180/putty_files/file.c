#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <pthread.h>

#include <openssl/crypto.h>
#include <openssl/ssl.h>
#include <openssl/err.h>
#include <openssl/conf.h>
#include <openssl/x509.h>

#define SERVER_SSL_CERT_FILE "server.pem"
#define SERVER_SSL_KEY_FILE "server.key"
#define AUTH_CLIENTS_SSL_CERTS_FILE "client.pem"

#define DATA_SIZE 1000
#define BUF_SIZE 30
#define BUF_SIZEU 300
#define SERVER_PORT "30902"
#define SERVER_PORT_CONFIG "30905"
#define GETS(B, S)              \
        {                           \
                fgets(B, S - 2, stdin); \
                B[strlen(B) - 1] = 0;   \
        }
#define VERSION_OFFSET 0
#define CODE_OFFSET 1
#define ID_OFFSET 2
#define DATA_LENGTH_OFFSET 4
#define RAW_DATA_OFFSET 6
#define HELLO_REQUEST 0
#define ACK 150
#define NACK 151

typedef struct
{
        char lastMSGFromSC;
        char rawData[1018];
        int flagStatus;
        unsigned short idMachine;
} shared_data;

typedef struct
{
        unsigned int idMaquina;
        unsigned int tempo_cadencia;
        int argc;
        char **argv;
        shared_data *sData;
} pacotes;

void *main(void *args)
{
        //int idMaquina = (int)args;
    unsigned char bt, b3, b4, version, code, buffer[1024] = "";
        int idMaquina = 12;
    unsigned int dataLength, dataLfinal, lenghtResp;
    struct sockaddr_storage from;
        int err, newSock, sock;
        unsigned int adl;
        unsigned long i, f, n, num, sum;
        struct addrinfo  req, *list;
    char cliIPtext[BUF_SIZE], cliPortText[BUF_SIZE], fileName[BUF_SIZE];

        // requesting a IPv6 local address will allow both IPv4 and IPv6 clients to use it

    bzero((char *)&req, sizeof(req));
    // requesting a IPv6 local address will allow both IPv4 and IPv6 clients to use it
    req.ai_family = AF_INET6;
    req.ai_socktype = SOCK_STREAM;
    req.ai_flags = AI_PASSIVE; // local address

    err = getaddrinfo(NULL, SERVER_PORT_CONFIG, &req, &list);

    if (err)
    {
        printf("Failed to get local address, error: %s\n", gai_strerror(err));
        exit(1);
    }

    sock = socket(list->ai_family, list->ai_socktype, list->ai_protocol);
    if (sock == -1)
    {
        perror("Failed to open socket");
        freeaddrinfo(list);
        exit(1);
    }

    if (bind(sock, (struct sockaddr *)list->ai_addr, list->ai_addrlen) == -1)
    {
        perror("Bind failed");
        close(sock);
        freeaddrinfo(list);
        exit(1);
    }

    freeaddrinfo(list);
    listen(sock, SOMAXCONN);
    const SSL_METHOD *method;
        SSL_CTX *ctx;

        method = SSLv23_server_method();
        ctx = SSL_CTX_new(method);

        // Load the server's certificate and key
        SSL_CTX_use_certificate_file(ctx, SERVER_SSL_CERT_FILE, SSL_FILETYPE_PEM);
        SSL_CTX_use_PrivateKey_file(ctx, SERVER_SSL_KEY_FILE, SSL_FILETYPE_PEM);
        if (!SSL_CTX_check_private_key(ctx)) {
                puts("Error loading server's certificate/key");
                close(sock);
                exit(1);
        }

        // THE CLIENTS' CERTIFICATES ARE TRUSTED
        SSL_CTX_load_verify_locations(ctx,AUTH_CLIENTS_SSL_CERTS_FILE,NULL);

        // Restrict TLS version and cypher suite
        SSL_CTX_set_min_proto_version(ctx,TLS1_2_VERSION);
        SSL_CTX_set_cipher_list(ctx, "HIGH:!aNULL:!kRSA:!PSK:!SRP:!MD5:!RC4");

        // The client must provide a certificate and it must be trusted, the handshake will fail otherwise
        SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER|SSL_VERIFY_FAIL_IF_NO_PEER_CERT, NULL);

    printf("%s", (char *)&req);
    puts("Accepting TCP connections (both over IPv6 or IPv4). Use CTRL+C to terminate the server");

    adl = sizeof(from);
        printf("Antes do while\n");
    while (1)
    {
                printf("Antes do while 2 \n");
        newSock = accept(sock, (struct sockaddr *)&from, &adl);
                if(newSock < 0){
                        printf("new sock <0\n");
                }else{
                        printf("socket criado\n");
                }
        getnameinfo((struct sockaddr *)&from, adl, cliIPtext, BUF_SIZE, cliPortText, BUF_SIZE, NI_NUMERICHOST | NI_NUMERICSERV);
        printf("Request from node %s, port number %s\n", cliIPtext, cliPortText);

        // le o conteudo do buffer
        read(newSock, buffer, sizeof(buffer));
        version = (char)buffer[0];
        code = (char)buffer[1];
        printf("Leitura do pedido:\n");
        printf("Version: %02x\n", version);
        printf("Code: %02x\n", code);
        printf("idMaquina: %02x %02x\n", buffer[2], buffer[3]);
        printf("DataLength: %02x %02x\n", buffer[4], buffer[5]);
        printf("Configuração recebida:\n");
        char recievedRD[1018] = "";
        int i = 6;
        while (buffer[i] != '\0')
        {
            printf("%d ", buffer[i]);
            int n = (int)buffer[i];
            recievedRD[i - 6] = n;
            i++;
        }
        printf("\n");

        printf("Recieved Raw Data : %s\n", recievedRD);

        unsigned short idMachineRecieved;
        printf("antes if\n");
         if (code == 0x0002)
        {
            printf("antes if\n");
            idMachineRecieved = (((short)buffer[2]) << 8) | (0x00ff & buffer[3]);
            char NACKresposta[35] = "Insucesso do pedido de configuracao";
            if (idMachineRecieved == idMaquina)
            {
                printf("antes 2 if\n");
                char ACKresposta[34] = "Sucesso do pedido de configuracao";
                /* Variable to store user content */
                char data[1018];
                char fileName[DATA_SIZE];
                int idMachineRecieved = 12;

                /* File pointer to hold reference to our file */
                FILE *fPtr;

                sprintf(fileName, "%d_CONFIG.txt", idMaquina);

                 /* Open file in w (write) mode.
                        * "data/file1.txt" is complete path to create file
                        */
                fPtr = fopen(fileName, "w");

                /* fopen() return NULL if last operation was unsuccessful */
                if (fPtr == NULL)
                {
                    /* File not created hence exit */
                    printf("Unable to create file.\n");

                    exit(EXIT_FAILURE);
                }

                /* Input contents from user to store in file */
                printf("Configuração a guardar na máquina : %s\n", recievedRD);
                fputs(recievedRD, fPtr);

                /* Close file to save file data */
                fclose(fPtr);
                printf("File created and saved successfully. :) \n");
                printf("ACK\n");
                version = '0';
                code = 150;
                buffer[1] = code;
                dataLength = strlen(recievedRD) + 1;
                dataLfinal = 6 + dataLength;
                b3 = (dataLfinal >> (8 * 1)) & 0xff;
                b4 = (dataLfinal >> (8 * 0)) & 0xff;
                buffer[4] = (char)b3;
                buffer[5] = (char)b4;
                printf("Version: %c\n", version);
                printf("Code: %d\n", code);
                printf("idMaquina: %02x %02x\n", buffer[2], buffer[3]);
                printf("DataLength: %02x %02x\n", b3, b4);
                printf("Mensagem de resposta para enviar:\n");
                printf("%s\n", ACKresposta);
                printf("Write\n");
                write(newSock, buffer, dataLfinal);
            }
             else
            {
                printf("NACK:\n");
                version = '0';
                code = 151;
                buffer[1] = code;
                dataLength = strlen(buffer) + 1;
                dataLfinal = 6 + dataLength;
                b3 = (dataLfinal >> (8 * 1)) & 0xff;
                b4 = (dataLfinal >> (8 * 0)) & 0xff;
                buffer[4] = (char)b3;
                buffer[5] = (char)b4;
                printf("Version: %c\n", version);
                printf("Code: %d\n", code);
                printf("idMaquina: %02x %02x\n", buffer[2], buffer[3]);
                printf("DataLength: %02x %02x\n", b3, b4);
                printf("Mensagem de resposta para enviar:\n");
                printf("%s\n", NACKresposta);

                printf("write\n");
                write(newSock, buffer, dataLfinal);
            }
        }
        printf("fora dos ifs\n");

        close(newSock);
    }
    //pthread_exit((void *)NULL);
    close(sock);
     return 0;
}
