
public class TetrisController {
	ConcreteTetrisModelSubject model;
	
	TetrisController(ConcreteTetrisModelSubject sub){
		model = sub;
		model.setNext(new Block());
		newBlock();
	}
	//�p�G�i�檺�ܩ��k½��A�_�h���Xerror
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
	//�p�G�i�檺�ܩ���½��A�_�h���Xerror
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
	//���k��
	public void right_shift(){
		if(model.Judge(model.getCur(), model.getCur_x() + 1, model.getCur_y())){
			model.setCurPosition(model.getCur_x() + 1, model.getCur_y());
			model.NotifyObserver();
		}
	}
	//������
	public void left_shift(){
		if(model.Judge(model.getCur(), model.getCur_x() - 1, model.getCur_y())){
			model.setCurPosition(model.getCur_x() - 1, model.getCur_y());
			model.NotifyObserver();
		}
	}
	//�������U��
	public void hard_drop(){
        while(!down_shift());
	}
	//�������U�@��
	public boolean down_shift(){
		boolean End = false;
		//���U�i�檺�ܴN�]�w���U
		if(model.Judge(model.getCur(), model.getCur_x(), model.getCur_y() + 1))
			model.setCurPosition(model.getCur_x(), model.getCur_y() + 1);
		//�_�h���ܴN�O�P�w�Ӥ���w�g�쩳
		else{
			//��m���
			model.setBlockToMap();
			//�]�w�s�����
			newBlock();
			End = true;
		}
		model.NotifyObserver();
		return End;
	}
	//Hold����
	public void hold(){
		  try {
			  	//�p�G���ӴN��Hold���ܡA���e�P�쥻��hold����洫
			  	if(model.hashold){
			  		Block temp = (Block) model.getHold().clone();
			  		model.setHold(model.getCur());
			  		model.setCur(temp);
					model.setCurPosition(3, 0);
				}
			  	//�p�G���ӨS��Hold���ܡA�N���e�������hold����A�ö}�l�U�@�ӷs�����
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
	//�]�w�U�@�ӷs�����
	public void newBlock(){
		model.setCur(model.getNext());
		model.setNext(new Block());
		model.setCurPosition(3, 0);
		//�p�G������
        if(model.Judge(model.getCur(), model.getCur_x(), model.getCur_y()) == false){
        	model.Init();
    		model.setNext(new Block());
    		newBlock();
        }
	}
}
