package laba.classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import jakarta.servlet.ServletContext;


public class UserListHelper {

	private static final String USERS_FILENAME = "WEB-INF/users.dat";

	private static String USERS_PATH = null;

	public static UserList readUserList(ServletContext context) {

		try {
			 USERS_PATH = context.getRealPath(USERS_FILENAME); 
			 
			// Создаем объектный поток ввода на основе файлового потока
			ObjectInputStream in = new ObjectInputStream(new
			FileInputStream(USERS_PATH));
			
			return (UserList)in.readObject();

			} catch (Exception e) {

				return new UserList();
			} 
		}
	

	public static void saveUserList(UserList users) {

		synchronized (users) {
			try {

				ObjectOutputStream out = new ObjectOutputStream(new
						FileOutputStream(USERS_PATH));

				out.writeObject(users);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}