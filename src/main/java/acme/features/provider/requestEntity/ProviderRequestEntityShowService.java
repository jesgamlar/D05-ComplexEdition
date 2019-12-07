
package acme.features.provider.requestEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestEntity.RequestEntity;
import acme.entities.roles.Provider;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class ProviderRequestEntityShowService implements AbstractShowService<Provider, RequestEntity> {

	//Internal state --------------------------------------------------

	@Autowired
	private ProviderRequestEntityRepository repository;


	//AbstractShowService<Administrator, RequestEntity> interface ------

	@Override
	public boolean authorise(final Request<RequestEntity> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<RequestEntity> request, final RequestEntity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "text", "rewardMin", "rewardMax", "ticker");
	}

	@Override
	public RequestEntity findOne(final Request<RequestEntity> request) {
		assert request != null;

		RequestEntity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

}
