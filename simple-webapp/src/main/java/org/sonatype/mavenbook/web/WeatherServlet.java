package org.sonatype.mavenbook.web;

import java.io.*;
import javax.servlet.*;                                                         
import javax.servlet.http.*;

import org.sonatype.mavenbook.weather.WeatherService;

public class WeatherServlet extends HttpServlet {
    private static final long serialVersionUID = -2655552127196241026L;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {
        String zip = request.getParameter("zip" );
        WeatherService weatherService = new WeatherService();
        PrintWriter out = response.getWriter();
        try {
            out.println( weatherService.retrieveForecast( zip ) );
        } catch( Exception e ) {
            out.println( "Error Retrieving Forecast: " + e.getMessage() );
        }
        out.flush();
        out.close();
    }
}
