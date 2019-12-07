
package acme.features.administrator.listData;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.offers.Offer;
import acme.entities.requestEntity.RequestEntity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorListDataRepository extends AbstractRepository {

	@Query("select count(title) from Announcement")
	Integer numbAnn();

	@Query("select count(ceo) from CompanyRecords")
	Integer numbComp();

	@Query("select count(stars) from InvestorRecord")
	Integer numbInv();

	@Query("select off from Offer off where deadline >= NOW()")
	ArrayList<Offer> offer();

	@Query("select req from RequestEntity req where deadline >= NOW()")
	ArrayList<RequestEntity> request();

}
