package gameFiles.misc;

// ======================================== //
//              Chess - CS242               //
//			   @author bcorrel2             //
// ======================================== //

public class coord {
	// Representation of an (x,y) coordinate
	
	int x;
	int y; 
	
	public coord(int newX, int newY) {
		// constructor for class coord
		x = newX;
		y = newY;
	}
	
	public int getX(){
		// Get-Function for x
		return x;
	}
	
	public int getY() {
		// Get-Function for y
		return y;
	}
	
	public void setX(int newX) {
		// Set-Function for x
		x = newX;
	}
	
	public void setY(int newY) {
		// Set-Function for y
		y = newY;
	}
}