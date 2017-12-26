
public class TetrisController {
	ConcreteTetrisModelSubject model;
	
	TetrisController(ConcreteTetrisModelSubject sub){
		model = sub;
		model.setNext(new Block());
		newBlock();
	}
	
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
	
	public void right_shift(){
		if(model.Judge(model.getCur(), model.getCur_x() + 1, model.getCur_y())){
			model.setCurPosition(model.getCur_x() + 1, model.getCur_y());
			model.NotifyObserver();
		}
	}
	
	public void left_shift(){
		if(model.Judge(model.getCur(), model.getCur_x() - 1, model.getCur_y())){
			model.setCurPosition(model.getCur_x() - 1, model.getCur_y());
			model.NotifyObserver();
		}
	}
	
	public void hard_drop(){
        while(!down_shift());
	}
	
	public boolean down_shift(){
		boolean End = false;
	
		if(model.Judge(model.getCur(), model.getCur_x(), model.getCur_y() + 1))
			model.setCurPosition(model.getCur_x(), model.getCur_y() + 1);
		
		else{
	
			model.setBlockToMap();
			
			newBlock();
			End = true;
		}
		model.NotifyObserver();
		return End;
	}
	
	public void hold(){
		  try {
			  	
			  	if(model.hashold){
			  		Block temp = (Block) model.getHold().clone();
			  		model.setHold(model.getCur());
			  		model.setCur(temp);
					model.setCurPosition(3, 0);
				}
		
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
	
	public void newBlock(){
		model.setCur(model.getNext());
		model.setNext(new Block());
		model.setCurPosition(3, 0);
		
        if(model.Judge(model.getCur(), model.getCur_x(), model.getCur_y()) == false){
        	model.Init();
    		model.setNext(new Block());
    		newBlock();
        }
	}
}
