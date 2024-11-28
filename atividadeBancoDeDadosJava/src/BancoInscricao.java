import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Inscricao visualizarInscricoesNome(String nome){

        try(Connection con = db.getConnection()){

            PreparedStatement ps = con.prepareStatement(
                    "select tb_inscricao.id, tb_participante.nome, tb_participante.email from tb_inscricao " +
                            "inner join tb_participante on tb_inscricao.id_participante = tb_participante.id " +
                            "WHERE tb_participante.nome = ?");

            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int id = rs.getInt("id");
                Participante participante = new Participante(rs.getInt("id"),
                        rs.getString("nome"), rs.getString("email"));

                return new Inscricao(id,participante);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Erro ao encontrar inscrições");
    }
}
