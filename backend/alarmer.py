import dataset
import datetime
from insert import delete_alarm as delete
from mail import sendmail as send

f = '%Y-%m-%d %H:%M:%S'
db = dataset.connect('mysql://bruker:passord@localhost/db2_gr9')

def alarmer():
  now = datetime.datetime.now()
  now_ten = now + datetime.timedelta(minutes = 12)
  alarmData = db['Alarm']
  personData = db['Person']
  avtaleData = db['Avtale']

  for alarm in alarmData:
    notifi = dict(alarm)
    alarmTime = notifi['Tidspunkt']

    aid = notifi['Avtale_AvtaleID']
    pid = notifi['Person_Ansattnummer']
    varsel = notifi['Varselstekst']

    if(now_ten > alarmTime ): #if now + 12minutes is after the alarm time, then send mail
      #Send mail og slett alarm!
      email = dict(personData.find_one(Ansattnummer=pid))['Epost']
      avtale = dict(avtaleData.find_one(AvtaleID=aid))
      tittel = avtale['Tittel']
      start = str(avtale['Starttidspunkt'])

      varsel = "Automatisk alarm for avtale: " + tittel + "\n" + "start: " + start +  "\n\n" + varsel

      send(email, 'no-reply@kalender.no', 'Alarm: Avtale', varsel)
      delete(pid,aid)

alarmer()
