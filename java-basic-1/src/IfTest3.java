public class IfTest3 {
	public static void main(String[] args) { 
		int x=Integer.parseInt(args[0]);
		/*以下を改造する。**/
        if(x>=7&&x<10){
		    System.out.println(x+" is Big Number");
        }
        else if(x>=4&&x<7){
            System.out.println(x+" is middle number");
        }
        else if(x>=1&&x<4){
            System.out.println(x+" is small number");
        }
        else if(x<1||x>9){
            System.out.println(x+" is out of scope");
        }

	}//main end
}//class end