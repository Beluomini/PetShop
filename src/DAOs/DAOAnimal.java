package DAOs;

import Entidades.Animal;
import java.util.ArrayList;
import java.util.List;

public class DAOAnimal extends DAOGenerico<Animal> {

    private final List<Animal> lista = new ArrayList<>();

    public DAOAnimal() {
        super(Animal.class);
    }

    public int autoIdAnimal() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idAnimal) FROM Animal e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Animal> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Animal e WHERE e.idAnimal LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Animal> listById(int id) {
        return em.createQuery("SELECT e FROM Animal + e WHERE e.nomeAnimal= :id").setParameter("id", id).getResultList();
    }

    public List<Animal> listInOrderNome() {
        return em.createQuery("SELECT e FROM Animal e ORDER BY e.nomeAnimal").getResultList();
    }

    public List<Animal> listInOrderId() {
        return em.createQuery("SELECT e FROM Animal e ORDER BY e.idAnimal").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Animal> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdAnimal() + "-" + lf.get(i).getNomeAnimal()+ "-" + lf.get(i).getDataNascAnimal()+ "-" + lf.get(i).getPorteIdPorte());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOAnimal daoAnimal = new DAOAnimal();
        List<Animal> listaAnimal = daoAnimal.list();
        for (Animal produto : listaAnimal) {
            System.out.println(produto.getIdAnimal() + "-" + produto.getNomeAnimal());
        }
    }
}
