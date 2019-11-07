package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

public class InsertProductHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductManager im = new ProductManager();
		Product product = new Product();
		// Getting the image (file)
		Part filePart = request.getPart("fileToUpload");
		// Getting the product name
		String productName = request.getParameter("name");
		String shortDescription = request.getParameter("shortDes");
		String longDescription = request.getParameter("longDes");

	    byte[] data = new byte[(int) filePart.getSize()];
	    filePart.getInputStream().read(data, 0, data.length);
	    // i2.setTitulo(request.getParameter("titulo"));
		
		product.setProductName(productName);
		product.setShortDesc(shortDescription);
		product.setLongDesc(longDescription);
		product.setCategory("1");
		product.setSubcategory(2);
		product.setProductPicture(data);
		product.setSellerId(4);
		
		try {
			im.create(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// response.sendRedirect("controlador");
		return "insertProduct.jsp";
	}

}
