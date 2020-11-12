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

#define DATA_SIZE 1000
#define BUF_SIZE 30
#define BUF_SIZEU 512
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
#define RESET_REQUEST 3
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

void *config_messg(void *args)
{
	int idMaquina = (int)args;
    unsigned char bt, b3, b4, version, code, buffer[1024] = "";
	idMaquina = 12;
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

        /*
		FD_ZERO(&fdset);

		// monitorizar o socket
		FD_SET(sock, &fdset);

		if (select(sock + 1, &fdset, NULL, NULL, NULL) < 0)
		{
			printf("Select error\n");
			exit(1);
		}
		*/

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

    pthread_exit((void *)NULL);
    close(sock);


}

void *tcp_cli(void *args)
{

	pacotes *pacote = (pacotes *)args;
	unsigned int idMaquina = (pacote->idMaquina);
	unsigned int tempo_cadencia = (pacote->tempo_cadencia);
	int argc = pacote->argc;
	char **argv = (char **)pacote->argv;
	shared_data *sData = (shared_data *)(pacote->sData);

	sData->idMachine = idMaquina;
	FILE *file;
	int err, sock, b1, b2, b3, b4;
	unsigned int dataLength, dataLfinal, lenghtResp;
	unsigned char bt[1024] = "", version, code;
	char rawData[1018] = "";
	struct addrinfo req, *list;
	char line[BUF_SIZE];
	if (argc != 5)
	{
		puts("Server's IPv4/IPv6 address or DNS name, IDMachine, FileName and Time is required as argument");
		exit(1);
	}

	bzero((char *)&req, sizeof(req));
	printf("1\n");
	// let getaddrinfo set the family depending on the supplied server address
	req.ai_family = AF_UNSPEC;
	printf("2\n");
	req.ai_socktype = SOCK_STREAM;
	printf("3\n");
	err = getaddrinfo(argv[1], SERVER_PORT, &req, &list);
	printf("4\n");
	if (err)
	{
		printf("Failed to get server address, error: %s\n", gai_strerror(err));
		sData->flagStatus = 0;
		exit(1);
	}

	printf("5\n");
	sock = socket(list->ai_family, list->ai_socktype, list->ai_protocol);
	printf("6\n");
	if (sock == -1)
	{
		perror("Failed to open socket");
		freeaddrinfo(list);
		sData->flagStatus = 0;
		exit(1);
	}

	printf("7\n");
	if (connect(sock, (struct sockaddr *)list->ai_addr, list->ai_addrlen) == -1)
	{
		perror("Failed connect");
		freeaddrinfo(list);
		close(sock);
		sData->flagStatus = 0;
		exit(1);
	}

	printf("8\n");

	const SSL_METHOD *method = SSLv23_client_method();
	SSL_CTX *ctx = SSL_CTX_new(method);

	if (argc == 5)
	{
		// Load client's certificate and key
		strcpy(line, argv[4]);
		strcat(line, ".pem");
		printf("%s\n", line);
		SSL_CTX_use_certificate_file(ctx, line, SSL_FILETYPE_PEM);
		strcpy(line, argv[4]);
		strcat(line, ".key");
		printf("%s\n", line);
		SSL_CTX_use_PrivateKey_file(ctx, line, SSL_FILETYPE_PEM);
		if (!SSL_CTX_check_private_key(ctx))
		{
			puts("Error loading client's certificate/key");
			close(sock);
			exit(1);
		}
	}

	SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER, NULL);

	// THE SERVER'S CERTIFICATE IS TRUSTED
	SSL_CTX_load_verify_locations(ctx, SERVER_SSL_CERT_FILE, NULL);

	// Restrict TLS version and cypher suites
	SSL_CTX_set_min_proto_version(ctx, TLS1_2_VERSION);
	SSL_CTX_set_cipher_list(ctx, "HIGH:!aNULL:!kRSA:!PSK:!SRP:!MD5:!RC4");

	SSL *sslConn = SSL_new(ctx);
	SSL_set_fd(sslConn, sock);
	if (SSL_connect(sslConn) != 1)
	{
		puts("TLS handshake error");
		SSL_free(sslConn);
		close(sock);
		exit(1);
	}
	printf("TLS version: %s\nCypher suite: %s\n", SSL_get_cipher_version(sslConn), SSL_get_cipher(sslConn));

	if (SSL_get_verify_result(sslConn) != X509_V_OK)
	{
		puts("Sorry: invalid server certificate");
		SSL_free(sslConn);
		close(sock);
		exit(1);
	}

	X509 *cert = SSL_get_peer_certificate(sslConn);
	X509_free(cert);

	if (cert == NULL)
	{
		puts("Sorry: no certificate provided by the server");
		SSL_free(sslConn);
		close(sock);
		exit(1);
	}

	version = '0';
	code = '0';
	bt[0] = version;
	bt[1] = code;
	if (idMaquina < 1 || idMaquina > 65535)
	{
		printf("Invalid ID machine!!!");
		sData->flagStatus = 0;
		exit(10);
	}

	b1 = (idMaquina >> (8 * 1)) & 0xff;
	b2 = (idMaquina >> (8 * 0)) & 0xff;
	bt[2] = (char)b1;
	bt[3] = (char)b2;
	dataLfinal = 7;
	b3 = (dataLfinal >> (8 * 1)) & 0xff;
	b4 = (dataLfinal >> (8 * 0)) & 0xff;
	bt[4] = (char)b3;
	bt[5] = (char)b4;
	printf("Version: %c\n", version);
	printf("Code: %c\n", code);
	printf("idMaquina: %02x %02x\n", b1, b2);
	printf("DataLength: %02x %02x\n", b3, b4);
	SSL_write(sslConn, bt, dataLfinal);
	printf("sleep\n");
	sleep(tempo_cadencia);
	printf("read 1\n");
	SSL_read(sslConn, &lenghtResp, sizeof(lenghtResp));
	printf("read 2");
	SSL_read(sslConn, bt, lenghtResp);

	unsigned char codeResp;
	codeResp = bt[1];
	if (codeResp == 151)
	{
		printf("ID machine not known, request has been refused and ignored.");
		sData->flagStatus = 0;
		exit(20);
	}

	pthread_t *configThread;
	pthread_create(&configThread, NULL, config_messg, (void *)idMaquina);
	//pthread_join(configThread, NULL);

	char fileName[100];
	sprintf(fileName, "%d.txt", idMaquina);

	file = fopen(fileName, "r");
	char chunk[506];
	size_t len = sizeof(chunk);
	char *l = malloc(len);
	if (l == NULL)
	{
		printf("Unable to allocate memory for the line buffer.");
		sData->flagStatus = 0;
		exit(7);
	}
	while (fgets(chunk, sizeof(chunk), file) != NULL)
	{

		size_t len_used = strlen(l);
		size_t chunk_used = strlen(chunk);
		if (len - len_used < chunk_used)
		{
			len *= 2;
			if ((l = realloc(l, len)) == NULL)
			{
				perror("Unable to reallocate memory for the line buffer.");
				free(l);
				exit(8);
			}
		}
		// Copy the chunk to the end of the line buffer
		strncpy(l + len_used, chunk, len - len_used);
		len_used += chunk_used;

		// Check if line contains '\n', if yes process the line of text
		if (l[len_used - 1] == '\n')
		{
			version = '0';
			code = '1';
			bt[0] = version;
			bt[1] = code;
			b1 = (idMaquina >> (8 * 1)) & 0xff;
			b2 = (idMaquina >> (8 * 0)) & 0xff;
			bt[2] = (char)b1;
			bt[3] = (char)b2;
			strcpy(rawData, l);
			for (int i = 0; i < strlen(rawData); i++)
			{
				sData->rawData[i] = rawData[i];
			}

			dataLength = strlen(rawData) + 1;
			dataLfinal = 6 + dataLength;
			b3 = (dataLfinal >> (8 * 1)) & 0xff;
			b4 = (dataLfinal >> (8 * 0)) & 0xff;
			bt[4] = (char)b3;
			bt[5] = (char)b4;
			printf("Version: %c\n", version);
			printf("Code: %c\n", code);
			printf("idMaquina: %02x %02x\n", b1, b2);
			printf("DataLength: %02x %02x\n", b3, b4);
			printf("Mensagem presente para enviar:\n");
			printf("%s\n", rawData);
			printf("write\n");
			strcat(bt, rawData);
			SSL_write(sslConn, bt, dataLfinal);
			printf("sleep\n");
			sleep(tempo_cadencia);
			printf("read1\n");
			SSL_read(sslConn, &lenghtResp, sizeof(lenghtResp));
			printf("read2\n");
			SSL_read(sslConn, bt, lenghtResp);
			printf("dsp reed 2\n");

			sData->lastMSGFromSC = bt[1];

			codeResp = bt[1];
			if (codeResp == 151)
			{
				printf("Request has been refused and ignored.");
				sData->flagStatus = 0;
				exit(20);
			}

			// "Empty" the line buffer
			l[0] = '\0';
			printf("\n");
		}
	}
	sData->flagStatus = 1;
	close(sock);
	pthread_exit(NULL);
	exit(0);
}










































































void *udp_machine(void *args)
{
	pthread_t thread_cli, thread_serv, thread_udp;

	pacotes *argy, *pacote = (pacotes *)args;
	shared_data *sData = (shared_data *)pacote->sData;
	argy = malloc(sizeof(pacotes));
	argy->idMaquina = pacote->idMaquina;
	argy->tempo_cadencia = pacote->tempo_cadencia;
	argy->argc = pacote->argc;
	argy->argv = pacote->argv;
	argy->sData = sData;

	struct sockaddr_storage client;
	int err, res, sock, i;
	unsigned int adl;
	char line[BUF_SIZEU];
	char cliIPtext[BUF_SIZEU], cliPortText[BUF_SIZEU];
	struct addrinfo req, *list;

	bzero((char *)&req, sizeof(req));
	// request a IPv6 local address will allow both IPv4 and IPv6 clients to use it
	req.ai_family = AF_INET;
	req.ai_socktype = SOCK_DGRAM;
	req.ai_flags = AI_PASSIVE; // local address

	err = getaddrinfo(NULL, SERVER_PORT, &req, &list);

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

	puts("Listening for UDP requests (both over IPv6 or IPv4). Use CTRL+C to terminate the server");

	adl = sizeof(client);
	while (1)
	{
		/* Recebe pedido*/
		res = recvfrom(sock, line, BUF_SIZEU, 0, (struct sockaddr *)&client, &adl);

		if (res == 0)
		{
			perror("Error on receiving request\n");
			exit(1);
		}

		if (!getnameinfo((struct sockaddr *)&client, adl, cliIPtext, BUF_SIZEU, cliPortText, BUF_SIZEU, NI_NUMERICHOST | NI_NUMERICSERV))
		{
			printf("Request from node %s, port number %s\n", cliIPtext, cliPortText);
		}
		else
		{
			puts("Got request, but failed to get client address");
		}

		/* Responde apenas ao pedido HELLO */
		if (line[CODE_OFFSET] == HELLO_REQUEST)
		{
			line[CODE_OFFSET] = sData->lastMSGFromSC;
			/* Define o número do ID da máquina em 2 bytes*/
			unsigned char firstByte = (unsigned)sData->idMachine & 0xff;
			unsigned char secondByte = (unsigned)sData->idMachine >> 8; /* 256 x secondByte */
			line[ID_OFFSET] = firstByte;
			line[ID_OFFSET + 1] = secondByte;

			/* Verifica se tem informação de status*/
			if (sData->flagStatus == 1)
			{
				/* Obtém o tamanho da rawData*/
				int length = strlen(sData->rawData);

				/* Define o número da DATA_LENGTH em 2 bytes */
				firstByte = (unsigned)length & 0xff;
				secondByte = (unsigned)length >> 8; /* 256 x secondByte */
				line[DATA_LENGTH_OFFSET] = firstByte;
				line[DATA_LENGTH_OFFSET + 1] = secondByte;

				/* Preenche o campo da raw_data com o status*/
				for (i = 0; i < length; i++)
				{
					line[RAW_DATA_OFFSET + i] = sData->rawData[i];
				}
			}
			else
			{
				/* Se não tiver informação de status*/
				line[DATA_LENGTH_OFFSET] = 0;
			}
			/* Envia a mensagem */
			err = sendto(sock, line, BUF_SIZEU, 0, (struct sockaddr *)&client, adl);
			if (err < 0)
			{
				perror("Error sending message");
				exit(1);
			}
		}else if (line[CODE_OFFSET] == RESET_REQUEST){
		if (sData->flagStatus == 1){
			line[CODE_OFFSET] = ACK;
		}else{
			line[CODE_OFFSET] = NACK;
		}
		unsigned char firstByte = (unsigned)sData->idMachine & 0xff;
		unsigned char secondByte = (unsigned)sData->idMachine >> 8; /* 256 x secondByte */
		line[ID_OFFSET] = firstByte;
		line[ID_OFFSET + 1] = secondByte;
		line[DATA_LENGTH_OFFSET] = 0;
		sleep(5);
		err = sendto(sock, line, BUF_SIZEU, 0, (struct sockaddr *)&client, adl);
		if (err < 0)
		{
			perror("Error sending message");
			exit(1);
		}

		printf("reset feito/n");

}
	else
	{
		printf("Invalid request");
	}
}
close(sock);
exit(0);

return 0;
}

int main(int argc, char **argv)
{

	pthread_t thread_cli, thread_serv, thread_udp;
	unsigned int idMaquina = (unsigned int)atoi(argv[2]);
	unsigned int tempo_cadencia = (unsigned int)atoi(argv[3]);
	pacotes *args;
	shared_data *sData = (shared_data *)malloc(sizeof(shared_data));
	args = malloc(sizeof(pacotes));
	args->idMaquina = idMaquina;
	args->tempo_cadencia = tempo_cadencia;
	args->argc = argc;
	args->argv = argv;
	args->sData = sData;
	pthread_create(&thread_cli, NULL, tcp_cli, (void *)args);
	pthread_join(thread_cli, NULL);
	pthread_create(&thread_udp, NULL, udp_machine, (void *)args);
	pthread_join(thread_udp, NULL);
	return 0;
}
