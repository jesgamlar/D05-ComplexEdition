
package acme.features.anonymous.navarroBulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.NavarroBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousNavarroBulletinCreateService implements AbstractCreateService<Anonymous, NavarroBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousNavarroBulletinRepository repository;


	@Override
	public boolean authorise(final Request<NavarroBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public NavarroBulletin instantiate(final Request<NavarroBulletin> request) {
		assert request != null;

		NavarroBulletin result;

		result = new NavarroBulletin();
		//		result.setName("Juan Martin");
		//		result.setAge(15);
		//		result.setHeight(1.68);
		//		result.setWeight(1.51);

		return result;
	}

	@Override
	public void unbind(final Request<NavarroBulletin> request, final NavarroBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "age", "height", "weight");
	}

	@Override
	public void bind(final Request<NavarroBulletin> request, final NavarroBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<NavarroBulletin> request, final NavarroBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<NavarroBulletin> request, final NavarroBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
