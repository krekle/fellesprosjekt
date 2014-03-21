# -*- coding: utf-8 -*-
import socket
import json

class Client(object):

    def __init__(self):
        # Initialiser en tilkobling
        self.connection = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def start(self, host, port):
        # Start tilkoblingen
        self.connection.connect((host, port))

        # Send en melding til serveren
        self.send('May the Force be with you')

        # Motta data fra serveren
        # Setter maks datastørrelse til 1kb
        received_data = self.connection.recv(1024)

        # Si ifra at klienten har mottatt en melding
        print 'Received from server: ' + received_data

        # Lukk tilkoblingen
        self.connection.close()

    # Lag en metode for å sende en melding til serveren
    def send(self, data):
        self.connection.send(data)


# Kjøres når programmet startes
if __name__ == "__main__":
    # Definer host og port for serveren
    HOST = 'localhost'
    PORT = 9999

    # Initialiser klienten
    client = Client()

    # Start klienten
    client.start(HOST, PORT)
