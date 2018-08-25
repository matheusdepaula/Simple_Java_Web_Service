package com.turmaA2018.servlets;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.turmaA2018.domain.Task;

/**
 * Servlet implementation class TasksServlet
 */
@WebServlet(name="task", urlPatterns="/tasks/*")
public class TasksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final List<Task> taskList = new ArrayList<Task>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TasksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			
			doGetAll(request, response);
			
		} else {
			
			doGetById(request, response, pathInfo);
		}
	}

	protected void doGetById(HttpServletRequest request, HttpServletResponse response, String pathInfo) throws ServletException, IOException {
		
		String[] splits = pathInfo.split("/");
		if (splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String taskId = splits[1];
		Task task = findTaskById(taskId);
		
		if (task == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			Gson gson = new Gson();
			
			String json = gson.toJson(task);
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write(json);
		}
	}
	
	private Task findTaskById(String taskId) {
		
		Optional<Task> result = taskList.stream()
				.filter(t -> t.getId().equals(taskId))
				.findFirst();
		
		if (!result.isPresent()) 
			return null;
		
		return result.get();
	}

	protected void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
		
		Gson gson = new Gson();
		String json = gson.toJson(taskList);
		
		response.getWriter().write(json);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new Gson();
		Task task = gson.fromJson(request.getReader(), Task.class);//Get reader pega oq vem no corpo da requisição
		
		taskList.add(task);
		
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().write(gson.toJson(task));
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
