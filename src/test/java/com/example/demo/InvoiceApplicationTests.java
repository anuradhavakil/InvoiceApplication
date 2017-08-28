package com.example.demo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class InvoiceApplicationTests {


		public static void main(String[] args) {

			try {

				Client client = Client.create();

				WebResource webResource = client
						.resource("http://localhost:8080/invoice/");

				String input = "{\"Amount\":\"22\"\"}";

				ClientResponse response = webResource.type("application/json")
						.post(ClientResponse.class, input);

				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);

			} catch (Exception e) {

				System.out.println("Exception caught in invoice application test"+e.getMessage()+e);

			}

		}

}
