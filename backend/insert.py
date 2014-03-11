import dataset

db = dataset.connect('mysql://bruker:passord@localhost/db2_gr9')

def avtale(d):
  avtale = db['Avtale']
  try:
    avtale.insert(d)
    return True
  except:
    return False
