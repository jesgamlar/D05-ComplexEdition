
package acme.features.anonymous.martinBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.MartinBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousMartinBulletinCreateService implements AbstractCreateService<Anonymous, MartinBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousMartinBulletinRepository repository;


	@Override
	public boolean authorise(final Request<MartinBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public MartinBulletin instantiate(final Request<MartinBulletin> request) {
		assert request != null;

		MartinBulletin result;
		Date date;

		date = new Date(System.currentTimeMillis() - 1);

		result = new MartinBulletin();
		result.setName("Nombre test");
		result.setSurname("Apellido test");
		result.setAge(20);
		result.setText("Test");
		result.setDate(date);

		return result;
	}

	@Override
	public void unbind(final Request<MartinBulletin> request, final MartinBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "surname", "age", "text");
	}

	@Override
	public void bind(final Request<MartinBulletin> request, final MartinBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<MartinBulletin> request, final MartinBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<MartinBulletin> request, final MartinBulletin entity) {
		assert request != null;
		assert entity != null;

		Date date;

		date = new Date(System.currentTimeMillis() - 1);
		entity.setDate(date);
		this.repository.save(entity);
	}

}
