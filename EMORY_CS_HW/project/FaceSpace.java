package project;



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;


public class FaceSpace extends JFrame implements ActionListener{
JMenuBar menubar;
JMenu menu;
JMenuItem item,item1;
JTextField text, text1;
JButton button;
JLabel label1, label2;
Panel area1, area2;
DIA dialog;
Image image=Toolkit.getDefaultToolkit().getImage("./home/qzhan37/1.jpg");
public FaceSpace(){}
public FaceSpace(String s, int x, int y, int w, int h){
	init(s);
	setLocation(x,y);
	setSize(w,h);
	setVisible(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}

public class DIA extends JDialog implements ActionListener{
	JButton yes, no;
	DIA(JFrame f, String s, boolean b){
		super (f,s,b);
		setLayout(new FlowLayout());
		yes=new JButton("Yes");
		no=new JButton("No");
		yes.addActionListener(this);
		no.addActionListener(this);
		add(yes);
		add(no);
		setBounds(60,60,100,100);
		
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==yes){
			setVisible(false);
		}
		if(e.getSource()==no){
			setVisible(false);
		}
	}
	
}
public void paint (Graphics g){
	g.drawImage(image, 200,250, 100, 100, this);
}
void init(String s){
	setTitle(s);
	setLayout(new BorderLayout());
	setLayout(new FlowLayout());
	text=new JTextField(15);
	area1=new Panel();
	
	area2=new Panel();
	area1.setLayout(new FlowLayout());
	area2.setLayout(new FlowLayout());
	label1=new JLabel("ID");
	button=new JButton("Login");
	
	button.addActionListener(this);
	text.addActionListener(this);
	
	
	menubar=new JMenuBar();
	menu=new JMenu("Help");
	item=new JMenuItem("Exit");
	item.addActionListener(this);
	item1=new JMenuItem("Help");
	item1.addActionListener(this);
	menu.add(item);
	menu.add(item1);
	menubar.add(menu);
	setJMenuBar(menubar);
	area1.add(label1);
	area1.add( text);
	area1.add(button);
	add("East", area1);
	dialog=new DIA(this,"Help",true );
	
	
}
public void actionPerformed(ActionEvent e){
	if(e.getSource()==button){
		String n=text.getText().trim();
		String name = n;
		
		
		
		System.out.println(n);
	
	
	}
	if(e.getSource()==text){
		text1.setText(text.getText());
	}
	if(e.getSource()==item){
		System.exit(0);
	}
	if(e.getSource()==item1){
		dialog.setVisible(true);
	}
	
	
}
public int getNumber(int n){
	System.out.println(n);
	return n;
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
FaceSpace dm=new FaceSpace("FaceSpace", 20, 30, 500,500);
	}

}
