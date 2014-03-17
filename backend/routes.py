from bottle import debug, response, request, route, run, post, get, HTTPResponse
import time
import read
import update
import insert

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
def get_gruppeavtale(gruppeid):
  gruppe_avtaler = None
  if(gruppeid):
    gruppe_avtaler = read.get_gruppeavtaler(gruppeid)
    #TODO: lag gruppe_avtaler i read
  else:
    return respond(132, 'Error: Input format', None)
  if(gruppe_avtaler):
    return respond(200, 'ok', dict(gruppe_avtaler))
  else:
    return respond(131, 'Error: Ingen avtaler funnet', None)

@route('/get/mineavtaler/<personid>', method = 'GET')
def get_mine_avtaler(personid):
  mine_avtaler = None
  if (personid):
    mine_avtaler = read.get_mine_avtaler(personid)
  else:
    return respond(132, 'Error: Input format', None)
  if (mine_avtaler):
    return respond(200, 'ok', dict(mine_avtaler))
  else:
    return respond(131, 'Error, Ingen avtaler funner', None)

### Set Avtaler ###

#Lag ny avtale
@route('/add/avtale', method ='GET')
def add_avtale():
  avtale = request.query.decode()
  print(avtale)
  if(insert.create_avtale(avtale)):
    return respond(200, 'Avtale lagret', None)
  else:
    return respond(133, 'Error: avtale ikke lagret', None)

### Get Varsler ###

#Returnere varsler for person med id
@route('/get/person/varsler/<id>', method = 'GET')
def get_person_notifications(id):
  notifications = None
  if(id):
    notifications = read.get_personvarsler(id)
  else:
    return respond(132, 'Error: Input format', None) 
  if(notifications):
    return respond(200, 'ok', notifications)
  else:
    return respond(131, 'Error: Ingen varsler funnet', None)

#Returnere vasler fro gruppe med id gruppeid
@route('/get/gruppe/varsler/<gruppeid>', method = 'GET')
def get_group_notifications(gruppeid):
  group_notifications = None
  if(gruppeid):
    group_notifications = read.get_gruppevarsler(gruppeid)
  else:
    return respond(132, 'Error: Input format', None)
  if(group_notifications):
    return respond(200, 'ok', dict(group_notifications))
  else:
    return respond(131, 'Error: Ingen varsler funnet', None)

#Returnerer gruppemeldinger
@route('/get/gruppe/meldinger/<gruppeid>', method = 'GET')
def get_group_messages(gruppeid):
  group_messages = None
  if (gruppeid):
    group_messages = read.get_gruppemeldinger(gruppeid)
  else:
    return respond(132, 'Error: Input format', None)
  if (group_messages):
    return respond(200, 'ok', dict(group_notications))
  else:
    return respond(131, 'Error: Ingen meldinger funnet', None)

#Returnerer personmeldinger
@route('/get/person/meldinger/<personid>', method = 'GET')
def get_person_messages(personid):
  personmessages = None
  if (personid):
    personmessages = read.get_personmeldinger(personid)
  else:
    return respnd(132, 'Error: Input format', None)
  if (personmessages):
    return respond(200, 'ok', dict(personmessages))
  else:
    return respond(131, 'Error: Ingen meldinger funnet', None)


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
    return respond(200, 'ok', dict(person))
  else:
    return respond(131, 'Error: Ingen varsler funnet', None)

### Get Login###
@route('/login', method = 'POST')
def get_login():
  email = request.forms.get('email')
  password = request.forms.get('password')
  if (email and password):
    person = read.get_login(email, password)
    if (person):
      return respond(200, 'Logged in', dict(person))
    else:
      return respond(134, 'Error: Wrong Password', None)
  else:
    return respond(132, 'Input format', None)
    

### Methods for updates ###

@route('/update/person/<personid>', method = 'GET')
def update_person(personid):
  person = request.query.decode()
  person['Ansattnummer'] = personid
  if (person):
    if(update.update_person(person)):
      return respond (200, 'ok', None)
    else:
      return respond(132, 'Error: Input format', None)
  else:
    return respond(131, 'Error: No person found', None)

@route('/update/group/<avtaleid>', method = 'GET')
def update_avtale(avtaleid):
  avtale = request.query.decode()
  avtale['AvtaleID'] = avtaleid
  if (avtale):
    if(update.update_avtale(avtale)):
      return respond(200, 'ok', None)
    else:
      return respond(132, 'Error: Input format', None)
  else:
    return respond(131, 'Error: No avtale found', None)

@route('/add/toavtale/', method = 'GET')
def add_to_avtale():
  person = request.forms.get('personid')
  avtale = request.forms.get('avtaleid')
  if (person and avtale):
    if(update.add_to_avtale(avtale, person)):
      return True
    else:
      return respond(132, 'Error: Input format', None)
  else:
    return respond(131, 'Error: Wrong information', None)

### Methods for creating ###

@route('/create/person/', method = 'GET')
def create_person():
  person = request.forms.decode()
  if (person):
    if (insert.create_person(person)):
      return (200, 'ok', None)
    else:
      return respond(132, 'Error: Input format', None)
  else:
    return respond(131, 'Error: No person found', None)

@route('/create/avtale/', method = 'GET')
def create_avtale():
  avtale = request.forms.decode()
  if (avtale):
    if (insert.create_avtale(avtale)):
      return (200, 'ok', None)
    else:
      return respond(132, 'Error: Input format', None)
  else:
    return respond(131, 'Error: No person found', None)

### Get Rom ###
#Hent ledige rom i tidsrom?
##moar??

if __name__ == '__main__':
    run(host='0.0.0.0', port=4242, debug=True)

