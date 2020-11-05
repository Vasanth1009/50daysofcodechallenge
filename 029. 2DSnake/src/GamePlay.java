import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	
	private int[] snakeXlength=new int[750]; 
	private int[] snakeYlength=new int[750];
	
	private int snakeLength = 3;
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon leftMouth;
	private ImageIcon rightMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	
	private ImageIcon snakeBody;
	
	private ImageIcon titleImage;
	
	private Timer timer;
	private int delay = 200;
	private int moves = 0;
	private int score = 0;
	
	private int[] enemyXPos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
			525,550,575,600,625,650,675,700,725,750,775,800,825,850 };
	
	private int[] enemyYPos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
			525,550,575,600,625};
	
	private ImageIcon enemyImage;
	
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	GamePlay() 
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) 
	{
		
		
		if(moves == 0 )
		{
			snakeXlength[2]=50;
			snakeXlength[1]=75;
			snakeXlength[0]=100;
			
			snakeYlength[2]=100;
			snakeYlength[1]=100;
			snakeYlength[0]=100;
			
		}
		
		//title border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//title 
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//game border
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 576);
		
		//game background
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		// scores
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: "+score, 780, 30);
		
		//length
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: "+snakeLength, 780, 50);
		
		rightMouth = new ImageIcon("rightmouth.png");
		rightMouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		for(int i=0; i<snakeLength;i++) 
		{
			if(i==0 && right) 
			{
				rightMouth = new ImageIcon("rightmouth.png");
				rightMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && left) 
			{
				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && up) 
			{
				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && down) 
			{
				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			
			if(i!=0) {
				snakeBody = new ImageIcon("snakeBody.png");
				snakeBody.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
		}
		
		enemyImage = new ImageIcon("enemy.png");
		enemyImage.paintIcon(this, g, enemyXPos[xpos], enemyYPos[ypos]);
		
		if(enemyXPos[xpos]==snakeXlength[0] && enemyYPos[ypos] == snakeYlength[0]) 
		{
			score++;
			snakeLength++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		for(int i=1;i<snakeLength;i++) {
			if(snakeXlength[i] == snakeXlength[0] && snakeYlength[i] == snakeYlength[0]) {
				 left = false;
				 right = false;
				 up = false;
				 down = false;
				 moves=0;
				 score=0;
				 snakeLength=3;
				 if(moves == 0 )
				{
					snakeXlength[2]=50;
					snakeXlength[1]=75;
					snakeXlength[0]=100;
					
					snakeYlength[2]=100;
					snakeYlength[1]=100;
					snakeYlength[0]=100;
					
				}
				 g.setColor(Color.white);
				 g.setFont(new Font("arial", Font.BOLD, 50));
				 g.drawString("Game Over", 300, 300);
				 g.setFont(new Font("arial", Font.BOLD, 20));
				 g.drawString("Space to Restart", 350, 340);
				 
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int i=snakeLength-1;i>=0;i--) {
				snakeYlength[i+1] = snakeYlength[i];
			}
			for(int i=snakeLength;i>=0;i--) {
				if(i==0) {					
					snakeXlength[i] = snakeXlength[i]+25;
				}
				else {
					snakeXlength[i] = snakeXlength[i-1];
				}
				if(snakeXlength[i] > 850) {
					snakeXlength[i]=25;
				}
			}
			
			repaint();
		}
		if(left) {
			for(int i=snakeLength-1;i>=0;i--) {
				snakeYlength[i+1] = snakeYlength[i];
			}
			for(int i=snakeLength;i>=0;i--) {
				if(i==0) {					
					snakeXlength[i] = snakeXlength[i]-25;
				}
				else {
					snakeXlength[i] = snakeXlength[i-1];
				}
				if(snakeXlength[i] < 25) {
					snakeXlength[i]=850;
				}
			}
			
			repaint();
		}
		if(up) {
			for(int i=snakeLength-1;i>=0;i--) {
				snakeXlength[i+1] = snakeXlength[i];
			}
			for(int i=snakeLength;i>=0;i--) {
				if(i==0) {					
					snakeYlength[i] = snakeYlength[i]-25;
				}
				else {
					snakeYlength[i] = snakeYlength[i-1];
				}
				if(snakeYlength[i] < 75) {
					snakeYlength[i] = 625;
				}
			}
			
			repaint();
		}
		if(down) {
			for(int i=snakeLength-1;i>=0;i--) {
				snakeXlength[i+1] = snakeXlength[i];
			}
			for(int i=snakeLength;i>=0;i--) {
				if(i==0) {					
					snakeYlength[i] = snakeYlength[i]+25;
				}
				else {
					snakeYlength[i] = snakeYlength[i-1];
				}
				if(snakeYlength[i] > 625) {
					snakeYlength[i] = 75;
				}
			}
			
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			moves=0;
			score=0;
			snakeLength=3;
			if(moves == 0 )
			{
				snakeXlength[2]=50;
				snakeXlength[1]=75;
				snakeXlength[0]=100;
				
				snakeYlength[2]=100;
				snakeYlength[1]=100;
				snakeYlength[0]=100;
				
			}
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			moves++;
			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			moves++;
			left = true;
			if(!right) {
				left = true;
			}
			else {
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) 
		{
			moves++;
			up = true;
			if(!down) {
				up = true;
			}
			else {
				up = false;
				down = true;
			}
			right = false;
			left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			moves++;
			down = true;
			if(!up) {
				down = true;
			}
			else {
				down = false;
				up = true;
			}
			right = false;
			left = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

}
