import java.util.List;
import java.util.ArrayList;

public abstract class TetrisModelSubject {
	List<TetrisViewObserver> list = new ArrayList<TetrisViewObserver>();
	
	public void AddObserver(TetrisViewObserver obs){
		list.add(obs);
	}
	public void NotifyObserver(){
        for (TetrisViewObserver obs : list) {
            obs.Update();
        }
	}
}
