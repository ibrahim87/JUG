//import javax.ejb.EJB;
//import javax.inject.Inject;
//
//
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.jboss.weld.context.ejb.Ejb;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import edu.app.business.GreeterBean;
//
//@RunWith(Arquillian.class)
//public class GreeterTest {
//
//    @Deployment
//    public static JavaArchive createDeployment() {
//    	JavaArchive jar= ShrinkWrap.create(JavaArchive.class)
//          //  .addClass(GreeterTest.class)
//           .addClass(GreeterBean.class)
//            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    	
//    	System.out.println(jar.toString(true));
//    	
//    	return jar;
//    }
//
//    @EJB
//    GreeterBean greeter;
//
//   @Test
//    public void should_create_greeting() {
//	   
//	   System.out.println(greeter);
//	   
//        Assert.assertEquals("hello",  greeter.sayHello());
//          
//     
//    }
//}
