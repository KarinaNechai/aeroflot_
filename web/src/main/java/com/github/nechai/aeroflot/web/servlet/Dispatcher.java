package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.service.impl.PlaneService;

import javax.servlet.annotation.WebServlet;

@WebServlet("/dispatcher")
public class Dispatcher {
    private PlaneService planeService = (PlaneService) PlaneService.getInstance();

}
