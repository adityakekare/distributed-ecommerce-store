# The main server application of the E-commerce app

## Project Description:
This is an e-commerce order transaction website created using Spring Boot. A user can look at the
available products, and place an order. The buyer can call to create the product and update the
products on this website.

One use case is for multiple clients to look and place an order here while keeping the
system consistent. The basic flow of this use case is when a client places an order, the client
sends it to the server coordinator, and the coordinator will perform the Paxos algorithm and
communicate with the servers to reach a consensus. Once it comes to its decision, the server
sends back a confirmation to the client, then the client’s order is placed.
Here, multiple clients are accessing the servers and multiple servers are manipulating the
requests, They each have a copy of the database, and consistency is ensured across all servers.

Some Methods that the clients can call:
1) getAllProducts() to get an overview of available products.
2) createOrder(Map<Product, Quantity>) to create an order.
3) createProduct() allows the seller to update the product information on the servers


#### The JAR file is present in the target directory

### How to run:
```
cd target
java -jar demo-0.0.1-SNAPSHOT.jar --server.port=7788
```

### To run multiple servers, run multiple instances with different ports

### Coordinator App: [Link](https://github.com/adityakekare/distributed-ecommerce-coordinator)
