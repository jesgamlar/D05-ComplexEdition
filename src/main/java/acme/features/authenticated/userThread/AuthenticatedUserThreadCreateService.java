
package acme.features.authenticated.userThread;

import java.util.Collection;

import javax.validation.Valid;

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
public class AuthenticatedUserThreadCreateService implements AbstractCreateService<Authenticated, UserThread> {

	@Autowired
	AuthenticatedUserThreadRepository repository;


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
	public UserThread instantiate(final Request<UserThread> request) {
		UserThread result;
		result = new UserThread();

		String messageThreadId = request.getServletRequest().getQueryString().split("mtid=")[1];
		int intMessageThreadId = Integer.parseInt(messageThreadId);
		MessageThread mt = this.repository.findMessageThreadById(intMessageThreadId);
		result.setMessageThread(mt);

		return result;
	}

	@Override
	public void validate(final Request<UserThread> request, final UserThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public void create(final Request<UserThread> request, final UserThread entity) {
		assert request != null;
		assert entity != null;

		MessageThread messageThread = entity.getMessageThread();

		Authenticated user = this.repository.findAuthenticatedById(request.getModel().getInteger("userId"));

		entity.setMessageThread(messageThread);
		entity.setUser(user);

		Collection<@Valid UserThread> listUsers = messageThread.getUsers();
		listUsers.add(entity);
		messageThread.setUsers(listUsers);

		//-----------------------------------------------------------------

		this.repository.save(entity);

		this.repository.save(messageThread);

	}

}
