import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

enum Selection
{
	ROCK,PAPER,SCISSOR
}

class GameFrame1 extends JFrame{
	Container c;
	JRadioButton btnrock, btnpaper, btnscissor;
	JButton btnSubmit,btnPlayAgain,btnStopPlaying;
	JLabel lbl,rounds;
	Selection user, comp;
	static int no_of_ties=0,no_of_user=0,no_of_comp=0;
	static int matchesPlayed = 0,round=0;
	

	GameFrame1(){
		c = getContentPane();
		c.setLayout(null);
		round++;

		setIconImage(new ImageIcon("C:\\Users\\Tanmay\\Desktop\\final\\icon.png").getImage());


		btnrock = new JRadioButton("ROCK");
		btnpaper = new JRadioButton("PAPER");
		btnscissor = new JRadioButton("SCISSOR");
		btnSubmit = new JButton("Submit");
		btnPlayAgain = new JButton("Play Again");
		btnStopPlaying = new JButton("Stop Playing");
		lbl = new JLabel("Try Your Luck!");
		rounds = new JLabel("Round-"+round);

		lbl.setBounds(100,50,100,30);
		rounds.setBounds(300,50,100,30);
		btnrock.setBounds(100,100,100,30);
		btnpaper.setBounds(100,150,100,30);
		btnscissor.setBounds(100,200,100,30);
		btnSubmit.setBounds(100,250,100,30);
		btnPlayAgain.setBounds(100,300,100,30);
		btnStopPlaying.setBounds(210,300,130,30);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(btnrock);
		bg.add(btnpaper);
		bg.add(btnscissor);

		c.add(lbl);
		c.add(rounds);
		c.add(btnrock);
		c.add(btnpaper);
		c.add(btnscissor);
		c.add(btnSubmit);
		c.add(btnPlayAgain);
		c.add(btnStopPlaying);

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");


		ActionListener a1 = (ae) -> {
			int r = (int)(Math.random() * 3);
			if(r == 0)
				comp = Selection.ROCK;
			else if(r == 1)
				comp = Selection.PAPER;
			else 
				comp = Selection.SCISSOR;

			if(btnrock.isSelected()){
				user = Selection.ROCK;
				if(comp == Selection.ROCK){
					no_of_ties++;
					JOptionPane.showMessageDialog(c,"Whoosh\nYou have a tie!!");
				}
				else if(comp == Selection.PAPER){
					no_of_comp++;
					JOptionPane.showMessageDialog(c,"Sorry\nYou Lost!!");
				}
				else if(comp == Selection.SCISSOR){
					no_of_user++;
					JOptionPane.showMessageDialog(c,"Yippee\nYou Won!!");
				}
			}
			else if(btnpaper.isSelected()){
				user = Selection.PAPER;
				if(comp == Selection.ROCK){
					no_of_user++;
					JOptionPane.showMessageDialog(c,"Yippee\nYou Won!!");
				}
				else if(comp == Selection.PAPER){
					no_of_ties++;
					JOptionPane.showMessageDialog(c,"Whoosh\nYou have a tie!!");
				}
				else if(comp == Selection.SCISSOR){
					no_of_comp++;
					JOptionPane.showMessageDialog(c,"Sorry\nYou Lost!!");
				}
			}
			else if(btnscissor.isSelected()){
				user = Selection.SCISSOR;
				if(comp == Selection.ROCK){
					no_of_comp++;
					JOptionPane.showMessageDialog(c,"Sorry\nYou Lost!!");
				}
				else if(comp == Selection.PAPER){
					no_of_user++;
					JOptionPane.showMessageDialog(c,"Yippee\nYou Won!!");
				}
				else if(comp == Selection.SCISSOR){
					no_of_ties++;
					JOptionPane.showMessageDialog(c,"Whoosh\nYou have a tie!!");
				}
			}
			
			matchesPlayed++;

			int input = JOptionPane.showConfirmDialog(c,"Do You Wish To Play Again?","Select an option",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(input==0){
				new GameFrame1();
				dispose();
			}
			else if(input==1){
				SessionFactory factory  = cfg.buildSessionFactory();
				Session session = null;
				Transaction t = null;
				try{
					int id = Integer.parseInt(JOptionPane.showInputDialog(c,"Enter id"));
					String name=JOptionPane.showInputDialog(c,"Enter Name");
					JOptionPane.showMessageDialog(c,"Name -" + name + "ID - " + id);
					session = factory.openSession();
					t = session.beginTransaction();
					Person s = new Person();
					s.setPersonID(id);
					s.setName(name);
					s.setMatchesPlayed(matchesPlayed);
					s.setTies(no_of_ties);
					s.setWins(no_of_user);
					session.save(s);
					t.commit();
					no_of_ties=0;no_of_user=0;no_of_comp=0;
					matchesPlayed = 0;round=0; 
					new MainFrame();
					dispose();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(c,"Exception " + e);
					t.rollback();
				}finally{
					session.close();
				}
				

			}
			else{
				no_of_ties=0;no_of_user=0;no_of_comp=0;
				matchesPlayed = 0;
				round = 0;
				new MainFrame();
				dispose();
			}
		};
		btnSubmit.addActionListener(a1);


		ActionListener a3 = (ae) -> {

		};
		btnStopPlaying.addActionListener(a3);

		setTitle(("ROCK PAPER SCISSORS"));
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
