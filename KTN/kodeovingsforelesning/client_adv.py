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

        # Be brukeren om å skrive inn brukernavn
        nick = raw_input('Enter your username: ')

        message = ''
        # Så lenge brukeren ikke skriver exit i meldingsfeltet
        # vil programmet spørre etter tekst
        while True:
            message = raw_input('Enter a message (type "exit" to quit): ')

            # Lukk tilkoblingen hvis brukeren skriver "exit"
            if message == 'exit':
                self.send(json.dumps({'nick': nick, 'message': 'I\'m leaving. Goodbye!'}))
                self.connection.close()
                break

            # Konstruer et JSON objekt som som skal
            # sendes til serveren
            data = {'nick': nick, 'message': message}

            # Lag en streng av JSON-objektet
            data = json.dumps(data)

            # Send meldingen til serveren
            self.send(data)

            # Motta data fra serveren
            # Setter maks datastørrelse til 1kb
            received_data = self.connection.recv(1024)

            # Si ifra at klienten har mottatt en melding
            print 'Received from server: ' + received_data

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
