import Project1.services.P1_RequestMapper;
import io.javalin.Javalin;

public class maindriver {

	public static void main(String[] args) {

		//Start a javalin server
        //Over a network, we communicate between 2 machines using their OP (internet protocol) address.
        //When we have services running on our own machine, we use local host, referring to itself.


		Javalin app = Javalin.create().start(8501);

		P1_RequestMapper requestMapper = new P1_RequestMapper();

		requestMapper.configureRoutes(app);
		
		
		//Testing
//		AuthenticationDao user = new AuthenticationDaoImpl();
//		user.findEmployeeByUsername("Phamjust");
	}

}
