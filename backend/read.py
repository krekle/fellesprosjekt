import dataset

db = dataset.connect('mysql://bruker:passord@localhost/db2_gr9')

def avtale(id):
  deltaker = db['DeltageneI']
  avtaler = db['Avtale']
  md = deltaker.find(Person_Ansattnummer=id)
  mine_avtaler = []
  if(md):
    for deltar in md:
      mine_avtaler.append(avtaler.find(AvtaleID=md['Avtale_AvtaleID']))
  if(mine_avtaler):
    return mine_avtaler
  else:
    return False

def gruppe_avtale(gruppeid):
  #Not yet implemented in the DB
  return False

def varsler(id):
  alarm = db['Alarm']
  varlser = alarm.find(Person_Ansattnummer=id)
  mine_varsler = []
  if(varsler):
    return varsler
  else:
    return False

def gruppe_varsler(gruppeid):
  #Not yet implemented
  return False

def person(id):
  return False

def 
