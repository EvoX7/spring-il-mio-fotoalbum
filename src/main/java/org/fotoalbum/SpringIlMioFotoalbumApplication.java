package org.fotoalbum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.fotoalbum.pojo.Category;
import org.fotoalbum.pojo.Photo;
import org.fotoalbum.pojo.Role;
import org.fotoalbum.pojo.User;
import org.fotoalbum.service.CategoryService;
import org.fotoalbum.service.PhotoService;
import org.fotoalbum.service.RoleService;
import org.fotoalbum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Categories

		Category cat1 = new Category("Around the world");
		Category cat2 = new Category("Sea views");
		Category cat3 = new Category("Hot Summer");
		Category cat4 = new Category("Discover cities");
		Category cat5 = new Category("Mountain Call");
		Category cat6 = new Category("Wilderness");

//		Discover 
		List<Category> catList1 = new ArrayList<>();

		catList1.add(cat1);
		catList1.add(cat4);

//		Hot
		List<Category> catList2 = new ArrayList<>();

		catList2.add(cat2);
		catList2.add(cat3);

		List<Category> catList3 = new ArrayList<>();

		catList3.add(cat5);
		catList3.add(cat6);

		categoryService.save(cat1);
		categoryService.save(cat2);
		categoryService.save(cat3);
		categoryService.save(cat4);
		categoryService.save(cat5);
		categoryService.save(cat6);

//		Photos

		Photo photo1 = new Photo("Mexico", "Colorful houses in the desert",
				"https://images.unsplash.com/photo-1671483331012-86fa989701d1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
				"travel", true, catList2);

		System.err.println(photo1);

		Photo photo2 = new Photo("Into the woods", "Traveler hiking in the woods",
				"https://images.unsplash.com/photo-1642996719565-a4ec87362d61?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
				"hiking", true, catList3);

		System.err.println(photo2);

		Photo photo3 = new Photo("Temple", "A view of the temple with his columns",
				"https://images.unsplash.com/photo-1670967782319-87f9a520ca40?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=780&q=80",
				"travel", true, catList2);

		System.err.println(photo3);

		Photo photo4 = new Photo("Trevi's Fountain", "A beautiful view of the Fountain in Rome",
				"https://images.unsplash.com/photo-1670791737578-cc9b605ae2eb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDE1fEZ6bzN6dU9ITjZ3fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=600&q=60",
				"city", true, catList1);

		System.err.println(photo4);

		Photo photo5 = new Photo("Capri", "A fantastic view from Capri",
				"https://images.unsplash.com/photo-1670882108396-bc8caf7bfec1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
				"sea", true, catList2);

		System.err.println(photo5);

		Photo photo6 = new Photo("Al Maha", "Street view of desert town",
				"https://images.unsplash.com/photo-1670443147672-0e2df1121bb2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
				"desert", true, catList2);

		System.err.println(photo6);

		Photo photo7 = new Photo("Sidney", "Sea view of Sidney",
				"https://images.unsplash.com/photo-1666994853224-dcb2a2918da3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=627&q=80",
				"city", true, catList1);

		System.err.println(photo7);

		Photo photo8 = new Photo("Mykonos", "Sea view of the Greek island",
				"https://images.unsplash.com/photo-1660896591252-42540ae0b57e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=872&q=80",
				"sea", true, catList2);

		System.err.println(photo8);

		photoService.save(photo1);
		photoService.save(photo2);
		photoService.save(photo3);
		photoService.save(photo4);
		photoService.save(photo5);
		photoService.save(photo6);
		photoService.save(photo7);
		photoService.save(photo8);
		
		
		

//		Access roles 

		Role adminRole = new Role("ADMIN");

		roleService.save(adminRole);

		User adminUser = new User("admin", "{noop}7890", adminRole);

		Set<Role> userAdminRoles = new HashSet<>();
		userAdminRoles.add(adminRole);

		userService.save(adminUser);

		System.err.println("BUILD OK");

	}
}
