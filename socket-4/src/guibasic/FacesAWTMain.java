//FacesAWTMain.java
//FacesAWTMain 目標 インナークラスのFaceObjクラスを作ってみよう。描画処理を移譲してください。
//3x3　の顔を描画してください。色などもぬってオリジナルな楽しい顔にしてください。

package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FacesAWTMain {

	public static void main(String[] args){
		new FacesAWTMain();
	}

    FacesAWTMain(){
		FaceFrame f = new FaceFrame();
		f.setSize(800,800);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
			System.exit(0);}});
		f.setVisible(true);
	}

	// インナークラスを定義
	class FaceFrame extends Frame{

    private  FaceObj[][] fobjs;
    int xStart0=50;
    int yStart0=50;
    int faceWidth=200;
    int faceHeight =200;
	
	FaceFrame(){
		fobjs= new FaceObj[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                fobjs[i][j] = new FaceObj();
                fobjs[i][j].setPosition(xStart0 + j * faceWidth , yStart0 +i * faceHeight );
            }

        }
           // 一番上の行は怒りの表情
        for (int j = 0; j < 3; j++) {
            fobjs[0][j].setBrowAngle(-10);
            fobjs[0][j].setMouthCurve(-30);
            fobjs[0][j].setColor(Color.RED);
        }
        // 真ん中の行は困った表情
        for (int j = 0; j < 3; j++) {
            fobjs[1][j].setBrowAngle(0);
            fobjs[1][j].setMouthCurve(0);
            fobjs[1][j].setColor(Color.BLUE);
        }
        // 一番下の行は喜びの表情
        for (int j = 0; j < 3; j++) {
            fobjs[2][j].setBrowAngle(10);
            fobjs[2][j].setMouthCurve(30);
            fobjs[2][j].setColor(Color.YELLOW);
        }


    }

	public void paint(Graphics g) {
	// この中には、g.drawLine というのは入ってこない
	// Graphicsクラス(型のようなもの---今は--)のgという変数はメソッドに渡す
    for(FaceObj[] row :fobjs){
        for(FaceObj face :row){
            face.drawface(g);
        }
    }

    
    // FaceObj[] fobjs= new FaceObj[9];

	// 	fobjs[0]= new FaceObj();
    //     fobjs[0].setPosition(xStart0,yStart0);
    //     fobjs[0].drawface(g);
		
    //     fobjs[1]= new FaceObj();
    //     fobjs[1].setPosition(xStart0,yStart0);
	// 	fobjs[1].drawface(g);
	}
	
 }//FaceFrame end

	//Faceクラスを作ってみよう。
	private class FaceObj{
        private int w=200;
        private int h=200;
        private int xStart=350;
        private int yStart=350;
        private int browAngle=0;  // 眉の角度
        private int mouthCurve = 0; // 口のカーブの高さ
        private Color faceColor = Color.BLACK; // デフォルトの色

        public void drawRim(Graphics g) {  // wが横幅、hが縦幅
            g.setColor(faceColor);
            g.drawLine(xStart, yStart, xStart+w, yStart);
            g.drawLine(xStart, yStart, xStart, yStart+h);
            g.drawLine(xStart, yStart+h, xStart+w, yStart+h);
            g.drawLine(xStart+w, yStart, xStart+w, yStart+h);
        }
    
        public void drawface(Graphics g) {
            // TODO Auto-generated method stub
            g.setColor(faceColor);
            drawRim(g);
            drawBrow(g, browAngle);
            drawEye(g, 35);
            drawNose(g, 40);
            drawMouth(g, mouthCurve);
        }

		public void setPosition(int xStart0, int yStart0) {
			this.xStart=xStart0;
			this.yStart=yStart0;
		}

        public void setBrowAngle(int angle) {
            this.browAngle = angle;
        }

        public void setMouthCurve(int curve) {
            this.mouthCurve = curve;
        }
        public void setColor(Color color) {
            this.faceColor = color;
        }

        public void drawBrow(Graphics g,int angle) { // xは目の幅 呼ばれる方(=定義する方)
            int wx1 = xStart + w*2/8;
            int wx2 = xStart + w*5/8;
            int wy = yStart + h/5;
            g.drawLine(wx1, wy+angle, wx1+w*1/8, wy);
            g.drawLine(wx2, wy, wx2+w*1/8, wy+angle);
        }
    
        public void drawNose(Graphics g, int nx) { // xは鼻の長さ
            int wx = xStart + w/2;
            int wy1 = yStart + w/2;
            int wy2 =yStart + w/2+40;
            g.drawLine(wx , wy1,wx , wy2);
            
        }
    
        public void drawEye(Graphics g, int r) { // rは目の半径
            g.drawOval(xStart+45,yStart+65,r,r);
            g.drawOval(xStart+125,yStart+65,r,r);
            
        }
    
        public void drawMouth(Graphics g,  int curve) { // xは口の幅
            int xMiddle = xStart + w/2;
            int yMiddle = yStart + h - 30;
            if (curve > 0) {
                g.drawArc(xMiddle - 50, yMiddle - curve / 2, 100, curve, 0, -180);
            } else if (curve < 0) {
                g.drawArc(xMiddle - 50, yMiddle + curve / 2, 100, -curve, 0, 180);
            } else {
                g.drawLine(xMiddle - 50, yMiddle, xMiddle + 50, yMiddle);
            }
        }
	
	}

}//Faces class end