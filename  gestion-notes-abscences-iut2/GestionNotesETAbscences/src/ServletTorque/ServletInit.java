package ServletTorque;

import java.io.IOException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;

import model.*;


@SuppressWarnings("serial")
public class ServletInit extends GenericServlet {

	private static final String TORQUE_PROPS = new String("Torque.properties");

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		super.init();

		// Initialisation de la connection Torque
		if (!Torque.isInit()) {
			try {

				PropertiesConfiguration pc = new PropertiesConfiguration();
				System.out.println(this.getClass().getClassLoader().getResourceAsStream(
						TORQUE_PROPS));
				
				pc.load(this.getClass().getClassLoader().getResourceAsStream(
						TORQUE_PROPS));
				Torque.init(pc);

			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TorqueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
