package personaltrainer.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		SessionFactory sf = (SessionFactory) sce.getServletContext().getAttribute("SessionFactory");
		sf.close();
	}
 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		URL url = HibernateServletContextListener.class.getResource("/hibernate.cfg.xml");
//		Configuration config = new Configuration();
//		config.configure(url);
//		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//						.applySettings(config.getProperties()).build();
//		SessionFactory sf = config.buildSessionFactory(serviceRegistry);
//		sce.getServletContext().setAttribute("SessionFactory", sf);
		
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure( "hibernate.cfg.xml" ).build();
		Metadata metadata = new MetadataSources( standardRegistry ).getMetadataBuilder().build();
		
		
		SessionFactory sf = metadata.getSessionFactoryBuilder().build();
		sce.getServletContext().setAttribute("SessionFactory", sf);
	}

}