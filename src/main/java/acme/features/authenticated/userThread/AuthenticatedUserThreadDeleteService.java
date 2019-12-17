
package acme.features.authenticated.userThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.userThread.UserThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedUserThreadDeleteService implements AbstractDeleteService<Authenticated, UserThread> {
	//Internal state --------------------------------------------------------------

	@Autowired
	AuthenticatedUserThreadRepository repository;


	//AbstractUpdateService<Administrator, Announcement> interface -------------------------

	@Override
	public boolean authorise(final Request<UserThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<UserThread> request, final UserThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "user", "messageThread");
	}

	@Override
	public void unbind(final Request<UserThread> request, final UserThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public UserThread findOne(final Request<UserThread> request) {
		assert request != null;

		UserThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<UserThread> request, final UserThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<UserThread> request, final UserThread entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
