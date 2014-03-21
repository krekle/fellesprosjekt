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
      d = get_avtale(deltar['Avtale_AvtaleID'])
      d['Status'] = deltar['Status']
      mine_avtaler.append(d)
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
  avtale['skaper'] = str(skaperData.find_one(Avtale_AvtaleID = avtale_id)['Person_Ansattnummer'])

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
      varsel = dict(varsel)
      print(varsel)
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

def get_all_people():
  people = db['Person']
  all_people = {}
  people_list = []
  for person in people:
    d = dict(person)
    d.pop('Passord')
    people_list.append(d)
  all_people['people'] = people_list
  if(people_list):
    return all_people
  else:
    return False

def get_deltakere(aid):
  try:
    aid = str(aid)
    deltakereData = db['DeltagendeI']
    personData = db['Person']
    deltakere = deltakereData.find(Avtale_AvtaleID=aid)
    res = []
    for d in deltakere:
      p = personData.find_one(Ansattnummer=d['Person_Ansattnummer'])
      # print(p)
      d = dict([(str(k),str(v)) for k, v in d.items()])
      d['navn'] = p['Navn']
      res.append(d)
    return res
  except:
    traceback.print_exc()
    return False

def get_rom(romid):
  Rom = db['Rom']
  rom = {}
  rom['Room'] = dict(Rom.find_one(ID=romid))
  if (rom):
    return rom
  return false

def get_ledige_rom(starttidspunkt, sluttidspunkt):
  st = datetime.datetime.strptime(starttidspunkt, "%Y-%m-%d %H:%M:%S")
  et = datetime.datetime.strptime(sluttidspunkt, "%Y-%m-%d %H:%M:%S")
  TarPlassI = db['TarPlassI']
  Rom = db['Rom']
  romListe = []
  romDict = {}
  for rom in Rom:
    romListe.append(dict(Rom.find_one(ID=rom['ID'])))
  for rom in TarPlassI:
    if rom['Start'] >= st and rom['Start'] < et:
      if dict(Rom.find_one(ID=rom['Rom_ID'])) in romListe:
        romListe.remove(dict(Rom.find_one(ID=rom['Rom_ID'])))
    elif rom['Slutt'] > st and rom['Slutt'] <= et:
      if dict(Rom.find_one(ID=rom['Rom_ID'])) in romListe:
        romListe.remove(dict(Rom.find_one(ID=rom['Rom_ID'])))
  romDict['Room'] = romListe
  if romDict:
    return romDict
  return false

def get_avtale_rom(avtaleid):
  TarPlassI = db['TarPlassI']
  Rom = db['Rom']
  romListe = []
  romDict = {}
  for rom in TarPlassI:
    if rom['Avtale_AvtaleID'] == avtaleid:
      romListe.append(dict(Rom.find_one(ID=rom['Rom_ID'])))
      break
  romDict['Room'] = romListe
  if romDict:
    return romDict
  return false

def get_person_groups(personid):
  pid = personid
  MedlemI = db['MedlemI']
  Person = db['Person']
  Gruppe = db['Gruppe']
  groupList = []
  groupDict = {}
  personList = []
  try:
    for medlem in MedlemI:
      if medlem['Ansattnummer']==pid:
        groupList.append(dict(Gruppe.find_one(GruppeID=medlem['GruppeID'])))
    for group in groupList:
      personList = []
      for medlem in MedlemI:
        if medlem['GruppeID'] == group['GruppeID']:
          personList.append(dict(Person.find_one(Ansattnummer=medlem['Ansattnummer'])))
      group['people'] = personList
    groupDict['groups'] = groupList
  except:
    traceback.print_exc()
    return "ERROR"
  return groupDict


