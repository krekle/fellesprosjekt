# -*- coding: utf-8 -*-
import SocketServer

class CLientHandler(SocketServer.BaseRequestHandler):

    def handle(self):
        # Hent IP-adressen til klienten
        self.ip = self.client_address[0]

        # Hent portnummeret til klienten
        self.port = self.client_address[1]

        # Si ifra at en ny klient har koblet til serveren
        print 'Client connected @' + self.ip + ':' + str(self.port)

        # Motta data fra klienten
        # Setter maks datastørrelse til 1kb
        data = self.request.recv(1024)

        # Si ifra at serveren har mottatt en melding
        print data

        # Send en melding til klienten om at meldingen ble mottatt
        self.request.sendall('Message received')


class ThreadedTCPServer(SocketServer.ThreadingMixIn, SocketServer.TCPServer):
    pass


# Kjøres når programmet startes
if __name__ == "__main__":
    # Definer host og port for serveren
    HOST = 'localhost'
    PORT = 9999

    # Sett opp serveren
    server = ThreadedTCPServer((HOST, PORT), CLientHandler)

    # Aktiver serveren. Den vil kjøre til den avsluttes med Ctrl+C
    server.serve_forever()

