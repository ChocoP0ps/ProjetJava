package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import contract.IElement;


class ViewPanel extends JPanel implements Observer {				//Class JPanel which implements the interface Observer (Will be informed of the changes commit in the observable object)

	private ViewFrame					viewFrame;			//ViewFrame object

	private static final long	serialVersionUID	= -998294702363713521L;			//Serial version

	Image spriteLorann;			//Sprite of the hero
	Image spriteFire;			//Sprite of the fireball
	String name;				//Name of the Player
	int first;					//Verification of the hero's death
	

	public ViewPanel(final ViewFrame viewFrame) {		//Constructor of the ViewPanel
		this.first = 0;												//Setting the hero death to no
		this.name = "";												//Setting the name to ""
		Thread animLorann = new Thread(new AnimLorann());			//Thread which turn the Lorann's head
		animLorann.start();											//Start of the thread
		Thread animFire = new Thread(new AnimFire());				//Thread which change the color of the fireball
		animFire.start();											//Start of the thread
		this.setViewFrame(viewFrame);								//Setting of the ViewFrame
		viewFrame.getModel().getObservable().addObserver(this);		//Observe the Model
	}


	private ViewFrame getViewFrame() {								//Getter of the ViewFrame
		return this.viewFrame;
	}

	private void setViewFrame(final ViewFrame viewFrame) {			//Setter of the ViewFrame
		this.viewFrame = viewFrame;
	}


	public void update(final Observable arg0, final Object arg1) {	//Update of the window (Observer interface)
		this.repaint();
	}
	
	public void printMap(Graphics graphics){						//Print the map on the window
		ArrayList<IElement> elementsList = this.getViewFrame().getModel().getElementsList();		//Get the model's element's array
		for(int y = 0; y<12; y++){
			for (int x =0; x<20; x++){
				switch(elementsList.get(x+(20*y)).getTYPE()){			//for each element's type, print the associated sprite
				case 1 :
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/bone.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 2 :
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/horizontal_bone.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 3 :
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/vertical_bone.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 4 :
					if(elementsList.get(x+(20*y)).getPENETRABLE()){			//Verify if the door is open
						try {
							graphics.drawImage(ImageIO.read(new File("sprite/gate_open.png")), (x*64), (y*64), 64, 64, viewFrame);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else{
						try {
							graphics.drawImage(ImageIO.read(new File("sprite/gate_closed.png")), (x*64), (y*64), 64, 64, viewFrame);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					break;
				case 6 :
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/crystal_ball.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 5 :				//The sprite change with the other thread
					graphics.drawImage(spriteLorann, (x*64), (y*64), 64, 64, viewFrame);
					break;
				case 7 :				//The sprite change with the other thread
					graphics.drawImage(spriteFire, (x*64), (y*64), 64, 64, viewFrame);
				case 0 :
					break;
				case 8:
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/monster_1.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 9:
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/monster_2.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 10:
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/monster_3.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 11:
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/monster_4.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 12:	
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/purse.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}

	@Override
	protected void paintComponent(final Graphics graphics) {			//Print the component of the Panel
		this.name = this.viewFrame.getModel().getName();				//Setting the name with the model's attribute 'name'
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());		//Print a black rectangle which cover all the window
		Font font = new Font("07x5", Font.BOLD, 20);					//Define the font for the Best Scores
		if(this.viewFrame.getModel().getLevel() == 1){					//If it is the first level
			graphics.setFont(font);										//Set the font defined previously
			for(int i = 0; i<5;i++){									//Print the 5 first score
				graphics.setColor(Color.MAGENTA);											//In magenta
				graphics.drawString(this.viewFrame.getModel().loadBestName(i), 510, 280+(60*i));						//The pseudo 
				graphics.setColor(Color.yellow);											//In yellow
				graphics.drawString(Integer.toString(this.viewFrame.getModel().loadBestScore(i)), 510, 305+(60*i));		//And the score
			}
			font = new Font("07x5", Font.BOLD, 80);						//Define the font for the Game's Title
			graphics.setColor(Color.cyan);								//In cyan
			graphics.setFont(font);										//Set the font
			graphics.drawString("Lorann", 456, 256);					//Print the Game's Title (Lorann)
		}
		font = new Font("07x5", Font.BOLD, 32);							//Define the font for the score
		graphics.setFont(font);											//Set the font
		graphics.setColor(Color.yellow);								//In yellow
		graphics.drawString("Score : " + this.viewFrame.getModel().getScore(), 192, 800);			//Print "The score : " + The player's score at the bottom of the window
		this.printMap(graphics);										//Print the map on the window
	}

	class AnimFire implements Runnable{				//Thread which change the fireball's sprite
		public void run() {
			int n = 0;
			while(true){				//Infinite loop
				switch(n%5){			//switch of an incremented number modulo 5
				case 0:
					try {
						spriteFire = ImageIO.read(new File("sprite/fireball_1.png"));	//We change the sprite
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 1:
					try {
						spriteFire = ImageIO.read(new File("sprite/fireball_2.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						spriteFire = ImageIO.read(new File("sprite/fireball_3.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						spriteFire = ImageIO.read(new File("sprite/fireball_4.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					try {
						spriteFire = ImageIO.read(new File("sprite/fireball_5.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
				try {
					Thread.sleep(100);						//Every 100ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint(0,0, getWidth(), getHeight());		//Update of the window
				n++;										//Increase of the incremented number
			}
		}
	}	
	
	
	class AnimLorann implements Runnable{		//Thread which change the Lorann's sprite

		public void run() {
			int n = 0;
			while(true){					//Infinite loop
				switch(n%8){				//switch of an incremented number modulo 8
				case 0:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_b.png"));			//Change of the sprite
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 1:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_bl.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_l.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_ul.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_u.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 5:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_ur.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 6:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_r.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 7:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_br.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
				try {
					Thread.sleep(100);							//Every 100ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint(0,0, getWidth(), getHeight());			//Update of the window
				n++;											//Increase of the incremented number
			}
		}
	}
}
