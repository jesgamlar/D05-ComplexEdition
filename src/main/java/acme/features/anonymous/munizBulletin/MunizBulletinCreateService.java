
package acme.features.anonymous.munizBulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.MunizBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class MunizBulletinCreateService implements AbstractCreateService<Anonymous, MunizBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	MunizBulletinRepository repository;


	@Override
	public boolean authorise(final Request<MunizBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<MunizBulletin> request, final MunizBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<MunizBulletin> request, final MunizBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "surname", "idCode");
	}

	@Override
	public MunizBulletin instantiate(final Request<MunizBulletin> request) {
		assert request != null;

		MunizBulletin result;

		result = new MunizBulletin();
		result.setName("Isaac");
		result.setSurname("Muñiz");
		result.setIdCode("44242996N");

		return result;
	}

	@Override
	public void validate(final Request<MunizBulletin> request, final MunizBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<MunizBulletin> request, final MunizBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
