package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Максим", "Петров", "mpetrov@mail.ru");
      User user2 = new User("Анна", "Иванова", "aivanova@mail.ru");
      User user3 = new User("Виктор", "Королев", "vkorolev@mail.ru");
      User user4 = new User("Ольга", "Семенова", "osemenova@mail.ru");

      user1.setCar(new Car("Ford", 123));
      user2.setCar(new Car("BMW", 5));
      user3.setCar(new Car("KIA", 14));
      user4.setCar(new Car("Opel", 3020));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }


      System.out.println(userService.getUserByCar(new Car("Opel", 3020)));
      context.close();
   }
}
