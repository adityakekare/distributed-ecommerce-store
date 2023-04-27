import org.springframework.web.client.RestTemplate;

public class Server{



    public static void main(String[] args){
        getEmployees();
    }

    public static void getAllProducts() {
        final String uri = "http://localhost:8080";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }

    public static void getProductbyID(String ID) {

    }

    public static void cre
    public static void updateProduct(Integer id, Product product) {

    }
}