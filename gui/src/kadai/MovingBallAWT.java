 package kadai;
 import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

//配列で5つのボールを動かしてみよう

public class MovingBallAWT {
	public static void main(String[] args) {
		FFrame f = new FFrame();
		f.setSize(500, 500);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.show();
	}


	static class FFrame extends Frame implements Runnable {

		Thread th;
		Ball [] balls = new Ball[5];
        Random rand = new Random();

		private boolean enable = true;
		private int counter = 0;

		FFrame() {
			th = new Thread(this);
			th.start();
		}

		public void run() {

            for(int i =0;i<balls.length;i++){
                balls[i] = new Ball();
                balls[i].setPosition(rand.nextInt(400) + 50, rand.nextInt(400) + 50);
                balls[i].setR(10 +i*5);
                balls[i].setColor(new Color(rand.nextInt(0x1000000)));
				balls[i].setSpeed(rand.nextInt(10) + 1, rand.nextInt(10) + 1); // ランダムな速度


            }

			while (enable) {

				try {
					th.sleep(100);
					counter++;
					if (counter >= 200) enable = false;
				} catch (InterruptedException e) {
				}


                for(Ball ball :balls){
                    ball.move();
                }

				repaint();  // paint()メソッドが呼び出される
			}
		}


		public void paint(Graphics g) {
            for(Ball ball :balls){
                ball.draw(g);
            }
		}

		// Ball というインナークラスを作る
		class Ball {
			int x;
			int y;
			int r; // 半径
			Color c = Color.BLACK;

			int xDir = 1;  // 1:+方向  -1: -方向
			int yDir = 1;
			int xSpeed;
            int ySpeed;

			void setColor(Color c) {
				this.c = c;
			}

			void setSpeed(int xSpeed, int ySpeed) {
                this.xSpeed = xSpeed;
                this.ySpeed = ySpeed;
            }


			void move() {
				if ((xDir == 1) && (x >= getWidth() - 2*r)) {
                    xDir = -1;
                }
                if ((xDir == -1) && (x <= 2*r)) {
                    xDir = 1;
                }

                if (xDir == 1) {
                    x = x + xSpeed;
                } else {
                    x = x - xSpeed;
                }

                if ((yDir == 1) && (y >= getHeight() - 2*r)) {
                    yDir = -1;
                }
                if ((yDir == -1) && (y <= 2*r)) {
                    yDir = 1;
                }

                if (yDir == 1) {
                    y = y + ySpeed;
                } else {
                    y = y - ySpeed;
                }


			}


			void setPosition(int x, int y) {
				this.x = x;
				this.y = y;
			}

			void setR(int r) {
				this.r = r;
			}

			void draw(Graphics g) {
				g.setColor(c);
				g.fillOval(x, y, 2 * r, 2 * r);  // rは半径なので2倍にする

				int smallR = r / 3;
                int smallX = x + r / 2- smallR;
                int smallY = y + r / 2;
                g.setColor(Color.WHITE);
                g.fillOval(smallX, smallY, 2 * smallR, 2 * smallR); // 白い内側の円
                g.setColor(new Color(rand.nextInt(0x1000000)));
                g.fillOval(smallX + smallR / 2, smallY + smallR / 2, smallR, smallR); // 黒い中心の円
			}

		}//innner class Ball end

	}

}