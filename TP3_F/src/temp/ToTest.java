package temp;

public class ToTest {
	
	private MockConversion conv;
	
	public ToTest(MockConversion c){
		this.conv = c;
	}
	
	public double convert(double temperature, String direction){
		if(direction == "C2F"){
			return conv.convC2F(temperature);
		}else if (direction == "F2C"){
			return conv.convF2C(temperature);
		}else{
			throw new IllegalArgumentException();
		}
	}

}
