import be.afelio.software_academy.beans.Activity;
import be.afelio.software_academy.beans.Event;
import be.afelio.software_academy.repository.DataRepository;

public class testMain {

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost/projet_event";
		String user = "postgres";
		String password = "postgres";
		DataRepository dataRepository = new DataRepository(url, user, password);
		Event findOneEventById = dataRepository.findOneEventById(1);
		
		Activity activity = dataRepository.findOneActivityById(1);
		System.out.println(activity);
		
		
	}

}
