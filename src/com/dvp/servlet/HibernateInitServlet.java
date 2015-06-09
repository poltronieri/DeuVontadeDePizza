package com.dvp.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dvp.util.HibernateUtil;

/**
 * Servlet implementation class HibernateInitServlet
 */
public class HibernateInitServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HibernateInitServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		HibernateUtil.getSessionFactory();
		
/*		Usuario usuario = new Usuario("seu zé", "123", "abc@abc", "rua um dois", "1145333473");
		
		UsuarioDAO dao = DAOFactory.getUsuarioDAO();
		dao.salvar(usuario);*/
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		AlunoDAO dao = DAOFactory.getAlunoDAO();
//		if(req.getParameter("metodo").equals("novo")){
//			System.out.println("Salvando aluno...");
//			Aluno a = new Aluno(req.getParameter("nome"), Integer.parseInt(req.getParameter("idade")));
//			System.out.println(a);
//			dao.salvar(a);
//			resp.getWriter().write("Aluno: "+a.toString());
//			resp.getWriter().flush();
//		}else if(req.getParameter("metodo").equals("listar")){
//			List<Aluno> lista = dao.listar();
//			resp.getWriter().write("<html>");
//			for (Aluno a : lista) {
//				resp.getWriter().write(a.getCodigo()+" - "+a.getNome()+" - "+a.getIdade()+"<br/>");
//			}
//			resp.getWriter().write("</html>");
//			resp.getWriter().flush();
//		}
//	}

}
