package guibasic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SwingAnimationBasicTimerTest extends JPanel implements ActionListener {

  private Ellipse2D.Float ellipse = new Ellipse2D.Float();

  private double counter;

  private double MAXCounter = 60;

  private boolean isResetProcess = true;

  Timer timer;

  ActionListener updateProBar;

  public SwingAnimationBasicTimerTest() {

    setEllipseSize(20 * Math.random(), 200, 200);

    //イベント間の Timer の遅延時間をミリ秒単位で設定します。
    timer = new Timer(20, (ActionListener)this);

    //次のアクションイベントがトリガーされるまでの遅延時間 (ミリ秒単位) を返します。
    timer.setInitialDelay(190);

    timer.start();
  }

  public void setEllipseSize(double size, int w, int h) {
    counter = size;
    ellipse.setFrame(10, 10, size, size);
  }

  public void resetProcess(int w, int h) {
    setEllipseSize(1, w, h);
  }

  public void doNextStep(int w, int h) {
    counter++;
    if (counter > MAXCounter) {
      setEllipseSize(1, w, h);
    } else {
      ellipse.setFrame(ellipse.getX(), ellipse.getY(), counter, counter);
    }
  }

  public void paintProcess(int w, int h, Graphics2D g2) {

	g2.setColor(Color.BLUE);
    g2.draw(ellipse);

  }

  public void paint(Graphics g) {
	/* おまじない：開始*/

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

    g2.setRenderingHints(rh);
    Dimension windowSize = getSize();

    if (isResetProcess) {
      resetProcess(windowSize.width, windowSize.height);
      isResetProcess = false;
    }

    this.doNextStep(windowSize.width, windowSize.height);
    paintProcess(windowSize.width, windowSize.height, g2);
    /* おまじない：終了*/

  }
  //Timerが切れたときに呼び出されます。
  public void actionPerformed(ActionEvent e) {
    repaint();
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("TimerBasedAnimation");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new SwingAnimationBasicTimerTest());
    frame.setSize(350, 250);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}