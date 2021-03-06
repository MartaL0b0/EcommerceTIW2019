<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.commons.codec.binary.StringUtils" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>    
<!DOCTYPE html>
<html lang="en">
<head>
<title>A&ntilde;adir producto</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/add_product_styles.css">
<link rel="stylesheet" type="text/css" href="styles/contact_responsive.css">

</head>

<body>

<div class="super_container">
	
	<!-- Header -->
	<%@ include file="header.jsp" %>

	<!-- Contact Form -->

	<div class="contact_form">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 offset-lg-1">
					<div class="contact_form_container">
						<div class="contact_form_title">A&ntilde;adir producto</div>

						<form action="insert_product.html" id="contact_form" method="post" enctype="multipart/form-data">
							
							<div class="row_container">
								<div class="row_item">
									<h4 class="input_title">Nombre</h4>
									<input type="text" name="product_name"  class="contact_form_name input_field" placeholder="Introduce el nombre del producto" required="required">
								</div>
								<div class="row_item">
									<h4 class="input_title">Imagen</h4>
									<input type="file" id="fileToUpload" name ="fileToUpload"  class="contact_form_name input_field" required="required">
								</div>		
								<div style="flex: 0.2; margin: 0 30px;">
									<h4 class="input_title">Precio</h4>
									<input type="text" name ="product_price" class="contact_form_name input_field" placeholder="Precio &euro;" required="required">
								</div>							
							</div>
							
							<h4 style="padding-bottom: 10px;padding-top:10px">Descripcion</h4>
							<div class="contact_form_inputs d-flex flex-md-row flex-column justify-content-between align-items-between">
								<textarea class="input_field" name="pShortDesc" rows="2" placeholder="Introduce la descripci�n del producto"></textarea>
							</div>
							
							<h4 style="padding-bottom: 10px">Descripcion detallada</h4>
							<div class="contact_form_inputs d-flex flex-md-row flex-column justify-content-between align-items-between">
								<textarea class="input_field" name="pLongDesc" rows="4" placeholder="Introduce la descripci�n detallada del producto"></textarea>
							</div>
							
							<div class="row_container clearfix" style="z-index: 1000;margin-bottom: 25px;width:500px;">
							
								<div class="row_item">
									<h4 class="input_title">Stock</h4>
									<input type="text" name="product_stock"  class="contact_form_name input_field" placeholder="Introduce el stock" required="required">
								</div>
													
							</div>
							
							
							<div class="row_container clearfix" style="z-index: 1000;">
							
								<div class="row_item">
									<h4>Categoria</h4>
									<ul class="product_size">
										<li>
											<div class="size_mark_container">
											

												<input style="margin-left:20px;width:250px;padding-left:50px" type="text" name="category" id="selected_categ"  value = "Categor�a">

												
											</div>
											<div class="size_dropdown_button"><i class="fas fa-chevron-down"></i></div>

											<ul class="color_size">

												<li><input type="button"  class="size_mark" id="categ1" value="Partes de arriba" ></li>
												<li><input type="button"  class="size_mark" id="categ2" value="Partes de abajo" ></li>
												<li><input type="button"  class="size_mark" id="categ3" value="Prendas de abrigo"></li>
												<li><input type="button"  class="size_mark" id="categ4" value="Calzado"></li>
												<li><input type="button"  class="size_mark" id="categ5" value="Accesorios"></li>

											</ul>
										</li>
									</ul>
								</div>
								<div class="row_item">

									<h4>Subcategor�a</h4>
									<ul class="product_subcateg selectSubcategory">
										<li class="selectSubcategory">
											
											<div class="size_mark_container selectSubcategory">
											

												<input style="margin-left:20px;width:250px;padding-left:50px" type="text" name="subcategory" id="selected_subcateg"  value = "Subcategor�a">

											
											</div>
											
											<div class="size_dropdown_button"><i class="fas fa-chevron-down"></i></div>
											
											<ul class="color_size">
												<li><input type="button"  class="size_mark sub arriba" id="subcateg1" value="Camisetas de manga corta" ></li>
												<li><input type="button"  class="size_mark sub arriba" id="subcateg2" value="Camisetas de manga larga"></li>
												<li><input type="button"  class="size_mark sub arriba" id="subcateg3" value="Camisas y blusas"></li>
												<li><input type="button"  class="size_mark sub abajo" id="subcateg4" value="Pantalones cortos"></li>
												<li><input type="button"  class="size_mark sub abajo" id="subcateg5" value="Pantalones largos"></li>
												<li><input type="button"  class="size_mark sub abajo" id="subcateg6" value="Faldas y vestidos"></li>
												<li><input type="button"  class="size_mark sub abrigo" id="subcateg7" value="Chaquetas"></li>
												<li><input type="button"  class="size_mark sub abrigo" id="subcateg8" value="Jerseys y Sueters"></li>
												<li><input type="button"  class="size_mark sub abrigo" id="subcateg9" value="Abrigos y chaquetones"></li>
												<li><input type="button"  class="size_mark sub calzado" id="subcateg10" value="Sandalias y chanclas"></li>
												<li><input type="button"  class="size_mark sub calzado" id="subcateg11" value="Zapatos de vestir y de tac�n"></li>
												<li><input type="button"  class="size_mark sub calzado" id="subcateg12" value="Botas y botines"></li>
												<li><input type="button"  class="size_mark sub acc" id="subcateg13" value="Bufandas y fulares"></li>
												<li><input type="button"  class="size_mark sub acc" id="subcateg14" value="Gorros y sombreros"></li>
												<li><input type="button"  class="size_mark sub acc" id="subcateg15" value="Cinturones y bolsos"></li>
											</ul>
										</li>
									</ul>
								</div>		
													
							</div>
							
							
							<div class="contact_form_button center_horizontal">
								<button type="submit" class="button contact_submit_button">Crear producto</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="panel"></div>
	</div>

	<!-- Newsletter -->

	<div class="newsletter">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="newsletter_container d-flex flex-lg-row flex-column align-items-lg-center align-items-center justify-content-lg-start justify-content-center">
						<div class="newsletter_title_container">
							<div class="newsletter_icon"><img src="images/send.png" alt=""></div>
							<div class="newsletter_title">Suscr�bete a nuestra newsletter</div>
							<div class="newsletter_text"><p>...y obt�n un cup�n descuento del 20% para la 1� compra.</p></div>
						</div>
						<div class="newsletter_content clearfix">
							<form action="#" class="newsletter_form">
								<input type="email" class="newsletter_input" required="required" placeholder="Introduce tu correo electr�nico">
								<button class="newsletter_button">Suscribirse</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer --> 
	<%@ include file="footer.jsp" %>
	
</div>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="styles/bootstrap4/popper.js"></script>
<script src="styles/bootstrap4/bootstrap.min.js"></script>
<script src="plugins/greensock/TweenMax.min.js"></script>
<script src="plugins/greensock/TimelineMax.min.js"></script>
<script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="plugins/greensock/animation.gsap.min.js"></script>
<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCIwF204lFZg1y4kPSIhKaHEXMLYxxuMhA"></script>
<script src="js/add_product.js"></script>
</body>

</html>

