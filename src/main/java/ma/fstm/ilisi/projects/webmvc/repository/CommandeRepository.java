package ma.fstm.ilisi.projects.webmvc.repository;

import ma.fstm.ilisi.projects.webmvc.bo.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}