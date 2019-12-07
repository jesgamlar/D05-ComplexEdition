
package acme.features.authenticated.requestEntity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestEntity.RequestEntity;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedRequestEntityListService implements AbstractListService<Authenticated, RequestEntity> {

	//Internal state --------------------------------------------------

	@Autowired
	AuthenticatedRequestEntityRepository repository;


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

		request.unbind(entity, model, "moment", "title", "deadline");

	}

	@Override
	public Collection<RequestEntity> findMany(final Request<RequestEntity> request) {
		assert request != null;

		Collection<RequestEntity> result;

		result = this.repository.findManyAll();

		return result;
	}

}
