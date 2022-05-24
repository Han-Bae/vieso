package com.vieso.controller.phg;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.vieso.controller.BlpInter;

public class Write implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view ="/todo/write";
		return view;
	}

}