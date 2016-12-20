
public class Board {
	public int board[][];
	public int m_rectWidth;
	public int m_rectHeight;
	public int m_xSize;
	public int m_ySize;
	
	public Board(int xSize, int ySize, int rectWidth, int rectHeight){
		
		board = new int[ySize][xSize];
		
		m_rectWidth = rectWidth;
		m_rectHeight = rectHeight;
		m_xSize = xSize;
		m_ySize = ySize;
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				board[i][j] = 0;
			}
			board[i][0] = 1;
		}
	}
}
