


import com.flpss.ixc.Arguments;
import com.flpss.ixc.IXCModel;
import org.json.JSONObject;


public class Cliente extends IXCModel {



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
