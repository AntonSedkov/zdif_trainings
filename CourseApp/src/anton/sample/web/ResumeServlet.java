package anton.sample.web;

import anton.sample.WebAppConfig;
import anton.sample.dao.IStorage;
import anton.sample.exception.StorageException;
import anton.sample.model.Resume;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Sedkov Anton
 * Date: 22.06.2021
 */
public class ResumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Resume resume = Resume.EMPTY_RESUME;
        IStorage storage = WebAppConfig.getInstance().getStorage();

        switch (action) {
            case "delete":
                try {
                    storage.delete(uuid);
                } catch (StorageException e) {
                    System.out.println(e);
                }
                response.sendRedirect("list");
                return;
            case "create":
                break;
            case "view", "edit":
                try {
                    resume = storage.load(uuid);
                } catch (StorageException e) {
                    System.out.println(e);
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
