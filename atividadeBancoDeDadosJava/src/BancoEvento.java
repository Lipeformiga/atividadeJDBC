import java.sql.*;

public class BancoEvento {
    BancoDeDados db = new BancoDeDados();
    public void adicionarEvento(Evento evento){
        try(Connection con = db.getConnection()){

            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_evento ( nome, local, data, descricao)" +
                    " VALUES (?, ?, ?, ?)");
            
            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getLocal());
            ps.setString(3, evento.getData());
            ps.setString(4, evento.getDescricao());

            ps.execute();
            return;

        } catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Erro ao criar evento");
    }

    public Evento buscarEventoPorNome(String nome){

        try(Connection con = db.getConnection()){

            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_evento WHERE nome = ?");
            ps.setString(1,nome);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String nomeEvento = rs.getString("nome");
                String local = rs.getString("local");
                String data = rs.getString("data");
                String descricao = rs.getString("descricao");

                return new Evento(id,nomeEvento,local,data,descricao);
          }

        } catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Evento não encontrado");
    }

    public void removerEvento(int id){
        try(Connection con = db.getConnection()){

            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_evento WHERE id = ?");

            ps.setInt(1,id);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
