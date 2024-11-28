import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BancoParticipante {

    BancoDeDados db = new BancoDeDados();

    public void adicionarParticipante(Participante participante){

        try(Connection con = db.getConnection()){

            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_participante (nome,email) " +
                    "VALUES (?, ?)");

            ps.setString(1,participante.getNome());
            ps.setString(2,participante.getEmail());

            ps.execute();
            return;

        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Erro ao criar participante");
    }

    public Participante buscarparticipantePorEmail(String email){

        try(Connection con = db.getConnection()){

            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_participante WHERE email = ?");

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String emailParticipante = rs.getString("email");

                return new Participante(id,nome,emailParticipante);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Participante não encontrado");
    }

    public void removerParticipante(int id){

        try(Connection con = db.getConnection()){

            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_participante WHERE id = ?");

            ps.setInt(1,id);

            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
