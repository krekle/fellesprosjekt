package gruppe9.kalender.frontend;

import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.user.Bruker;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

public class Avtale_renderer extends JTextArea implements ListCellRenderer 
{

	Color lightGreen = new Color(100,255,100);
	Color lightRed = new Color(255, 100, 100);
	Color lightGray = new Color(200,200,200);
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		
		Meeting meeting = (Meeting) value;
		switch(meeting.getMyStatus())
		{
		case "Deltar":
			if(isSelected){
				this.setBackground(lightGreen);
			}
			else{
				this.setBackground(Color.GREEN);
			}
			break;
		case "Avslaatt":
			if(isSelected)
			{
				this.setBackground(lightRed);
			}
			else{
				
				this.setBackground(Color.RED);
			}
			break;
		case "IkkeSvart":
			if(isSelected){
				this.setBackground(lightGray);
			}
			else{
				
				this.setBackground(Color.GRAY);
			}
			break;
		}
		String start = meeting.getStartTime();
		String name = meeting.getName();
		String slutt = meeting.getEndTime();
		if(name.length()>12){
			name = name.substring(0, 13) + "...";
		}
		this.setText(start + "\n"+name+"\n"+slutt);
		return this;
	}
	
}
