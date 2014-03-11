import dataset

db = dataset.connect('mysql://bruker:passord@localhost/db2_gr9')


avtale = db['avtale']
