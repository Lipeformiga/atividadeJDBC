import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BancoInscricao {

    BancoDeDados db = new BancoDeDados();

    public void inscreverParticipante (int eventoId, int participanteId){

        try (Connection con = db.getConnection()){

            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_inscricao (id_evento, id_participante) " +
                    "VALUES (?, ?)");

            ps.setInt(1,eventoId);
            ps.setInt(2, participanteId);

            ps.execute();
            return;

        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Erro ao inscrever participante");
    }

    public void removerInscricao(int id){

        try (Connection con = db.getConnection()){

            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_inscricao WHERE id = ?");

            ps.setInt(1,id);

            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
