package com.gamestore.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.entity.Customer;
import com.gamestore.entity.Game;
import com.gamestore.entity.GameOrder;
import com.gamestore.entity.OrderDetail;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.APICallPreHandler;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaymentServices {
	private static final String CLIENT_ID = "AU5kW3P9JkOonXC_WG_P74jJWG0HYVWqIQMURXpFA-28jI5jMqPzi8HoON1dJv0w8DqFYSPgQgst0M3A";
	private static final String CLIENT_SECRET = "ECyQOd2BbfgB0rGob0v_LNURqZ22lsm9Vd3ajbdClyQxvbBNlp1mr9JSguSBNU43qHLO_Nu0ma17pr5s";
	private String mode = "sandbox";
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public PaymentServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	public void authorizePayment(GameOrder order) throws ServletException, IOException {
		// VALIDATE INFO
		//get payer info
		Payer payer = getPayerInformation(order);
		
		//get redirect urls
		RedirectUrls redirectUrls = getRedirectURLs();
		
		// get amount details - moved to trnsaction info
		
		//shipping address (recipient info) - transactiom
		
		//get transaction details
		List <Transaction> transactions = getTransactionInformation(order);
		
		//request payment
		Payment requestPayment = new Payment();
		requestPayment.setPayer(payer)
					  .setRedirectUrls(redirectUrls)
					  .setIntent("authorize")
					  .setTransactions(transactions);
		
		System.out.println("--- REQUEST PAYMENT ---");
		System.out.println(requestPayment);
		
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode);
		
		try {
			Payment authorizedPayment = requestPayment.create(apiContext);
			System.out.println("--- AUTHORIZED PAYMENT ---");
			System.out.println(authorizedPayment);
			
			String approvalURL = getApprovalURL(authorizedPayment);
			
			response.sendRedirect(approvalURL);
			
		} catch (PayPalRESTException e) {
			e.printStackTrace();
			throw new ServletException("Error in authorizing payment!");
		}
		
		//get approval link
		
		//redirect to paypals paymnt page
		
	}
	
	private String getApprovalURL(Payment authorizedPayment) {
		String approvalURL = null;
		
		List<Links> links = authorizedPayment.getLinks();
		
		for(Links link : links) {
			if(link.getRel().equalsIgnoreCase("approval_url")) {
				approvalURL = link.getHref();
				break;
			}
		}
		
		return approvalURL;
	}

	private List<Transaction> getTransactionInformation(GameOrder order) {
		Transaction transaction = new Transaction();
		transaction.setDescription("Gamestores orders");
		Amount amount = getAmountDetails(order);
		transaction.setAmount(amount);
		
		ItemList itemList = new ItemList();
		ShippingAddress shippingAddress = getRecipientInformation(order);
		itemList.setShippingAddress(shippingAddress);
		
		List<Item> paypalItems = new ArrayList<>();
		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			Game game = orderDetail.getGame();
			Integer quantity = orderDetail.getQuantity();
			
			Item paypalItem = new Item();
			paypalItem.setCurrency("INR")
					  .setName(game.getTitle())
					  .setQuantity(String.valueOf(quantity))
					  .setPrice(String.format("%.2f" , game.getPrice()));
			
			paypalItems.add(paypalItem);
		}
		
		itemList.setItems(paypalItems);
		transaction.setItemList(itemList);
		
		List<Transaction> listTransaction = new ArrayList<>();
		listTransaction.add(transaction);
		
		return listTransaction;
		
	}
	
	private ShippingAddress getRecipientInformation(GameOrder order) {
		ShippingAddress shippingAddress = new ShippingAddress();
		String recipientName = order.getFirstname() + " " + order.getLastname();
		shippingAddress.setRecipientName(recipientName)
					   .setPhone(order.getPhone())
					   .setLine1(order.getAddressLine1())
					   .setLine2(order.getAddressLine2())
					   .setCity(order.getCity())
					   .setState(order.getState())
					   .setCountryCode(order.getCountry())
					   .setPostalCode(order.getZipcode());
		
		return shippingAddress;
	}
	
	private Payer getPayerInformation(GameOrder order) {
		//get payer info
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		
		Customer customer = order.getCustomer();
		
		PayerInfo payerInfo=new PayerInfo();
		payerInfo.setFirstName(customer.getFirstname());
		payerInfo.setLastName(customer.getLastname());
		payerInfo.setEmail(customer.getEmail());
		
		payer.setPayerInfo(payerInfo);
		
		return payer;
	}
	
	private RedirectUrls getRedirectURLs() {
		// get redirect urls
		String requestURL = request.getRequestURL().toString();
		String requestURI = request.getRequestURI();
		String baseURL = requestURL.replace(requestURI, "").concat(request.getContextPath());

		RedirectUrls redirectUrls = new RedirectUrls();
		String cancelURL = baseURL.concat("/view_cart");
		String returnURL = baseURL.concat("/review_payment");

		System.out.println("Return url: "+ requestURL);
		System.out.println("CAncel url: "+ cancelURL);
		
		redirectUrls.setCancelUrl(cancelURL);
		redirectUrls.setReturnUrl(returnURL);
		
		return redirectUrls;
	}
	
	private Amount getAmountDetails(GameOrder order) {
		// get amount details
		Details details = new Details();
		details.setShipping(String.format("%.2f" , order.getShippingFee()));
		details.setTax(String.format("%.2f" , order.getTax()));
		details.setSubtotal(String.format("%.2f" , order.getSubtotal()));

		Amount amount = new Amount();
		amount.setCurrency("INR");
		amount.setDetails(details);
		amount.setTotal(String.format("%.2f" , order.getTotal()));
		
		return amount;
	}

	public void reviewPayment() throws ServletException {
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("payerId");
		
		if(payerId == null || paymentId == null) {
			throw new ServletException("Error in displaying payment review!");
		}
		
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode);
		
		try {
			Payment payment = Payment.get(apiContext, paymentId);
			
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
			
			request.setAttribute("payer", payerInfo);
			request.setAttribute("recipient", shippingAddress);
			request.setAttribute("transaction", transaction);
			
			String reviewPage = "frontend/review_payment.jsp?paymentId=" + paymentId + "&payerId=" + payerId;
			request.getRequestDispatcher(reviewPage).forward(request, response);
			
		} catch (PayPalRESTException | IOException e) {
			e.printStackTrace();
			throw new ServletException("Error in getting payment details from paypal!");
		}
		
	}

	public Payment executePayment() throws PayPalRESTException {
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("payerId");
		
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		
		Payment payment = new Payment().setId(paymentId);
		
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode);
		
		return payment.execute(apiContext, paymentExecution);
	}
}
