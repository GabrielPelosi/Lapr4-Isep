package eapli.base.materiaprimamanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;

import eapli.base.materiaprimamanagement.domain.MateriaPrima;

import eapli.base.materiaprimamanagement.repositories.MateriaRepository;

public class ListMateriasService {

    private final MateriaRepository materiaRepository = PersistenceContext.repositories().materias();

    public Iterable<MateriaPrima> allMaterias() {
        return this.materiaRepository.findAll();
    }
}
