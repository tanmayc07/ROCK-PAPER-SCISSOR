import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame{
	Container c;
	JButton btnPlay,btnExit,btnScore;

	MainFrame(){
		c = getContentPane();
		c.setLayout(null);
		setIconImage(new ImageIcon("C:\\Users\\Tanmay\\Desktop\\final\\icon.png").getImage());

		btnPlay = new JButton("LETS PLAY");
		btnExit = new JButton("EXIT");
		btnScore = new JButton("ScoreBoard");
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("rpc.jpg"));

		btnPlay.setBounds(185,390,100,50);
		btnExit.setBounds(185,450,100,50);
		label.setBounds(100,70,300,300);
		btnScore.setBounds(185,510,110,50);

		c.add(btnPlay);
		c.add(btnExit);
		c.add(label);
		c.add(btnScore);

		ActionListener a1 = (ae) -> {
			GameFrame1 gf = new GameFrame1();
			dispose();
		};
		btnPlay.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			dispose();
		};
		btnExit.addActionListener(a2);

		ActionListener a3 = (ae) -> {
			ViewFrame vf = new ViewFrame();
			dispose();
		};
		btnScore.addActionListener(a3);

		setTitle(("ROCK PAPER SCISSORS"));
		setSize(500, 610);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String args[]){
		MainFrame mf = new MainFrame();
	}
}