package gruppe9.kalender.frontend;

import gruppe9.kalender.model.Meeting;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

public class Avtale_renderer extends JTextArea implements ListCellRenderer 
{

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		
//		Meeting meeting = (Meeting) value;
//		switch(meeting.get)
//		{
//		case "deltar":
//			this.setBackground(Color.GREEN);
//			break;
//		case "deltar_ikke":
//			this.setBackground(Color.RED);
//			break;
//		case "ikke_svart":
//			this.setBackground(Color.GRAY);
//			break;
//		}
		String start = "11:00";//avtale.getStartTime();
		String name = "Bursdag hos hans";//avtale.getName();
		String slutt = "16:00";//avtale.getEndTime();
		this.setText(start + "\n"+name+"\n"+slutt);
		return this;
	}

}
