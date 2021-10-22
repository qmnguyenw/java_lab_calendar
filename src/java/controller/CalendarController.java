/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "CalendarController", urlPatterns = {"/CalendarController"})
public class CalendarController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int year = -1;
            int month = -1;
            ArrayList listDay = new ArrayList();
            String[] monthArray = new String[]{
                "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"
            };
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date;
            
            Calendar cal = Calendar.getInstance();
            
            try {
                year = Integer.parseInt(request.getParameter("year").trim());
                month = Integer.parseInt(request.getParameter("month").trim());
                // if invalid parameter info 
                if (month < 1 || month > 12 || year < 1921 || year > 2119) {
                    throw new NumberFormatException();
                }
            } catch (NullPointerException e) {
                
                // if url not contains parameters - year, month, create a current date
                date = new Date();
                cal.setTime(date);
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH) + 1;
                
                HttpSession s = request.getSession();
                s.setAttribute("currMonth", month);
                s.setAttribute("currYear", year);
                s.setAttribute("currDay", cal.get(Calendar.DAY_OF_MONTH));
            } catch (NumberFormatException e) {
                request.setAttribute("err", "Month or year does not exist");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            date = sdf.parse("01-" + month + "-" + year);
            cal.setTime(date);
            int firstDay = cal.get(Calendar.DAY_OF_WEEK);
//            System.out.println(sdf.format(cal.getTime()));
            
            int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            
//            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
            
            // set the first blank dates in calendar
            for (int i = 1; i < firstDay; i++) {
                listDay.add("0");
            }
            // set dates in calendar
            for (int i = 1; i <= maxDay; i++) {
                listDay.add(i);
            }
            // set the last blank dates in calendar
            int lastDate = ((maxDay - 1) % 7 + firstDay) % 7;
            for (int i = lastDate; i < 7; i++) {
                listDay.add("0");
            }
            // get number of week 
            int numberOfWeek = listDay.size() / 7;
            
            request.setAttribute("year", year);
            request.setAttribute("month", month);
            request.setAttribute("monthName", monthArray[month - 1]);
            request.setAttribute("listDay", listDay);
            request.setAttribute("numberOfWeek", numberOfWeek);
            request.getRequestDispatcher("calendar.jsp").forward(request, response);   
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
