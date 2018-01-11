//map資料
public class Map {
	private int[][] map = new int[20][10];
	
	Map(){
		Init();
	}
	//把map初始化
	public void Init(){
        for(int i = 0; i < map.length; i++)
            for(int j = 0; j < map[0].length; j++)
                map[i][j] = 0;
	}
	//判斷當前的方塊是否有無超過map的邊界
    public boolean Judge(Block block, int x, int y){
        for(int i = 0; i < 16; i++){
            if(block.getBlock()[i] >= 1){
                if(x+i%4 >= map[0].length || y+i/4 >= map.length || x+i%4 < 0 || y+i/4 < 0)
                    return false;
                if(map[y+i/4][x+i%4] != 0)
                    return false;
            }
        }
        return true;
    }
    //當方塊放到最底時呼叫
    public void setBlock(Block block, int x, int y){
        for(int i = 0; i < 16; i++)
            if(block.getBlock()[i] >= 1)
                map[y+i/4][x+i%4] = block.getType() + 1;
        //刪除已經滿的最底方塊
        delLine();
    }
    //取得map資料
    public int[][] getMap(){
    	return map;
    }
    //刪除已經滿的最底方塊
    public void delLine(){
        int temp = map.length - 1;
        for(int i = map.length - 1; i >= 0; i--){
            int cnt = 0;
            for(int j = 0; j < map[0].length; j++)
                if(map[i][j] != 0)
                    cnt++;
            if(cnt == map[0].length)
                for(int j = 0; j < map[0].length; j++)
                    map[i][j] = 0;
            else{
                for(int j = 0; j < map[0].length; j++)
                    map[temp][j] = map[i][j];
                temp--;
            }
        }
    }
}
