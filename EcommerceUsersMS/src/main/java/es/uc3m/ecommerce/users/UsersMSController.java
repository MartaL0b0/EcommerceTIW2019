package es.uc3m.ecommerce.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.uc3m.ecommerce.users.model.*;

@Controller
@CrossOrigin
public class UsersMSController {
	
	@Autowired
	PurchasDAO daopurchas;
	
	@Autowired
	AppuserDAO appuserDAO;
	
	// Buscar todos los pedidos de un usuario
	@RequestMapping(method = RequestMethod.GET, value="/users/purchases")
	public ResponseEntity<List<Integer>> findAllConfirmationCode(@RequestParam(value = "userId") int userId) {
		List<Integer> allConfirmation = daopurchas.findAllConfirmationCode(userId);
		return new ResponseEntity<>(allConfirmation, HttpStatus.OK);
	}
	
	// Buscar todos los productos de un pedido
	@RequestMapping(method = RequestMethod.GET, value="/users/purchases/{confirmationCode}")
	public ResponseEntity<List<Purchas>> findByConfirmationCode(@PathVariable int confirmationCode) {
		List<Purchas> purchases = daopurchas.findByConfirmationCode(confirmationCode);
		return new ResponseEntity<>(purchases, HttpStatus.OK);
	}
		
	// Insertar un pedido
	@RequestMapping(method = RequestMethod.POST, value="/users/purchases")
	public ResponseEntity<Purchas> insertPurchase(@RequestBody Purchas purchase) {
		Purchas newPurchase = daopurchas.save(purchase);
		return new ResponseEntity<>(newPurchase, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/users/buyers")
	public ResponseEntity<List<Appuser>> findAllBuyers(){
		ResponseEntity<List<Appuser>> response;
		List<Appuser> buyers = appuserDAO.findByUserRoleAndIsDeleted(2, 0);
			
		if (buyers == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(buyers, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/users/sellers")
	public ResponseEntity<List<Appuser>> findAllSellers(){
		ResponseEntity<List<Appuser>> response;
		List<Appuser> sellers = appuserDAO.findByUserRoleAndIsDeleted(1, 0);
			
		if (sellers == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(sellers, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/users")
	public ResponseEntity<List<Appuser>> findAllUsers(){
		ResponseEntity<List<Appuser>> response;
		List<Appuser> sellers = appuserDAO.findByIsDeleted(0);
			
		if (sellers == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(sellers, HttpStatus.OK);
		}
		return response;
	}
	
}
