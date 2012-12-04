package com.epam.task5.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * This is main app controller
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class XsltController extends HttpServlet {
    private static final long serialVersionUID = -8219522518629860330L;

    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	process(request, response);
    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	process(request, response);
    }

    private void process(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	
	String xmlFilePath = request.getSession().getServletContext().getRealPath("");
	File xmlFile = new File(xmlFilePath + "/xml/products.xml");
	File xsltFile = new File(xmlFilePath + "/xsl/subcategories.xsl");

	Source xmlSource = new StreamSource(xmlFile);
	Source xsltSource = new StreamSource(xsltFile);
	Result result = new StreamResult(response.getWriter());
	
	// create an instance of TransformerFactory
	TransformerFactory transFact = javax.xml.transform.TransformerFactory
		.newInstance();
	Transformer transformer;
	try {
	    transformer = transFact.newTransformer(xsltSource);
	    transformer.transform(xmlSource, result);
	} catch (TransformerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
