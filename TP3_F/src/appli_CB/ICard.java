package appli_CB;

public interface ICard {
	
	public boolean isConnected();
	public boolean isValid();
	public void setConnection(boolean v);
	public void setValidation(boolean v);
	public boolean checkSecretCode(int code);
	
}
