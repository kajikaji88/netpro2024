public class XmasTree {

	 public static void main(String[] args) {

    int N=10;
    for (int j = 0; j < N; j++) {

        for (int i = 0; i <= N-j; i++) {
            if((i+j)%2==0){
            System.out.print("+");
            }else{
                System.out.print(" "); 
            }
        }

        for (int i = 0; i <= j*2; i++) {
            System.out.print("*");
        }

        for (int i = 0; i <= N-j; i++) {
            if((i+j)%2==0){
                System.out.print("+");
                }else{
                    System.out.print(" "); 
                }
        }
        
        System.out.print("\n");
    }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < N-3/2; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < 3; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

	}

