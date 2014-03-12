import dataset
import datetime

db = dataset.connect('mysql://bruker:passord@localhost/db2_gr9')

#Tested and works
def add_to_avtale(avtaleid, personid):
  try:
    DeltarIData = db['DeltagendeI']
    DeltarIData.insert(dict(Status='Ikke Svart', SistSett=datetime.datetime.now(), Person_Ansattnummer=personid, Avtale_AvtaleID=avtaleid))
    print("Success")
    return True
  except:
    print("Error")
    return False

#Tested and works
def update_person(d):
  try:
    db['Person'].update(d,['Ansattnummer'])
    print("Success")
    return True
  except:
    print("Error")
    return False

#Tested and works
def update_avtale(d):
  try:
    db['Avtale'].update(d, ['AvtaleID'])
    print("Success")
    return True
  except:
    print("Error")
    return False
