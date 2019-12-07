
package acme.features.authenticated.requestEntity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requestEntity.RequestEntity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestEntityRepository extends AbstractRepository {

	@Query("select r from RequestEntity r where r.id = ?1")
	RequestEntity findOneById(int id);

	@Query("select r from RequestEntity r where r.deadline > NOW()")
	Collection<RequestEntity> findManyAll();

}
