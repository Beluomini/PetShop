package DAOs;

import Entidades.Porte;
import java.util.ArrayList;
import java.util.List;

public class DAOPorte extends DAOGenerico<Porte> {

    private final List<Porte> lista = new ArrayList<>();

    public DAOPorte() {
        super(Porte.class);
    }

    public int autoIdPorte() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPorte) FROM Porte e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Porte> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Porte e WHERE e.idPorte LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Porte> listById(int id) {
        return em.createQuery("SELECT e FROM Porte + e WHERE e.nomePorte= :id").setParameter("id", id).getResultList();
    }

    public List<Porte> listInOrderNome() {
        return em.createQuery("SELECT e FROM Porte e ORDER BY e.nomePorte").getResultList();
    }

    public List<Porte> listInOrderId() {
        return em.createQuery("SELECT e FROM Porte e ORDER BY e.idPorte").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Porte> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdPorte() + "-" + lf.get(i).getNomePorte()+ "-" + lf.get(i).getPesoMaxPorte());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPorte daoPorte = new DAOPorte();
        List<Porte> listaPorte = daoPorte.list();
        for (Porte unidadeDeMedida : listaPorte) {
            System.out.println(unidadeDeMedida.getIdPorte() + "-" + unidadeDeMedida.getNomePorte()+ "-" + unidadeDeMedida.getPesoMaxPorte());
        }
    }
}
