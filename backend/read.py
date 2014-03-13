import dataset

db = dataset.connect('mysql://bruker:passord@localhost/db2_gr9')

#Tested and works
def get_person(person_id):
  personData = db['Person']
  person = personData.find_one(Ansattnummer = id)
  if (person):
    return person
  return False

#Tested and works
def get_login(email, password):
  personData = db['Person']
  person = personData.find_one(Epost = email, Passord = password)
  if (person):
    return person
  return False

def avtaleToString(avtale):
  avtale['Starttidspunkt'] = str(avtale['Starttidspunkt'])
  avtale['Sluttidspunkt'] = str(avtale['Sluttidspunkt'])
  avtale['SistEndret'] = str(avtale['SistEndret'])
  avtale['Opprettet'] = str(avtale['Opprettet'])
  avtale['varighet'] = str(avtale['varighet'])

#Tested and works
def get_mine_avtaler(person_id):
  deltaker = db['DeltagendeI']
  avtaler = db['Avtale']
  md = deltaker.find(Person_Ansattnummer=person_id)
  mine_avtaler = []
  if(md):
    for deltar in md:
      mine_avtaler.append(avtaler.find(AvtaleID=deltar['Avtale_AvtaleID']))
  if(mine_avtaler):
    for avtale in mine_avtaler:
      avtaleToString(avtale)
    return mine_avtaler
  return False

#Tested and works
def get_avtale(avtale_id):
  avtaleData = db['Avtale']
  avtale = avtaleData.find_one(AvtaleID = avtale_id)
  if (avtale):
    avtaleToString(avtale)
    return avtale
  return False

print(get_avtale(5))

#Tested and works
def get_personvarsler(id):
  alarm = db['Alarm']
  varsler = alarm.find(Person_Ansattnummer=id)
  mine_varsler = []
  if(varsler):
    for varsel in varsler:
      mine_varsler.append(varsel)
  if (mine_varsler):
    return mine_varsler
  return False

#Tested and works
def get_gruppevarsler(gruppeid):
  alarm = db['Alarm']
  varsler = alarm.find(Avtale_AvtaleID = gruppeid)
  gruppevarsler = []
  if (varsler):
    for varsel in varsler:
      gruppevarsler.append(varsel)
  if (gruppevarsler):
    return gruppevarsler
  return False

#Tested and works
def get_personmelding(personid):
  melding = db['Melding']
  varsler = melding.find(Person_Ansattnummer = personid)
  personvarsler = []
  if (varsler):
    for varsel in varsler:
      personvarsler.append(varsel)
  if (personvarsler):
    return personvarsler
  return False

#Tested and works
def get_gruppemelding(gruppeid):
  melding = db['Melding']
  varsler = melding.find(Avtale_AvtaleID = gruppeid)
  gruppevarsler = []
  if (varsler):
    for varsel in varsler:
      gruppevarsler.append(varsel)
  if (gruppevarsler):
    return gruppevarsler
  return False

#Tested and works
def get_gruppe(gruppeid):
  gruppeData = db['Gruppe']
  gruppe = gruppeData.find_one(GruppeID = gruppeid)
  if (gruppe):
    return gruppe
  return False

