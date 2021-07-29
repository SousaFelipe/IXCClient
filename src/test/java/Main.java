import com.flpss.ixc.Arguments;
import com.flpss.ixc.IXCModel;
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



class Cliente extends IXCModel {

    public Cliente() {
        super("cliente");
    }

    public JSONObject get(String query) {
        String qtype = query.matches(".*\\d.*") ? "cnpj_cpf" : "razao";

        Arguments args = new Arguments(this)
                .add("qtype", qtype)
                .add("query", query);

        return super.get(args).toJSON();
    }
}
