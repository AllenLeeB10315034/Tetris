import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

//這個class為介面輸出
public class ConcreteTetrisViewObserver extends JFrame implements TetrisViewObserver {
	//遊戲資料
	ConcreteTetrisModelSubject subject;
	Panel panel;
	//介面初始化
	ConcreteTetrisViewObserver(ConcreteTetrisModelSubject sub){
		subject = sub;
        setTitle("Tetris");
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new Panel();
        panel.setBounds(0, 0, 700, 700);
        add(panel);//unknown
        Update();
	}
	
	//更新介面所有資料
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		panel.map = subject.getMap();
		panel.cur = subject.getCur().getBlock();
		panel.cur_x = subject.getCur_x();
		panel.cur_y = subject.getCur_y();
		panel.next = subject.getNext().getBlock();
		panel.hashold = subject.hashold;
		if(subject.hashold)
			panel.hold = subject.getHold().getBlock();
		panel.repaint();
	}
}

class Panel extends JPanel{
    private Image b1, b2, shadow, nextImage, holdImage;
    private Image [] color = new Image[7];
	private final String [] colorname = {"role1.png", "role2.png", "role3.png", "role4.png", "role5.png", "role6.png", "role7.png"};

    public int[][] map;
    public int[] cur, next, hold;
    public int cur_x, cur_y;
	public boolean hashold;

    public Panel(){
        setLayout(null);
        setBackground(Color.gray); 
        b1 = Toolkit.getDefaultToolkit().getImage("back1.png");
        b2 = Toolkit.getDefaultToolkit().getImage("back2.png");
        nextImage =  Toolkit.getDefaultToolkit().getImage("next.png");
        holdImage=  Toolkit.getDefaultToolkit().getImage("hold.png");
        for(int i = 0; i < 7; i++)
            color[i] = Toolkit.getDefaultToolkit().getImage(colorname[i]);
    }
    
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == 0){
                    if((i+j)%2 == 0)
                        g.drawImage(b1, j*32+190, i*32, null);
                    else
                        g.drawImage(b2, j*32+190, i*32, null);
                }
                else
                    g.drawImage(color[map[i][j]-1], j*32+190, i*32, null);
            }
        }
        //cur
        for(int i = 0; i < 16; i++){
        	if(cur[i] >= 1)
        		g.drawImage(color[cur[i]-1], (cur_x+i%4)*32+190, (cur_y+i/4)*32, null);
        }
        g.drawImage(holdImage, 550, 200, null);
        //hold
        if(hashold){
            for(int i = 0; i < 16; i++){
                if(hold[i] >= 1){
                    g.drawImage(color[hold[i]-1], (i%4)*32+540, (i/4)*32+250, null);
                }
            }
        }
        g.drawImage(nextImage, 550, 50, null);
        //next
        for(int i = 0; i < 16; i++){
            if(next[i] >= 1){
                g.drawImage(color[next[i]-1], (i%4)*32+540, (i/4)*32+100, null);
            }
        }
    }
}