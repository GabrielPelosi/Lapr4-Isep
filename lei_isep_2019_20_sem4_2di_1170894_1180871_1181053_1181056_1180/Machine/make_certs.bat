
openssl req  -new -x509 -days 3650 -nodes -sha256 -out client.pem -keyout client.key

openssl pkcs12 -export -in client.pem -inkey client.key -out client.p12 -name client -passin pass:forgotten -passout pass:forgotten

keytool -importkeystore -srckeystore client.p12 -srcstoretype PKCS12 -srcstorepass forgotten -alias client -deststorepass forgotten -destkeypass forgotten -destkeystore client-keystore.jks


keytool -genkey -v -alias server -keyalg RSA -keysize 2048 -validity 365 -keystore server.jks -storepass forgotten

keytool -exportcert -alias server -keystore server.jks -storepass forgotten -rfc -file server.pem


keytool -import -alias client -keystore server.jks -file client.pem -storepass forgotten
keytool -import -alias server -keystore client-keystore.jks -file server.pem -storepass forgotten
