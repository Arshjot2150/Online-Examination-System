import java.util.Scanner;

public class DemoOfException 
{
	public static void main(String[] args) 
	{
		System.out.println("code before try");
		try 
		{
			System.out.println("code before RISKY code");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("Num1: ");
			int x = sc.nextInt(); // risky code
			
			System.out.print("Num2: ");
			int y = sc.nextInt(); // risky code
			
			int z = x / y; // risky code
			
			int[] array = new int[z]; // risky code
			
			System.out.println("code after RISKY code");
		} 
		catch (Exception e) 
		{
			System.out.println("catch after exception arise "+e);
		}
		finally
		{
			System.out.println("finnaly");
		}
		
		System.out.println("rest of application");
	}
}