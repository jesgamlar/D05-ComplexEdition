
package acme.features.provider.requestEntity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestEntity.RequestEntity;
import acme.entities.roles.Provider;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ProviderRequestEntityListService implements AbstractListService<Provider, RequestEntity> {

	//Internal state --------------------------------------------------

	@Autowired
	ProviderRequestEntityRepository repository;


	//AbstractListService<Authenticated, Request> interface ------

	@Override
	public boolean authorise(final Request<RequestEntity> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<RequestEntity> request, final RequestEntity entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title");

	}

	@Override
	public Collection<RequestEntity> findMany(final Request<RequestEntity> request) {
		assert request != null;

		Collection<RequestEntity> result;

		result = this.repository.findManyAll();

		return result;
	}

}
