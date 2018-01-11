
public class TetrisController {
	ConcreteTetrisModelSubject model;
	
	TetrisController(ConcreteTetrisModelSubject sub){
		model = sub;
		model.setNext(new Block());
		newBlock();
	}
	//如果可行的話往右翻轉，否則跳出error
	public void CW_rotate(){
		  try {
				Block temp = (Block) model.getCur().clone();
				temp.CW_rotate();
				if(model.Judge(temp, model.getCur_x(), model.getCur_y()))
					model.setCur(temp);
			  } 
		  catch (CloneNotSupportedException e) {
			   e.printStackTrace();
			  }
	
		  model.NotifyObserver();
	}
	//如果可行的話往左翻轉，否則跳出error
	public void CCW_rotate(){
		  try {
				Block temp = (Block) model.getCur().clone();
				temp.CCW_rotate();
				if(model.Judge(temp, model.getCur_x(), model.getCur_y()))
					model.setCur(temp);
			  } 
		  catch (CloneNotSupportedException e) {
			   e.printStackTrace();
			  }
		  model.NotifyObserver();
	}
	//往右移
	public void right_shift(){
		if(model.Judge(model.getCur(), model.getCur_x() + 1, model.getCur_y())){
			model.setCurPosition(model.getCur_x() + 1, model.getCur_y());
			model.NotifyObserver();
		}
	}
	//往左移
	public void left_shift(){
		if(model.Judge(model.getCur(), model.getCur_x() - 1, model.getCur_y())){
			model.setCurPosition(model.getCur_x() - 1, model.getCur_y());
			model.NotifyObserver();
		}
	}
	//直接往下放
	public void hard_drop(){
        while(!down_shift());
	}
	//把方塊往下一格
	public boolean down_shift(){
		boolean End = false;
		//往下可行的話就設定往下
		if(model.Judge(model.getCur(), model.getCur_x(), model.getCur_y() + 1))
			model.setCurPosition(model.getCur_x(), model.getCur_y() + 1);
		//否則的話就是判定該方塊已經到底
		else{
			//放置方塊
			model.setBlockToMap();
			//設定新的方塊
			newBlock();
			End = true;
		}
		model.NotifyObserver();
		return End;
	}
	//Hold住方塊
	public void hold(){
		  try {
			  	//如果本來就有Hold的話，把當前與原本的hold方塊交換
			  	if(model.hashold){
			  		Block temp = (Block) model.getHold().clone();
			  		model.setHold(model.getCur());
			  		model.setCur(temp);
					model.setCurPosition(3, 0);
				}
			  	//如果本來沒有Hold的話，就把當前的方塊放成hold方塊，並開始下一個新的方塊
			  	else{
			  		model.setHold(model.getCur());
			  		newBlock();
			  		model.hashold = true;
			  	}
			  } 
		  catch (CloneNotSupportedException e) {
			   e.printStackTrace();
			  }
		  model.NotifyObserver();
	}
	//設定下一個新的方塊
	public void newBlock(){
		model.setCur(model.getNext());
		model.setNext(new Block());
		model.setCurPosition(3, 0);
		//如果滿的話
        if(model.Judge(model.getCur(), model.getCur_x(), model.getCur_y()) == false){
        	model.Init();
    		model.setNext(new Block());
    		newBlock();
        }
	}
}
