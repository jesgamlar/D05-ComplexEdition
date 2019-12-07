
package acme.features.administrator.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select c from NonCommercialBanner c where c.id = ?1")
	NonCommercialBanner findOneById(int id);

	@Query("select c from 	NonCommercialBanner c")
	Collection<NonCommercialBanner> findManyAll();

}
