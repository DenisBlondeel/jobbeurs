package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.RoleEnum;
import domain.model.Spot;
import domain.model.User;

public class ShowOptionsHandler extends RequestHandler {
	
	User user;

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spotID = request.getParameter("id");
		request.setAttribute("spotnr", spotID);
		
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("user");
		
		if(service.getSpotFromUser(user.getUserID()) != null){
			request.setAttribute("errors", "Er werd al een plaats gereserveerd voor " + user.getCompanyName() + ".");
			response.sendRedirect("Controller");
		}

		if (user!=null) {
			request.setAttribute("userid", user.getUserID());
			Spot spot = service.getSpotFromUser(user.getUserID());
			if (spot != null) {
				request.setAttribute("mine", spot.getSpotID());
			}
		}
		request.setAttribute("bezet", service.getOccupiedSpots());
		request.getRequestDispatcher("spotoptions.jsp").forward(request, response);
		
	}
		@Override
		public RoleEnum[] getAccesList() {
			return new RoleEnum[]{RoleEnum.COMPANY, RoleEnum.ADMIN};
			
			
		/*else
		{
			redirect(request, response, "index");
			
		}
		if(service.getSpot(spot).getUser() == null)redirect(request, response, "index");
		if(service.getSpot(spot).getUserID().equals(user.getUserID()))
		{
			redirect(request, response, "index");
		}
		else{
		}
	}*/
	
	/*private void redirect(HttpServletRequest request, HttpServletResponse response, String dest)
	{
		if(user != null) request.setAttribute("mine", service.getSpotFromUser(user.getUserID()).getSpotID());
		try
		{
			request.getRequestDispatcher(dest + ".jsp").forward(request, response);
			} catch (IOException | ServletException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
}
