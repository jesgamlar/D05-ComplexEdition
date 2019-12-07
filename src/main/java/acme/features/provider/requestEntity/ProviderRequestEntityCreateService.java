
package acme.features.provider.requestEntity;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestEntity.RequestEntity;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service

public class ProviderRequestEntityCreateService implements AbstractCreateService<Provider, RequestEntity> {

	@Autowired

	ProviderRequestEntityRepository repository;


	@Override

	public boolean authorise(final Request<RequestEntity> request) {

		assert request != null;

		return true;

	}

	@Override

	public void bind(final Request<RequestEntity> request, final RequestEntity entity, final Errors errors) {

		assert request != null;

		assert entity != null;

		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override

	public void unbind(final Request<RequestEntity> request, final RequestEntity entity, final Model model) {

		assert request != null;

		assert entity != null;

		assert model != null;

		request.unbind(entity, model, "title", "deadline", "text", "rewardMin", "rewardMax", "ticker");

	}

	@Override

	public RequestEntity instantiate(final Request<RequestEntity> request) {

		RequestEntity result;

		result = new RequestEntity();

		return result;

	}

	@Override

	public void validate(final Request<RequestEntity> request, final RequestEntity entity, final Errors errors) {

		assert request != null;

		assert entity != null;

		assert errors != null;

		if (!errors.hasErrors("rewardMax")) {
			errors.state(request, entity.getRewardMax() != null, "rewardMax", "provider.requestEntity.form.error.rewardMaxIncorrect");
		}

		if (!errors.hasErrors("rewardMin")) {
			errors.state(request, entity.getRewardMin() != null, "rewardMin", "provider.requestEntity.form.error.rewardMinIncorrect");
		}

		if (!errors.hasErrors("rewardMax")) {
			errors.state(request, entity.getRewardMax().getCurrency().equals("EUR") || entity.getRewardMax().getCurrency().equals("€"), "rewardMax", "provider.requestEntity.form.error.rewardMaxEUR");
		}

		if (!errors.hasErrors("rewardMin")) {
			errors.state(request, entity.getRewardMin().getCurrency().equals("EUR") || entity.getRewardMin().getCurrency().equals("€"), "rewardMin", "provider.requestEntity.form.error.rewardMinEUR");
		}

		if (!errors.hasErrors("rewardMax") && !errors.hasErrors("rewardMin")) {
			errors.state(request, entity.getRewardMax().getAmount() >= entity.getRewardMin().getAmount(), "rewardMax", "provider.requestEntity.form.error.rewardMaxAmount");
		}

		if (!errors.hasErrors("deadline")) {
			errors.state(request, entity.getDeadline() != null, "deadline", "provider.requestEntity.form.error.deadline");
		}

		if (!errors.hasErrors("deadline")) {
			Date currentDate = new Date(System.currentTimeMillis());
			errors.state(request, entity.getDeadline().after(currentDate), "deadline", "provider.requestEntity.form.error.deadlineIncorrect");
		}

		if (!errors.hasErrors("ticker")) {
			Collection<String> tickers = this.repository.findAllTickers();
			errors.state(request, !tickers.contains(entity.getTicker()), "ticker", "provider.requestEntity.form.error.tickerIncorrect");
		}

		if (!errors.hasErrors("accept")) {
			errors.state(request, request.getModel().getAttribute("accept").equals("true"), "accept", "provider.requestEntity.form.error.accept");
		}

	}

	@Override

	public void create(final Request<RequestEntity> request, final RequestEntity entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
