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
	
	
	Tetris(){
		//subject為遊戲中的資料
		subject = new ConcreteTetrisModelSubject();
		//controller為控制遊戲流程
		controller = new TetrisController(subject);
		userEvents = new UserEvents(controller);
		


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

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
