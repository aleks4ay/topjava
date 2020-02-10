package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.dao.MealsDaoMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealsDao mealsDao = new MealsDaoMemory();
    String action = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        System.out.println("doGet action=" + request.getParameter("action"));
        action = "";
        if ( (action = request.getParameter("action")) != null) {
            if (request.getParameter("action").equals("delete")) {
                mealsDao.delete(Integer.parseInt(request.getParameter("id")));
            }
            if (request.getParameter("action").equals("update")) {
                Meal mealToEdit = mealsDao.readOne(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("mealUpdate", mealToEdit);
            }
        }

        request.setAttribute("mealsTo", MealsUtil.filteredByStreams(mealsDao.read(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (action.equals("update")) {
            mealsDao.update(new Meal(
                    Integer.parseInt(req.getParameter("id")),
                    LocalDateTime.parse(req.getParameter("dateTime")),
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("calories")))
            );
        }
        else {  // action.equals("insert")
            mealsDao.create(new Meal(
                    MealsUtil.getNextId(),
                    LocalDateTime.parse(req.getParameter("dateTime")),
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("calories")))
            );
        }

        req.setAttribute("mealsTo", MealsUtil.filteredByStreams(mealsDao.read(), LocalTime.MIN, LocalTime.MAX, 2000));
        req.setAttribute("action", "hide");
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}