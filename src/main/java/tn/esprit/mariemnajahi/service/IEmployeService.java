package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Employe;

import java.util.List;

public interface IEmployeService {
    default Employe create(Employe employe) { return save(employe); }

    Employe save(Employe employe);

    Employe findById(Long id);

    List<Employe> findAll();

    Employe update(Employe employe);
    default Employe update(Long id, Employe employe) {
        employe.setIdEmploye(id);
        return update(employe);
    }

    void deleteById(Long id);

    default Employe addEmploye(Employe employe) {
        return save(employe);
    }

    default Employe retrieveEmploye(Long id) {
        return findById(id);
    }

    default List<Employe> retrieveAllEmployes() {
        return findAll();
    }

    default void deleteEmploye(Long id) {
        deleteById(id);
    }
}
