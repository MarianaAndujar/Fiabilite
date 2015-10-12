package appli_CB;

public class ConnectToTerminal {

	private ICard connectedCard;
	private IValidator validator;
	
	public ConnectToTerminal(){
		
	}
	
	public boolean validateCardNumber(int cardNumber) throws Exception{
		if(validator.validateCard(cardNumber)!= null){
			connectedCard = validator.validateCard(cardNumber);
			return true;
		}else{
			 throw new Exception("IllegalCard");
		}
		
	}
	
	public void authenticateCode(int secretCode){
		
	}
	
}
