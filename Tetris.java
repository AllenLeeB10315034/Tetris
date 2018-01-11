import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import UserEvents.TimeClock;

public class Tetris {

	UserEvents userEvents;
	ConcreteTetrisModelSubject subject;
	TetrisController controller;
	ConcreteTetrisViewObserver observer;
	
	Tetris(){
		//subject為遊戲中的資料
		subject = new ConcreteTetrisModelSubject();
		//controller為控制遊戲流程
		controller = new TetrisController(subject);
		userEvents = new UserEvents(controller);
		observer = new ConcreteTetrisViewObserver(subject);
		
		observer.addKeyListener(useoffsetrEvents);
		
		//將畫面與subject連結
		subject.AddObserver(observer);
		
		observer.setVisible(true);


	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//入口點
		Tetris tetris = new Tetris();
	}
	
	
}

class UserEvents implements KeyListener {
	TetrisController controller;
	Timer time;
	
	UserEvents(TetrisController controller){
		this.controller = controller;
		//每400ms跑一次，每一次呼叫TimeClock這個class(往下)
        time = new Timer(400, new TimeClock());
        time.start();
	}
	
    class TimeClock implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	controller.down_shift();
        }
    }
  //在KeyListener中，監聽keyPressed事件
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
        		//按上的話往右旋轉
            case KeyEvent.VK_UP:
            	controller.CW_rotate();
                break;
            //按下的話就往下一格
            case KeyEvent.VK_DOWN:
            	controller.down_shift();
                break;
            //按右的話往右一格
            case KeyEvent.VK_RIGHT:
            	controller.right_shift();
                break;
            //按左的話往左一格
            case KeyEvent.VK_LEFT:
            	controller.left_shift();
                break;
            //按space的時候直接不斷往下
            case KeyEvent.VK_SPACE:
            	controller.hard_drop();
                break;
            //按下Z的話往左旋轉
            case KeyEvent.VK_Z:
            	controller.CCW_rotate();
                break;
            //按下Shift的話，Hold著當前方塊
            case KeyEvent.VK_SHIFT:
            	controller.hold();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
