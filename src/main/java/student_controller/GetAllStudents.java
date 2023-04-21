package student_controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import student_dao.StudentDao;
import student_dto.Admin;
import student_dto.Student;

@WebServlet("/viewStudents")
public class GetAllStudents extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		
		if(admin!=null) {
		
		List<Student> students=new StudentDao().GetAllStudents(admin);
		
		req.setAttribute("students", students);
		
		req.getRequestDispatcher("ViewStudents.jsp").forward(req, resp);
	}else {
		resp.sendRedirect("AdminLogin.jsp");
	}

}
}
