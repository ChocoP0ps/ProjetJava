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

/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer {

	/** The view frame. */
	private ViewFrame					viewFrame;
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -998294702363713521L;

	Image spriteLorann;
	Image spriteFire;
	String name;
	int first;
	
	/**
	 * Instantiates a new view panel.
	 *
	 * @param viewFrame
	 *          the view frame
	 */
	public ViewPanel(final ViewFrame viewFrame) {
		this.first = 0;
		this.name = "";
		try {
			spriteLorann = ImageIO.read(new File("sprite/lorann_b.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread animLorann = new Thread(new AnimLorann());
		animLorann.start();
		Thread animFire = new Thread(new AnimFire());
		animFire.start();
		this.setViewFrame(viewFrame);
		viewFrame.getModel().getObservable().addObserver(this);
	}

	/**
	 * Gets the view frame.
	 *
	 * @return the view frame
	 */
	private ViewFrame getViewFrame() {
		return this.viewFrame;
	}

	/**
	 * Sets the view frame.
	 *
	 * @param viewFrame
	 *          the new view frame
	 */
	private void setViewFrame(final ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable arg0, final Object arg1) {
		this.repaint();
	}
	
	public void printMap(Graphics graphics){
		ArrayList<IElement> elementsList = this.getViewFrame().getModel().getElementsList();
		for(int y = 0; y<12; y++){
			for (int x =0; x<20; x++){
				switch(elementsList.get(x+(20*y)).getTYPE()){
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
					if(elementsList.get(x+(20*y)).getPENETRABLE()){
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 5 :
					graphics.drawImage(spriteLorann, (x*64), (y*64), 64, 64, viewFrame);
					break;
				case 7 :
					graphics.drawImage(spriteFire, (x*64), (y*64), 64, 64, viewFrame);
				case 0 :
					break;
				case 8:
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/monster_1.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 9:
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/monster_2.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 10:
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/monster_3.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 11:
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/monster_4.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 12:	
					try {
						graphics.drawImage(ImageIO.read(new File("sprite/purse.png")), (x*64), (y*64), 64, 64, viewFrame);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(final Graphics graphics) {
		this.name = this.viewFrame.getModel().getName();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(this.viewFrame.getModel().getLevel() == 1){
			Font font = new Font("07x5", Font.BOLD, 20);
			graphics.setFont(font);
			for(int i = 0; i<5;i++){
				graphics.setColor(Color.MAGENTA);
				graphics.drawString(this.viewFrame.getModel().loadBestName(i), 510, 310+(60*i));
				graphics.setColor(Color.yellow);
				graphics.drawString(Integer.toString(this.viewFrame.getModel().loadBestScore(i)), 510, 350+(60*i));
			}
			font = new Font("07x5", Font.BOLD, 80);
			graphics.setColor(Color.cyan);
			graphics.setFont(font);
			graphics.drawString("Lorann", 456, 256);
		}
		this.printMap(graphics);
	}

	class AnimFire implements Runnable{
		public void run() {
			int n = 0;
			while(true){
				switch(n%5){
				case 0:
					try {
						spriteFire = ImageIO.read(new File("sprite/fireball_1.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 1:
					try {
						spriteFire = ImageIO.read(new File("sprite/fireball_2.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						spriteFire = ImageIO.read(new File("sprite/fireball_3.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						spriteFire = ImageIO.read(new File("sprite/fireball_4.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 4:
					try {
						spriteFire = ImageIO.read(new File("sprite/fireball_5.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint(0,0, getWidth(), getHeight());
				n++;
			}
		}
	}	
	
	
	class AnimLorann implements Runnable{

		public void run() {
			int n = 0;
			while(true){
				switch(n%8){
				case 0:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_b.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 1:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_bl.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_l.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_ul.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 4:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_u.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 5:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_ur.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 6:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_r.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 7:
					try {
						spriteLorann = ImageIO.read(new File("sprite/lorann_br.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint(0,0, getWidth(), getHeight());
				n++;
			}
		}
	}
}
