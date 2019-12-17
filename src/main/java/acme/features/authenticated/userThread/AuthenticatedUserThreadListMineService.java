
package acme.features.authenticated.userThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.userThread.UserThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedUserThreadListMineService implements AbstractListService<Authenticated, UserThread> {

	//Internal state --------------------------------------------------

	@Autowired
	AuthenticatedUserThreadRepository repository;


	//AbstractListService<Authenticated, Announcement> interface ------

	@Override
	public boolean authorise(final Request<UserThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<UserThread> request, final UserThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userUsername");

	}

	@Override
	public Collection<UserThread> findMany(final Request<UserThread> request) {
		assert request != null;

		Collection<UserThread> result;
		int id = request.getModel().getInteger("id");

		result = this.repository.findManyAll(id);

		return result;
	}

}
