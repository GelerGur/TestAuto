package file;



public class Test  {
 
   public static void main(String[] args)  {
	  
	  int  n = 245;
	  System.out.println(test(n)); 
	   
   }  
   public static int test(int n)  {
		    
		  int countOfone= 0;
		  String one= "1";
		  String binString= Integer.toBinaryString(n); 
		
		  for(int i = 0; i < binString.length(); i++){
			  if (binString.charAt(i)==one.charAt(0)){
				  countOfone++;  
				  }
		  }
		  
		  
		  return (countOfone);
		   
	   }  
   
   
}


