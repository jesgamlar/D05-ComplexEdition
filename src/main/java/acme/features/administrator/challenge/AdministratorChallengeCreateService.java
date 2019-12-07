
package acme.features.administrator.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	//Internal state --------------------------------------------------

	@Autowired
	AdministratorChallengeRepository repository;


	//AbstractListService<Authenticated, Announcement> interface ------

	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goalGold", "goalSilver", "goalBronze", "rewardGold", "rewardSilver", "rewardBronze");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;

		result = new Challenge();

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			errors.state(request, entity.getDeadline() != null, "deadline", "administrator.challenge.form.error.deadlineIncorrect");
		}

		if (!errors.hasErrors("rewardGold")) {
			errors.state(request, entity.getRewardGold() != null, "rewardGold", "administrator.challenge.form.error.goldIncorrect");
		}

		if (!errors.hasErrors("rewardSilver")) {
			errors.state(request, entity.getRewardSilver() != null, "rewardSilver", "administrator.challenge.form.error.silverIncorrect");
		}

		if (!errors.hasErrors("rewardBronze")) {
			errors.state(request, entity.getRewardBronze() != null, "rewardBronze", "administrator.challenge.form.error.bronzeIncorrect");
		}

		if (!errors.hasErrors("deadline")) {
			Date currentDate = new Date(System.currentTimeMillis());
			errors.state(request, entity.getDeadline().after(currentDate), "deadline", "administrator.challenge.form.error.deadline");
		}

		if (!errors.hasErrors("rewardGold")) {
			errors.state(request, entity.getRewardGold().getCurrency().equals("EUR") || entity.getRewardGold().getCurrency().equals("€"), "rewardGold", "administrator.challenge.form.error.goldEUR");
		}

		if (!errors.hasErrors("rewardSilver")) {
			errors.state(request, entity.getRewardSilver().getCurrency().equals("EUR") || entity.getRewardSilver().getCurrency().equals("€"), "rewardSilver", "administrator.challenge.form.error.silverEUR");
		}

		if (!errors.hasErrors("rewardBronze")) {
			errors.state(request, entity.getRewardBronze().getCurrency().equals("EUR") || entity.getRewardBronze().getCurrency().equals("€"), "rewardBronze", "administrator.challenge.form.error.bronzeEUR");
		}

		if (!errors.hasErrors("rewardGold") && !errors.hasErrors("rewardSilver") && !errors.hasErrors("rewardBronze")) {
			errors.state(request, entity.getRewardGold().getAmount() >= entity.getRewardSilver().getAmount() && entity.getRewardGold().getAmount() >= entity.getRewardBronze().getAmount(), "rewardGold", "administrator.challenge.form.error.goldAmount");
		}

		if (!errors.hasErrors("rewardSilver") && !errors.hasErrors("rewardBronze")) {
			errors.state(request, entity.getRewardSilver().getAmount() >= entity.getRewardBronze().getAmount(), "rewardSilver", "administrator.challenge.form.error.silverAmount");
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
