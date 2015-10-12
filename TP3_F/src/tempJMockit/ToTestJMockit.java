package tempJMockit;

/**
 * Classe a tester avec JMockit
 * @author k904029
 *
 */

public class ToTestJMockit {
	
	private IConversion conv;
		
		public ToTestJMockit(IConversion conv2){
			this.conv = conv2;
		}
		
		public double convert(double temperature, String direction){
			if(direction == "C2F"){
				return conv.convC2F(temperature);
			}else if (direction == "F2C"){
				return conv.convF2C(temperature);
			}else{
				throw new IllegalArgumentException("Erreur au niveau test");
			}
		}

}
