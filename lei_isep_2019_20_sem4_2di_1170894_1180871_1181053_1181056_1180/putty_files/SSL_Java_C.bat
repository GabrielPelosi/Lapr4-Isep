openssl req  -new -x509 -days 3650 -nodes -sha256 -out server.pem -keyout server.key

openssl req  -new -x509 -days 3650 -nodes -sha256 -out client.pem -keyout client.key

openssl pkcs12 -export -in client.pem -inkey client.key -out client.p12 -name client -passin pass:forgotten -passout pass:forgotten

keytool -importkeystore -srckeystore client.p12 -srcstoretype PKCS12 -srcstorepass forgotten -alias client -deststorepass forgotten -destkeypass forgotten -destkeystore client.jks
