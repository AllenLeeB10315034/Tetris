//ConcreteTetrisModelSubject為遊戲基本資料，主要為當前方塊狀態，初始值
public class ConcreteTetrisModelSubject extends TetrisModelSubject {
	Map map;
	private Block cur, next, hold;
	private int cur_x = 3, cur_y = 0;
	public boolean hashold;

	ConcreteTetrisModelSubject(){
		Init();
	}
	//遊戲資料初始化
	public void Init(){
		map = new Map();
		hashold = false;
		hold = Block.emptyBlock();
		cur_x = 3;
		cur_y = 0;
	}
	//呼叫map當中判斷是否超出邊界的function
    public boolean Judge(Block block, int x, int y){
        return map.Judge(block, x, y);
    }
    //設定當前的方塊
	public void setCur(Block block){
		cur = block;
	}
	//設定下一個方塊
	public void setNext(Block block){
		next = block;
	}
	//設定Hold的方塊
	public void setHold(Block block){
		hold = block;
	}
	//設定當前方塊的位置
	public void setCurPosition(int x, int y){
		cur_x = x;
		cur_y = y;
	}
	//將當前的方塊到最底下的時候碰到其他方塊時放置
	public void setBlockToMap(){
		map.setBlock(cur, cur_x, cur_y);
	}
	//取得當前方塊
	public Block getCur(){
		return cur;
	}
	//取得下一個方塊
	public Block getNext(){
		return next;
	}
	//取得原本hold的方塊
	public Block getHold(){
		return hold;
	}
	//取得map的資料
	public int[][] getMap(){
		return map.getMap();
	}
	//取得當前方塊的x座標
	public int getCur_x(){
		return cur_x;
	}
	//取得當前方塊的y座標
	public int getCur_y(){
		return cur_y;
	}
}
