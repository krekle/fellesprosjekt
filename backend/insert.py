import dataset

db = dataset.connect('mysql://bruker:passord@localhost/db2_gr9')

def create_avtale(d):
  meeting = db['Avtale']
  maker = db['SkaperAv']
  room = db['TarPlassI']
  try:
    meet = {d['start'], d['slutt'], d['beskrivelse'], d['varighet'], d['sted']} #sist endret, opprettet <-- SQL now()?
    avtale.insert(meet)
    print("Avtale: Success")
    # Hent avtale_id
    
    avtale_id = db.query(
    person_maker = {d['skaper']} # + avtale_id
    maker.insert(person_maker)
    print("Skaper: Success")

    in_room = {d['romid'], d['start'], d['slutt']} # + avtale_id
    room.insert(in_room)
    print('Rom: Success')

    return True
  except:
    print("Error")
    return False

def add_people(people):
  meeting_id = people.pop('avtale_id')
  for k in people:


def create_Person(d):
  person = db['Person']
  try:
    person.insert(d)
    print("Success")
    return True
  except:
    print("False")
    return False

def create_varsel():
  #todo
  return

def delete_avtale(avtale_id):
  db['Avtale'].delete(AvtaleID = avtale_id)
  #Må delete alle entries fra deltagendeI
  return True

delete_avtale(2)
