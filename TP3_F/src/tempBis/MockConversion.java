package tempBis;

public class MockConversion implements IConversion{

	@Override
	public double convF2C(double farenheit){
		if(farenheit == 32){
				return 0;
		}else if(farenheit == 100){
				return 212;
		}else if(farenheit == 37){ 
				return 98.6;
		}else if(farenheit == -40){ 
				return -40;
		}else{
				throw new IllegalArgumentException("erreur mock");
		}
	}
	
	@Override
	public double convC2F(double celsius){
			if(celsius == 0){
				return 32;
		}else if(celsius == 212){
				return 100;
		}else if(celsius == 98.6){ 
				return 37;
		}else if(celsius == -40){ 
				return -40;
		}else{
				throw new IllegalArgumentException("erreur mock");
		}
	}

}
