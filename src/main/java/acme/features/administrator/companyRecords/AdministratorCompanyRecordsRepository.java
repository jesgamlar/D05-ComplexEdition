
package acme.features.administrator.companyRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companyRecords.CompanyRecords;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCompanyRecordsRepository extends AbstractRepository {

	@Query("select a from CompanyRecords a where a.id = ?1")
	CompanyRecords findOneById(int id);

	@Query("select a from CompanyRecords a")
	Collection<CompanyRecords> findManyAll();

}
