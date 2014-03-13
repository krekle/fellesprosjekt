from bottle import debug, response, request, route, run, post, get, HTTPResponse
import time
import read

@route('/status')
def status():
  return respond(200, 'Api up and running','Server Time: '+ str (time.time()))

def respond(co, ms, resp):
  response = 'application/json'
  return {'code':co , 'msg':ms , 'response':resp  }

### Get Avtaler ###

#Returnerer avtaler for bruker med id id
@route('/get/person/avtaler/<id>', method ='GET')
def get_avtale(id):
  avtaler = None
  if(id):
    avtaler = {}
    avtaler["avtaler"] = read.get_avtale(id)
    #TODO: lag avtale i read
  else:
    return respond(132, 'Error: Input format', None)
  if(avtaler):
    return respond(200, 'ok', avtaler)
  else:
    return respond(131, 'Error: Ingen avtaler funnet', None)

@route('/test', method = 'GET')
def test():
  test = dict(request.query)
  return respond(200, 'ok', test)


#Returnere avtaler for gruppe med id gruppeid
@route('/get/gruppe/avtaler/<gruppeid>', method = 'GET')
def get_gruppe_avtale(gruppeid):
  gruppe_avtaler = None
  if(gruppeid):
    gruppe_avtaler = read.gruppe_avtale(gruppeid)
    #TODO: lag gruppe_avtaler i read
  else:
    return respond(132, 'Error: Input format', None)
  if(gruppe_avtaler):
    return respond(200, 'ok', gruppe_avtaler)
  else:
    return respond(131, 'Error: Ingen avtaler funnet', None)

### Set Avtaler ###

#Lag ny avtale
@route('/get/add/avtale', method ='GET')
def get_avtale(id):
  avtale = request.query.decode() 
  #TODO: Test denne! og parse ut deltakere
  if(insert.avtale(avtale)):
    #TODO: lag insert avtale
    return respond(200, 'Avtale lagret', None)
  else:
    return respond(133, 'Error: avtale ikke lagret', None)

### Get Varsler ###

#Returnere varsler for person med id id
@route('/get/person/varsler/<id>', method = 'GET')
def person_notifications(id):
  notifications = None
  if(id):
    notifications = read.varsler(id)
  else:
    return respond(132, 'Error: Input format', None)
  
  if(notifications):
    return respond(200, 'ok', notifications)
  else:
    return respond(131, 'Error: Ingen varsler funnet', None)

#Returnere vasler fro gruppe med id gruppeid
@route('/get/gruppe/varsler/<gruppeid>', method = 'GET')
def group_notifications(gruppeid):
  group_notifications = None
  if(gruppeid):
    group_notifications = read.gruppe_varsler(gruppeid)
  else:
    return respond(132, 'Error: Input format', None)
  if(group_notifications):
    return respond(200, 'ok', group_notifications)
  else:
    return respond(131, 'Error: Ingen varsler funnet', None)

### Get Personer ###
#Hent ledige personer i tidsrom?
# moar?
@route('/get/person/<personid>', method = 'Get')
def get_person(personid):
  person = None
  if (personid):
    person = read.get_person(personid)
  else:
    return respond(132, 'Error: Input format', None)
  if (person):
    return respond(200, 'ok', person)
  else:
    return respond(131, 'Error: Ingen varsler funnet', None)

### Get Rom ###
#Hent ledige rom i tidsrom?
##moar??



if __name__ == '__main__':
    run(host='0.0.0.0', port=4242, debug=True)

