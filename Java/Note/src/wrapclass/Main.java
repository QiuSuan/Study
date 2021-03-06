package wrapclass;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int columns=in.nextInt();
        int rows = in.nextInt();
        CellGrids cg = new CellGrids(columns, rows);
    	int row = in.nextInt();
    	int column=in.nextInt();
    	while(row!=-1){
    		cg.setAlive(row, column);
    		row = in.nextInt();
    		column=in.nextInt();
    	}
    	int steps = in.nextInt();
    	for(int i=0;i<steps;i++){
    		cg.step();
    	}
        System.out.println(cg.countAlive());
        in.close();
    }
}

class Cell{
	private boolean isalive;
	private boolean change=false;
	
	public Cell(boolean isalive) {
		this.isalive=isalive;
	}
	public void initialAlive(){
		this.isalive = true;
	}
	public void die(){
		this.change=true;
		this.isalive=false;
	}
	public void reborn(){
		this.change =true;
		this.isalive=true;
	}
	public boolean getAlive(){
		return isalive;
	}
	public boolean getChange(){
		return change;
	}
	public void setChange(){
		this.change=false;
	}
	
}

class CellGrids{
	private int columns;//��
	private int rows;//��
	private Cell[][] cells;
	
	public CellGrids(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		this.cells = new Cell[rows][columns];
		cellInitial();
	}
	private  void cellInitial(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				cells[i][j]=new Cell(false);
			}
		}
	}
	public void setAlive(int row,int column){
		cells[row][column].initialAlive();
	}
	public void step(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				if(cells[i][j].getAlive()){
					if(checkAround(i, j)<2||checkAround(i, j)>3){
						cells[i][j].die();
					}
				}else{
					if(checkAround(i, j)==3){
						cells[i][j].reborn();
					}
				}
			}
		}
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				cells[i][j].setChange();
			}
		}
		
	}
	public int checkAround(int row,int column){
		int sum=0;
		for(int i=row-1;i<=row+1;i++){
			for(int j=column-1;j<=column+1;j++){
				if(i>=0&&j>=0&&i<rows&&j<columns&&!(i==row&&j==column)){
					if(cells[i][j].getChange()&&!cells[i][j].getAlive()){
						sum++;
					}
					if(!cells[i][j].getChange()&&cells[i][j].getAlive()){
						sum++;
					}
				}
			}
		}
		return sum;
	}
	public int countAlive(){
		int sum=0;
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				if(cells[i][j].getAlive()){
					sum++;
				}
			}
		}
		return sum;
	}
}