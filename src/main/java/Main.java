import org.json.JSONObject;

public class Main {


    public static void main(String[] args) {

        // Instanciando a classe Model
        Cliente cliente = new Cliente();

        // Obtendo a resposta da requisição
        // Em um Objeto do tipo JSONObject
        JSONObject clientes = cliente.get("Felipe");

        // Exibindo o resultado da requisição
        System.out.println(clientes);
    }
}
