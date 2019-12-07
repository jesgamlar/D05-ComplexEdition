
package acme.features.authenticated.messageThread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.MessageThread;
import acme.entities.userThread.UserThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "starter", "users");
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
	}

	@Override
	public MessageThread instantiate(final Request<MessageThread> request) {
		MessageThread result;

		result = new MessageThread();

		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void create(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		Authenticated authenticated;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		int id = request.getPrincipal().getAccountId();
		authenticated = this.repository.findAuthenticatedByPrincipal(id);
		entity.setStarter(authenticated);

		//-----------------------------------------------------------------

		List<UserThread> users = new ArrayList<>();
		users = (List<UserThread>) request.getModel().getAttribute("users");

		entity.setUsers(users);

		this.repository.save(entity);
	}

}
