import dataset
import datetime
import traceback
import insert

db = dataset.connect('mysql://bruker:passord@localhost/db2_gr9')

#Tested and works
def add_deltakere_to_avtale(avtaleid, personids, statuses):
  try:
    DeltarIData = db['DeltagendeI']
    for i in range(len(personids)):
      d = {}
      d['Status'] = statuses[i]
      d['SistSett'] = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
      d['Person_Ansattnummer'] = personids[i]
      d['Avtale_AvtaleID'] = avtaleid
      DeltarIData.upsert(d, ['Person_Ansattnummer', 'Avtale_AvtaleID'])
      
      #DeltarIData.insert(dict(Status='Ikke Svart', SistSett=datetime.datetime.now(), Person_Ansattnummer=pid, Avtale_AvtaleID=avtaleid))
    print("Success")
    return True
  except:
    traceback.print_exc()
    print("Error")
    return False

#Tested and works
def update_person(d):
  try:
    d = dict(d)
    print(d)
    db['Person'].update(d,['Ansattnummer'])
    print("Success")
    return True
  except:
    print("Error")
    return False

#Tested and works
def update_avtale(d):
  try:
    persons= db['DeltagendeI']
    p = persons.find(Avtale_AvtaleID = d['AvtaleID'])
    people = []
    for peo in p:
      people.append(str(peo['Person_Ansattnummer']))
    db['Avtale'].update(d, ['AvtaleID'])
    insert.multiple_melding(people, d['AvtaleID'], ("Avtale" + d['AvtaleID'] + " er blitt endret"))
    print("Success")
    return True
  except:
    traceback.print_exc()
    print("Error")
    return False

def update_person_status(pid, aid, status):
  try:
    d = {}
    d['Status'] = status
    d['Person_Ansattnummer'] = pid
    d['Avtale_AvtaleID'] = aid
    db['DeltagendeI'].update(d, ['Person_Ansattnummer', 'Avtale_AvtaleID'])
    return True
  except:
    return False
