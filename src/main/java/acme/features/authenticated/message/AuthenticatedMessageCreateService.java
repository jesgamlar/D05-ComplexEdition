
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message.Message;
import acme.entities.messageThread.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service

public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired

	AuthenticatedMessageRepository repository;


	@Override

	public boolean authorise(final Request<Message> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int idUser = principal.getAccountId();
		int idAuthenticated = this.repository.findAuthenticatedIdByUserId(idUser);
		int messageThreadId = request.getModel().getInteger("id");
		boolean res = this.repository.findManyUserIdByMessageThreadId(messageThreadId).contains(idAuthenticated);

		return res;

	}

	@Override

	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "messageThread");

	}

	@Override

	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tags", "body");

	}

	@Override

	public Message instantiate(final Request<Message> request) {
		Message result;
		result = new Message();

		int messageThreadId = request.getModel().getInteger("id");
		MessageThread mt = this.repository.findOneMessageThreadById(messageThreadId);
		result.setMessageThread(mt);

		return result;

	}

	@Override

	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("accept")) {
			errors.state(request, request.getModel().getAttribute("accept").equals("true"), "accept", "authenticated.message.form.error.accept");
		}

		if (!errors.hasErrors("body")) {
			double threshold = this.repository.findThreshold();
			String[] spanishWords = this.repository.findSpanishWords().split(", ");
			String[] englishWords = this.repository.findEnglishWords().split(", ");
			double numberWordsBody = entity.getBody().split("\\s|\\.|\\,").length;

			double spamWords = 0.;
			for (String s : spanishWords) {
				if (entity.getBody().contains(s)) {
					spamWords++;
				}
			}
			for (String s : englishWords) {
				if (entity.getBody().contains(s)) {
					spamWords++;
				}
			}

			errors.state(request, spamWords / numberWordsBody * 100 <= threshold, "body", "authenticated.message.form.error.spamDescription");

		}

	}

	@Override

	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
