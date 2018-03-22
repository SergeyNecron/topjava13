package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    List<Meal> meals = MealsUtil.createList();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        int id =0;
        LocalDateTime date = null;
        String description = "";
        int calories = 0;
        // date = LocalDateTime.parse(request.getParameter("date"));
        try {
            id = Integer.parseInt(request.getParameter("id"));
            description = (String) request.getParameter("description");
            calories = Integer.parseInt(request.getParameter("calories"));
        } catch (Exception e) {
            pw.println("<B>Ошибка!</B>");
        }
// если значения не пустые, то добавь в список
        if (id!=0&&!description.equals(null)&&calories!=0) {
            meals.add(id,new Meal(LocalDateTime.of(2015, Month.MAY, 31, 21, 0), description, calories));
            List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
            //pw.println("<B>Оk!</B>");
            request.setAttribute("meals", mealsWithExceeded);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
        }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        response.setContentType("text/html;charset=utf-8");

        List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        request.setAttribute("meals", mealsWithExceeded);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);

/*        //  Для проверки данных без JSP
        PrintWriter pw = response.getWriter();
        pw.println("<B>Моя еда</B>");

        pw.println("<table border=1>");
            pw.println("<tr>");
            pw.println("<td>" + "№" + "</td>");
            pw.println("<td>" + "Дата/Время" + "</td>");
            pw.println("<td>" + "Описание" + "</td>");
            pw.println("<td>" + "Калории" + "</td>");
            pw.println("<td>" + "Превышена" + "</td>");
            pw.println("</tr>");
        try {
            int id = 0;
            for (MealWithExceed gr : mealsWithExceeded) {
                pw.println("<tr>");
                pw.println("<td>" + gr.getId()+ "</td>");
                pw.println("<td>" + gr.getDate()+"/"+gr.getTime() + "</td>");
                pw.println("<td>" + gr.getDescription() + "</td>");
                pw.println("<td>" + gr.getCalories() + "</td>");
                if(gr.isExceed())
                     pw.println("<td>" + "да"  + "</td>");
                else pw.println("<td>" + "нет"  + "</td>");
                pw.println("</tr>");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        pw.println("</table>");*/

    }
}
