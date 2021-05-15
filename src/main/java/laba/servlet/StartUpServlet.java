package laba.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import laba.classes.Ad;
import laba.classes.AdList;
import laba.classes.AdListHelper;
import laba.classes.UserList;
import laba.classes.UserListHelper;

/**
 * Servlet implementation class StartUpServlet
 */
@WebServlet("/StartUpServlet")
public class StartUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		UserList userList = 
				UserListHelper.readUserList(getServletContext());

		getServletContext().setAttribute("users", userList);

		AdList adList = AdListHelper.readAdList(getServletContext());

		getServletContext().setAttribute("ads", adList);
		for (Ad ad: adList.getAds()) {

			ad.setAuthor(userList.findUser(ad.getAuthorId()));

			ad.setLastModified(ad.getLastModified());
		}
	}

}
