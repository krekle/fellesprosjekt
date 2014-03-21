from bottle import debug, response, request, route, run, post, get, HTTPResponse
import time
import read
import update
import insert
import traceback
from mail import sendmail as send

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
    avtaler = read.get_avtale(id)
    #TODO: lag avtale i read
  else:
    return respond(132, 'Error: Input format', None)
  if(avtaler):
    return respond(200, 'ok', avtaler)
  else:
    return respond(131, 'Error: Ingen avtaler funnet', None)

@route('/send/mail', method='GET')
def send_mail():
  d = request.query.decode()
  print(d['to'])
  print(d['subject'])
  print(d['msg'])
  send_to = d['to'].replace('[space]', ' ')
  send_from = 'no-reply@kalender.no'
  send_subject = d['subject'].replace('[space]', ' ')
  send_msg = d['msg'].replace('[space]', ' ')
  try:
    send(send_to, send_from, send_subject, send_msg)
    return respond(200, 'Mail sent', None)
  except:
    traceback.print_exc()
    return respond(134, 'Error: mail not sent', None)


@route('/test', method = 'GET')
def test():
  test = dict(request.query)
  return respond(200, 'ok', test)

# returnerer alle personer
@route('/get/person/all', method = 'GET')
def get_people():
  res = read.get_all_people()
  if(res):
    return respond(200, 'ok', res)
  else:
    return respond(131, 'Error: people not found', None)

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
  mine_avtaler = {}
  if (personid):
    mine_avtaler['avtaler'] = read.get_mine_avtaler(personid)
  else:
    return respond(132, 'Error: Input format', None)
  if (mine_avtaler):
    return respond(200, 'ok', mine_avtaler)
  else:
    return respond(131, 'Error, Ingen avtaler funner', None)

### Set Avtaler ###

#Lag ny avtale
@route('/add/avtale', method ='GET')
def add_avtale():
  avtale = request.query.decode()
  if(avtale):
    avtale['beskrivelse'] = avtale['beskrivelse'].replace('[space]', ' ')
  print(avtale)
  number = insert.create_avtale(avtale)
  if(number):
    return respond(200, 'Avtale lagret', {"avtaleid":number})
  else:
    return respond(133, 'Error: avtale ikke lagret', None)

@route('/add/deltakere/<avtale_id>', method = 'GET')
def add_detakere(avtale_id):
  d = request.query.decode()
  ids = list(str(d['people']).split(','))
  status = list(str(d['status']).split(','))
  print(ids)
  if(avtale_id and ids):
    if(update.add_deltakere_to_avtale(avtale_id, ids, status)):
      return respond(200, 'Deltakere lagt til', None)
    else:
      return respond(133, 'Error: deltakere ikke lagt til', None)
  else:
    return respond(134, 'Error: input format', None)


#Tested and works
def get_avtale(avtale_id):
  avtaleData = db['Avtale']
  avtale = avtaleData.find_one(AvtaleID = avtale_id)
  avtale['Starttidspunkt'] = str(avtale['Starttidspunkt'])
  avtale['Sluttidspunkt'] = str(avtale['Sluttidspunkt'])
  avtale['SistEndret'] = str(avtale['SistEndret'])
  avtale['Opprettet'] = str(avtale['Opprettet'])
  avtale['varighet'] = str(avtale['varighet'])

@route('/get/person/varsler/<id>', method = 'GET')
def get_person_notifications(id):
  notifications = None
  if(id):
    notifications = read.get_personvarsler(id)
    if(notifications):
      for no in notifications['alarm']:
        print('NOTIFICATION AVTALE_ID ' + str(no['Avtale_AvtaleID']))
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
    return respond(132, 'Error: Input format', None)
  if (personmessages):
    return respond(200, 'ok', dict(personmessages))
  else:
    return respond(131, 'Error: Ingen meldinger funnet', None)

@route('/get/deltakere/<aid>', method = 'GET')
def get_deltakere(aid):
  if(aid):
    d = {}
    d['deltakere'] = read.get_deltakere(aid)
    if(d):
      return respond(200, 'Ok', d)
    else:
      return respond(131, 'Deltakere ikke funnet', None)
  else:
    return respond(134, 'Error: input format', None)


### Get Personer ###
@route('/get/person/<personid>', method = 'GET')
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
@route('/update/avtale/<aid>', method = 'GET')
def update_avtale(aid):
  avtale = request.query.decode()
  avtale['AvtaleID'] = aid
  avtale = dict(avtale)
  if(update.update_avtale(avtale)):
    return respond(200, 'Avtale endret', None)
  else:
    return respond(131, 'Error: Avtale ikke endret', None)

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
@route('/update/avtalestatus/<pid>', method = 'GET')
def update_status(pid):
  d = request.query.decode()
  status = d['status']
  avtale_id = d['avtale_id']
  if(pid and status and avtale_id):
    if(update.update_person_status(pid, avtale_id, status)):
      return respond(200, 'status endret', None)
    else:
      return respond(132, 'status ikke endret', None)
  else:
    return respond(134, 'Error: input format', None)

### Methods for creating ###

@route('/create/person/', method = 'GET')
def create_person():
  person = request.forms.decode()
  if (person):
    if (insert.create_person(person)):
      return respond(200, 'ok', None)
    else:
      return respond(132, 'Error: Input format', None)
  else:
    return respond(131, 'Error: No person found', None)

@route('/create/varsel/', method = 'GET')
def create_varsel():
  varsel = request.forms.decode()
  if (varsel):
    if (insert.create_varsel(varsel)):
      return respond (200, 'ok', None)
    else:
      return respond(132, 'Error: Input format', None)
  else:
    return respond(131, 'Error: Noe person found', None)

def create_melding():
  melding = request.forms.decode()
  if (melding):
    if (insert.create_melding(melding)):
      return respond(200, 'ok', None)
    else:
      return respond(132, 'Error: Input format', None)
  else:
    return respond(131, 'Error: No person found', None)

@route('/delete/avtale', method = 'GET')
def delete_avtale():
  d = request.query.decode()
  avtaleid = str(d['avtale_id'])
  if (avtaleid):
    if (insert.delete_avtale(avtaleid)):
      return respond(200, 'ok', None)
    else:
      return respond (131, 'Error: No avtale found', None)
  else:
    return respond(132, 'Error: Input format', None)

@route('/delete/deltaker', method = 'GET')
def delete_deltaker():
  d = request.query.decode()
  if(d):
    aid = d['avtale_id']
    pid = d['person_id']
    if(insert.delete_deltaker(aid, pid)):
      return respond (200, 'Deltaker fjernet', None)
    else: 
      return respond (131, 'Error: deltaker ikke fjernet', None)
  else:
    return respond(134, 'Error: input format', None)

@route('/delete/varsel', method = 'GET')
def delete_varsel():
  d = request.query.decode()
  aid = d['avtale_id']
  pid = d['person_id']
  if(insert.delete_varsel(pid, aid)):
    return respond(200, 'Varsel slettet', None)
  else:
    return respond(131, 'Varsel ikke slettet', None)

@route('/delete/alarm', method = 'GET')
def delete_varsel():
  d = request.query.decode()
  aid = d['avtale_id']
  pid = d['person_id']
  if(insert.delete_alarm(pid, aid)):
    return respond(200, 'Alarm slettet', None)
  else:
    return respond(131, 'Alarm ikke slettet!', None)

### Get Rom ###

@route('/get/ledigerom', method = 'GET')
def get_ledige_rom():
  d = request.query.decode()
  starttidspunkt = d['Starttidspunkt'].replace('[space]', ' ')
  sluttidspunkt = d['Sluttidspunkt'].replace('[space]', ' ')
  if (starttidspunkt and sluttidspunkt):
    rom = read.get_ledige_rom(starttidspunkt, sluttidspunkt)
    return respond(200, 'ok', rom)
  else:
    return respond(132, 'Error: Input format', None)

### Get room of a meeting ###
@route('/get/avtalerom', method = 'GET')
def get_avtale_rom():
  d = request.query.decode()
  aid = d['avtale_id']
  if (aid):
    rom = read.get_avtale_rom(aid)
    return respond(200, 'ok', rom)
  else:
    return respond(132, 'Error: Input format', None)

### Get a persons group ###

@route('/get/groups/<pid>', method = 'GET')
def get_groups(pid):
  personid = int(pid)
  if (personid):
    try:
      groups = read.get_person_groups(personid)
      return respond(200, 'ok', groups)
    except:
      traceback.print_exc()
  else:
    return respond(131, 'Error: Input format', None)

if __name__ == '__main__':
    run(host='0.0.0.0', port=4242, debug=True)

