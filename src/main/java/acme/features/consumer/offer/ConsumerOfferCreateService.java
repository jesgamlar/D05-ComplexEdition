
package acme.features.consumer.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service

public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired

	ConsumerOfferRepository repository;


	@Override

	public boolean authorise(final Request<Offer> request) {

		assert request != null;

		return true;

	}

	@Override

	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {

		assert request != null;

		assert entity != null;

		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override

	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {

		assert request != null;

		assert entity != null;

		assert model != null;

		request.unbind(entity, model, "title", "deadline", "info", "maxMoney", "minMoney");

	}

	@Override

	public Offer instantiate(final Request<Offer> request) {

		Offer result;

		result = new Offer();

		return result;

	}

	@Override

	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {

		assert request != null;

		assert entity != null;

		assert errors != null;

		if (!errors.hasErrors("maxMoney")) {
			errors.state(request, entity.getMaxMoney() != null, "maxMoney", "consumer.offer.form.error.maxMoneyIncorrect");
		}

		if (!errors.hasErrors("minMoney")) {
			errors.state(request, entity.getMinMoney() != null, "minMoney", "consumer.offer.form.error.minMoneyIncorrect");
		}

		if (!errors.hasErrors("maxMoney")) {
			errors.state(request, entity.getMaxMoney().getCurrency().equals("EUR") || entity.getMaxMoney().getCurrency().equals("€"), "maxMoney", "consumer.offer.form.error.maxMoneyEUR");
		}

		if (!errors.hasErrors("minMoney")) {
			errors.state(request, entity.getMinMoney().getCurrency().equals("EUR") || entity.getMinMoney().getCurrency().equals("€"), "minMoney", "consumer.offer.form.error.minMoneyEUR");
		}

		if (!errors.hasErrors("maxMoney") && !errors.hasErrors("minMoney")) {
			errors.state(request, entity.getMaxMoney().getAmount() >= entity.getMinMoney().getAmount(), "maxMoney", "consumer.offer.form.error.maxMoneyAmount");
		}

		if (!errors.hasErrors("deadline")) {
			errors.state(request, entity.getDeadline() != null, "deadline", "consumer.offer.form.error.deadlineIncorrect");
		}

		if (!errors.hasErrors("deadline")) {
			Date currentDate = new Date(System.currentTimeMillis());
			errors.state(request, entity.getDeadline().after(currentDate), "deadline", "consumer.offer.form.error.deadline");
		}

		if (!errors.hasErrors("accept")) {
			errors.state(request, request.getModel().getAttribute("accept").equals("true"), "accept", "consumer.offer.form.error.accept");
		}

	}

	@Override

	public void create(final Request<Offer> request, final Offer entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
