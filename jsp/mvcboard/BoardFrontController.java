package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardFrontController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		ActionForward forward = null;
		Action action = null;

		System.out.println("BoardFrontController.doProcess(): requestURI|" + requestURI);
		System.out.println("BoardFrontController.doProcess(): contextPath|" + contextPath);
		System.out.println("BoardFrontController.doProcess(): command|" + command);

		// 1.
		if (command.equals("/BoardWrite.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_write.jsp");
		} 
		else if (command.equals("/BoardAddAction.bo")) {
			action = new BoardAddAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		//9.
		} else if (command.equals("/BoardList.bo")) {
			action = new BoardListAction();

			try {

				forward = action.execute(request, response);

			} catch (Exception e) {   
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardModifyAction.bo")) {
			
			
			
			action = new BoardModifyAction();
			
			try {
				forward = action.execute(request, response);

			}catch(Exception e) {
				
				e.printStackTrace();
			}
		}
		
		
		//10.
		else if(command.equals("/BoardDetailAction.bo")) {
			action = new BoardDetailAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/BoardModify.bo")) {
			action = new BoardModifyView();
			
			try {
					forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// *********** 삭제 개인 구현
		else if(command.equals("/BoardDelete.bo")) {
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_delete.jsp");
			
		}
		else if(command.equals("/BoardDeleteAction.bo")) {
			action = new BoardDeleteAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("BoardDeleteAction error : " + e);
			}
			
		}
		else if(command.equals("/BoardReplyView.bo")) {
		
			action = new BoardReplyView();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/BoardReplyAction.bo")) {
			
			action = new BoardReplyAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		

		// Action interface의 execute 실행 후 결과로 받은 forward 처리
		if (forward != null) {
			if (forward.isRedirect()) {
				// 단순 페이지 Redirection
				response.sendRedirect(forward.getPath()); // insert 시 List로 이동
			} else {
				// Action이 request에 Attribute로 저장한 데이터를 활용하여 View 페이지를 작성한다
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				
				dispatcher.forward(request, response);
			}
		}
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}