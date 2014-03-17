import dataset
import datetime
import traceback


db = dataset.connect('mysql://bruker:passord@localhost/db2_gr9')
now = datetime.datetime.now()
f = '%Y-%m-%d %H:%M:%S'

def create_avtale(d):
  meeting = db['Avtale']
  maker = db['SkaperAv']
  room = db['TarPlassI']
  try:
    meet = {'Starttidspunkt':d['start'],'Sluttidspunkt': d['slutt'],'Beskrivelse': d['beskrivelse'],'varighet': d['varighet'],'sted': d['sted'], 'SistEndret':now.strftime(f), 'Opprettet': now.strftime(f)}
    print(meet)
    meeting.insert(meet)
    print("Avtale: Success")
    
    avtale_ider = meeting.find(order_by='AvtaleID')
    id_list = []
    for id in avtale_ider:
      id_list.append(id['AvtaleID'])
    avtale_id = str(max(id_list))

    person_maker = {'Person_Ansattnummer': d['skaper'], 'Avtale_AvtaleID':avtale_id}
    maker.insert(person_maker)
    print("Skaper: Success")

    in_room = {'Rom_ID':d['romid'],'Start': d['start'],'Slutt': d['slutt'], 'Avtale_AvtaleID': avtale_id} # + avtale_id
    room.insert(in_room)
    print('Rom: Success')

    return True
  except:
    traceback.print_exc()
    print("Error")
    return False

#start = now.strftime(f)
#endtime = now + datetime.timedelta(hours=2)
#end = endtime.strftime(f)
#test = {'start':start, 'slutt':end, 'beskrivelse':'lorem ipsum dolor', 'varighet':'2','skaper':'76', 'sted':'4', 'romid':'414'}
#create_avtale(test)

def create_Person(d):
  person = db['Person']
  try:
    person.insert(d)
    print("Success")
    return True
  except:
    print("False")
    return False

def create_varsel(d):
  varsel = db['Alarm']
  try:
    varsel.insert(d)
    return True
  except:
    return False
  
def create_melding(d):
  varsel = db['Alarm']
  try:
    varsel.insert(d)
    return True
  except:
    return False

## Deletes: ##

def delete_deltaker(aid, pid):
  try:
    db['DeltagendeI'].delete(Avtale_AvtaleID=aid, Person_Ansattnummer=pid)
    return True
  except:
    return False

def delete_avtale(avtale_id):
  try:
    db['Avtale'].delete(AvtaleID = avtale_id)
    return True
  except:
    print("Error")
    return False
