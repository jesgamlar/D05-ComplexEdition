
package acme.features.anonymous.gamezBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.GamezBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousGamezBulletinCreateService implements AbstractCreateService<Anonymous, GamezBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousGamezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<GamezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public GamezBulletin instantiate(final Request<GamezBulletin> request) {
		assert request != null;

		GamezBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new GamezBulletin();
		result.setFood("Tu comida");
		result.setKcalories(1000.01);
		result.setDirections("Preparacion de tu comida");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<GamezBulletin> request, final GamezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "kcalories", "directions", "food");
	}

	@Override
	public void bind(final Request<GamezBulletin> request, final GamezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<GamezBulletin> request, final GamezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<GamezBulletin> request, final GamezBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
