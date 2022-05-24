package com.vieso.controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public interface BlpInter {
	String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
