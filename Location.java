public class Location{
    private int xCoord;
    private int yCoord;
    private String gridX;
    private int gridY;
    public static int SPACE = 10;

    private int length;

    private boolean inBounds;

    public Location(int x, int y){
        xCoord = x;
        yCoord = y;
        gridX = convertIntX(x);
        gridY = convertToGridY(y);
        inBounds = checkBounds();
        if(inBounds){
            snapTo();
        }
    }

    public Location(int x, int y, int l){
        xCoord = x;
        yCoord = y;
        gridX = convertIntX(x);
        gridY = convertToGridY(y);
        inBounds = checkBounds();
        length = l;
        if(inBounds){
            snapTo();
        }
    }

    public Location(String x, int y){
        gridX = x;
        gridY = y;
    }

    public boolean equals(Location check){
        if(check.checkBounds() && this.checkBounds()){
            if(check.getGridX() == this.getGridX() && check.getGridY() == this.getGridY())
                return true;
        }
        else{
            if(check.getXVal() >= this.getXVal() && check.getXVal() <= this.getXVal() + 60*length)
                if(check.getYVal() >= this.getYVal() && check.getYVal() <= this.getYVal() + 30){
                    return true;
                }
        }
        return false;
    }

    public int getXVal(){
        return xCoord;
    }

    public int getYVal(){
        return yCoord;
    }

    public String getGridX(){
        return gridX;
    }

    public int getGridY(){
        return gridY;
    }

    public boolean checkBounds(){
        if(gridY == -1 || gridX == null)
            return false;
        return true;
    }

    public void snapTo(){
        if(!inBounds)
            return;
        int colVal = getColVal(gridX);
        xCoord = ((colVal) * Board.SIDE) + Board.LEFT + SPACE;
        yCoord = ((gridY) * Board.SIDE) + Board.TOP + 2*SPACE;
    }

    public static int getColVal(String col){
        if (col==null)
            return -1;
        else if(col.equals("A"))
            return 0;
        else if (col.equals("B"))
            return 1;
        else if (col.equals("C"))
            return 2;
        else if (col.equals("D"))
            return 3;
        else if (col.equals("E"))
            return 4;
        else if (col.equals("F"))
            return 5;
        else if (col.equals("G"))
            return 6;
        else if (col.equals("H"))
            return 7;
        else if (col.equals("I"))
            return 8;
        else if (col.equals("J"))
            return 9;
        return -1;
    }

    public static String convertIntX(int x){
        String answer = "A";
        int num = 0;
        while(answer!=null){
            if(x >= Board.LEFT + Board.SIDE*num && x <= Board.LEFT + Board.SIDE*(num+1))
                return answer;
            answer = increment(answer);
            num++;
        }
        return null;
    }

    public static int convertToGridY(int y){
        int answer = 0;
        int num = 0;
        while(num <= 9){
            if(y >= Board.TOP + Board.SIDE*num && y <= Board.TOP + Board.SIDE*(num+1))
                return answer;
            answer ++;
            num ++;
        }
        return -1;
    }
    
    public String toString(){
        return gridX + ", " + gridY;
    }

    public static String increment(String change){
        if(change == null)
            return null;
        else if(change.equals("A"))
            return "B";
        else if(change.equals("B"))
            return "C";
        else if(change.equals("C"))
            return "D";
        else if(change.equals("D"))
            return "E";
        else if(change.equals("E"))
            return "F";
        else if(change.equals("F"))
            return "G";
        else if(change.equals("G"))
            return "H";
        else if(change.equals("H"))
            return "I";
        else if(change.equals("I"))
            return "J";
        return null;
    }
}