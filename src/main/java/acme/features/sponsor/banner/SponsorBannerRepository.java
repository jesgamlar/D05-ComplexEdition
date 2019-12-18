
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorBannerRepository extends AbstractRepository {

	@Query("select b from CommercialBanner b where b.id = ?1")
	CommercialBanner findOneBannerById(int id);

	@Query("select b from CommercialBanner b where b.sponsor.id = ?1")
	Collection<CommercialBanner> findManyBySponsorId(int sponsorId);

	@Query("select ua from Sponsor ua where ua.id = ?1")
	Sponsor findOneSponsorById(int id);

	@Query("select c from CommercialBanner c where c.id = ?1")
	CommercialBanner findOneCommercialBannerById(int id);

}
