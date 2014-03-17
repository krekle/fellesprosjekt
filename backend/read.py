import dataset
import datetime
import traceback

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
      mine_avtaler.append(get_avtale(deltar['Avtale_AvtaleID']))
  if(mine_avtaler):
    for avtale in mine_avtaler:
      avtaleToString(avtale)
    return mine_avtaler
  return False

#Tested and works
def get_avtale(avtale_id):
  avtaleData = db['Avtale']
  romData = db['TarPlassI']
  skaperData = db['SkaperAv']

  avtale = avtaleData.find_one(AvtaleID = avtale_id)
  avtale['rom'] = str(romData.find_one(Avtale_AvtaleID = avtale_id)['Rom_ID'])
  print(avtale['rom'])
  avtale['skaper'] = str(skaperData.find_one(Avtale_AvtaleID = avtale_id)['Person_Ansattnummer'])
  print(avtale['skaper'])

  if (avtale):
    avtale = dict(avtale)
    avtaleToString(avtale)
    return avtale
  return False

#Tested and works
def get_personvarsler(id):
  alarm = db['Alarm']
  varsler = alarm.find(Person_Ansattnummer=id)
  result = {}
  mine_varsler = []
  if(varsler):
    for varsel in varsler:
      varsel['Tidspunkt'] = str(varsel['Tidspunkt'])
      mine_varsler.append(varsel)
  if (mine_varsler):
    result['alarm'] = mine_varsler
    return result
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
def get_personmeldinger(personid):
  melding = db['Melding']
  varsler = melding.find(Person_Ansattnummer = personid)
  result = {}
  personvarsler = []
  if (varsler):
    for varsel in varsler:
      varsel['Tidspunkt'] = str(varsel['Tidspunkt'])
      personvarsler.append(varsel)
  if (personvarsler):
    result['melding'] = personvarsler
    return result
  return False

#Tested and works
def get_gruppemeldinger(gruppeid):
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

def get_deltakere(aid):
  try:
    aid = str(aid)
    deltakereData = db['DeltagendeI']
    deltakere = deltakereData.find(Avtale_AvtaleID=aid)
    res = []
    for d in deltakere:
      d = dict([(str(k),str(v)) for k, v in d.items()])
      res.append(d)
    return res
  except:
    traceback.print_exc()
    return False

def get_ledige_rom(starttidspunkt, sluttidspunkt):
  st = datetime.datetime.strptime(starttidspunkt, "%Y-%m-%d %H:%M:%S")
  et = datetime.datetime.strptime(sluttidspunkt, "%Y-%m-%d %H:%M:%S")
  TarPlassI = db['TarPlassI']
  Rom = db['Rom']
  romListe = []
  for rom in Rom:
    romListe.append(rom['ID'])
  romDictionary = dict(Room = romListe)

  #Sjekker hvorvidt rom er ledig i tidsperiode, rotekode uten sidestykke, sorry...
  if (et < st):
    return
  for rom in TarPlassI:
    start = datetime.datetime.strptime(str(rom['Start']), '%Y-%m-%d %H:%M:%S')
    slutt = None
    if(rom['Slutt']):
      slutt = datetime.datetime.strptime(str(rom['Slutt']), '%Y-%m-%d %H:%M:%S')
    if (not(start or slutt)):
      continue
    if (st >= start and st < slutt):
      romDictionary['Room'].remove(rom['ID'])
      continue
    if (et > start and et <= slutt):
      romDictionary['Room'].remove(rom['ID'])
      continue
    if (st <= start and et >= slutt):
      romDictionary['Room'].remove(rom['ID'])
      continue
  return romDictionary


