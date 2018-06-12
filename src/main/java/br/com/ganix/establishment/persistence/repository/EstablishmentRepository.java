/**
 * 
 */
package br.com.ganix.establishment.persistence.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ganix.establishment.persistence.db.entity.Establishment;

/**
 * @author Marllon
 *
 */
@Repository
@Qualifier(value = "establishmentRepository")
public interface EstablishmentRepository extends CrudRepository<Establishment, Long> {
}
