package guichat;

import java.awt.Color;
import java.awt.Graphics;

class GUIAnimatinFaceLook {// 顔のオブジェクト

	int h = 100;
	int w = 100;

	int xStart = 0;
	int yStart = 0;
    private int browAngle=0;  // 眉の角度
	private Color faceColor = Color.BLACK; // デフォルトの色

	public void setXY(int x, int y) {
		this.xStart = x;
		this.yStart = y;
	}

	public void setSize(int h, int w) {
		this.h = h;
		this.w = h;
	}

	public GUIAnimatinFaceLook() {

	}

	void makeFace(Graphics g) {
		// makeRim(g);
		makeEyes(g, w / 5);
		makeNose(g, h / 5);
		makeMouth(g, w / 2);
        drawBrow(g, browAngle);
	}

	public void makeFace(Graphics g, String emotion) {
		// **ここにemotion毎の顔を用意する。*///
		if (emotion.equals("normal")) {
			makeEyes(g, w / 5);
			makeNose(g, h / 5);
			makeMouth(g, 0);
            drawBrow(g, 0);
		}

        if(emotion.equals("angry")){
			makeEyes(g, w / 5);
			makeNose(g, h / 5);
			makeMouth(g,-h/3);
            drawBrow(g, -10);
            setColor(Color.RED);
        }

        if(emotion.equals("happy")){
			makeEyes(g, w / 5);
			makeNose(g, h / 5);
			makeMouth(g, h/3);
            drawBrow(g, 10);
			setColor(Color.YELLOW);
        }
		     // 顔の輪郭を描画
			 g.setColor(faceColor);
			 g.drawOval(xStart, yStart, w, h);

	}


	void makeEyes(Graphics g, int eyeSize) {
		g.setColor(Color.blue);
		g.drawOval(xStart + (h * 2 / 7), yStart + (w * 1 / 3), eyeSize, eyeSize);
		g.drawOval(xStart + (h * 4 / 7), yStart + (w * 1 / 3), eyeSize, eyeSize);
	}// makeEyes()

	public void makeNose(Graphics g, int noseSize) {
        g.drawLine(xStart + (w * 1 / 2), yStart + (h * 2 / 4), xStart + (w * 1 / 2), yStart + (h * 2 / 4) + noseSize);
	}// makeNose() end

	public void makeMouth(Graphics g, int curve) {
		int xMiddle = xStart + w/2;
		int yMiddle = yStart + h * 3 / 4;
		int mouthWidth =w/2;
		int mouthHeight = Math.abs(curve);

		if (curve > 0) {
			g.drawArc(xMiddle - mouthWidth/2, yMiddle - mouthHeight / 2, mouthWidth, mouthHeight, 0, -180);
		} else if (curve < 0) {
			g.drawArc(xMiddle - mouthWidth/2, yMiddle + mouthHeight / 2, mouthWidth, mouthHeight, 0, 180);
		} else {
			g.drawLine(xMiddle - mouthWidth/2, yMiddle, xMiddle + mouthWidth/2, yMiddle);
		}
	}

    public void drawBrow(Graphics g,int angle) { // xは目の幅 呼ばれる方(=定義する方)
        int wx1 = xStart + w*2/7;
        int wx2 = xStart + w*5/7;
        int wy = yStart + h/4;
		int browWudth = w / 8;

        g.drawLine(wx1, wy+angle, wx1+browWudth, wy);
        g.drawLine(wx2, wy, wx2+browWudth, wy+angle);
    }
	public void setColor(Color color) {
		this.faceColor = color;
	}

}// FaceObj End