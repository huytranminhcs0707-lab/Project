package CustomException;

import java.util.ArrayList;
import java.util.List;

public class FieldRequiredException extends RuntimeException {
	public List<String> error = new ArrayList<String>();
	
	public FieldRequiredException(List<String> error) {
		super("There are some problems of the data!");
		// TODO Auto-generated constructor stub
		this.error = error;
		
	}
	public List<String> getError(){
		return error;
	}
	
}
