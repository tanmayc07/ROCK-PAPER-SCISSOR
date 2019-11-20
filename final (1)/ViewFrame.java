import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.util.List;


public class ViewFrame extends JFrame{
	Container c;

	ViewFrame(){
		c = getContentPane();
		c.setLayout(new FlowLayout());
		JTextArea taData;
		JButton btnBack;
		
		taData = new JTextArea(20, 30);
		btnBack = new JButton("Back");

		c.add(taData);
		c.add(btnBack);

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = null;

		try{
			session = factory.openSession();
			List<Person> s1 = new ArrayList<>();
			s1 = session.createQuery("from Person").list();
			taData.append("Name\t         Matches Played              Ties                  Wins\n");
			for(Person s: s1){
				
				String data = s.getName() + "\t\t" + s.getMatchesPlayed() + "\t" + s.getTies() + "\t" + s.getWins();
				taData.append(data + System.getProperty("line.separator"));
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(c,"Some Error Occurred. Please Try Again");
		}
		finally{
			session.close();
		}

		ActionListener a1 = (ae) -> {
			MainFrame mf = new MainFrame();
			dispose();
		};
		btnBack.addActionListener(a1);

		setTitle("View Student");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}